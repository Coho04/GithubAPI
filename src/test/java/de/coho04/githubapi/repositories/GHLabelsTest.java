package de.coho04.githubapi.repositories;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GHLabelsTest {

    @Test
    void shouldReturnCorrectId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 123);

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals(123, ghLabels.getId());
    }

    @Test
    void shouldReturnCorrectUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "http://test.com");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("http://test.com", ghLabels.getUrl());
    }

    @Test
    void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Test Name");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("Test Name", ghLabels.getName());
    }

    @Test
    void shouldReturnCorrectColor() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", "ffffff");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("ffffff", ghLabels.getColor());
    }

    @Test
    void shouldReturnCorrectNodeId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("node_id", "abc123");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("abc123", ghLabels.getNodeId());
    }

    @Test
    void shouldReturnCorrectDefaultStatus() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("default", true);

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertTrue(ghLabels.isDefault());
    }

    @Test
    void shouldReturnCorrectDescription() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", "Test Description");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("Test Description", ghLabels.getDescription());
    }
}