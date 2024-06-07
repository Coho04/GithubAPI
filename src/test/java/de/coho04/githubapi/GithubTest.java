package de.coho04.githubapi;

import de.coho04.githubapi.entities.*;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GithubTest {

    private Github github;
    private final String token = "test_token";

    @BeforeEach
    void setUp() {
        github = new Github(token);
    }

    @Test
    void testGetToken() {
        assertEquals(token, github.getToken());
    }

    @Test
    void testFindOrganisationByName() {
        try (MockedStatic<GHOrganisation> mockedStatic = mockStatic(GHOrganisation.class)) {
            GHOrganisation organisation = mock(GHOrganisation.class);
            mockedStatic.when(() -> GHOrganisation.getOrganisation(any(Github.class), anyString()))
                    .thenReturn(organisation);

            GHOrganisation result = github.findOrganisationByName("test_org");
            assertNotNull(result);
            assertEquals(organisation, result);
        }
    }

    @Test
    void testFindUserByName() {
        try (MockedStatic<GHUser> mockedStatic = mockStatic(GHUser.class)) {
            GHUser user = mock(GHUser.class);
            mockedStatic.when(() -> GHUser.getUser(any(Github.class), anyString()))
                    .thenReturn(user);

            GHUser result = github.findUserByName("test_user");
            assertNotNull(result);
            assertEquals(user, result);
        }
    }

    @Test
    void testListGlobalAdvisories() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = mockStatic(HttpRequestHelper.class)) {
            JSONObject jsonObject1 = new JSONObject().put("id", "1").put("cvss", new JSONObject()).put("cwes", new JSONArray());
            JSONObject jsonObject2 = new JSONObject().put("id", "1").put("cvss", new JSONObject()).put("cwes", new JSONArray());
            JSONArray jsonArray = new JSONArray().put(jsonObject1).put(jsonObject2);
            String[] responseAndLink = {jsonArray.toString(), null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);

            List<GHAdvisory> advisories = github.listGlobalAdvisories();
            assertNotNull(advisories);
            assertEquals(2, advisories.size());
        }
    }

    @Test
    void testGetGlobalAdvisories() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = mockStatic(HttpRequestHelper.class)) {
            JSONObject jsonObject = new JSONObject().put("id", "1")
                    .put("cvss", new JSONObject())
                    .put("cwes", new JSONArray());
            String jsonResponse = jsonObject.toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(jsonResponse);

            GHAdvisory advisory = github.getGlobalAdvisories("1");
            assertNotNull(advisory);
            assertEquals(1, advisory.getId());
        }
    }

    @Test
    void testListGitignoreTemplates() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = mockStatic(HttpRequestHelper.class)) {
            String jsonResponse = "[\"template1\", \"template2\"]";
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(jsonResponse);

            List<GHGitignoreTemplate> templates = github.listGitignoreTemplates();
            assertNotNull(templates);
            assertEquals(2, templates.size());
            assertEquals("template1", templates.get(0).getName());
            assertEquals("template2", templates.get(1).getName());
        }
    }

    @Test
    void testGetGitignoreTemplate() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = mockStatic(HttpRequestHelper.class)) {
            String jsonResponse = "{\"name\": \"template1\"}";
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(jsonResponse);

            GHGitignoreTemplate template = github.getGitignoreTemplate("template1");
            assertNotNull(template);
            assertEquals("template1", template.getName());
        }
    }

    @Test
    void testGetSelfUser() {
        try (MockedStatic<SelfUser> mockedStatic = mockStatic(SelfUser.class)) {
            SelfUser selfUser = mock(SelfUser.class);
            mockedStatic.when(() -> SelfUser.getSelfUser(any(Github.class)))
                    .thenReturn(selfUser);

            SelfUser result = github.getSelfUser();
            assertNotNull(result);
            assertEquals(selfUser, result);
        }
    }
}
