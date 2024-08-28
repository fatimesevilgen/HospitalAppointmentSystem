package com.appointmensystem.appointmentsystem.advice;

import com.appointmensystem.appointmentsystem.enums.AppointmentStatus;
import com.appointmensystem.appointmentsystem.utilities.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result> handleEnumValidationException(HttpMessageNotReadableException ex) {
        String allowedValues = Stream.of(AppointmentStatus.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        String errorMessage = "Please use one of the following: " + allowedValues;
        return new ResponseEntity<>(new Result(false, errorMessage), HttpStatus.BAD_REQUEST);
    }
}
