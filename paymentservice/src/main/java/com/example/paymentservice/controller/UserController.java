package com.example.paymentservice.controller;


import com.example.paymentservice.dto.TransactionWithUserDTO;
import com.example.paymentservice.dto.UserDTO;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RequestContext requestContext;

    @PostMapping("/")
    public ResponseEntity<String> registerUser(  @RequestBody UserDTO user) {
      String userId =   userService.createUser(user);
      return new ResponseEntity<String>(userId, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser( @RequestBody UserDTO user) {
         String userId =   userService.updateUser(user);
        return new ResponseEntity<UserDTO>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String Id) {
        UserDTO user =   userService.getUserById(Id);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id) {
        String userId =   userService.deleteUser(id);
        return new ResponseEntity<String>("User successfully deleted with with id:- " + userId, HttpStatus.OK);
    }
}
