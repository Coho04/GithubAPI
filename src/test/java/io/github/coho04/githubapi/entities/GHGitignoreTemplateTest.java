package io.github.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHGitignoreTemplateTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("name", "Java");
        jsonObject.put("source", "https://github.com/github/gitignore/blob/master/Java.gitignore");
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals("Java", new GHGitignoreTemplate(jsonObject).getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        jsonObject.remove("name");
        assertNull(new GHGitignoreTemplate(jsonObject).getName());
    }

    @Test
    void shouldReturnCorrectSource() {
        assertEquals("https://github.com/github/gitignore/blob/master/Java.gitignore", new GHGitignoreTemplate(jsonObject).getSource());
    }

    @Test
    void shouldReturnNullWhenSourceIsNotPresent() {
        jsonObject.remove("source");
        assertNull(new GHGitignoreTemplate(jsonObject).getSource());
    }
}