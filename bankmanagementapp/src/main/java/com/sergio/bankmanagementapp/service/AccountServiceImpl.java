package com.sergio.bankmanagementapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergio.bankmanagementapp.entity.Account;
import com.sergio.bankmanagementapp.repository.AccountRepository;

import javax.swing.text.html.Option;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> accountfindById = accountRepository.findById(accountNumber);
        if (accountfindById.isEmpty()){
            throw new RuntimeException("Account isn't present :'D ");
        }
        return accountfindById.get();
    }

    @Override
    public List<Account> getAllAccountDetails() {
        return accountRepository.findAll();
    }

    // Actualizando nuestro monto
    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if (accountOptional.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = accountOptional.get();
        double totalBalance = accountPresent.getAccount_balance() + amount;
        accountPresent.setAccount_balance(totalBalance);
        accountRepository.save(accountPresent);

        return accountPresent;
    }

    @Override
    public Account withDrawAmount(Long accountNumber, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if (accountOptional.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = accountOptional.get();
        double accountBalance = accountPresent.getAccount_balance() - amount;
        accountPresent.setAccount_balance(accountBalance);
        accountRepository.save(accountPresent);
        return accountPresent;
    }

    @Override
    public void deleteAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber);
        accountRepository.deleteById(accountNumber);
    }

}
