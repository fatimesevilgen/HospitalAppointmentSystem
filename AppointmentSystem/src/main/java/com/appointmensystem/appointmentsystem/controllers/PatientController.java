package com.appointmensystem.appointmentsystem.controllers;

import com.appointmensystem.appointmentsystem.dto.PatientDetailDto;
import com.appointmensystem.appointmentsystem.dto.PatientLoginDto;
import com.appointmensystem.appointmentsystem.dto.PatientRegisterDto;
import com.appointmensystem.appointmentsystem.dto.PatientUpdateDto;
import com.appointmensystem.appointmentsystem.services.PatientService;
import com.appointmensystem.appointmentsystem.utilities.DataResult;
import com.appointmensystem.appointmentsystem.utilities.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<Result> register(@Valid @RequestBody PatientRegisterDto args) {
        try {
            Result result = patientService.register(args);
            if(!result.Success){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new Result(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<DataResult<PatientDetailDto>> login(@Valid @RequestBody PatientLoginDto args){
        try {
            return new ResponseEntity<>(patientService.login(args), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new DataResult<>(null, false, e.getMessage()),HttpStatus.OK);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<PatientDetailDto>>> getAll() {
        try {
            DataResult<List<PatientDetailDto>> patients = patientService.getAll();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
        catch (Exception e) {

            return new ResponseEntity<>(new DataResult<>(null, false, e.getMessage()),HttpStatus.OK);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody PatientUpdateDto args){
        try {
            return new ResponseEntity<>(patientService.update(args), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new DataResult<>(null, false, e.getMessage()),HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> deleteById(@RequestParam Long id) {
        Result isDelete = patientService.deleteById(id);

        if (!isDelete.Success) {
            return new ResponseEntity<>(isDelete, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(isDelete, HttpStatus.OK);
    }
}
