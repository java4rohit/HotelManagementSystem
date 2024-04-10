package com.lcwd.user.service.controller;


import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/u1")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Operation(summary = "Create a new Users", tags = {"POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/users")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        for (User user : users) {
            String randomUserId = UUID.randomUUID().toString();
            user.setUserId(randomUserId);
        }
        List<User> saveUsers = userService.createUsers(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUsers);
    }

    @Operation(summary = "Create a new User", tags = {"POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User saveUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }

    @Operation(summary = "Get All users", tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/users")
    //@CircuitBreaker(name = "ratingHotelBreaker" ,fallbackMethod = "ratingHotelFallback" )
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = userService.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }



    int retryCount=1;
    @Operation(
            summary = "Retrieve a Tutorial by Id",
            description = "Get a User object by specifying its id. The response is User object with id",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
  //  @CircuitBreaker(name = "ratingHotelBreaker" ,fallbackMethod = "ratingHotelFallback" )
  //  @Retry(name = "ratingHotelBreaker" ,fallbackMethod = "ratingHotelFallback" )
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    @GetMapping("users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        logger.info("Get Single User Handler: UserController");
        logger.info("Retry Count {}", retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){

        ex.printStackTrace();

        logger.info("FallBack is Executed because service is down {} ", ex.getMessage());

        User dummyname = User.builder().email("dummy@gmail.com")
                .userName("dummy name")
                .about("Rating is service is down its dummy user")
                .userId("12234").build();
        return  new ResponseEntity<>(dummyname,HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);

    }
    @Operation(summary = "Update a Tutorial by Id", tags = {"PUT"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        ResponseEntity<User> userResponseEntity = userService.updateUser(userId, user);
        return userResponseEntity;

    }

    @Operation(summary = "Delete a Users by Id", tags = {"DELETE" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId) {
        ResponseEntity<HttpStatus>  statusResponseEntity= userService.deleteUser(userId);
        return statusResponseEntity;
    }
}
