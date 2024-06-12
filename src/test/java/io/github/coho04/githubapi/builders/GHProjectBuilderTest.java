package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.entities.GHProject;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GHProjectBuilderTest {

    private GHProjectBuilder projectBuilder;
    private final String url = "https://api.github.com/repos/test/repo/projects";
    private Github github;

    @BeforeEach
    void setUp() {
        github = mock(Github.class);
        when(github.getToken()).thenReturn("test_token");
        projectBuilder = new GHProjectBuilder(github, url);
    }

    @Test
    void shouldSetBodyCorrectly() {
        String body = "Test body";
        projectBuilder.setBody(body);
        assertEquals(body, projectBuilder.getBody());
    }

    @Test
    void shouldSetNameCorrectly() {
        String name = "Test name";
        projectBuilder.setName(name);
        assertEquals(name, projectBuilder.getName());
    }

    @Test
    void shouldGetUrlCorrectly() {
        assertEquals(url, projectBuilder.getUrl());
    }

    @Test
    void shouldGetGithubCorrectly() {
        assertEquals(github, projectBuilder.getGithub());
    }

    @Test
    void shouldBuildProjectCorrectly() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String name = "Test name";
            String body = "Test body";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("body", body);
            String response = jsonObject.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenReturn(response);

            projectBuilder.setName(name);
            projectBuilder.setBody(body);

            GHProject project = projectBuilder.build();

            assertEquals(name, project.getName());
            assertEquals(body, project.getBody());
        }
    }
}