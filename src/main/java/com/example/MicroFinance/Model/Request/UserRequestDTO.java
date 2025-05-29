package com.example.MicroFinance.Model.Request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class UserRequestDTO {

    @NotNull(message = "User name cannot be null")
    @Size(min = 2, max = 50, message = "User name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

    // İç içe sınıfı SİL
    // @Data public class UserrRequestDTO {...} kısmını kaldırdık
}
