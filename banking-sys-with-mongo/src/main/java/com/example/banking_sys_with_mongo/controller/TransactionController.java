package com.example.banking_sys_with_mongo.controller;


import com.example.banking_sys_with_mongo.dao.TransactionDaoImpl;
import com.example.banking_sys_with_mongo.dto.TransactionDto;
import com.example.banking_sys_with_mongo.dto.requestdto.DepositRequest;
import com.example.banking_sys_with_mongo.dto.requestdto.TransferRequest;
import com.example.banking_sys_with_mongo.dto.requestdto.WithdrawRequest;
import com.example.banking_sys_with_mongo.dto.responsedtos.TransactionInfoResponse;
import com.example.banking_sys_with_mongo.service.DaoTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    private DaoTransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> makeDepositTransaction(@RequestBody DepositRequest depositRequest) {
        return new ResponseEntity<>(transactionService.makeDeposit(depositRequest), HttpStatus.CREATED);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDto> makeWithdrawTransaction(@RequestBody WithdrawRequest withdrawRequest) {
        return new ResponseEntity<>(transactionService.makeWithdraw(withdrawRequest), HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> makeTransferTransaction(@RequestBody TransferRequest transferRequest) {
        return new ResponseEntity<>(transactionService.makeTransfer(transferRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        if (transactionService.cancelTransaction(id)){
            return new ResponseEntity<>("Transaction Cancelled with Id: " + id, HttpStatus.OK);
        }
        else  {
            return new ResponseEntity<>("Transaction Failed to Cancelled with Id: " + id, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/")
    public ResponseEntity<TransactionDto> getTransaction(@RequestParam String id) {
        return new ResponseEntity<TransactionDto>(transactionService.getTransactionById(id), HttpStatus.OK);
    }

    @GetMapping("/list/{accountNumber}")
    public ResponseEntity <List<TransactionDto>> getTransactionListByAccountNumber(@PathVariable String accountNumber) {
        return new ResponseEntity<List<TransactionDto>>(transactionService.getTransactionsByAccountNumber(accountNumber), HttpStatus.OK);
    }

    @GetMapping("/number")
    public ResponseEntity<Map<String,String>> numberOfTransactionByAccount(@RequestParam String accountNumber){
        return new ResponseEntity<>(transactionService.numberOfTransactionsByAccount(accountNumber), HttpStatus.OK);
    }
}
