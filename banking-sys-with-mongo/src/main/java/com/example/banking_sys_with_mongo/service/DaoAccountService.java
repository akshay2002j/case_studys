package com.example.banking_sys_with_mongo.service;


import com.example.banking_sys_with_mongo.dao.AccountDaoImpl;
import com.example.banking_sys_with_mongo.dao.UserDaoImpl;
import com.example.banking_sys_with_mongo.dto.AccountDto;
import com.example.banking_sys_with_mongo.dto.requestdto.OpenAccountRequest;
import com.example.banking_sys_with_mongo.exception.AccountException;
import com.example.banking_sys_with_mongo.exception.ExceptionType;
import com.example.banking_sys_with_mongo.model.Account;
import com.example.banking_sys_with_mongo.model.User;
import com.example.banking_sys_with_mongo.utils.AccountHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DaoAccountService {


    AccountDaoImpl accountDao;

    UserDaoImpl userDaoImpl;

    public DaoAccountService(AccountDaoImpl accountDao, UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
        this.accountDao = accountDao;
    }


    public AccountDto openAccount(OpenAccountRequest openAccountRequest) {
        User user = userDaoImpl.getUserById(openAccountRequest.getUserId());
        Account account = new Account();
        account.setUser(user.getUserId());
        account.setAccountNumber(AccountHelper.generateAccountNumber());
        account.setBalance(openAccountRequest.getBalance());
        account.setCreatedAt(new Date());
        account.setUpdatedAt(new Date());
        Account savedAccount = accountDao.saveAccount(account);
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(savedAccount, accountDto);
        return accountDto;
    }

    public AccountDto getAccount(String accountNumber) {
        Account account = accountDao.findByAccountNumber(accountNumber);
        if (account != null) {
            AccountDto accountDto = new AccountDto();
            BeanUtils.copyProperties(account, accountDto);
            return accountDto;
        }
        else {
            throw  new AccountException(ExceptionType.ACCOUNT_NOT_FOUND);
        }
    }

    public AccountDto updateAccount(AccountDto accountDto) {
        User user = userDaoImpl.getUserById(accountDto.getUser());
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setUser(user.getUserId());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBalance(accountDto.getBalance());
        account.setUpdatedAt(new Date());
        Account savedAccount = accountDao.updateAccount(account);
        BeanUtils.copyProperties(savedAccount, accountDto);
        return accountDto;
    }

    public boolean deleteAccount(String accountNumber) {
        return accountDao.deleteByAccountNumber(accountNumber);
    }
//
//    public List<AccountDto> getAllAccountsByUserId(String userId) {
//
//    }

}