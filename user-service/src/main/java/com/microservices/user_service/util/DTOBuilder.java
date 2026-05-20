package com.microservices.user_service.util;

import com.microservices.user_service.model.User;
import com.microservices.user_service.payload.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOBuilder {
    public UserDTO userDtoBuilder(User user) {
        UserDTO userDto = new UserDTO();

        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> {
            roles.add(role.getRoleName().name());
        });
        userDto.setRoles(roles);

        return userDto;
    }
}
