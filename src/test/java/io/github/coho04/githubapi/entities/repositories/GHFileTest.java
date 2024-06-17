package io.github.coho04.githubapi.entities.repositories;

import io.github.coho04.githubapi.TestBase;
import io.github.coho04.githubapi.builders.GHFileBuilder;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GHFileTest extends TestBase {

    @Test
    void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Test File");

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertEquals("Test File", ghFile.getName());
    }

    @Test
    void shouldReturnCorrectPath() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path", "/path/to/file");

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertEquals("/path/to/file", ghFile.getPath());
    }

    @Test
    void shouldReturnCorrectSha() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sha", "abc123");

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertEquals("abc123", ghFile.getSha());
    }

    @Test
    void shouldReturnCorrectSize() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", 100);

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertEquals(100, ghFile.getSize());
    }

    @Test
    void shouldReturnCorrectUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "https://test.com");

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertEquals("https://test.com", ghFile.getUrl());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertNull(ghFile.getName());
    }

    @Test
    void shouldReturnNullWhenPathIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertNull(ghFile.getPath());
    }

    @Test
    void shouldReturnNullWhenShaIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertNull(ghFile.getSha());
    }

    @Test
    void shouldReturnZeroWhenSizeIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertEquals(0, ghFile.getSize());
    }

    @Test
    void shouldReturnNullWhenUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertNull(ghFile.getUrl());
    }

    @Test
    void shouldReturnCorrectHtmlUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("html_url", "https://example.com/html_url");
        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());
        assertEquals("https://example.com/html_url", ghFile.getHtmlUrl());
    }

    @Test
    void shouldReturnNullWhenHtmlUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());
        assertNull(ghFile.getHtmlUrl());
    }

    @Test
    void shouldReturnCorrectDownloadUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("download_url", "https://example.com/download_url");
        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());
        assertEquals("https://example.com/download_url", ghFile.getDownloadUrl());
    }

    @Test
    void shouldReturnNullWhenDownloadUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());
        assertNull(ghFile.getDownloadUrl());
    }

    @Test
    void shouldReturnCorrectGitUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("git_url", "https://example.com/git_url");
        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());
        assertEquals("https://example.com/git_url", ghFile.getGitUrl());
    }

    @Test
    void shouldReturnNullWhenGitUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());
        assertNull(ghFile.getGitUrl());
    }

    @Test
    void shouldReturnCorrectType() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "Seven stands upon somebody else's legs.");
        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());
        assertEquals("Seven stands upon somebody else's legs.", ghFile.getType());
    }

    @Test
    void shouldReturnNullWhenTypeIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());
        assertNull(ghFile.getType());
    }

    @Test
    void constructorDecodesContentWhenPresent() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", Base64.getEncoder().encodeToString("Test content".getBytes(StandardCharsets.UTF_8)));

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertEquals("Test content", ghFile.getContent());
    }

    @Test
    void constructorSetsContentToNullWhenContentIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertNull(ghFile.getContent());
    }

    @Test
    void updateFile_returnsGHFileBuilderWithCorrectProperties() {
        GHFile ghFile = setupGHFile();
        GHFileBuilder fileBuilder = ghFile.updateFile();
        assertEquals(ghFile.getRepository(), fileBuilder.getRepository());
        assertEquals(ghFile.getGithub(), fileBuilder.getGithub());
        assertEquals(ghFile.getSha(), fileBuilder.getSha());
    }

    @Test
    void getContent_returnsCorrectContentWhenPresent() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", Base64.getEncoder().encodeToString("Test content".getBytes(StandardCharsets.UTF_8)));

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertEquals("Test content", ghFile.getContent());
    }

    @Test
    void getContent_returnsNullWhenContentIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHFile ghFile = new GHFile(getMockedGithub(), jsonObject, setupRepository());

        assertNull(ghFile.getContent());
    }
}