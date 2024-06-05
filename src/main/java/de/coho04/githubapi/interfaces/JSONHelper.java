package de.coho04.githubapi.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public interface JSONHelper {

    default String getStringOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? null : jsonObject.getString(key);
    }

    default Integer getIntOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? 0 : jsonObject.getInt(key);
    }

    default Long getLongOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? 0L : jsonObject.getLong(key);
    }

    default boolean getBooleanOrNull(JSONObject jsonObject, String key) {
        return !jsonObject.isNull(key) && jsonObject.getBoolean(key);
    }

    default JSONObject getJSONObjectOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? null : jsonObject.getJSONObject(key);
    }

    default OffsetDateTime getLocalDateOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? null : OffsetDateTime.parse(jsonObject.getString(key));
    }

    default <T> List<T> getArrayOrNull(JSONObject jsonObject, String key, JSONToObject<T> factory) {
        if (jsonObject.isNull(key)) {
            return null;
        }
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(factory.apply(jsonArray.getJSONObject(i)));
        }
        return list;
    }

    @FunctionalInterface
    interface JSONToObject<T> {
        T apply(JSONObject jsonObject);
    }
}
