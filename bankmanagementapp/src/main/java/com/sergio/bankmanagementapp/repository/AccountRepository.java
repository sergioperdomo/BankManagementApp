package com.sergio.bankmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergio.bankmanagementapp.entity.Account;

public interface AccountRepository  extends JpaRepository< Account, Long>{

}
