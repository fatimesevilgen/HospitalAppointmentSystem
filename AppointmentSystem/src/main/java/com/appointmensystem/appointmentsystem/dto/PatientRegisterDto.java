package com.appointmensystem.appointmentsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientRegisterDto {
    @NotEmpty(message = "First name cannot be empty")
    private String firstName ;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName ;

    @NotEmpty(message = "Username cannot be empty")
    private String userName ;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Your age cannot be smaller than 18")
    private int age ;

    @NotEmpty(message = "Password cannot be empty")
    private String password ;
}