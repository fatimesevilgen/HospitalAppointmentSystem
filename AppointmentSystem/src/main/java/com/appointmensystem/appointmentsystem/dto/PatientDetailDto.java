package com.appointmensystem.appointmentsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientDetailDto {
    private String firstName ;
    private String lastName ;
    private String userName ;
    private String email;
    private int age ;
}
