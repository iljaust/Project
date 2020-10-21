package com.iljaust.project.view;

import com.iljaust.project.controller.DeveloperController;
import com.iljaust.project.model.Developer;


import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private DeveloperController controller = new DeveloperController();
    private Scanner scan = new Scanner(System.in);

    public void deleteById(){
        Long inputID = scan.nextLong();
        controller.deleteById(inputID);
    }

    public void save(){
        String developerName = scan.nextLine();
        Developer developer = new Developer.DeveloperBuilder(developerName,controller.generateID()).build();
        controller.save(developer);
    }

    public void getByIdDev(){
        Long inputID = scan.nextLong();
        Developer developer = controller.getById(inputID);
        System.out.println(developer);
    }

    public void update(){
        long id = scan.nextLong();
        String developerName = scan.nextLine();
        controller.update(new Developer.DeveloperBuilder(developerName,id).build());
    }

    public void getAll(){
        List<Developer> developers = controller.getAll();

        developers.forEach(System.out::println);
    }
}
