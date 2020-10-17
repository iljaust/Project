package com.iljaust.project.controller;

import com.iljaust.project.model.Account;
import com.iljaust.project.repository.AccountRepository;

import java.util.List;

public class AccountController {
    AccountRepository accountRepository = new AccountRepository();

    public List<Account> getAll(){
        return accountRepository.getAll();
    }

    public Account getById(Long id) {
        return accountRepository.getById(id);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account update(Account account) {
        return accountRepository.update(account);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public long generateID(){
        return accountRepository.generateID() + 1;
    }


}
