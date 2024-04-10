package com.lcwd.user.service.exception;


import lombok.*;
import org.springframework.http.HttpStatus;

@ToString
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private HttpStatus status;
    private boolean success;
}
