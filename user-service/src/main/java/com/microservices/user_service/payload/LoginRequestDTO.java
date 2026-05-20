package com.microservices.user_service.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
