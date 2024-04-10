package com.lcwd.rating.controller;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/ratings")
public class RatingController {


    @Autowired
    RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<List<Rating>> createUsers(@RequestBody List<Rating> ratings) {
        for (Rating rating : ratings) {
            String randomUserId = UUID.randomUUID().toString();
            rating.setRatingId(randomUserId);
        }
        List<Rating> saveRatings = ratingService.createUsers(ratings);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRatings);
    }

    @PostMapping("/rating")
    public ResponseEntity<Rating> createUser(@RequestBody Rating rating) {

            String randomUserId = UUID.randomUUID().toString();
            rating.setRatingId(randomUserId);

        Rating saveRating = ratingService.createUser(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRating);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> get(@PathVariable String ratingId) {
        Rating rating = ratingService.get(ratingId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUser(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUser(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotel(hotelId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Rating>> getAllRating() {
        List<Rating> ratingList = ratingService.getAllRating();
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String ratingId) {
        String s = ratingService.delete(ratingId);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PutMapping("/update/{ratingID}")
    public ResponseEntity<String> update(@PathVariable String ratingID) {
        String s = ratingService.update(ratingID);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
