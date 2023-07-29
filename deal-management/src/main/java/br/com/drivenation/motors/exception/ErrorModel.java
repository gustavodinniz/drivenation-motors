package br.com.drivenation.motors.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorModel {
    private String code;
    private String message;
}
