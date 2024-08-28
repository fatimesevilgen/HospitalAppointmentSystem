package com.appointmensystem.appointmentsystem.services;

import com.appointmensystem.appointmentsystem.dto.DoctorDetailDto;
import com.appointmensystem.appointmentsystem.dto.DoctorLoginDto;
import com.appointmensystem.appointmentsystem.dto.DoctorRegisterDto;
import com.appointmensystem.appointmentsystem.dto.DoctorUpdateDto;
import com.appointmensystem.appointmentsystem.entities.Doctor;
import com.appointmensystem.appointmentsystem.entities.Policlinic;
import com.appointmensystem.appointmentsystem.enums.Errors;
import com.appointmensystem.appointmentsystem.repository.DoctorRepository;
import com.appointmensystem.appointmentsystem.repository.PoliclinicRepository;
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
public class DoctorService {
    public final DoctorRepository doctorRepository;
    public final ModelMapper modelMapper;
    public final PasswordEncoder passwordEncoder;
    private final PoliclinicRepository policlinicRepository;

    //REGISTER
    public Result doctorRegister(DoctorRegisterDto args) {
        if (!EmailValidator.isValidEmail(args.getEmail())) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        if (doctorRepository.findByEmail(args.getEmail()).isPresent()) {
            return new Result(false, Errors.EmailAlreadyInUseError.getDescription());
        }

        String hashedPassword = passwordEncoder.encode(args.getPassword());
        Policlinic policlinic = policlinicRepository.getById(args.getPoliclinicId());

        Doctor doctor = modelMapper.map(args, Doctor.class);
        doctor.setHashedPassword(hashedPassword);
        doctor.setPoliclinic(policlinic);

        doctorRepository.save(doctor);

        return new Result(true, "Doctor registered successfully");
    }


    //LOGIN
    public DataResult<DoctorDetailDto> doctorLogin(DoctorLoginDto args) {

        Doctor doctor = doctorRepository.findByEmail(args.getEmail()).orElse(null);
        boolean ok = passwordEncoder.matches(args.getPassword(), doctor.getHashedPassword());

        if (!ok) {
            return new DataResult<>(new DoctorDetailDto(), false, Errors.InvalidPasswordError.getDescription());
        }

        DoctorDetailDto doctorDetailDto = modelMapper.map(doctor, DoctorDetailDto.class);

        return new DataResult<DoctorDetailDto>(doctorDetailDto, true);
    }


    //UPDATE
    public Result update(DoctorUpdateDto args) {
        Doctor doctor = doctorRepository.findById(args.getId()).orElse(null);

        if (doctor == null) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        doctor.setPoliclinic(policlinicRepository.getById(args.getPoliclinicId()));
        modelMapper.map(args, doctor);

        doctorRepository.save(doctor);
        return new Result(true);
    }


    //DELETE
    public Result deleteById(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return new Result(true, "Doctor deleted successfully.");
        } else {
            return new Result(false, Errors.DoctorNotFoundError.getDescription());
        }
    }



    //LIST DOCTOR
    public DataResult<List<DoctorDetailDto>> getAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDetailDto> responses = doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDetailDto.class))
                .collect(Collectors.toList());
        return new DataResult<>(responses, true);
    }


    //GET BY ID
    public DataResult<Doctor> getById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor == null) {
            return new DataResult<>(null, false, Errors.DoctorNotFoundError.getDescription());
        }
        return new DataResult<>(doctor, true);
    }
}