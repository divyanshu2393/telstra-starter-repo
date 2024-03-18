package au.com.telstra.simcardactivator.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

public class JsonUtil {

    private JsonUtil() {

    }

    @Getter
    private static final Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public static String getJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T getObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

}

