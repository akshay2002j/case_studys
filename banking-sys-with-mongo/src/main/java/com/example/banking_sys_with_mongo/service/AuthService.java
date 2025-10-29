package com.example.banking_sys_with_mongo.service;

import com.example.banking_sys_with_mongo.dao.UserDaoImpl;
import com.example.banking_sys_with_mongo.exception.ExceptionType;
import com.example.banking_sys_with_mongo.exception.UserException;
import com.example.banking_sys_with_mongo.model.User;
import com.example.banking_sys_with_mongo.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDaoImpl userDaoImpl;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserDaoImpl userDaoImpl, JwtUtil jwtUtil) {
        this.userDaoImpl = userDaoImpl;
        this.jwtUtil = jwtUtil;
    }

    public String login(String username, String password) {
        User user = userDaoImpl.getByEmail(username);
        if (user == null) {
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserException(ExceptionType.INVALID_CREDENTIALS);
        }
        return jwtUtil.generateToken(user.getEmail());
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDaoImpl.save(user);
    }
}