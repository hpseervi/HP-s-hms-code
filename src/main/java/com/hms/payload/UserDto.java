package com.hms.payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
