package com.appointmensystem.appointmentsystem.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
public class AppointmentCreateDto {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentDate;
}
