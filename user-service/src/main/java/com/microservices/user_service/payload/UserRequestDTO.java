
package com.microservices.user_service.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotNull
    @Size(min = 2, message = "username needs to be of at least 2 characters")
    private String username;

    @NotNull
    @Size(min = 3, message = "password needs to be of at least 3 characters")
    private String password;

    @NotNull
    @Size(min = 5, message = "email needs to be of at least 5 characters")
    private String email;

    private List<String> roles;

}
