package de.goldendeveloper.githubapi.repositories;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GHFileTest {

    @Test
    public void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Test File");

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals("Test File", ghFile.getName());
    }

    @Test
    public void shouldReturnCorrectPath() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path", "/path/to/file");

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals("/path/to/file", ghFile.getPath());
    }

    @Test
    public void shouldReturnCorrectSha() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sha", "abc123");

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals("abc123", ghFile.getSha());
    }

    @Test
    public void shouldReturnCorrectSize() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", 100);

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals(100, ghFile.getSize());
    }

    @Test
    public void shouldReturnCorrectUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "http://test.com");

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals("http://test.com", ghFile.getUrl());
    }

    @Test
    public void shouldReturnNullWhenNameIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertNull(ghFile.getName());
    }

    @Test
    public void shouldReturnNullWhenPathIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertNull(ghFile.getPath());
    }

    @Test
    public void shouldReturnNullWhenShaIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertNull(ghFile.getSha());
    }

    @Test
    public void shouldReturnZeroWhenSizeIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals(0, ghFile.getSize());
    }

    @Test
    public void shouldReturnNullWhenUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertNull(ghFile.getUrl());
    }
}