package com.appointmensystem.appointmentsystem.repository;

import com.appointmensystem.appointmentsystem.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AppointmenRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> getAllAppointmentsByDoctorId(Long id);
    List<Appointment> getAllAppointmentsByPatientId(Long id);
}
