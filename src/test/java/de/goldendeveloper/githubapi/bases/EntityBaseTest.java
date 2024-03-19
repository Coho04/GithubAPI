package de.goldendeveloper.githubapi.bases;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityBaseTest {

    @Test
    void shouldReturnCorrectType() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "User");

        EntityBase entityBase = new EntityBase(jsonObject);

        assertEquals("User", entityBase.getType());
    }

    @Test
    void shouldReturnNullWhenTypeIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        EntityBase entityBase = new EntityBase(jsonObject);

        assertNull(entityBase.getType());
    }

    @Test
    void shouldReturnCorrectLogin() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", "octocat");

        EntityBase entityBase = new EntityBase(jsonObject);

        assertEquals("octocat", entityBase.getLogin());
    }

    @Test
    void shouldReturnNullWhenLoginIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        EntityBase entityBase = new EntityBase(jsonObject);

        assertNull(entityBase.getLogin());
    }

    @Test
    void shouldReturnCorrectReposUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("repos_url", "https://repos.github.com/octocat");

        EntityBase entityBase = new EntityBase(jsonObject);

        assertEquals("https://repos.github.com/octocat", entityBase.getReposUrl());
    }

    @Test
    void shouldReturnNullWhenReposUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        EntityBase entityBase = new EntityBase(jsonObject);

        assertNull(entityBase.getReposUrl());
    }

    @Test
    void shouldReturnCorrectAvatarUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("avatar_url", "https://avatars.github.com/octocat");

        EntityBase entityBase = new EntityBase(jsonObject);

        assertEquals("https://avatars.github.com/octocat", entityBase.getAvatarUrl());
    }

    @Test
    void shouldReturnNullWhenAvatarUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        EntityBase entityBase = new EntityBase(jsonObject);

        assertNull(entityBase.getAvatarUrl());
    }
}