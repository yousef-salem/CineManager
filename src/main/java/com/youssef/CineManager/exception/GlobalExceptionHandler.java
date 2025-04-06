package com.youssef.CineManager.exception;

import com.youssef.CineManager.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Collections.singletonList(ex.getMessage())
        );

        logger.info(ex.getMessage());

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                errorDetails,
                "An error occurred"
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundResourceException(NotFoundResourceException ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                Collections.singletonList(ex.getErrorMessage())
        );

        logger.info(ex.getErrorMessage());

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                errorDetails,
                ex.getErrorMessage() // This will be the "message" in your response
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
