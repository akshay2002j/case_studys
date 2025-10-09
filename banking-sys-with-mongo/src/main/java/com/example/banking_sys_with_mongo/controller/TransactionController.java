package com.example.banking_sys_with_mongo.controller;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

//    @Autowired
//    private TransactionService transactionService;
//
//    @PostMapping("/deposit")
//    public ResponseEntity<TransactionDto> makeDepositTransaction(@RequestBody DepositRequest depositRequest) {
//        return new ResponseEntity<>(transactionService.depositTransaction(depositRequest), HttpStatus.CREATED);
//    }
//    @PostMapping("/withdraw")
//    public ResponseEntity<TransactionDto> makeWithdrawTransaction(@RequestBody WithdrawRequest withdrawRequest) {
//        return new ResponseEntity<>(transactionService.withdrawTransaction(withdrawRequest), HttpStatus.CREATED);
//    }
//
//    @PostMapping("/transfer")
//    public ResponseEntity<TransactionDto> makeTransferTransaction(@RequestBody TransferRequest transferRequest) {
//        return new ResponseEntity<>(transactionService.transferTransaction(transferRequest), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
//
//        return new ResponseEntity<>(transactionService.deleteTransaction(id), HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<TransactionInfoResponse> getTransaction(@PathVariable String id) {
//        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
//    }
//
//    @GetMapping("/list/{accountNumber}")
//    public ResponseEntity <List<TransactionDto>> getTransactionListByAccountNumber(@PathVariable String accountNumber) {
//        return new ResponseEntity<List<TransactionDto>>(transactionService.getTransactionsByAccountNumber(accountNumber), HttpStatus.OK);
//    }
}
