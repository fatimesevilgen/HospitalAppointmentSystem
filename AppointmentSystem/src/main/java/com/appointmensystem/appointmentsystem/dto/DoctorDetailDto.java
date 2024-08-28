package com.appointmensystem.appointmentsystem.dto;

import com.appointmensystem.appointmentsystem.entities.Policlinic;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DoctorDetailDto {
    private Policlinic policlinic;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
}
