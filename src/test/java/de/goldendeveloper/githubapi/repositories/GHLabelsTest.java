package de.goldendeveloper.githubapi.repositories;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GHLabelsTest {

    @Test
    public void shouldReturnCorrectId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 123);

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals(123, ghLabels.getId());
    }

    @Test
    public void shouldReturnCorrectUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "http://test.com");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("http://test.com", ghLabels.getUrl());
    }

    @Test
    public void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Test Name");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("Test Name", ghLabels.getName());
    }

    @Test
    public void shouldReturnCorrectColor() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", "ffffff");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("ffffff", ghLabels.getColor());
    }

    @Test
    public void shouldReturnCorrectNodeId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("node_id", "abc123");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("abc123", ghLabels.getNodeId());
    }

    @Test
    public void shouldReturnCorrectDefaultStatus() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("default", true);

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertTrue(ghLabels.isDefault());
    }

    @Test
    public void shouldReturnCorrectDescription() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", "Test Description");

        GHLabels ghLabels = new GHLabels(jsonObject);

        assertEquals("Test Description", ghLabels.getDescription());
    }
}