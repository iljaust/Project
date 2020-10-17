package com.iljaust.project.serialization;

import com.google.gson.*;
import com.iljaust.project.model.Account;
import com.iljaust.project.model.Developer;
import com.iljaust.project.model.Skill;

import java.lang.reflect.Type;

public class DeveloperDeserializer implements JsonDeserializer<Developer> {
    @Override
    public Developer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        Developer developer = new Developer.DeveloperBuilder(jsonObj.get("name").getAsString(),jsonObj.get("id").getAsLong())
                .skillSet(jsonDeserializationContext.deserialize(jsonObj, Skill.class))
                .accountStatus(jsonDeserializationContext.deserialize(jsonObj, Account.class))
                .build();

        return developer;
    }
}
