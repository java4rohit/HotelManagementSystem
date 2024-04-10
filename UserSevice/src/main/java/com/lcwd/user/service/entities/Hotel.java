package com.lcwd.user.service.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel implements Serializable {

    private String hotelId;
    private String hotelName;
    private String location;
    private String about;




}
