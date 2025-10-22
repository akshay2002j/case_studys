package com.example.user_sign_up.component;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if (uri.contains("/login") || uri.contains("/register")) {
            return true;
        }

        if (session != null && session.getAttribute("email") != null) {
            return true;  // Session valid, proceed to controller
        }

        // Session invalid → block access
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write("Access Denied! Please login first.");
//        return false;  // Stop request from reaching controller
        return true;
    }

}
