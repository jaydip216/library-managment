package com.library.exceptions;

import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.library.exceptions.type.ApplicationException;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchUserExistsException(ApplicationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.httpStatus.toString(), new Date());
        return new ResponseEntity<>(errorResponse, e.httpStatus);
    }

}
