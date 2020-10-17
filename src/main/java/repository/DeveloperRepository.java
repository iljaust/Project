package repository;

import serialization.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.*;


import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperRepository implements GenDeveloperRepository{
    private final String DEVELOPER_FILE_PATH = "./src/main/resources/developers.json";
    private final File developerFile;

    public DeveloperRepository() {
        this.developerFile = new File(DEVELOPER_FILE_PATH);
    }

    private List<Developer> fromFileToArray() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Developer.class, new DeveloperDeserializer())
                .registerTypeAdapter(Skill.class, new SkillDeserializer())
                .registerTypeAdapter(Account.class, new AccountDeserializer())
                .create();
        try {
            FileReader reader = new FileReader(developerFile);
            return gson.fromJson(reader, new TypeToken<List<Developer>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void fromArrayToFile(List<Developer> developers) {
         Gson gson = new GsonBuilder()
                 .setPrettyPrinting()
                 .registerTypeAdapter(Developer.class, new DeveloperSerializer())
                 .registerTypeAdapter(Skill.class, new SkillSerializer())
                 .registerTypeAdapter(Account.class, new AccountSerializer())
                 .create();

        try (Writer writer = new FileWriter(DEVELOPER_FILE_PATH)) {
            gson.toJson(developers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(Long id) {
        List<Developer> developers = fromFileToArray();
        return developers.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Developer save(Developer developer) {
        List<Developer> developers= fromFileToArray();
        developers.add(developer);
        fromArrayToFile(developers);

        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> developers = fromFileToArray();
        developers.removeIf(n -> n.getId() == developer.getId());
        developers.add(developer);

        fromArrayToFile(developers);
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> developers = fromFileToArray();
        developers.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
        fromArrayToFile(developers);

    }

    public List<Developer> getAll() {
        return fromFileToArray();
    }

    public long generateID() {
        List<Developer> developers = fromFileToArray();

        return developers.stream().max(Comparator.comparingLong(Developer::getId)).orElse(new Developer(new Developer.DeveloperBuilder(null,(long)0))).getId();
    }
}


