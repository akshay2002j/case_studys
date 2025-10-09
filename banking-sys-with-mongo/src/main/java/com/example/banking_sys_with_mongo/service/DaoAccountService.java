package com.example.banking_sys_with_mongo.service;


import com.example.banking_sys_with_mongo.dao.AccountDaoImpl;
import com.example.banking_sys_with_mongo.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoAccountService {


    AccountDaoImpl account;

    public DaoAccountService(AccountDaoImpl account) {
        this.account=account;
    }



}