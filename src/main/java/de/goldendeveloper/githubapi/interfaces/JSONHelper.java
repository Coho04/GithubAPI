package de.goldendeveloper.githubapi.interfaces;

import org.json.JSONObject;

public interface JSONHelper {

    default String getStringOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? null : jsonObject.getString(key);
    }

    default Integer getIntOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? 0 : jsonObject.getInt(key);
    }

    default boolean getBooleanOrNull(JSONObject jsonObject, String key) {
        return !jsonObject.isNull(key) && jsonObject.getBoolean(key);
    }

    default JSONObject getJSONObjectOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? null : jsonObject.getJSONObject(key);
    }
}
