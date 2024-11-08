package com.appointmensystem.appointmentsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class DoctorRegisterDto {

    @NotEmpty(message = "Password cannot be null")
    private String password ;

    @NotNull(message = "ClinicId cannot be null")
    private Long policlinicId;

    @NotEmpty(message = "Firstname cannot be not null")
    private String firstName;

    @NotEmpty(message = "Lastname cannot be not null")
    private String lastName;

    @NotEmpty(message = "Username cannot be not null")
    private String userName;

    @NotEmpty(message = "Email cannot be not null")
    private String email;
}
