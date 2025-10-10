package com.example.banking_sys_with_mongo.controller;


import com.example.banking_sys_with_mongo.dto.AccountDto;
import com.example.banking_sys_with_mongo.dto.requestdto.OpenAccountRequest;
import com.example.banking_sys_with_mongo.service.DaoAccountService;
import com.example.banking_sys_with_mongo.service.DaoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private DaoAccountService accountService;

    @Autowired
    private DaoUserService userService;


    @PostMapping("/")
    public ResponseEntity<AccountDto> createAccount(@RequestBody OpenAccountRequest openAccountRequest) {
        return new ResponseEntity<>(accountService.openAccount(openAccountRequest), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.updateAccount(accountDto), HttpStatus.OK);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber) {
        if (accountService.deleteAccount(accountNumber)) {
            return new ResponseEntity<>("Account with account Number: " + accountNumber + " Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account with account Number: " + accountNumber + " Not Found", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String accountNumber) {
        return new ResponseEntity<>(accountService.getAccount(accountNumber), HttpStatus.OK);
    }


}
