package com.iljaust.project.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.iljaust.project.model.Developer;

import java.lang.reflect.Type;

public class DeveloperSerializer implements JsonSerializer<Developer> {
    @Override
    public JsonElement serialize(Developer developer, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("id",developer.getId());
        result.addProperty("name",developer.getName());
        result.add("skills", jsonSerializationContext.serialize(developer.getSkillSet()));
        result.add("Account", jsonSerializationContext.serialize(developer.getAccountStatus()));

        return result;
    }
}
