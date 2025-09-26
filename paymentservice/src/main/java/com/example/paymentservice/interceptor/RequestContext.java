package com.example.paymentservice.interceptor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


import java.util.UUID;

@Data
@RequestScope
@NoArgsConstructor
@Component
public class RequestContext {
    String requestID;
    String userID;

    public RequestContext(String userID) {
        this.requestID = UUID.randomUUID().toString();
        this.userID = userID;
    }

}
