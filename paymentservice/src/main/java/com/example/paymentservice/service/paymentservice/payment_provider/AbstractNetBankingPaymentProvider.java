package com.example.paymentservice.service.paymentservice.payment_provider;

import com.example.paymentservice.entity.NetBanking;
import com.example.paymentservice.entity.User;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.service.userservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.webmvc.core.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
public class AbstractNetBankingPaymentProvider  extends AbstractPaymentProvider<NetBanking> {

    @Autowired
    UserService userService;

    @Autowired
    RequestContext requestContext;

    @Override
    public boolean validateDetails(Map<String, Object> data) {
        try {
            String username = (String) data.get("username");
            String rawPassword = (String) data.get("userpasword"); // ⚠️ confirm spelling in JSON
            User user = userService.getUserByEmail(username);
            log.debug("Received request to validate user {}",user, "for NetBankingPaymentProvider");
            if (user == null) {
                // User not found
                log.error("User {} not found for NetBanking Validation",username);
                return false;
            }

            return user.getUserEmail().equalsIgnoreCase(username)
                    && user.getUserPassword().equalsIgnoreCase(rawPassword);

        } catch (Exception e) {
            log.error("Exception Occured While validating the NetBanking Credentials",e.getMessage(),"for user {}",requestContext.getUserID());
            e.printStackTrace();
            return false;
        }

    }
}
