package io.github.coho04.githubapi.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONHelperTest implements JSONHelper {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        JSONArray array = new JSONArray();
        array.put(new JSONObject().put("arrayObjectKey", "arrayObjectValue"));
        jsonObject = new JSONObject();
        jsonObject.put("stringKey", "stringValue");
        jsonObject.put("intKey", 123);
        jsonObject.put("longKey", 123456789012345L);
        jsonObject.put("booleanKey", true);
        jsonObject.put("dateKey", "2007-12-03T10:15:30+01:00");
        jsonObject.put("jsonObjectKey", new JSONObject("nestedKey", "nestedValue"));
        jsonObject.put("jsonArrayKey", array);
    }

    @Test
    void getStringOrNullTest() {
        assertEquals("stringValue", getStringOrNull(jsonObject, "stringKey"));
        assertNull(getStringOrNull(jsonObject, "nonExistentKey"));
    }

    @Test
    void getIntOrNullTest() {
        assertEquals(123, getIntOrNull(jsonObject, "intKey"));
        assertEquals(0, getIntOrNull(jsonObject, "nonExistentKey"));
    }

    @Test
    void getLongOrNullTest() {
        assertEquals(123456789012345L, getLongOrNull(jsonObject, "longKey"));
        assertEquals(0L, getLongOrNull(jsonObject, "nonExistentKey"));
    }

    @Test
    void getBooleanOrNullTest() {
        assertTrue(getBooleanOrNull(jsonObject, "booleanKey"));
        assertFalse(getBooleanOrNull(jsonObject, "nonExistentKey"));
    }

    @Test
    void getJSONObjectOrNullTest() {
        assertNotNull(getJSONObjectOrNull(jsonObject, "jsonObjectKey"));
        assertNull(getJSONObjectOrNull(jsonObject, "nonExistentKey"));
    }

    @Test
    void getLocalDateOrNullTest() {
        assertEquals(OffsetDateTime.parse("2007-12-03T10:15:30+01:00"), getLocalDateOrNull(jsonObject, "dateKey"));
        assertNull(getLocalDateOrNull(jsonObject, "nonExistentKey"));
    }

    @Test
    void getArrayOrNullTest() {
        List<String> resultList = getArrayOrNull(jsonObject, "jsonArrayKey", jsonObject -> jsonObject.getString("arrayObjectKey"));
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals("arrayObjectValue", resultList.get(0));
        List<String> list = getArrayOrNull(jsonObject, "nonExistentKey", jsonObject -> jsonObject.getString("arrayObjectKey"));
        assertTrue(list.isEmpty());
    }
}