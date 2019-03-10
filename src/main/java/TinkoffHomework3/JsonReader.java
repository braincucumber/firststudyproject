package TinkoffHomework3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonReader {
    public List<Person> getPersons(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<Person>>(){}.getType());
    }
}
