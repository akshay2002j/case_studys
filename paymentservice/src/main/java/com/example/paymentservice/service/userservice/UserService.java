package com.example.paymentservice.service.userservice;

import com.example.paymentservice.dto.UserDTO;
import com.example.paymentservice.entity.User;
import com.example.paymentservice.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository1, ModelMapper modelMapper) {
        this.userRepository = userRepository1;
        this.modelMapper = modelMapper;
    }


    public String createUser(UserDTO userDto){
        User user = modelMapper.map(userDto,User.class);
      User savedUser =   userRepository.save(user);
        return savedUser.getUserId();
    }

    public UserDTO getUserById(String userId){
         User user = userRepository.findById(userId).orElse(null);
         if (user == null){
             return null;
         }
         return modelMapper.map(user,UserDTO.class);
    }

    public String updateUser(UserDTO userDto){
        User saveduser = userRepository.getReferenceById(userDto.getUserId());
        saveduser.setUserEmail(userDto.getUserEmail());
        saveduser.setUserPassword(userDto.getUserPassword());
        saveduser.setPaymentList(userDto.getPaymentList());
        return userRepository.save(saveduser).getUserId();
    }

    public String deleteUser(String userId){
        userRepository.deleteById(userId);
        return userId;
    }

    public User getUserByEmail(String email){
       return userRepository.findByUserEmail(email);
    }
}
