package serialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Skill;
import repository.SkillRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SkillDeserializer implements JsonDeserializer<List<Skill>> {
    @Override
    public List<Skill> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        SkillRepository repository = new SkillRepository();
        List<Skill> skillList = repository.getAll();

        Gson gson = new Gson();


        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray id = jsonObject.get("skills").getAsJsonArray();

        Type t = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> idList = gson.fromJson(id, t);

        List<Skill> skills = new ArrayList<>();

        for (int i = 0; i < idList.size(); i++) {
            for (int j = 0; j < skillList.size(); j++) {
                 if (idList.get(i) == skillList.get(j).getId()){
                     skills.add(skillList.get(j));
                 }

            }

        }


        return skills;
    }
}
