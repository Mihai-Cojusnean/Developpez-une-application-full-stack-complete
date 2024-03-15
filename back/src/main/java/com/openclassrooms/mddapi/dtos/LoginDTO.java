package com.openclassrooms.mddapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDTO {
    @Email
    @NotEmpty
    @Size(max = 60)
    String email;

    @NotEmpty
    @Size(max = 120)
    String password;
}
