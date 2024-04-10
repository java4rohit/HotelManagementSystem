package com.lcwd.rating.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Validated
public class Rating implements Serializable {
    @Id
    @Column(name = "RatingId", unique = true, nullable = false)
    @JsonProperty( value = "ratingId", access = JsonProperty.Access.READ_ONLY)
    private String ratingId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Hotel ID is required")
    private String hotelId;

    @Range(min = 1, max = 10, message = "Rating must be between 1 and 10")
    private int rating;

    @Size(max = 500, message = "Feedback must be 500 characters or less")
    private String feedback;
}
