package com.iljaust.project.controller;

import com.iljaust.project.model.Developer;
import com.iljaust.project.repository.DeveloperRepository;

import java.util.List;

public class DeveloperController {
    DeveloperRepository repository = new DeveloperRepository();

    public void deleteById(long id){
        repository.deleteById(id);
    }

    public Developer save(Developer developer){
        repository.save(developer);
        return developer;
    }

    public Developer getById(long id){
        return repository.getById(id);
    }

    public List<Developer> getAll(){
        return repository.getAll();
    }

    public Developer update(Developer developer){
        return repository.update(developer);
    }

    public long generateID(){
        return repository.generateID();
    }
}
