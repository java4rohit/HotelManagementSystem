package com.lcwd.rating.service;

import com.lcwd.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface RatingService {

    public List<Rating> createUsers(List<Rating> ratings);
    Rating createUser(Rating rating);

    //get All rating
    public List<Rating> getAllRating();

    //get rating By Id
    public Rating get(String ratingId);
    //get all by UserID

    public List<Rating> getRatingByUser(String userId);

    public List<Rating> getRatingByHotel(String hotelId);

    String delete(String ratingId);

    String update(String ratingID);


}
