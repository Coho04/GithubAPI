package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHSecretTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("name", "Secret1");
        jsonObject.put("created_at", "2022-01-01T00:00:00Z");
        jsonObject.put("updated_at", "2022-01-02T00:00:00Z");
        jsonObject.put("visibility", "private");
        jsonObject.put("selected_repositories_url", "https://github.com/repos/1");
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals("Secret1", new GHSecret(jsonObject).getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        jsonObject.remove("name");
        assertNull(new GHSecret(jsonObject).getName());
    }

    @Test
    void shouldReturnCorrectCreatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHSecret(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenCreatedAtIsNotPresent() {
        jsonObject.remove("created_at");
        assertNull(new GHSecret(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnCorrectUpdatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-02T00:00:00Z"), new GHSecret(jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenUpdatedAtIsNotPresent() {
        jsonObject.remove("updated_at");
        assertNull(new GHSecret(jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnCorrectVisibility() {
        assertEquals("private", new GHSecret(jsonObject).getVisibility());
    }

    @Test
    void shouldReturnNullWhenVisibilityIsNotPresent() {
        jsonObject.remove("visibility");
        assertNull(new GHSecret(jsonObject).getVisibility());
    }

    @Test
    void shouldReturnCorrectSelectedRepositoriesUrl() {
        assertEquals("https://github.com/repos/1", new GHSecret(jsonObject).getSelectedRepositoriesUrl());
    }

    @Test
    void shouldReturnNullWhenSelectedRepositoriesUrlIsNotPresent() {
        jsonObject.remove("selected_repositories_url");
        assertNull(new GHSecret(jsonObject).getSelectedRepositoriesUrl());
    }
}