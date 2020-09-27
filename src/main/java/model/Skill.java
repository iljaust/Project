package model;

import com.google.gson.annotations.SerializedName;

public class Skill {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;

    public Skill(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Skill() {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Override
    public String toString(){

        return id+"."+name;
    }

}

