package com.lcwd.rating.serviceImpl;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.exception.ResourceNotFoundException;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<Rating> createUsers(List<Rating> ratings) {
        return ratingRepository.saveAll(ratings);
    }

    @Override
    public Rating createUser(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating get(String ratingId) {
        Optional<Rating> user = ratingRepository.findById(ratingId);
        return user.orElseThrow(() -> new ResourceNotFoundException("user with given Id is not found on Server !! : " + ratingId));

    }

    @Override
    public List<Rating> getRatingByUser(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public String delete(String ratingId) {
        return null;
    }

    @Override
    public String update(String ratingID) {
        return null;
    }
}
