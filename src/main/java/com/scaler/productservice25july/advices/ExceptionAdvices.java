package com.scaler.productservice25july.advices;

import com.scaler.productservice25july.dtos.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRunTimeException(RuntimeException e) {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setStatus("ERROR");
        dto.setMessage(e.getMessage());
        return dto;
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "Something went wrong";
    }
}
