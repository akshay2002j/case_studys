//package com.example.banking_sys_with_mongo.service;
//
//import com.example.banking_sys_with_mongo.dto.AccountDto;
//import com.example.banking_sys_with_mongo.dto.requestdto.OpenAccountRequest;
//import com.example.banking_sys_with_mongo.exception.AccountException;
//import com.example.banking_sys_with_mongo.exception.ExceptionType;
//import com.example.banking_sys_with_mongo.model.Account;
//import com.example.banking_sys_with_mongo.model.User;
//import com.example.banking_sys_with_mongo.repository.AccountRepository;
//import com.example.banking_sys_with_mongo.repository.UserRepository;
//import com.example.banking_sys_with_mongo.utils.AccountHelper;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AccountService {
//
//    AccountRepository accountRepository;
//    UserRepository userRepository;
//
//
//    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
//        this.accountRepository = accountRepository;
//        this.userRepository = userRepository;
//    }
//
//
//    public AccountDto createAccount(OpenAccountRequest openAccountRequest) {
//        if (openAccountRequest != null) {
//            User user = userRepository.findById(openAccountRequest.getUserId()).orElseThrow(
//                    () -> new AccountException("User Not Found"));
//            Account account = new Account();
//            BeanUtils.copyProperties(openAccountRequest, account);
//            account.setAccountNumber(AccountHelper.generateAccountNumber());
//            account.setUser(user);
//            Account savedAccount = accountRepository.save(account);
//            AccountDto accountDto = new AccountDto();
//            BeanUtils.copyProperties(savedAccount, accountDto);
//            accountDto.setUser(savedAccount.getUser().getUserId());
//            return accountDto;
//        }
//        return null;
//    }
//
//    public AccountDto updateAccount(AccountDto accountDto) {
//        if (accountDto != null) {
//            Account account = new Account();
//            BeanUtils.copyProperties(accountDto, account);
//            accountRepository.save(account);
//        }
//        return null;
//    }
//
//    public String deleteAccount(String accountNumber) {
//        accountRepository.deleteAccountByAccountNumber(accountNumber);
//        return "Account deleted with Number: " + accountNumber;
//    }
//
//    public AccountDto getAccount(String accountNumber) {
//        Account account = accountRepository.findAccountByAccountNumber((accountNumber)).orElseThrow(
//                () -> new AccountException("Account not found"));
//        AccountDto accountDto = new AccountDto();
//        BeanUtils.copyProperties(account, accountDto);
//        return accountDto;
//    }
//
//
//}
