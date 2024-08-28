package com.appointmensystem.appointmentsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientUpdateDto {
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotEmpty(message = "Firstname cannot be not null")
    private String firstName ;

    @NotEmpty(message = "Lastname cannot be not null")
    private String lastName ;

    @NotEmpty(message = "Username cannot be not null")
    private String userName ;

    @NotEmpty(message = "Email cannot be not null")
    private String email;

    @NotNull(message = "Age cannot be not null")
    @Min(value = 18, message = "Your age cannot be smaller than 18")
    private int age ;

    @NotEmpty(message = "Password cannot be not null")
    private String password ;
}
