package com.example.user_sign_up.repo;

import com.example.user_sign_up.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession,String> {
    Optional<UserSession> findBySessionId(String sessionId);

    void deleteBySessionId(String sessionId);

    Optional<UserSession> findByEmail(String email);
}
