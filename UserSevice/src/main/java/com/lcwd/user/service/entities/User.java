package com.lcwd.user.service.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "UserId", unique = true, nullable = false)
    @JsonProperty( value = "userId", access = JsonProperty.Access.READ_ONLY)
    private String userId;

    @Column(name = "UserName", length = 100)
    @NotBlank(message = "User name is required")
    @Size(min = 2, max = 100, message = "User name must be between 2 and 100 characters")
    private String userName;

    @Column(name = "EmailId", nullable = false, length = 45)
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @Column(name = "About")
    @Size(max = 500, message = "About must be 500 characters or less")
    private String about;


    @Transient
    @JsonProperty( value = "ratings", access = JsonProperty.Access.READ_ONLY)
    @OrderBy("hotelId ASC")
    private List<Rating> ratings;

//    @Transient
//    @JsonProperty( value = "ratingLis", access = JsonProperty.Access.READ_ONLY)
//    private List<Hotel> hotels;

}
