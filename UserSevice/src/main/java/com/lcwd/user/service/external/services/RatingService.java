package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    //GET

    @GetMapping("r1/users/{userId}")
    public List<Rating> getRating(@PathVariable String userId);
    //POST
    @PostMapping("r1/rating")
    public Rating createRating(Rating rating);
    //UPDATE
//    @PutMapping("r1/update")
//    public Rating updateRating(@PathVariable String ratingId, @RequestBody Rating rating);
}
