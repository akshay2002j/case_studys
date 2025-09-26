package com.example.paymentservice.service.userservice;

import com.example.paymentservice.dto.UserDTO;
import com.example.paymentservice.entity.User;
import com.example.paymentservice.exception.UserNotFoundException;
import com.example.paymentservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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

         User user = userRepository.findById(userId).orElseThrow(
                 ()->{
                     log.debug("User not found with id {}",userId);
                     return new UserNotFoundException("User not found");}
         );
         return modelMapper.map(user,UserDTO.class);
    }

    public String updateUser(UserDTO userDto){
        User saveduser = Optional.of(userRepository.getReferenceById(userDto.getUserId())).orElseThrow(
                ()-> new UserNotFoundException("User not found with id: " + userDto.getUserId())
        );
        saveduser.setUserEmail(userDto.getUserEmail());
        saveduser.setUserPassword(userDto.getUserPassword());
        saveduser.setTransactionList(userDto.getTransactionList());
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
