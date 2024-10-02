package com.sbi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.entities.Account;
import com.sbi.service.AccountService;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	     @Autowired
	    private AccountService accountService;
	     @GetMapping
    public List<Account> getAllAccounts() {
    	return accountService.getAllAccounts();
    }
      
      @GetMapping("/{id}")
       public ResponseEntity<Account> getAccountsById(@PathVariable Long id) {
		return accountService.getAccountById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() ->ResponseEntity.notFound().build());
	}
      @PostMapping
      public Account createAccount(@RequestBody Account account) {
  		return accountService.saveAccount(account);
  	}
      @PutMapping("/{id}")
      public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails){
    	  return ResponseEntity.ok(accountService.updateAccount(id, accountDetails));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
          accountService.deleteAccount(id);
          return ResponseEntity.noContent().build();
      }
      @PatchMapping("/{id}/addBalance")
      public ResponseEntity<Account> addBalance(@PathVariable Long id, @RequestParam Double amount) {
          Account updatedAccount = accountService.addBalance(id, amount);
          return ResponseEntity.ok(updatedAccount);
      }
      
  
      @PatchMapping("/{id}/subtractBalance")
      public ResponseEntity<Account> subtractBalance( @PathVariable Long id,  @RequestParam Double amount) {
          Account updatedAccount = accountService.subtractBalance(id, amount);
          return ResponseEntity.ok(updatedAccount);
      }
}

