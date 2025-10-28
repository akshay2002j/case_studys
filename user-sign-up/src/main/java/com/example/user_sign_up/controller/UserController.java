package com.example.user_sign_up.controller;

import com.example.user_sign_up.dto.LoginRequest;
import com.example.user_sign_up.dto.UserDto;
import com.example.user_sign_up.entity.User;
import com.example.user_sign_up.entity.UserSession;
import com.example.user_sign_up.service.UserService;
import com.example.user_sign_up.service.UserSessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RequestMapping("/api/user")
@RestController
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    UserSessionService userSessionService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
            return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        if (userService.getUserByEmail(loginRequest.getEmail())!=null){
            if(userService.loginUser(loginRequest)){
                UserSession  userSession = new UserSession();
                userSession.setEmail(loginRequest.getEmail());
                userSession.setCreatedAt(LocalDateTime.now());
                userSession.setExpiresAt(LocalDateTime.now().plusMinutes(15));
               UserSession session =  userSessionService.saveUserSession(userSession);
                return new ResponseEntity<>(session.getSessionId(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>( false,HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logoutUser(String sessionId){
        if(userSessionService.deleteSessionBySessionId(sessionId)){
            return new ResponseEntity<>("User logout successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }




}
