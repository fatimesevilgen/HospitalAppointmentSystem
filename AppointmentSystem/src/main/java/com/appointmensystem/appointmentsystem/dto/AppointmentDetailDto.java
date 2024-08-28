package com.appointmensystem.appointmentsystem.dto;

import com.appointmensystem.appointmentsystem.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
public class AppointmentDetailDto {
    private DoctorDetailDto doctor;
    private PatientDetailDto patient;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
}
