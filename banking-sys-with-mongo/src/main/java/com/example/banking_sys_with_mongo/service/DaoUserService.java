package com.example.banking_sys_with_mongo.service;

import com.example.banking_sys_with_mongo.dao.UserDao;
import com.example.banking_sys_with_mongo.dto.UserDto;
import com.example.banking_sys_with_mongo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DaoUserService {


    UserDao userDao;

    public DaoUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
       User savedUser =  userDao.saveUser(user);
       BeanUtils.copyProperties(savedUser, userDto);

        return userDto;
    }

}
