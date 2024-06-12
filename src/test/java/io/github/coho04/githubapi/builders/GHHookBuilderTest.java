package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.entities.GHHook;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class GHHookBuilderTest {

    private Github github;
    private GHHookBuilder builder;
    private final String url = "https://api.github.com/repos/test_owner/test_repo/hooks";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        github = Mockito.mock(Github.class);
        when(github.getToken()).thenReturn("test_token");

        builder = new GHHookBuilder(github, url);
    }

    @Test
    void testSetAndGetConfigContentType() {
        String contentType = "json";
        builder.setConfigContentType(contentType);
        assertEquals(contentType, builder.getConfigContentType());
    }

    @Test
    void testSetAndGetConfigInsecureSSL() {
        String insecureSSL = "0";
        builder.setConfigInsecureSSL(insecureSSL);
        assertEquals(insecureSSL, builder.getConfigInsecureSSL());
    }

    @Test
    void testSetAndGetConfigPassword() {
        String password = "password";
        builder.setConfigPassword(password);
        assertEquals(password, builder.getConfigPassword());
    }

    @Test
    void testSetAndGetConfigSecret() {
        String secret = "secret";
        builder.setConfigSecret(secret);
        assertEquals(secret, builder.getConfigSecret());
    }

    @Test
    void testSetAndGetConfigURL() {
        String hookUrl = "http://example.com/hook";
        builder.setConfigURL(hookUrl);
        assertEquals(hookUrl, builder.getConfigURL());
    }

    @Test
    void testSetAndGetConfigUsername() {
        String username = "username";
        builder.setConfigUsername(username);
        assertEquals(username, builder.getConfigUsername());
    }

    @Test
    void testSetAndGetEvents() {
        List<String> events = Arrays.asList("push", "pull_request");
        builder.setEvents(events);
        assertEquals(events, builder.getEvents());
    }

    @Test
    void testAddEvent() {
        List<String> events = List.of("push");
        builder.setEvents(events);
        builder.addEvent("pull_request");
        assertEquals(Arrays.asList("push", "pull_request"), builder.getEvents());
    }

    @Test
    void testSetAndGetName() {
        String name = "web";
        builder.setName(name);
        assertEquals(name, builder.getName());
    }

    @Test
    void testSetAndGetActive() {
        builder.setActive(true);
        assertTrue(builder.isActive());
    }

    @Test
    void testToJSONObject() {
        builder.setName("web");
        builder.setActive(true);
        builder.setConfigURL("http://example.com/hook");
        builder.setConfigContentType("json");
        builder.setConfigSecret("secret");
        builder.setConfigInsecureSSL("0");
        builder.setConfigUsername("username");
        builder.setConfigPassword("password");
        builder.setEvents(Arrays.asList("push", "pull_request"));

        JSONObject jsonObject = builder.toJSONObject();
        assertEquals("web", jsonObject.getString("name"));
        assertTrue(jsonObject.getBoolean("active"));
        JSONObject config = jsonObject.getJSONObject("config");
        assertEquals("http://example.com/hook", config.getString("url"));
        assertEquals("json", config.getString("content_type"));
        assertEquals("secret", config.getString("secret"));
        assertEquals("0", config.getString("insecure_ssl"));
        assertEquals("username", config.getString("username"));
        assertEquals("password", config.getString("password"));
        assertEquals(Arrays.asList("push", "pull_request"), jsonObject.getJSONArray("events").toList());
    }

    @Test
    void testBuild() {
        builder.setName("web");
        builder.setActive(true);
        builder.setConfigURL("http://example.com/hook");
        builder.setConfigContentType("json");
        builder.setConfigSecret("secret");
        builder.setConfigInsecureSSL("0");
        builder.setConfigUsername("username");
        builder.setConfigPassword("password");
        builder.setEvents(Arrays.asList("push", "pull_request"));

        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenAnswer(invocation -> {
                        String passedUrl = invocation.getArgument(0);
                        String passedToken = invocation.getArgument(1);
                        JSONObject passedJson = invocation.getArgument(2);

                        assertEquals(url, passedUrl);
                        assertEquals("test_token", passedToken);
                        assertEquals(builder.toJSONObject().toString(), passedJson.toString());
                        return "{\"id\": 1}";
                    });

            GHHook hook = builder.build();
            assertNotNull(hook);
            assertEquals(1, hook.getId());

            mockedStatic.verify(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), any(JSONObject.class)), times(1));
        }
    }

    @Test
    void shouldGetGithubCorrectly() {
        assertEquals(github, builder.getGithub());
    }

    @Test
    void shouldGetUrlCorrectly() {
        assertEquals(url, builder.getUrl());
    }
}