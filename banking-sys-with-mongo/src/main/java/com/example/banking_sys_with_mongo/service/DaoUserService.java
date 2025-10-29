package com.example.banking_sys_with_mongo.service;

import com.example.banking_sys_with_mongo.dao.UserDaoImpl;
import com.example.banking_sys_with_mongo.dto.UserDto;
import com.example.banking_sys_with_mongo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DaoUserService {


    UserDaoImpl userDaoImpl;

    public DaoUserService(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User savedUser = userDaoImpl.save(user);
        BeanUtils.copyProperties(savedUser, userDto);
        return userDto;
    }

    public UserDto updateUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User savedUser = userDaoImpl.update(user);
        BeanUtils.copyProperties(savedUser, userDto);
        return userDto;
    }

    public boolean deleteUser(String userId) {
        return userDaoImpl.deleteById(userId);
    }

    public UserDto getUserById(String userId) {
        User user = userDaoImpl.getById(userId);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public User getUserByEmail(String email) {
        User user = userDaoImpl.getByEmail(email);
        return user;
    }

}
