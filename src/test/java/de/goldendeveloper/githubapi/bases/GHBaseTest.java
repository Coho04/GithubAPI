package de.goldendeveloper.githubapi.bases;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHBaseTest {

    @Test
    void shouldReturnEmptyJSONObject() {
        GHBase ghBase = new GHBase();
        JSONObject jsonObject = ghBase.toJSONObject();
        assertTrue(jsonObject.isEmpty());
    }

    @Test
    void shouldReturnCorrectBaseUrl() {
        String baseUrl = GHBase.getBaseUrl();
        assertEquals("https://api.github.com", baseUrl);
    }
}