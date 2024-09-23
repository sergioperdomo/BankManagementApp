package com.sergio.bankmanagementapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sergio.bankmanagementapp.entity.Account;
import com.sergio.bankmanagementapp.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    // EN el controlador es en realidad donde se crean las APIS


    @Autowired
    AccountService accountService;

    // Create the account
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account createAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }

    // Obteniendo los detalles de la cuenta, es decir, de una en especifico:
    @GetMapping("/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable Long accountNumber) {
        Account account = accountService.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }

    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountDetails() {
        return accountService.getAllAccountDetails();
    }

    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAmount(@PathVariable Long accountNumber,@PathVariable Double amount){
        return accountService.depositAmount(accountNumber, amount);
    }

    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withDrawAmount(@PathVariable Long accountNumber, @PathVariable Double amount){
        return accountService.withDrawAmount(accountNumber, amount);
    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String>  deleteAccount(@PathVariable Long accountNumber){
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account closed");
    }

}
