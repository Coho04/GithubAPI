package de.coho04.githubapi;

import de.coho04.githubapi.entities.GHPublicKey;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.entities.repositories.GHIssue;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class SelfUserTest {

    @Test
    void testGetSelfUser() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("login", "selfUser").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), eq("test_token"))).thenReturn(response);
            SelfUser selfUser = SelfUser.getSelfUser(new Github("test_token"));
            assertNotNull(selfUser);
            assertEquals("selfUser", selfUser.getLogin());
        }
    }

    @Test
    void testGetIssues() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject response = new JSONObject().put("title", "issue1");
            String[] responseAndLink = {new JSONArray().put(response).toString(), null};
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token"))).thenReturn(responseAndLink);
            List<GHIssue> issues = new SelfUser(new Github("test_token"), new JSONObject()).getIssues();
            assertNotNull(issues);
            assertEquals(1, issues.size());
            assertEquals("issue1", issues.get(0).getTitle());
        }
    }

    @Test
    void testGetBlockedUsers() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray().put(new JSONObject().put("login", "blockedUser")).toString();
            String[] responseAndLink = {response, null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token")))
                    .thenReturn(responseAndLink);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");
            SelfUser selfUser = new SelfUser(github, new JSONObject());

            List<GHUser> blockedUsers = selfUser.getBlockedUsers();
            assertNotNull(blockedUsers);
            assertEquals(1, blockedUsers.size());
            assertEquals("blockedUser", blockedUsers.get(0).getLogin());
        }
    }

    @Test
    void testFollowUser() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendPutRequest(anyString(), anyString(), any())).thenAnswer(invocationOnMock -> null);
            SelfUser selfUser = new SelfUser(new Github("test_token"), new JSONObject());
            assertDoesNotThrow(() -> selfUser.followUser("username"));
        }
    }

    @Test
    void testUnfollowUser() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequest(anyString(), anyString())).thenAnswer(invocationOnMock -> null);
            SelfUser selfUser = new SelfUser(new Github("test_token"), new JSONObject());
            assertDoesNotThrow(() -> selfUser.unfollowUser("username"));
        }
    }

    @Test
    void testAddPublicKeyWithBuilder() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("key_id", "key1").put("key", "ssh-rsa AAAAB...").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), Mockito.any(JSONObject.class))).thenReturn(response);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            SelfUser selfUser = new SelfUser(github, new JSONObject());
            GHPublicKey publicKey = selfUser.addPublicKey("title", "ssh-rsa AAAAB...");

            assertNotNull(publicKey);
            assertEquals("key1", publicKey.getKeyId());
            assertEquals("ssh-rsa AAAAB...", publicKey.getKey());
        }
    }

    @Test
    void testAddPublicKey() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("key_id", "key1").put("key", "ssh-rsa AAAAB...").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), Mockito.any(JSONObject.class))).thenReturn(response);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            SelfUser selfUser = new SelfUser(github, new JSONObject());
            GHPublicKey publicKey = selfUser.addPublicKey().setTitle("asdasd").setKey("asdasd").build();

            assertNotNull(publicKey);
            assertEquals("key1", publicKey.getKeyId());
            assertEquals("ssh-rsa AAAAB...", publicKey.getKey());
        }
    }

    @Test
    void testGetPublicKey() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("key_id", "key1").put("key", "ssh-rsa AAAAB...").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(response);
            SelfUser selfUser = new SelfUser(new Github("test_token"), new JSONObject());
            GHPublicKey publicKey = selfUser.getPublicKey(1);
            assertNotNull(publicKey);
            assertEquals("key1", publicKey.getKeyId());
            assertEquals("ssh-rsa AAAAB...", publicKey.getKey());
        }
    }

    @Test
    void testDeletePublicKey() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequest(anyString(), anyString())).thenAnswer(invocationOnMock -> null);
            Github github = Mockito.mock(Github.class);
            SelfUser selfUser = new SelfUser(github, new JSONObject());
            assertDoesNotThrow(() -> selfUser.deletePublicKey(1));
        }
    }
}
