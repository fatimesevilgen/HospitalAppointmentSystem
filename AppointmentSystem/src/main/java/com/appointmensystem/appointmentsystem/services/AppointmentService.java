package com.appointmensystem.appointmentsystem.services;

import com.appointmensystem.appointmentsystem.dto.AppointmentCreateDto;
import com.appointmensystem.appointmentsystem.dto.AppointmentDetailDto;
import com.appointmensystem.appointmentsystem.dto.AppointmentUpdateStatus;
import com.appointmensystem.appointmentsystem.entities.Appointment;
import com.appointmensystem.appointmentsystem.entities.Doctor;
import com.appointmensystem.appointmentsystem.entities.Patient;
import com.appointmensystem.appointmentsystem.repository.AppointmenRepository;
import com.appointmensystem.appointmentsystem.utilities.DataResult;
import com.appointmensystem.appointmentsystem.utilities.Result;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final DoctorService doctorService;
    private final AppointmenRepository appointmenRepository;
    private final ModelMapper modelMapper;
    private final PatientService patientService;


    //ADD
    public Result add(AppointmentCreateDto args){

        DataResult<Doctor> doctor = doctorService.getById(args.getDoctorId());
        if(!doctor.Success){
            return new Result(false, doctor.Message);
        }

        DataResult<Patient> patient = patientService.getById(args.getPatientId());
        if(!patient.Success){
            return new Result(false, patient.Message);
        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(args.getAppointmentDate());
        appointment.setDoctor(doctor.Data);
        appointment.setPatient(patient.Data);

        appointmenRepository.save(appointment);
        return new Result(true, "Appointment added successfully");
    }


    //GETBYID
    public DataResult<Appointment> getById(Long id) {
        if(appointmenRepository.existsById(id)){
            return new DataResult<>(appointmenRepository.findById(id).get(), true);
        }
        return new DataResult<>(null, false);
    }


    //UPDATE
    public Result update(Appointment args) {
        if(appointmenRepository.existsById(args.getId())) {
            Appointment appointment = appointmenRepository.findById(args.getId()).get();
            modelMapper.map(args, appointment);
            appointmenRepository.save(appointment);
            return new Result(true, "Appointment updated successfully");
        }

        return new Result(false, "Appointment not found");
    }


    //DELETE
    public Result delete(Long id) {
        if(appointmenRepository.existsById(id)) {
            appointmenRepository.deleteById(id);
            return new Result(true, "Appointment deleted successfully");
        }
        return new Result(false, "Appointment not found");
    }


    //List Appointments by Doctor
    public DataResult<List<Appointment>> listAppointmentsByDoctor(Long id) {
        if(appointmenRepository.getAllAppointmentsByDoctorId(id).isEmpty()){
            return new DataResult<>(null, false);
        }
        List<Appointment> appointments = appointmenRepository.getAllAppointmentsByDoctorId(id);
        return new DataResult<>(appointments, true);
    }


    //List Appointments by Patient
    public DataResult<List<Appointment>> listAppointmentsByPatient(Long id) {
        if(appointmenRepository.getAllAppointmentsByPatientId(id).isEmpty()){
            return new DataResult<>(null, false);
        }
        List<Appointment> appointmentList = appointmenRepository.getAllAppointmentsByPatientId(id);
        return new DataResult<>(appointmentList, true);
    }


    //GETALL
    public DataResult<List<AppointmentDetailDto>> getAll(){
        List<Appointment> appointments = appointmenRepository.findAll();
        List<AppointmentDetailDto> dto  = appointments.stream().map(m -> modelMapper.map(m,AppointmentDetailDto.class)).toList();

        return new DataResult<>(dto, true);
    }

    //UPDATE STATUS
    public Result updateStatus(AppointmentUpdateStatus args) {
        Appointment appointment = appointmenRepository.findById(args.getId()).orElse(null);
        appointment.setStatus(args.getStatus());
        appointmenRepository.save(appointment);
        return new Result(true, "Appointment updated successfully");
    }
}