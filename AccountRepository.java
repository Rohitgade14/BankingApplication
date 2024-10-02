package com.sbi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbi.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
