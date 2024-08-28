package com.appointmensystem.appointmentsystem.controllers;

import com.appointmensystem.appointmentsystem.dto.DoctorDetailDto;
import com.appointmensystem.appointmentsystem.dto.DoctorLoginDto;
import com.appointmensystem.appointmentsystem.dto.DoctorRegisterDto;
import com.appointmensystem.appointmentsystem.dto.DoctorUpdateDto;
import com.appointmensystem.appointmentsystem.entities.Doctor;
import com.appointmensystem.appointmentsystem.enums.Errors;
import com.appointmensystem.appointmentsystem.services.DoctorService;
import com.appointmensystem.appointmentsystem.utilities.DataResult;
import com.appointmensystem.appointmentsystem.utilities.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    public final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<Result> doctorRegister(@Valid @RequestBody DoctorRegisterDto args, BindingResult result) {
        try {
            return new ResponseEntity<>(doctorService.doctorRegister(args), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new Result(false, Errors.UnhandledExceptionOccurred.getDescription()), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<DataResult<DoctorDetailDto>> doctorLogin(@Valid @RequestBody DoctorLoginDto args) {
        try {
            return new ResponseEntity<>(doctorService.doctorLogin(args), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new DataResult(null, false, Errors.UserNotFoundError.getDescription()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<DoctorDetailDto>>> getAll(){
        try {
            DataResult<List<DoctorDetailDto>> responses = doctorService.getAll();
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new DataResult(null, false, Errors.UnhandledExceptionOccurred.getDescription()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody DoctorUpdateDto args){
        try {
            Result result = doctorService.update(args);
            return new ResponseEntity<>(result,HttpStatus.OK );
        }
        catch (Exception e) {
            return new ResponseEntity<>(new Result(false), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Result> deleteById(@RequestParam Long id) {
        Result isDelete = doctorService.deleteById(id);

        if (!isDelete.Success) {
            return new ResponseEntity<>(isDelete, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(isDelete, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataResult<DoctorDetailDto>> getById(@RequestParam Long id) {
        try {
            DataResult<Doctor> result = doctorService.getById(id);
            if (!result.Success) {
                return new ResponseEntity<>(new DataResult(null, false, result.Message), HttpStatus.NOT_FOUND);
            }

            DoctorDetailDto dto = modelMapper.map(result.Data, DoctorDetailDto.class);
            return new ResponseEntity<>(new DataResult<>(dto, true), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new DataResult<>(null, false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}

