package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.entity.NetBanking;
import com.example.paymentservice.entity.User;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractIPaymentProvider;
import com.example.paymentservice.service.userservice.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service("NETBANKING")
public class NetBankingIPaymentProvider extends AbstractIPaymentProvider<NetBanking> {

    /// Netbanking - Validate userName/password, balance -> Generate OTP -> Validate OTP

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

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

    @PostConstruct
    public void init() {
        paymentTypeBeanMap.putPaymentType(PaymentType.NETBANKING,this);
        log.debug("Bean of the NetBanking payment provider injected in HashMap");
    }

}
