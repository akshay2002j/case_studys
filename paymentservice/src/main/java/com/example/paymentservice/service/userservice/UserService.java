package com.example.paymentservice.service.userservice;

import com.example.paymentservice.entity.User;
import com.example.paymentservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService( UserRepository userRepository1) {
        this.userRepository = userRepository1;
    }


    public String createUser(User user){
      User savedUser =   userRepository.save(user);
        return savedUser.getUserId();
    }

    public User getUserById(String userId){
        return userRepository.findById(userId).orElse(null);
    }

    public String updateUser(User user){
        User saveduser = userRepository.getReferenceById(user.getUserId());
        saveduser.setUserEmail(user.getUserEmail());
        saveduser.setUserPassword(user.getUserPassword());
        return userRepository.save(saveduser).getUserId();
    }

    public String deleteUser(String userId){
        userRepository.deleteById(userId);
        return userId;
    }
}
