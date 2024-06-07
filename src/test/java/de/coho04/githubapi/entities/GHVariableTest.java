package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHVariableTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("name", "Variable1");
        jsonObject.put("value", "Value1");
        jsonObject.put("created_at", "2022-01-01T00:00:00Z");
        jsonObject.put("updated_at", "2022-01-02T00:00:00Z");
        jsonObject.put("visibility", "private");
        jsonObject.put("selected_repositories_url", "https://github.com/repos/1");
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals("Variable1", new GHVariable(jsonObject).getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        jsonObject.remove("name");
        assertNull(new GHVariable(jsonObject).getName());
    }

    @Test
    void shouldReturnCorrectValue() {
        assertEquals("Value1", new GHVariable(jsonObject).getValue());
    }

    @Test
    void shouldReturnNullWhenValueIsNotPresent() {
        jsonObject.remove("value");
        assertNull(new GHVariable(jsonObject).getValue());
    }

    @Test
    void shouldReturnCorrectCreatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHVariable(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenCreatedAtIsNotPresent() {
        jsonObject.remove("created_at");
        assertNull(new GHVariable(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnCorrectUpdatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-02T00:00:00Z"), new GHVariable(jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenUpdatedAtIsNotPresent() {
        jsonObject.remove("updated_at");
        assertNull(new GHVariable(jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnCorrectVisibility() {
        assertEquals("private", new GHVariable(jsonObject).getVisibility());
    }

    @Test
    void shouldReturnNullWhenVisibilityIsNotPresent() {
        jsonObject.remove("visibility");
        assertNull(new GHVariable(jsonObject).getVisibility());
    }

    @Test
    void shouldReturnCorrectSelectedRepositoriesUrl() {
        assertEquals("https://github.com/repos/1", new GHVariable(jsonObject).getSelectedRepositoriesUrl());
    }

    @Test
    void shouldReturnNullWhenSelectedRepositoriesUrlIsNotPresent() {
        jsonObject.remove("selected_repositories_url");
        assertNull(new GHVariable(jsonObject).getSelectedRepositoriesUrl());
    }
}