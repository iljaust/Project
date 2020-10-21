package com.iljaust.project;

import com.iljaust.project.repository.AccountRepository;
import com.iljaust.project.repository.json.JsonAccountRepositoryImpl;
import com.iljaust.project.view.DeveloperView;

public class Main {
    public static void main(String[] args) {

        DeveloperView developerRepository = new DeveloperView();
        AccountRepository accountRepository = new JsonAccountRepositoryImpl();

        developerRepository.getAll();



    }
}
