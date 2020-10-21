package com.iljaust.project.model;

import java.util.List;

public class Developer {
    private Long id;
    private String name;
    private List<Skill> skillSet;
    private Account accountStatus;


    public Developer(DeveloperBuilder builder){
        this.name = builder.name;
        this.id = builder.id;
        this.skillSet = builder.skillSet;
        this.accountStatus = builder.accountStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id){ this.id = id;}

    public String getName() {
        return name;
    }

    public List<Skill> getSkillSet(){return skillSet;}

    public Account getAccountStatus(){return accountStatus;}

    public static class DeveloperBuilder
    {
        private final String name;
        private final Long id;
        private List<Skill> skillSet;
        private Account accountStatus;

        public DeveloperBuilder(String name, Long id){
            this.name = name;
            this.id = id;
        }

        public DeveloperBuilder skillSet(List<Skill> skillSet){
            this.skillSet = skillSet;
            return this;
        }

        public DeveloperBuilder accountStatus(Account accountStatus){
            this.accountStatus = accountStatus;
            return this;
        }

        public Developer build(){
            return new Developer(this);
        }

    }

    @Override
    public String toString() {
        return "Developer : \n" +
                "id=" + id +
                ", \nname='" + name + '\'' +
                ", \nskillSet=" + skillSet +
                ", \naccountStatus=" + accountStatus +
                ' ';
    }
}
