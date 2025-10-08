package com.example.banking_sys_with_mongo.controller;


import com.example.banking_sys_with_mongo.dto.AccountDto;
import com.example.banking_sys_with_mongo.model.Account;
import com.example.banking_sys_with_mongo.service.AccountService;
import com.example.banking_sys_with_mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>( accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>( accountService.updateAccount(accountDto), HttpStatus.OK);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber) {
        return new ResponseEntity<>(accountService.deleteAccount(accountNumber), HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String accountNumber) {
        return new ResponseEntity<>( accountService.getAccount(accountNumber), HttpStatus.OK);
    }

}
