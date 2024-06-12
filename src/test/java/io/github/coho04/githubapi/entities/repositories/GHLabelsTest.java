package io.github.coho04.githubapi.entities.repositories;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GHLabelsTest {

    @Test
    void shouldReturnCorrectId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 123);

        GHLabel ghLabel = new GHLabel(jsonObject);

        assertEquals(123, ghLabel.getId());
    }

    @Test
    void shouldReturnCorrectUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "https://test.com");

        GHLabel ghLabel = new GHLabel(jsonObject);

        assertEquals("https://test.com", ghLabel.getUrl());
    }

    @Test
    void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Test Name");

        GHLabel ghLabel = new GHLabel(jsonObject);

        assertEquals("Test Name", ghLabel.getName());
    }

    @Test
    void shouldReturnCorrectColor() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", "ffffff");

        GHLabel ghLabel = new GHLabel(jsonObject);

        assertEquals("ffffff", ghLabel.getColor());
    }

    @Test
    void shouldReturnCorrectNodeId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("node_id", "abc123");

        GHLabel ghLabel = new GHLabel(jsonObject);

        assertEquals("abc123", ghLabel.getNodeId());
    }

    @Test
    void shouldReturnCorrectDefaultStatus() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("default", true);

        GHLabel ghLabel = new GHLabel(jsonObject);

        assertTrue(ghLabel.isDefault());
    }

    @Test
    void shouldReturnCorrectDescription() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", "Test Description");

        GHLabel ghLabel = new GHLabel(jsonObject);

        assertEquals("Test Description", ghLabel.getDescription());
    }
}