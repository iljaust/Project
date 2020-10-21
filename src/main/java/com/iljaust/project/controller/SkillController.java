package com.iljaust.project.controller;

import com.iljaust.project.model.Skill;
import com.iljaust.project.repository.SkillRepository;
import com.iljaust.project.repository.json.JsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private SkillRepository repository =new JsonSkillRepositoryImpl();

    public void deleteById(long id){
        repository.deleteById(id);
    }

    public Skill save(Skill skill){
        repository.save(skill);
        return skill;
    }

    public Skill getById(long id){
        return repository.getById(id);
    }

    public List<Skill> getAll(){
        return repository.getAll();
    }

    public Skill update(Skill skill){
        return repository.update(skill);
    }

    public long generateID(){
        return repository.generateID();
    }

}
