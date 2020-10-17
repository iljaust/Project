package com.iljaust.project.serialization;

import com.google.gson.*;
import com.iljaust.project.model.Skill;

import java.lang.reflect.Type;

public class SkillSerializer implements JsonSerializer<Skill> {

    @Override
    public JsonElement serialize(Skill skill, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(skill.getId());
    }
}
