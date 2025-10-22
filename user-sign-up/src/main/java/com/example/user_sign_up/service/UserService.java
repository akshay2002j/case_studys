package com.example.user_sign_up.service;

import com.example.user_sign_up.dto.LoginRequest;
import com.example.user_sign_up.dto.UserDto;
import com.example.user_sign_up.entity.User;
import com.example.user_sign_up.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDto registerUser(UserDto userDto){
        log.info("user register with email " + userDto.getEmail());
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        User savUser  = userRepository.save(user);
        BeanUtils.copyProperties(savUser,user);
        return userDto;
    }

    public UserDto getUserByEmail(String email){
        User savedUser =  userRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("User not found with email " + email)
        );
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(savedUser,userDto);
        return userDto;
    }

    public boolean loginUser(LoginRequest loginRequest){
        if(loginRequest.getEmail()!=null || loginRequest.getPassword()!=null){
            User user =  userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                    ()-> new RuntimeException("User not found with email " + loginRequest.getEmail())
            );
            if (user!=null){
                if (user.getPassword().equals(loginRequest.getPassword()) && user.getEmail().equals(loginRequest.getEmail())){
                    return true;
                }
            }
        }
        return false;
    }
}
