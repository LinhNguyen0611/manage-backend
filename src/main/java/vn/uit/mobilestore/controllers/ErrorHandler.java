package vn.uit.mobilestore.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.uit.mobilestore.exception.RestException;
import vn.uit.mobilestore.responses.ErrorResponse;

import java.util.List;

/**
 * Created by HieuNP on 15/04/2018.
 */
@RestControllerAdvice
public class ErrorHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    private static final ResponseEntity<ErrorResponse> handleRestException(RestException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setCode(exception.getCode());

        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }

    private static final ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("The request is not valid");
        errorResponse.setCode("REQUEST_IS_INVALID");
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorResponse.getFieldErrors().add(new ErrorResponse.FieldError(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private static final ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("The constraint specified is not valid");
        errorResponse.setCode("REQUEST_CONSTRAINT_VIOLATION");

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private static final ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("The data integrity violation");
        errorResponse.setCode("DATA_INTEGRITY_VIOLATION");

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(Throwable exception) {
        LOGGER.warn("Error: {}", exception);
        if (exception instanceof DataIntegrityViolationException) {
            return handleDataIntegrityViolationException((DataIntegrityViolationException) exception);
        }
        if (exception instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException) exception);
        }
        if (exception instanceof MethodArgumentNotValidException) {
            return handleMethodArgumentNotValidException((MethodArgumentNotValidException) exception);
        }
        if (exception instanceof RestException) {
            return handleRestException((RestException) exception);
        }

        //Default error
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.name());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
