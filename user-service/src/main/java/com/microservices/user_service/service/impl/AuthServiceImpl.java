
package com.microservices.user_service.service.impl;

import com.microservices.user_service.model.User;
import com.microservices.user_service.repository.UserRepository;
import com.microservices.user_service.security.utils.JwtUtils;
import com.microservices.user_service.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwtCookie}")
    private String jwtCookie;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> verify(HttpServletRequest httpServletRequest) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, jwtCookie);
        if(cookie == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String jwt = cookie.getValue();
        String username = jwtUtils.getUsernameFromJwt(jwt);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        return ResponseEntity.ok()
                .header("X-USER-ID", user.getUserId().toString())
                .body(null);
    }
}
