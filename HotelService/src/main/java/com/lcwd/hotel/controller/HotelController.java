package com.lcwd.hotel.controller;


import com.lcwd.hotel.entities.Hoteiii;
import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.service.HotelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
@Validated
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/creates")
    @NotEmpty(message = "Input Hotel list cannot be empty.")
    public ResponseEntity<List<Hotel>> creates( @RequestBody @Valid Hoteiii hotels) {
        for (Hotel hotel : hotels.getHotels()) {
            String randomUserId = UUID.randomUUID().toString();
            hotel.setHotelId(randomUserId);
        }
        List<Hotel> saveHotel = hotelService.creates(hotels.getHotels());
        return new ResponseEntity<>(saveHotel, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Hotel> create( @Valid @RequestBody Hotel hotel) {

            String randomUserId = UUID.randomUUID().toString();
            hotel.setHotelId(randomUserId);

        Hotel saveHotel = hotelService.create(hotel);
        return new ResponseEntity<>(saveHotel, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAll() {
        List<Hotel> hotelList = hotelService.getAll();
        return new ResponseEntity<>(hotelList, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> get(@PathVariable String hotelId) {
        Hotel hotel = hotelService.get(hotelId);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String hotelId) {
        String s = hotelService.delete(hotelId);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PutMapping("/update/{hotelId}")
    public ResponseEntity<String> update(@PathVariable String hotelId) {
        String s = hotelService.update(hotelId);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
