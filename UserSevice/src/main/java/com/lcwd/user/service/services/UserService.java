package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    //user operation

    //create
    List<User> createUsers(List<User> users);

    //getUser all user
    List<User> getAllUser();

    ResponseEntity<HttpStatus> deleteUser(String userId);

    ResponseEntity<User>  updateUser(String userId, User user);

    User getUser(String userId);


    User createUser(User user);
}
