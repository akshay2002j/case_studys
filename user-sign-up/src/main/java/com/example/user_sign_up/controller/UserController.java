package com.example.user_sign_up.controller;

import com.example.user_sign_up.dto.LoginRequest;
import com.example.user_sign_up.dto.UserDto;
import com.example.user_sign_up.entity.User;
import com.example.user_sign_up.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/user")
@RestController
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        if(userService.getUserByEmail(userDto.getEmail())!=null){
            return new ResponseEntity<>(
                    userService.registerUser(userDto), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest , HttpSession session){
        if (userService.getUserByEmail(loginRequest.getEmail())!=null){
            if(userService.loginUser(loginRequest)){
                session.setAttribute("email",loginRequest.getEmail());
                return new ResponseEntity<>( true,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>( false,HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }




}
