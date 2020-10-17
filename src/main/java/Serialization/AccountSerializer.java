package Serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.Account;

import java.lang.reflect.Type;

public class AccountSerializer implements JsonSerializer<Account> {
    @Override
    public JsonElement serialize(Account account, Type type, JsonSerializationContext jsonSerializationContext) {

        return new JsonPrimitive(account.getId());
    }
}
