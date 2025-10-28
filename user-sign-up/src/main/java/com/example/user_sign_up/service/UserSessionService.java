package com.example.user_sign_up.service;

import com.example.user_sign_up.entity.UserSession;
import com.example.user_sign_up.repo.UserSessionRepository;
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

    public boolean deleteSessionBySessionId(String email){
        userSessionRepository.deleteUserSessionByEmail(email);
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
