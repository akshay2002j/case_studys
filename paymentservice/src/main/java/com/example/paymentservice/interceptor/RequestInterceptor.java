package com.example.paymentservice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequestScope
public class RequestInterceptor implements HandlerInterceptor {

    private final RequestContext request;

    public RequestInterceptor(RequestContext request) {
        this.request = request; // Spring will inject request-scoped bean
    }



    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        try {
            this.request.setUserID(request.getHeader("userID"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
