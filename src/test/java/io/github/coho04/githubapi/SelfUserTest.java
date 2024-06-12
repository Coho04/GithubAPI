package io.github.coho04.githubapi;

import io.github.coho04.githubapi.builders.GHProjectBuilder;
import io.github.coho04.githubapi.entities.GHProject;
import io.github.coho04.githubapi.entities.GHPublicKey;
import io.github.coho04.githubapi.entities.GHUser;
import io.github.coho04.githubapi.entities.repositories.GHIssue;
import io.github.coho04.githubapi.entities.repositories.GHRepository;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
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

    @Test
    void shouldListCreatedRepositories() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Mock response for HttpRequestHelper.sendGetRequestWithLinkHeader
            JSONObject response = new JSONObject().put("name", "repo1").put("owner", new JSONObject().put("login", "test_user"));
            String[] responseAndLink = {new JSONArray().put(response).toString(), null};
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token"))).thenReturn(responseAndLink);

            // Instantiate GitHub and SelfUser with a mock user JSON
            Github github = new Github("test_token");
            JSONObject mockUserJson = new JSONObject().put("login", "test_user");
            SelfUser selfUser = new SelfUser(github, mockUserJson);

            // Call listCreatedRepositories and verify the result
            List<GHRepository> repos = selfUser.listCreatedRepositories();
            assertNotNull(repos);
            assertEquals(1, repos.size());
            assertEquals("repo1", repos.get(0).getName());
        }
    }

    @Test
    void shouldListRepositoriesWithAccess() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject response = new JSONObject().put("name", "repo1");
            String[] responseAndLink = {new JSONArray().put(response).toString(), null};
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), eq("test_token"))).thenReturn(responseAndLink);
            List<GHRepository> repos = new SelfUser(new Github("test_token"), new JSONObject()).listRepositoriesWithAccess();
            assertNotNull(repos);
            assertEquals(1, repos.size());
            assertEquals("repo1", repos.get(0).getName());
        }
    }

    @Test
    void shouldGetEmail() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = "test@example.com";
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), eq("test_token"))).thenReturn(response);
            String email = new SelfUser(new Github("test_token"), new JSONObject()).getEmail();
            assertNotNull(email);
            assertEquals("test@example.com", email);
        }
    }

    @Test
    void shouldCreateProject() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("name", "project1").put("body", "project body").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), Mockito.any(JSONObject.class))).thenReturn(response);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            SelfUser selfUser = new SelfUser(github, new JSONObject());
            GHProject project = selfUser.createProject("project1", "project body");

            assertNotNull(project);
            assertEquals("project1", project.getName());
            assertEquals("project body", project.getBody());
        }
    }

    @Test
    void shouldCreateProjectBuilder() {
        Github github = Mockito.mock(Github.class);
        Mockito.when(github.getToken()).thenReturn("test_token");

        SelfUser selfUser = new SelfUser(github, new JSONObject());
        GHProjectBuilder projectBuilder = selfUser.createProject();

        assertNotNull(projectBuilder);
    }
}
