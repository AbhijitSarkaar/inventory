package com.microservices.user_service.service.impl;

import com.microservices.user_service.enums.AppRole;
import com.microservices.user_service.model.Role;
import com.microservices.user_service.model.User;
import com.microservices.user_service.payload.LoginRequestDTO;
import com.microservices.user_service.payload.UserDTO;
import com.microservices.user_service.payload.UserRequestDTO;
import com.microservices.user_service.repository.RoleRepository;
import com.microservices.user_service.repository.UserRepository;
import com.microservices.user_service.security.service.impl.UserDetailsImpl;
import com.microservices.user_service.security.utils.JwtUtils;
import com.microservices.user_service.service.UserService;
import com.microservices.user_service.util.DTOBuilder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DTOBuilder dtoBuilder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Value("${jwtCookie}")
    private String jwtCookie;

    @Override
    public UserDTO register(UserRequestDTO userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(
                passwordEncoder.encode(userRequestDto.getPassword())
        );

        List<String> roles = userRequestDto.getRoles();
        List<Role> roleList = new ArrayList<>();
        if(roles == null) {
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            roleList.add(userRole);
        } else {
            roles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Role not found"));
                        roleList.add(adminRole);
                        break;
                    case "user":
                        Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Role not found"));
                        roleList.add(userRole);
                        break;
                }
            });
        }

        user.setRoles(roleList);
        user = userRepository.save(user);

        return dtoBuilder.userDtoBuilder(user);
    }

    @Override
    public ResponseCookie login(LoginRequestDTO loginRequestDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()
                    )
            );
        } catch(RuntimeException e) {
            throw new RuntimeException("Bad credentials");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return jwtUtils.getJwtCookie(userDetails.getUsername());
    }

    @Override
    public ResponseCookie logout() {
        return jwtUtils.cleanCookie();
    }

    @Override
    public ResponseEntity<?> details(HttpServletRequest httpServletRequest) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, jwtCookie);
        String username = jwtUtils.getUsernameFromJwt(cookie.getValue());
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String roles = user.getRoles().stream().map(role -> role.getRoleName().name())
                .collect(Collectors.joining(":"));

        return ResponseEntity.ok()
                .header("X-USER-ID", user.getUserId().toString())
                .header("X-USER-ROLE", roles)
                .body(null);
    }
}
