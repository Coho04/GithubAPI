package de.coho04.githubapi.bases;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class GHBaseTest {


    private GHBase ghBase;

    @BeforeEach
    void setUp() {
        ghBase = new GHBase();
    }

    @Test
    void shouldReturnEmptyJSONObject() {
        GHBase ghBase = new GHBase();
        JSONObject jsonObject = ghBase.toJSONObject();
        assertTrue(jsonObject.isEmpty());
    }

    @Test
    void shouldReturnCorrectBaseUrl() {
        String baseUrl = GHBase.getBaseUrl();
        assertEquals("https://api.github.com", baseUrl);
    }

    @Test
    void shouldReturnCorrectDataWhenFetchArrayDataIsCalled() {
        // Mocking HttpRequestHelper
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Mock response
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(new JSONObject().put("login", "user1"));
            jsonArray.put(new JSONObject().put("login", "user2"));

            JSONObject responseJson = new JSONObject();
            responseJson.put("items", jsonArray);

            String response = responseJson.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(response);
            Github github = Mockito.mock(Github.class);
            List<GHUser> users = ghBase.fetchArrayData("/users", jsonObject -> new GHUser(github, jsonObject), "token", "items");

            assertEquals(2, users.size());
            assertEquals("user1", users.get(0).getLogin());
            assertEquals("user2", users.get(1).getLogin());
        }
    }

    @Test
    void shouldReturnEmptyListWhenFetchArrayDataIsCalledAndResponseIsEmpty() {
        // Mocking HttpRequestHelper
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Mock response
            JSONArray jsonArray = new JSONArray();

            JSONObject responseJson = new JSONObject();
            responseJson.put("items", jsonArray);

            String response = responseJson.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(response);
            Github github = Mockito.mock(Github.class);
            List<GHUser> users = ghBase.fetchArrayData("/users", jsonObject -> new GHUser(github, jsonObject), "token", "items");

            assertTrue(users.isEmpty());
        }
    }

    @Test
    void shouldReturnEmptyListWhenFetchArrayDataIsCalledAndResponseIsNull() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(null);
            Github github = Mockito.mock(Github.class);
            List<GHUser> users = ghBase.fetchArrayData("/users", jsonObject -> new GHUser(github, jsonObject), "token", "items");
            assertTrue(users.isEmpty());
        }
    }
}