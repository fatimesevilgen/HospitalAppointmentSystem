package com.appointmensystem.appointmentsystem.controllers;

import com.appointmensystem.appointmentsystem.dto.AppointmentCreateDto;
import com.appointmensystem.appointmentsystem.dto.AppointmentDetailDto;
import com.appointmensystem.appointmentsystem.dto.AppointmentUpdateStatus;
import com.appointmensystem.appointmentsystem.entities.Appointment;
import com.appointmensystem.appointmentsystem.enums.Errors;
import com.appointmensystem.appointmentsystem.services.AppointmentService;
import com.appointmensystem.appointmentsystem.utilities.DataResult;
import com.appointmensystem.appointmentsystem.utilities.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;


    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody AppointmentCreateDto args){
        try {
            return new ResponseEntity<>(appointmentService.add(args), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new Result(false, e.getMessage()), HttpStatus.OK);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<AppointmentDetailDto>>> getAll(){
        try {
            return new ResponseEntity<>(appointmentService.getAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new DataResult(null, false, Errors.UnhandledExceptionOccurred.getDescription()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getById")
    public ResponseEntity<DataResult<Appointment>> getById(@RequestParam Long id){
        try {
            return new ResponseEntity<>(appointmentService.getById(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new DataResult<>(null, false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Result> update( @RequestBody Appointment args){
        try {
            return new ResponseEntity<>(appointmentService.update(args), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new Result(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam Long id){
        try {
            return new ResponseEntity<>(appointmentService.delete(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new Result(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/listAppointmentsByDoctor")
    public ResponseEntity<DataResult<List<Appointment>>> listAppointmentsByDoctor(@RequestParam Long id){
        try {
            return new ResponseEntity<>(appointmentService.listAppointmentsByDoctor(id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new DataResult<>(null, false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/listAppointmentsByPatient")
    public ResponseEntity<DataResult<List<Appointment>>> listAppointmentsByPatient(@RequestParam Long id){
        try {
            return new ResponseEntity<>(appointmentService.listAppointmentsByPatient(id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new DataResult<>(null, false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/updateStatus")
    public ResponseEntity<Result> updateStatus(@Valid @RequestBody AppointmentUpdateStatus args){
        try{
            return new ResponseEntity<>(appointmentService.updateStatus(args), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new Result(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
