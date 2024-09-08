package com.mmddvg.taskmanager.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record NewUser(
        @NotEmpty
        String name,
        @Email
        String email,
        @NotEmpty
        @Min(value = 8)
        String password
) {

}
