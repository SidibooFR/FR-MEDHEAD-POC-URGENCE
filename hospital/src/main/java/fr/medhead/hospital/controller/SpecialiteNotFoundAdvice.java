package fr.medhead.hospital.controller;

import fr.medhead.hospital.exceptions.SpecialiteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class SpecialiteNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SpecialiteNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String specialiteNotFoundAdvice(SpecialiteNotFoundException exception) {
        return exception.getMessage();
    }
}
