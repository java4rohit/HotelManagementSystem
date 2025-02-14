package com.lcwd.rating.repository;

import com.lcwd.rating.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {


    //custom find Method
   List<Rating> findByUserId(String userId);
   List<Rating> findByHotelId(String userId);

}
