package com.appointmensystem.appointmentsystem.dto;

import com.appointmensystem.appointmentsystem.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentUpdateStatus {
    private Long id;
    private AppointmentStatus status;
}
