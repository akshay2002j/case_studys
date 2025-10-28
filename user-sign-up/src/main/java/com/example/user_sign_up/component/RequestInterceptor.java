package com.example.user_sign_up.component;


import com.example.user_sign_up.entity.UserSession;
import com.example.user_sign_up.service.UserSessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    UserSessionService userSessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        String sessionId = request.getHeader("sessionId");

        if (uri.contains("/login") || uri.contains("/register")) {
            return true;
        }
        UserSession userSession = userSessionService.findBySessionId(sessionId).orElse(null);
        if (userSession != null && userSessionService.checkSessionExpired(sessionId)) {
            log.info("User loged in with session " + userSession.getSessionId());
            return true;
        }
       else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

    }

}
