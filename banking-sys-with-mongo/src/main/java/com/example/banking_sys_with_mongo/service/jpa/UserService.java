//package com.example.banking_sys_with_mongo.service;
//
//import com.example.banking_sys_with_mongo.dto.UserDto;
//import com.example.banking_sys_with_mongo.exception.AccountException;
//import com.example.banking_sys_with_mongo.exception.UserException;
//import com.example.banking_sys_with_mongo.model.User;
//import com.example.banking_sys_with_mongo.repository.UserRepository;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//
//    final private UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//    public UserDto createUser(UserDto userDto) {
//        try {
//            User user = new User();
//            BeanUtils.copyProperties(userDto, user);
//            User savUser = userRepository.save(user);
//            userDto.setUserId(savUser.getUserId());
//            userDto.setCreatedAt(savUser.getCreatedAt());
//            return userDto;
//        } catch (IllegalStateException e) {
//            throw new  UserException("User creation failed");
//        } catch (Exception exception) {
//            throw exception;
//        }
//    }
//
//    public UserDto updateUser(UserDto userDto) {
//        try {
//            User user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new UserException("User not found with id " + userDto.getUserId()));
//            BeanUtils.copyProperties(userDto, user);
//            userRepository.save(user);
//            userDto.setUserId(user.getUserId());
//            return userDto;
//        } catch (IllegalStateException e) {
//            throw new AccountException("User not found with id " + userDto.getUserId());
//        } catch (Exception exception) {
//            throw exception;
//        }
//    }
//
//    public String deleteUser(String id) {
//        if (id != null) {
//            userRepository.deleteById(id);
//        }
//        return "User has been deleted with id: " + id;
//    }
//
//    public UserDto getUser(String id) {
//        if (id != null) {
//            User user = userRepository.findById(id).orElseThrow(() -> new UserException("User with id: " + id + " not found"));
//            UserDto userDto = new UserDto();
//            BeanUtils.copyProperties(user, userDto);
//            return userDto;
//        }
//        return null;
//    }
//
//}
