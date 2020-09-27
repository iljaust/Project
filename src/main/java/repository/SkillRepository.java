package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.Skill;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SkillRepository implements GenSkillRepository{
    private final Gson gson = new Gson();
    private File SkillFile;
    private FileReader reader;



    private List<Skill> fromFileToArray() {
        try {
            SkillFile = new File(Thread.currentThread().getContextClassLoader().getResource("skills.json").toURI());
            reader = new FileReader(SkillFile);

        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Skill> skills = gson.fromJson(reader, new TypeToken<List<Skill>>() {}.getType());

        return skills;

    }

    private void fromArrayToFile(List<Skill> skills){
        try {
            SkillFile = new File(Thread.currentThread().getContextClassLoader().getResource("skills.json").toURI());
            gson.toJson(skills, new FileWriter(SkillFile));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Skill save(Skill skill){
        List<Skill> skills = fromFileToArray();
        skills.add(skill);
        fromArrayToFile(skills);
        return skill;
    }

    public void deleteById(Long id){
        List<Skill> skills = fromFileToArray();
        skills.stream()
                .filter(e -> e.getId()!= id)
                .collect(Collectors.toList());
        fromArrayToFile(skills);
    }

    public Skill update(Skill skill){
        List<Skill> skills = fromFileToArray();
        skills.removeIf(n ->n.getId() == skill.getId());
        skills.add(skill);
        fromArrayToFile(skills);
        return skill;
    }

    public Skill getById(Long id) {
        List <Skill> skill = fromFileToArray();
        return skill.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public List<Skill> getAll(){
        return fromFileToArray();
    }

    public long generateID(){
        List<Skill> skills = fromFileToArray();

        if(skills == null) return 1;

        Skill highestID = skills.stream().max(Comparator.comparingLong(Skill::getId)).get();
        return highestID.getId() + 1;
    }

}
