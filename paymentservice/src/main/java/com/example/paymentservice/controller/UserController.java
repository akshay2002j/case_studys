package com.example.paymentservice.controller;


import com.example.paymentservice.dto.UserDTO;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.service.userservice.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(name = "User Management", description = "Operations related to users")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RequestContext requestContext;

    @Operation(summary = "Register the user with email and the password")
    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "User created successfully",
            content = @Content(schema = @Schema(implementation = UserDTO.class)))
    public ResponseEntity<String> registerUser(  @RequestBody UserDTO user) {
        log.info("Received request to register user {}",user);
      String userId =   userService.createUser(user);
      log.info("User {} registered successfully",userId);
      return new ResponseEntity<String>(userId, HttpStatus.CREATED);
    }

    @Operation(summary = "Update the existing user based on the User object provided in request body")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "")
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser( @RequestBody UserDTO user) {
        log.info("Received request to update user {}",user);
         String userId =   userService.updateUser(user);
         log.info("User {} updated successfully",userId);
        return new ResponseEntity<UserDTO>(user, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get user by ID", description = "Fetch a user using their unique ID")
    @GetMapping("/{Id}")
    public ResponseEntity<UserDTO> getUser(@Parameter(description = "Unique Id of the user")
                                               @PathVariable String Id) {
        UserDTO user =   userService.getUserById(Id);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @Operation(summary = "Delete the user from system based on the unique userId provided")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Parameter(description = "Unique Id of the user")
                                                 @PathVariable String id) {
        log.info("Received request to delete user {}",id);
        String userId =   userService.deleteUser(id);
        log.info("User {} deleted successfully",userId);
        return new ResponseEntity<String>("User successfully deleted with with id:- " + userId, HttpStatus.OK);
    }
}
