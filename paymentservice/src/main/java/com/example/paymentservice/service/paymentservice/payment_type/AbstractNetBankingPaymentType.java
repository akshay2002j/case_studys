package com.example.paymentservice.service.paymentservice.payment_type;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.entity.User;
import com.example.paymentservice.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractNetBankingPaymentType extends AbstractPaymentType<Payment> {

    @Autowired
    UserService userService;

    @Override
    public boolean validateDetails(Payment entity) {
        User user = userService.getUserByEmail(entity.getUser().getUserEmail());
        if(user!= null){
            return user.getUserEmail().equals(entity.getNetBankingUsername()) && user.getUserPassword().equals(entity.getNetBankingPassword());
        }
        return false;
    }
}
