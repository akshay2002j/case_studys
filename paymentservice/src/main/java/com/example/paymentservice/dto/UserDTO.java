package com.example.paymentservice.dto;

import com.example.paymentservice.entity.Transaction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "User data transfer object")
public class UserDTO {
    @Schema(description = "unique id for each user(not mandatory to pass for registration)")
    String userId;
    @Schema(description = "unique user email")
    String userEmail;
    @Schema(description = "unique user password")
    String userPassword;
    @Schema(description = "List for transaction associated with user")
    List<Transaction> transactionList;
}
