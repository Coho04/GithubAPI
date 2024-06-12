package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.entities.GHTeam;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GHTeamBuilderTest {

    private Github github;
    private GHTeamBuilder teamBuilder;
    private final String url = "https://api.github.com/orgs/test/teams";

    @BeforeEach
    void setUp() {
        github = mock(Github.class);
        when(github.getToken()).thenReturn("test_token");
        teamBuilder = new GHTeamBuilder(github, url);
    }

    @Test
    void shouldSetPropertiesCorrectly() {
        String name = "Test team";
        String description = "Test description";
        List<String> maintainers = Arrays.asList("user1", "user2");
        List<String> repoNames = Arrays.asList("repo1", "repo2");
        String privacy = "closed";
        String notificationSetting = "always";
        String permission = "admin";
        int parentTeamId = 1;

        teamBuilder.setName(name)
                .setDescription(description)
                .setMaintainers(maintainers)
                .setRepoNames(repoNames)
                .setPrivacy(privacy)
                .setNotificationSetting(notificationSetting)
                .setPermission(permission)
                .setParentTeamId(parentTeamId);

        assertEquals(name, teamBuilder.getName());
        assertEquals(description, teamBuilder.getDescription());
        assertEquals(maintainers, teamBuilder.getMaintainers());
        assertEquals(repoNames, teamBuilder.getRepoNames());
        assertEquals(privacy, teamBuilder.getPrivacy());
        assertEquals(notificationSetting, teamBuilder.getNotificationSetting());
        assertEquals(permission, teamBuilder.getPermission());
        assertEquals(parentTeamId, teamBuilder.getParentTeamId());
    }

    @Test
    void shouldBuildTeamCorrectly() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String name = "Test team";
            String response = "{\"name\":\"" + name + "\"}";

            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenReturn(response);

            teamBuilder.setName(name);

            GHTeam team = teamBuilder.build();

            assertEquals(name, team.getName());
        }
    }

    @Test
    void shouldGetUrlCorrectly() {
        assertEquals(url, teamBuilder.getUrl());
    }

    @Test
    void shouldGetGithubCorrectly() {
        assertEquals(github, teamBuilder.getGithub());
    }
}