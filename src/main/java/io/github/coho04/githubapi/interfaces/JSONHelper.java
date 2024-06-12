package io.github.coho04.githubapi.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface provides helper methods for working with JSON objects.
 */
public interface JSONHelper {

    /**
     * Returns the string value of the specified key in the JSON object, or null if the key is not present.
     *
     * @param jsonObject the JSON object
     * @param key        the key
     * @return the string value or null
     */
    default String getStringOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? null : jsonObject.getString(key);
    }

    /**
     * Returns the integer value of the specified key in the JSON object, or 0 if the key is not present.
     *
     * @param jsonObject the JSON object
     * @param key        the key
     * @return the integer value or 0
     */
    default Integer getIntOrNull(JSONObject jsonObject, String key) {
        if (!jsonObject.has(key)) {
            return 0;
        }
        return jsonObject.isNull(key) ? 0 : jsonObject.getInt(key);
    }

    /**
     * Returns the long value of the specified key in the JSON object, or 0 if the key is not present.
     *
     * @param jsonObject the JSON object
     * @param key        the key
     * @return the long value or 0
     */
    default Long getLongOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? 0L : jsonObject.getLong(key);
    }

    /**
     * Returns the boolean value of the specified key in the JSON object, or false if the key is not present.
     *
     * @param jsonObject the JSON object
     * @param key        the key
     * @return the boolean value or false
     */
    default boolean getBooleanOrNull(JSONObject jsonObject, String key) {
        return !jsonObject.isNull(key) && jsonObject.getBoolean(key);
    }

    /**
     * Returns the JSON object value of the specified key in the JSON object, or null if the key is not present.
     *
     * @param jsonObject the JSON object
     * @param key        the key
     * @return the JSON object value or null
     */
    default JSONObject getJSONObjectOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? null : jsonObject.getJSONObject(key);
    }

    /**
     * Returns the OffsetDateTime value of the specified key in the JSON object, or null if the key is not present.
     *
     * @param jsonObject the JSON object
     * @param key        the key
     * @return the OffsetDateTime value or null
     */
    default OffsetDateTime getLocalDateOrNull(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? null : OffsetDateTime.parse(jsonObject.getString(key));
    }

    /**
     * Returns a list of objects of type T, created from a JSON array in the JSON object using the provided factory method.
     * If the key is not present, returns null.
     *
     * @param jsonObject the JSON object
     * @param key        the key
     * @param factory    the factory method to create objects of type T from a JSON object
     * @return the list of objects or null
     */
    default <T> List<T> getArrayOrNull(JSONObject jsonObject, String key, JSONToObject<T> factory) {
        List<T> list = new ArrayList<>();
        if (jsonObject.isNull(key)) {
            return list;
        }
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(factory.apply(jsonArray.getJSONObject(i)));
        }
        return list;
    }

    default List<String> getJSONArrayToStringList(JSONObject jsonObject, String key) {
        List<String> list = new ArrayList<>();
        if (jsonObject.isNull(key)) {
            return list;
        }
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        if (jsonArray.isEmpty()) {
            return list;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }


    /**
     * This functional interface defines a method to create an object of type T from a JSON object.
     */
    @FunctionalInterface
    interface JSONToObject<T> {
        /**
         * Creates an object of type T from a JSON object.
         *
         * @param jsonObject the JSON object
         * @return the created object
         */
        T apply(JSONObject jsonObject);
    }
}