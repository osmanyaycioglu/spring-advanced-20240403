package org.training.kafka.spring.advanced.rest.error;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handlerException(IllegalArgumentException exceptionParam) {
        return ErrorObj.builder()
                       .withErrorStr(exceptionParam.getMessage())
                       .withErrorCode(1001)
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handlerException(MethodArgumentNotValidException exceptionParam) {
        return ErrorObj.builder()
                       .withErrorStr("validation exception")
                       .withErrorCode(1002)
                       .withErrorObjs(exceptionParam.getAllErrors()
                                                    .stream()
                                                    .map(e -> ErrorObj.builder()
                                                                      .withErrorStr(e.toString())
                                                                      .withErrorCode(1003)
                                                                      .build())
                                                    .collect(Collectors.toList()))
                       .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handlerException(ConstraintViolationException exceptionParam) {
        return ErrorObj.builder()
                       .withErrorStr("validation exception")
                       .withErrorCode(1002)
                       .withErrorObjs(exceptionParam.getConstraintViolations()
                                                    .stream()
                                                    .map(e -> ErrorObj.builder()
                                                                      .withErrorStr(e.toString())
                                                                      .withErrorCode(1003)
                                                                      .build())
                                                    .collect(Collectors.toList()))
                       .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handlerException(Exception exceptionParam) {
        if (exceptionParam instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                                 .body(ErrorObj.builder()
                                               .withErrorStr(exceptionParam.getMessage())
                                               .withErrorCode(5001)
                                               .build());

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorObj.builder()
                                           .withErrorStr(exceptionParam.getMessage())
                                           .withErrorCode(5000)
                                           .build());
    }

}
