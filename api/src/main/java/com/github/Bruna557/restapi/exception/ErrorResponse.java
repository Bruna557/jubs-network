package com.github.Bruna557.restapi.exception;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private Date timestamp;
    private String status;
    private String message;
    private String details;
}
