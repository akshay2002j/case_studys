package com.example.banking_sys_with_mongo.controller;

import com.example.banking_sys_with_mongo.dto.TransactionDto;
import com.example.banking_sys_with_mongo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<TransactionDto> updateTransaction(@RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.updateTransaction(transactionDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {

        return new ResponseEntity<>(transactionService.deleteTransaction(id), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable String id) {
        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
    }
}
