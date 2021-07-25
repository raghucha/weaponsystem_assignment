package com.weponsystem.weaponsystem.exception;

import com.weponsystem.weaponsystem.model.ErrorResonse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class ConstraintViolationExceptionHandle   extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ConstraintViolationExceptionHandle.class);


    @ExceptionHandler(ConstraintViolationException.class )
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<String>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add( violation.getPropertyPath() + ": " + violation.getMessage());
        }

        final ErrorResonse errorResonse = new ErrorResonse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(errorResonse, new HttpHeaders(), errorResonse.getStatus());
    }


}