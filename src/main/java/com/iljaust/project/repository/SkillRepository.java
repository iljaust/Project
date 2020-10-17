package com.iljaust.project.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iljaust.project.model.Skill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SkillRepository implements GenSkillRepository {
    private final String SKILLS_FILE_PATH = "./src/main/resources/skills.json";
    private final Gson gson = new Gson();
    private final File skillFile;


    public SkillRepository() {
        this.skillFile = new File(SKILLS_FILE_PATH);
    }

    private List<Skill> fromFileToArray() {
        try {
            FileReader reader = new FileReader(skillFile);
            return gson.fromJson(reader, new TypeToken<List<Skill>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void fromArrayToFile(List<Skill> skills) {
        try (Writer writer = new FileWriter(SKILLS_FILE_PATH)) {
            gson.toJson(skills, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Skill save(Skill skill) {
        List<Skill> skills = fromFileToArray();
        skills.add(skill)
        ;
        fromArrayToFile(skills);
        return skill;
    }

    public void deleteById(Long id) {
        List<Skill> skills = fromFileToArray();
        skills.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
        fromArrayToFile(skills);
    }

    public Skill update(Skill skill) {
        List<Skill> skills = fromFileToArray();
        skills.removeIf(n -> n.getId() == skill.getId());
        skills.add(skill)
        ;
        fromArrayToFile(skills);
        return skill;
    }

    public Skill getById(Long id) {
        List<Skill> skill = fromFileToArray();
        return skill.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public List<Skill> getAll() {
        return fromFileToArray();
    }

    public long generateID() {
        List<Skill> skills = fromFileToArray();
        if (skills == null) {
            return 1;
        }
        return skills.stream().max(Comparator.comparingLong(Skill::getId)).orElse(new Skill()).getId();
    }

}