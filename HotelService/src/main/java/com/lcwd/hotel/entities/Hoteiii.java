package com.lcwd.hotel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hoteiii {
    List< @Valid Hotel> hotels;
}
