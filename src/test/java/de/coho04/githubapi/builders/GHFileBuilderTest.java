package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.SelfUser;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.repositories.GHBranch;
import de.coho04.githubapi.repositories.GHRepository;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GHFileBuilderTest {

    private Github github;
    private GHRepository repository;
    private GHFileBuilder fileBuilder;

    @BeforeEach
    void setUp() {
        github = mock(Github.class);
        repository = mock(GHRepository.class);
        when(github.getToken()).thenReturn("test_token");
        when(repository.getUrl()).thenReturn("https://api.github.com/repos/test/repo");

        fileBuilder = new GHFileBuilder(repository, github);
    }

    @Test
    void testSetBranchWithGHBranch() {
        GHBranch branch = mock(GHBranch.class);
        fileBuilder.setBranch(branch);
        assertEquals(branch, fileBuilder.getBranch());
    }

    @Test
    void testConstructorWithRepositoryAndGithub() {
        GHFileBuilder fileBuilder = new GHFileBuilder(repository, github);
        assertEquals(repository, fileBuilder.getRepository());
        assertEquals(github, fileBuilder.getGithub());
    }

    @Test
    void testConstructorWithRepositoryBranchAndGithub() {
        GHBranch branch = mock(GHBranch.class);
        GHFileBuilder fileBuilder = new GHFileBuilder(repository, branch, github);
        assertEquals(repository, fileBuilder.getRepository());
        assertEquals(branch, fileBuilder.getBranch());
        assertEquals(github, fileBuilder.getGithub());
    }

    @Test
    void testConstructorWithAllProperties() {
        GHBranch branch = mock(GHBranch.class);
        String path = "path/to/file.txt";
        String content = "Test content";
        String message = "Test commit message";
        GHFileBuilder fileBuilder = new GHFileBuilder(repository, branch, path, content, message, github);
        assertEquals(repository, fileBuilder.getRepository());
        assertEquals(branch, fileBuilder.getBranch());
        assertEquals(path, fileBuilder.getPath());
        assertEquals(content, fileBuilder.getContent());
        assertEquals(message, fileBuilder.getMessage());
        assertEquals(github, fileBuilder.getGithub());
    }


    @Test
    void testSetBranchByName() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String branchName = "main";
            String branchUrl = "https://api.github.com/repos/test/repo/branches";
            String branchResponse = "[{\"name\":\"main\"}]";

            // Mocking the static method sendGetRequest to return the branch information
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(branchUrl, "test_token"))
                    .thenReturn(branchResponse);

            // Creating a mock branch to return
            GHBranch branch = new GHBranch(new JSONObject().put("name", "main").put("commit", new JSONObject()));
            Map<String, GHBranch> branches = new HashMap<>();
            branches.put(branchName, branch);

            // Mocking the getBranches method to return the mock branch map
            when(repository.getBranches()).thenReturn(branches);

            fileBuilder.setBranch(branchName);
            assertEquals(branch, fileBuilder.getBranch());
        }
    }

    @Test
    void testSetContent() {
        String content = "Test content";
        fileBuilder.setContent(content);
        assertEquals(content, fileBuilder.getContent());
    }

    @Test
    void testSetMessage() {
        String message = "Test commit message";
        fileBuilder.setMessage(message);
        assertEquals(message, fileBuilder.getMessage());
    }

    @Test
    void testSetPath() {
        String path = "path/to/file.txt";
        fileBuilder.setPath(path);
        assertEquals(path, fileBuilder.getPath());
    }

    @Test
    void testGetRepository() {
        assertEquals(repository, fileBuilder.getRepository());
    }

    @Test
    void testGetGithub() {
        assertEquals(github, fileBuilder.getGithub());
    }

    @Test
    void testCommitWithEmailAndName() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String email = "test@example.com";
            String name = "testuser";
            String content = "Test content";
            String encodedContent = Base64.getEncoder().encodeToString(content.getBytes());
            String path = "path/to/file.txt";
            String message = "Test commit message";
            String url = "https://api.github.com/repos/test/repo/contents/" + path;

            fileBuilder.setContent(content);
            fileBuilder.setPath(path);
            fileBuilder.setMessage(message);

            JSONObject expectedJson = new JSONObject()
                    .put("message", message)
                    .put("committer", new JSONObject().put("name", name).put("email", email))
                    .put("content", encodedContent);

            mockedStatic.when(() -> HttpRequestHelper.sendPutRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenAnswer(invocation -> null);

            fileBuilder.commit(email, name);

            mockedStatic.verify(() -> HttpRequestHelper.sendPutRequest(
                    eq(url),
                    eq("test_token"),
                    argThat(json -> jsonSimilar(new JSONObject(json.toString()), expectedJson))
            ));
        }
    }

    @Test
    void testCommitWithAuthenticatedUser() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String email = "test@example.com";
            String login = "testuser";
            String content = "Test content";
            String encodedContent = Base64.getEncoder().encodeToString(content.getBytes());
            String path = "path/to/file.txt";
            String message = "Test commit message";
            String url = "https://api.github.com/repos/test/repo/contents/" + path;

            when(github.getSelfUser()).thenReturn(mock(SelfUser.class));
            when(github.getSelfUser().getLogin()).thenReturn(login);

            JSONObject emailJson = new JSONObject().put("email", email);
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(emailJson.toString());

            JSONObject expectedJson = new JSONObject()
                    .put("message", message)
                    .put("committer", new JSONObject().put("name", login).put("email", email))
                    .put("content", encodedContent);

            mockedStatic.when(() -> HttpRequestHelper.sendPutRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenAnswer(invocation -> null);

            fileBuilder.setContent(content);
            fileBuilder.setPath(path);
            fileBuilder.setMessage(message);

            fileBuilder.commit();

            mockedStatic.verify(() -> HttpRequestHelper.sendGetRequest(
                    eq(GHBase.getBaseUrl() + "/user/email"),
                    eq("test_token")
            ));

            mockedStatic.verify(() -> HttpRequestHelper.sendPutRequest(
                    eq(url),
                    eq("test_token"),
                    argThat(json -> jsonSimilar(new JSONObject(json.toString()), expectedJson))
            ));
        }
    }

    // Helper method to compare JSON objects
    private boolean jsonSimilar(JSONObject actual, JSONObject expected) {
        System.out.println("actual " + actual);
        System.out.println("expected " + expected);
        if (actual == null || expected == null) return false;
        for (String key : expected.keySet()) {
            if (!actual.has(key)) return false;
            if (actual.isNull(key) && expected.isNull(key)) continue;
            if (actual.isNull(key) || expected.isNull(key)) return false;
            if (!actual.get(key).toString().equals(expected.get(key).toString())) return false;
        }
        return true;
    }
}
