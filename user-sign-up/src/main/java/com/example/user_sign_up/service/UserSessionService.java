package com.example.user_sign_up.service;

import com.example.user_sign_up.entity.UserSession;
import com.example.user_sign_up.repo.UserSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserSessionService  {

    @Autowired
    UserSessionRepository userSessionRepository;


    public Optional<UserSession> findBySessionId(String sessionId) {
       Optional<UserSession> session = (userSessionRepository.findBySessionId(sessionId));
        return session;
    }

    public Optional<UserSession> findByEmail(String email) {
        return userSessionRepository.findByEmail(email);
    }

    @Transactional
    public boolean deleteSessionBySessionId(String sessionId) {
        userSessionRepository.deleteBySessionId(sessionId);
        return true;
    }

    public boolean checkSessionExpired(String sessionId){
        Optional<UserSession> session = userSessionRepository.findBySessionId(sessionId);
        if (session.isPresent()) {
            UserSession userSession = session.get();
            return userSession.getExpiresAt().isAfter(LocalDateTime.now());
        }
        return false;
    }

    public UserSession saveUserSession(UserSession userSession){
       UserSession savedsession =  userSessionRepository.save(userSession);
       return savedsession;

    }
}
