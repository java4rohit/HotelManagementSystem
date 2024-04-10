package com.lcwd.user.service.serviceImpl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.RatingServiceException;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.external.services.RatingService;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> createUsers(List<User> users) {
        List<User> saveUser = userRepository.saveAll(users);
        return saveUser;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {

        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        // fetch rating of the above  user from RATING SERVICE
        //http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        //List<Rating>  ratingsOfUser = ratingService.getRating(user.getUserId());
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            // logger.info("response status code: {} ",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        logger.info("Successfully retrieved user with id {} and their ratings", userId);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            List<Rating> userRatings;
            try {
                //  List<Rating> ratings = restTemplate.exchange("http://localhost:8082/r1/users/" + user.getUserId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Rating>>() {}).getBody();
                userRatings = ratingService.getRating(user.getUserId());
                user.setRatings(userRatings);
                for (Rating rating : userRatings) {
                    //  Hotel hotel = restTemplate.getForObject("http://localhost:8083/hotels/" + rating.getHotelId(), Hotel.class);
                    Hotel hotel = hotelService.getHotel(rating.getHotelId());
                    rating.setHotel(hotel);
                }
            } catch (HttpClientErrorException e) {
                logger.error("Error while fetching ratings: {}", e.getMessage());
                throw new RatingServiceException("Error while fetching ratings from the rating service", e);
            }
        }
        return users;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteUser(String userId) {
        try {
            userRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> updateUser(String userId, User user) {
        User _user = userRepository.findById(userId).stream().findAny().orElse(null);
        if (_user != null) {
            _user.setUserName(user.getUserName());
            _user.setAbout(user.getAbout());
            _user.setEmail(user.getEmail());
            try {
                User savedUser = userRepository.save(_user);
                return new ResponseEntity<>(savedUser, HttpStatus.OK);
            } catch (OptimisticLockingFailureException e) {
                // Handle the optimistic locking failure
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
