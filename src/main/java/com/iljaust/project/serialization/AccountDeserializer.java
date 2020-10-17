package com.iljaust.project.serialization;

import com.google.gson.*;
import com.iljaust.project.model.Account;
import com.iljaust.project.repository.AccountRepository;

import java.lang.reflect.Type;
import java.util.List;

public class AccountDeserializer implements JsonDeserializer<Account> {
    @Override
    public Account deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        AccountRepository rep = new AccountRepository();
        List<Account> accounts = rep.getAll();

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Long id = jsonObject.get("Account").getAsLong();

        Account acc = accounts.stream().filter(account -> id == account.getId()).findAny().orElse(null);

        return acc;
    }
}
