package com.iljaust.project.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iljaust.project.model.Account;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepository implements GenAccountRepository{
    private final String ACCOUNT_FILE_PATH = "./src/main/resources/accounts.json";
    Gson gson = new Gson();
    private final File accountFile;

    public AccountRepository(){
        this.accountFile = new File(ACCOUNT_FILE_PATH);
    }

    private List<Account> fromFileToArray() {
        try {
            FileReader reader = new FileReader(accountFile);
            return gson.fromJson(reader, new TypeToken<List<Account>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void fromArrayToFile(List<Account> accounts) {
        try (Writer writer = new FileWriter(ACCOUNT_FILE_PATH)) {
            gson.toJson(accounts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Account> getAll(){
        return fromFileToArray();
    }


    @Override
    public Account getById(Long id) {
        List<Account> accounts = fromFileToArray();
        return accounts.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Account save(Account account) {
        List<Account> accounts = fromFileToArray();
        accounts.add(account);
        fromArrayToFile(accounts);
        return account;
    }

    @Override
    public Account update(Account account) {
        List<Account> accounts = fromFileToArray();
        accounts.removeIf(n -> n.getId() == account.getId());
        accounts.add(account);
        fromArrayToFile(accounts);
        return account;
    }

    @Override
    public void deleteById(Long id) {
        List<Account> accounts = fromFileToArray();
        accounts.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
        fromArrayToFile(accounts);

    }

    public long generateID() {
        List<Account> accounts = fromFileToArray();
        if (accounts == null) {
            return 0;
        }
        return accounts.stream().max(Comparator.comparingLong(Account::getId)).orElse(new Account((long)0,null,null)).getId();
    }
}
