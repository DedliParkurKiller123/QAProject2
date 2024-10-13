package org.sec.jwtsecurityproject.config.excep;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        Map<String, String> errorMessages = new HashMap<>();
        errorMessages.put("phoneNumber", ex.getMessage());

        return new ResponseEntity<>(new CustomErrorResponse(errorMessages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleConstraintViolationException(
            ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        Map<String, String> errorMessages = violations.stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
        return new ResponseEntity<>(new CustomErrorResponse(errorMessages)
                , HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<CustomErrorResponse> handleAuthenticationException(AuthenticationException ex){
//        Map<String, String> errorMessages = new HashMap<>();
//        errorMessages.put("auth", "Invalid phone number or password");
//
//        return new ResponseEntity<>(new CustomErrorResponse(errorMessages), HttpStatus.BAD_REQUEST);
//    }

}
