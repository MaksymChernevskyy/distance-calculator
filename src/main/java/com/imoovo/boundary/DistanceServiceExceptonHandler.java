package com.imoovo.boundary;

import com.imoovo.business.entity.FindDistanceException;
import com.imoovo.business.entity.FindUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DistanceServiceExceptonHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> getResponseForError(String message) {
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {FindUserException.class, FindDistanceException.class})
    public ResponseEntity<String> getResponseForNoContent(String message) {
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }
}
