package com.lcwd.hotel.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel implements Serializable {

    @NotNull
    @Transient
    private Long id;

    @Id
    @Column(name = "HotelId", unique = true, nullable = false)
    @JsonProperty( value = "hotelId", access = JsonProperty.Access.READ_ONLY)
    private String hotelId;

    @NotEmpty(message = "Hotel name is required")
    private String hotelName;

    @NotEmpty( message = "Hotel location is required")
    @Column(name = "Location", length = 1024)
    private String location;

    @Column(name = "About", length = 1024)
    @NotEmpty( message = "About is required")
    private String about;

}