package com.lcwd.hotel.payload;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Builder
public class ApiResponse {

    private  String message;
    private  boolean success;
    private HttpStatus status;

}
