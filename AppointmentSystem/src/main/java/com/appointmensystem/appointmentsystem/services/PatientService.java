package com.appointmensystem.appointmentsystem.services;

import com.appointmensystem.appointmentsystem.dto.*;
import com.appointmensystem.appointmentsystem.entities.Patient;
import com.appointmensystem.appointmentsystem.enums.Errors;
import com.appointmensystem.appointmentsystem.repository.PatientRepository;
import com.appointmensystem.appointmentsystem.utilities.DataResult;
import com.appointmensystem.appointmentsystem.utilities.EmailValidator;
import com.appointmensystem.appointmentsystem.utilities.Result;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    //REGISTER
    public Result register(PatientRegisterDto args) {

        if (patientRepository.findByEmail(args.getEmail()).isPresent()) {
            return new Result(false, Errors.EmailAlreadyInUseError.getDescription());
        }

        if (!EmailValidator.isValidEmail(args.getEmail())) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        String hashedpassword = passwordEncoder.encode(args.getPassword());

        Patient patient = modelMapper.map(args, Patient.class);
        patient.setHashedPassword(hashedpassword);
        patientRepository.save(patient);

        return new Result(true, "Patient registered successfully");
    }


    //LOGIN
    public DataResult<PatientDetailDto> login(PatientLoginDto args) {
        Patient patient = patientRepository.findByEmail(args.getEmail()).orElse(null);

        if (patient == null) {
            return new DataResult<>(null, false, "Patient not found");
        }
        if (!passwordEncoder.matches(args.getPassword(), patient.getHashedPassword())) {
            return new DataResult<>(null, false, "Wrong password");
        }
        PatientDetailDto patientDetailDto = modelMapper.map(patient, PatientDetailDto.class);
        return new DataResult<>(patientDetailDto, true, "Patient logged in successfully");
    }


    //GETALL
    public DataResult<List<PatientDetailDto>> getAll(){

        List<Patient> patients = patientRepository.findAll();
        List<PatientDetailDto> patientDetailDtos = patients.stream()
                .map(patient -> modelMapper.map(patient,PatientDetailDto.class))
                .collect(Collectors.toList());
        return new DataResult<>(patientDetailDtos, true);
    }


    //UPDATE
    public Result update(PatientUpdateDto args) {
        Patient patient = patientRepository.findById(args.getId()).orElse(null);
        if (patient == null) {
            return new Result(false, "Patient not found");
        }
        modelMapper.map(args, patient);
        patientRepository.save(patient);
        return new Result(true, "Patient updated successfully");
    }


    //DELETE
    public Result deleteById(Long id) {
        if(patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return new Result(true, "Patient deleted successfully");
        }
        return new Result(false, "Patient not found");
    }


    //GETBYID
    public DataResult<Patient> getById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            return new DataResult<>(new Patient(), false, Errors.PatientNotFound.getDescription());
        }
        return new DataResult<>(patient, true);
    }
}