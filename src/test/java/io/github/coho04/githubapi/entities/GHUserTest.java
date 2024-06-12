package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.entities.repositories.GHRepository;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

class GHUserTest {

    private Github github;
    private GHUser user;

    @BeforeEach
    void setUp() {
        github = new Github("test_token");
        JSONObject jsonObject = new JSONObject()
                .put("login", "testuser")
                .put("contributions", 10)
                .put("gists_url", "https://api.github.com/users/testuser/gists")
                .put("gravatar_id", "gravatar_id")
                .put("site_admin", true)
                .put("starred_url", "https://api.github.com/users/testuser/starred")
                .put("followers_url", "https://api.github.com/users/testuser/followers")
                .put("following_url", "https://api.github.com/users/testuser/following")
                .put("subscriptions_url", "https://api.github.com/users/testuser/subscriptions")
                .put("received_events_url", "https://api.github.com/users/testuser/received_events")
                .put("organizations_url", "https://api.github.com/users/testuser/orgs");

        user = new GHUser(github, jsonObject);
    }

    @Test
    void testConstructor() {
        assertNotNull(user);
        assertEquals("testuser", user.getLogin());
        assertEquals(10, user.getContributions());
        assertEquals("https://api.github.com/users/testuser/gists", user.getGistsUrl());
        assertEquals("gravatar_id", user.getGravatarId());
        assertTrue(user.isSiteAdmin());
        assertEquals("https://api.github.com/users/testuser/starred", user.getStarredUrl());
        assertEquals("https://api.github.com/users/testuser/followers", user.getFollowersUrl());
        assertEquals("https://api.github.com/users/testuser/following", user.getFollowingUrl());
        assertEquals("https://api.github.com/users/testuser/subscriptions", user.getSubscriptionsUrl());
        assertEquals("https://api.github.com/users/testuser/received_events", user.getReceivedEventsUrl());
        assertEquals("https://api.github.com/users/testuser/orgs", user.getOrganizationsUrl());
    }

    @Test
    void testGetUser() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("login", "testuser").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), eq("test_token"))).thenReturn(response);

            GHUser fetchedUser = GHUser.getUser(github, "testuser");
            assertNotNull(fetchedUser);
            assertEquals("testuser", fetchedUser.getLogin());
        }
    }

    @Test
    void testToJSONObject() {
        JSONObject json = user.toJSONObject();
        assertEquals("testuser", json.getString("login"));
        assertEquals(10, json.getInt("contributions"));
        assertEquals("https://api.github.com/users/testuser/gists", json.getString("gists_url"));
        assertEquals("gravatar_id", json.getString("gravatar_id"));
        assertTrue(json.getBoolean("site_admin"));
        assertEquals("https://api.github.com/users/testuser/starred", json.getString("starred_url"));
        assertEquals("https://api.github.com/users/testuser/followers", json.getString("followers_url"));
        assertEquals("https://api.github.com/users/testuser/following", json.getString("following_url"));
        assertEquals("https://api.github.com/users/testuser/subscriptions", json.getString("subscriptions_url"));
        assertEquals("https://api.github.com/users/testuser/received_events", json.getString("received_events_url"));
        assertEquals("https://api.github.com/users/testuser/orgs", json.getString("organizations_url"));
    }

    @Test
    void testFindRepositoryByName() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("name", "test-repo").put("topics", new JSONArray(List.of("test1", "test2"))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), eq("test_token"))).thenReturn(response);

            GHRepository repo = user.findRepositoryByName("test-repo");
            assertNotNull(repo);
            assertEquals("test-repo", repo.getName());
        }
    }

    @Test
    void testGetFollowers() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Mock response should be a JSON array
            String response = new JSONArray().put(new JSONObject().put("login", "follower1")).toString();
            String[] responses = {response, null};
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token"))).thenReturn(responses);

            List<GHUser> followers = user.getFollowers();
            assertNotNull(followers);
            assertEquals(1, followers.size());
            assertEquals("follower1", followers.get(0).getLogin());
        }
    }

    @Test
    void testGetFollowing() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Mock response should be a JSON array
            String response = new JSONArray().put(new JSONObject().put("login", "following1")).toString();
            String[] responses = {response, null};
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token"))).thenReturn(responses);

            List<GHUser> following = user.getFollowing();
            assertNotNull(following);
            assertEquals(1, following.size());
            assertEquals("following1", following.get(0).getLogin());
        }
    }

    @Test
    void testGetPublicKeys() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Mock response should be a JSON array
            String response = new JSONArray().put(new JSONObject().put("key_id", "key1")).toString();
            String[] responses = {response, null};
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token"))).thenReturn(responses);

            List<GHPublicKey> publicKeys = user.getPublicKeys();
            assertNotNull(publicKeys);
            assertEquals(1, publicKeys.size());
            assertEquals("key1", publicKeys.get(0).getKeyId());
        }
    }

    @Test
    void shouldListRepositories() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject response = new JSONObject().put("name", "repo1");
            String[] responseAndLink = {new JSONArray().put(response).toString(), null};
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token"))).thenReturn(responseAndLink);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            GHUser user = new GHUser(github, new JSONObject());
            List<GHRepository> repos = user.listRepositories();

            assertNotNull(repos);
            assertEquals(1, repos.size());
            assertEquals("repo1", repos.get(0).getName());
        }
    }

    @Test
    void shouldReturnEmptyListWhenNoRepositories() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String[] responseAndLink = {new JSONArray().toString(), null};
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token"))).thenReturn(responseAndLink);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            GHUser user = new GHUser(github, new JSONObject());
            List<GHRepository> repos = user.listRepositories();

            assertNotNull(repos);
            assertEquals(0, repos.size());
        }
    }

}
