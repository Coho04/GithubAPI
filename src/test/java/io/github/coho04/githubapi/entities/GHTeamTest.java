package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.TestBase;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GHTeamTest extends TestBase {

    private GHTeam team;
    private Github github;
    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        github = mock(Github.class);
        when(github.getToken()).thenReturn("test_token");

        jsonObject = new JSONObject();
        jsonObject.put("name", "Test Team");
        jsonObject.put("slug", "test-team");
        jsonObject.put("description", "A test team");
        jsonObject.put("privacy", "closed");
        jsonObject.put("notification_setting", "all");
        jsonObject.put("permission", "admin");
        jsonObject.put("members_url", "https://api.github.com/teams/1/members");
        jsonObject.put("repositories_url", "https://api.github.com/teams/1/repos");
        jsonObject.put("created_at", OffsetDateTime.now().toString());
        jsonObject.put("updated_at", OffsetDateTime.now().toString());
        jsonObject.put("members_count", 10);
        jsonObject.put("repos_count", 5);
        jsonObject.put("parent", setupTeam().toJSONObject());

        team = new GHTeam(jsonObject);
    }

    @Test
    void testGetName() {
        assertEquals("Test Team", team.getName());
    }

    @Test
    void testGetSlug() {
        assertEquals("test-team", team.getSlug());
    }

    @Test
    void testGetDescription() {
        assertEquals("A test team", team.getDescription());
    }

    @Test
    void testGetPrivacy() {
        assertEquals("closed", team.getPrivacy());
    }

    @Test
    void testGetNotificationSetting() {
        assertEquals("all", team.getNotificationSetting());
    }

    @Test
    void testGetPermission() {
        assertEquals("admin", team.getPermission());
    }

    @Test
    void testGetMembersUrl() {
        assertEquals("https://api.github.com/teams/1/members", team.getMembersUrl());
    }

    @Test
    void testGetRepositoriesUrl() {
        assertEquals("https://api.github.com/teams/1/repos", team.getRepositoriesUrl());
    }

    @Test
    void testGetCreatedAt() {
        assertNotNull(team.getCreatedAt());
    }

    @Test
    void testGetUpdatedAt() {
        assertNotNull(team.getUpdatedAt());
    }

    @Test
    void testGetMembersCount() {
        assertEquals(10, team.getMembersCount());
    }

    @Test
    void testGetReposCount() {
        assertEquals(5, team.getReposCount());
    }

    @Test
    void testGetParent() {
        assertNotNull(team.getParent());
    }

    @Test
    void testParentIfIsNull() {
        jsonObject.put("parent", JSONObject.NULL);
        team = new GHTeam(jsonObject);
        assertNull(team.getParent());
    }

    @Test
    void testGetTeamByName() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = mockStatic(HttpRequestHelper.class)) {
            String response = jsonObject.toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(response);

            GHOrganisation organisation = mock(GHOrganisation.class);
            when(organisation.getGivenName()).thenReturn("test-org");

            GHTeam fetchedTeam = GHTeam.getTeamByName(github, "test-team", organisation);
            assertNotNull(fetchedTeam);
            assertEquals("Test Team", fetchedTeam.getName());
        }
    }
}
