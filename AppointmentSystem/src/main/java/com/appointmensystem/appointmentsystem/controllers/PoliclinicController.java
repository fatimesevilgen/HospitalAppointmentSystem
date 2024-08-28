package com.appointmensystem.appointmentsystem.controllers;

import com.appointmensystem.appointmentsystem.dto.PoliclinicCreateDto;
import com.appointmensystem.appointmentsystem.services.PoliclinicService;
import com.appointmensystem.appointmentsystem.utilities.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policlinic")
public class PoliclinicController {

    private final PoliclinicService policlinicService;

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody PoliclinicCreateDto args) {
        try {
            return new ResponseEntity<>(policlinicService.add(args), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new Result(false, e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
