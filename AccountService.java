package com.sbi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbi.entities.Account;
import com.sbi.exception.ResourceNotFoundException;
import com.sbi.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> getAllAccounts() {
	    return accountRepository.findAll();
	}
	public Optional<Account> getAccountById( Long id) {
		return accountRepository.findById(id);
	}
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
		
	}
	
	public Account updateAccount(Long id,Account accountDetails) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		account.setFirstName(accountDetails.getFirstName());
		account.setLastName(accountDetails.getLastName());
		account.setBalance(accountDetails.getBalance());
		account.setEmailId(accountDetails.getEmailId());
		account.setPassword(accountDetails.getPassword());
		return accountRepository.save(account);
		
	}
	public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }
	
	  //patch //http://localhost:8080/api/accounts/2/addBalance?amount=0.37
	public  Account addBalance(Long id,Double amount)
	{
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		account.setBalance(account.getBalance()+amount);
		return accountRepository.save(account);
	}
	 //patch //http://localhost:8080/api/accounts/2/subtractBalance?amount=0.37
	public Account subtractBalance(Long id, Double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        
        double newBalance = account.getBalance() - amount;
        
        if (newBalance < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }
	
}
