package com.yunus.stockapi.exception;

import com.yunus.stockapi.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        log.error(Constants.EXCEPTION_PREFIX.concat("BadRequestException: {}"), e.getMessage());
        ErrorResponse exceptionResponse = generateExceptionResponse(Constants.BAD_REQUEST_CODE, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundValidationException(NotFoundException e) {
        log.error(Constants.EXCEPTION_PREFIX.concat("NotFoundException: {}"), e.getMessage());
        ErrorResponse exceptionResponse = generateExceptionResponse(Constants.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<ErrorResponse> otherExceptions(Exception e) {
        log.error(Constants.EXCEPTION_PREFIX.concat("RuntimeException: {}"), ExceptionUtils.getMessage(e));
        ErrorResponse exceptionResponse = generateExceptionResponse(Constants.INTERNAL_SERVER_ERROR_CODE, ExceptionUtils.getMessage(e));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    private ErrorResponse generateExceptionResponse(String code, String message) {
        return ErrorResponse.builder().code(code).message(message).build();
    }
}
