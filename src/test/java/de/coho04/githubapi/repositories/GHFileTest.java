package de.coho04.githubapi.repositories;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GHFileTest {

    @Test
    void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Test File");

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals("Test File", ghFile.getName());
    }

    @Test
    void shouldReturnCorrectPath() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path", "/path/to/file");

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals("/path/to/file", ghFile.getPath());
    }

    @Test
    void shouldReturnCorrectSha() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sha", "abc123");

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals("abc123", ghFile.getSha());
    }

    @Test
    void shouldReturnCorrectSize() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", 100);

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals(100, ghFile.getSize());
    }

    @Test
    void shouldReturnCorrectUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "http://test.com");

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals("http://test.com", ghFile.getUrl());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertNull(ghFile.getName());
    }

    @Test
    void shouldReturnNullWhenPathIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertNull(ghFile.getPath());
    }

    @Test
    void shouldReturnNullWhenShaIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertNull(ghFile.getSha());
    }

    @Test
    void shouldReturnZeroWhenSizeIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertEquals(0, ghFile.getSize());
    }

    @Test
    void shouldReturnNullWhenUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(jsonObject);

        assertNull(ghFile.getUrl());
    }

    @Test
    void shouldReturnCorrectHtmlUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("html_url", "https://example.com/html_url");
        GHFile ghFile = new GHFile(jsonObject);
        assertEquals("https://example.com/html_url", ghFile.getHtmlUrl());
    }

    @Test
    void shouldReturnNullWhenHtmlUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHFile ghFile = new GHFile(jsonObject);
        assertNull(ghFile.getHtmlUrl());
    }

    @Test
    void shouldReturnCorrectDownloadUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("download_url", "https://example.com/download_url");
        GHFile ghFile = new GHFile(jsonObject);
        assertEquals("https://example.com/download_url", ghFile.getDownloadUrl());
    }

    @Test
    void shouldReturnNullWhenDownloadUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHFile ghFile = new GHFile(jsonObject);
        assertNull(ghFile.getDownloadUrl());
    }

    @Test
    void shouldReturnCorrectGitUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("git_url", "https://example.com/git_url");
        GHFile ghFile = new GHFile(jsonObject);
        assertEquals("https://example.com/git_url", ghFile.getGitUrl());
    }

    @Test
    void shouldReturnNullWhenGitUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHFile ghFile = new GHFile(jsonObject);
        assertNull(ghFile.getGitUrl());
    }

    @Test
    void shouldReturnCorrectType() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "Sevenworm stands upon somebody else's legs.");
        GHFile ghFile = new GHFile(jsonObject);
        assertEquals("Sevenworm stands upon somebody else's legs.", ghFile.getType());
    }

    @Test
    void shouldReturnNullWhenTypeIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHFile ghFile = new GHFile(jsonObject);
        assertNull(ghFile.getType());
    }
}