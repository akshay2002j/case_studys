package com.example.banking_sys_with_mongo.service;

import com.example.banking_sys_with_mongo.dto.AccountDto;
import com.example.banking_sys_with_mongo.model.Account;
import com.example.banking_sys_with_mongo.model.User;
import com.example.banking_sys_with_mongo.repository.AccountRepository;
import com.example.banking_sys_with_mongo.repository.UserRepository;
import com.example.banking_sys_with_mongo.utils.AccountHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;
    UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    public AccountDto createAccount(AccountDto accountDto){
        if (accountDto!=null){
              User user = userRepository.findById(accountDto.getUserId()).orElseThrow(
                      ()-> new RuntimeException("User not found")
              );
            Account account = new Account();
            BeanUtils.copyProperties(accountDto,account);
            account.setAccountNumber(AccountHelper.generateAccountNumber());
            account.setUser(user);
            Account savedAccount =   accountRepository.save(account);
            BeanUtils.copyProperties(savedAccount,accountDto);
            return accountDto;
        }
        return null;
    }

    public AccountDto updateAccount(AccountDto accountDto){
        if (accountDto!=null){
            Account account = new Account();
            BeanUtils.copyProperties(accountDto,account);
            accountRepository.save(account);
        }
        return null;
    }

    public String deleteAccount(String accountNumber){
        accountRepository.deleteAccountByAccountNumber(accountNumber);
        return "Account deleted with Number: "+accountNumber;
    }

    public AccountDto getAccount(String accountNumber){
        Account account = accountRepository.findAccountByAccountNumber((accountNumber)).orElseThrow(
                ()-> new RuntimeException("Account not found")
        );
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account,accountDto);
        return accountDto;
    }
}
