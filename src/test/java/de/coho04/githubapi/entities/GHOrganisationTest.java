package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.repositories.GHRepository;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GHOrganisationTest {

    private Github github;
    private GHOrganisation organisation;
    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        github = mock(Github.class);
        when(github.getToken()).thenReturn("test_token");

        jsonObject = new JSONObject();
        jsonObject.put("hooks_url", "https://api.github.com/orgs/test/hooks");
        jsonObject.put("issues_url", "https://api.github.com/orgs/test/issues");
        jsonObject.put("members_url", "https://api.github.com/orgs/test/members{/member}");
        jsonObject.put("public_members_url", "https://api.github.com/orgs/test/public_members{/member}");
        jsonObject.put("description", "Test Organization");
        jsonObject.put("name", "testorg");
        jsonObject.put("company", "Test Company");
        jsonObject.put("blog", "https://blog.test.org");
        jsonObject.put("location", "Test Location");
        jsonObject.put("email", "contact@test.org");
        jsonObject.put("twitter_username", "testorg");
        jsonObject.put("is_verified", true);
        jsonObject.put("has_organization_projects", true);
        jsonObject.put("has_repository_projects", true);
        jsonObject.put("public_repos", 10);
        jsonObject.put("public_gists", 5);
        jsonObject.put("followers", 100);
        jsonObject.put("following", 50);
        jsonObject.put("created_at", OffsetDateTime.now().toString());
        jsonObject.put("updated_at", OffsetDateTime.now().toString());
        jsonObject.put("archived_at", OffsetDateTime.now().toString());
        jsonObject.put("total_private_repos", 20);
        jsonObject.put("owned_private_repos", 15);
        jsonObject.put("private_gists", 2);
        jsonObject.put("disk_usage", 1024);
        jsonObject.put("collaborators", 30);
        jsonObject.put("billing_email", "billing@test.org");
        jsonObject.put("default_repository_permission", "read");
        jsonObject.put("members_can_create_repositories", true);
        jsonObject.put("two_factor_requirement_enabled", true);
        jsonObject.put("members_allowed_repository_creation_type", "all");
        jsonObject.put("members_can_create_public_repositories", true);
        jsonObject.put("members_can_create_private_repositories", true);
        jsonObject.put("members_can_create_internal_repositories", true);
        jsonObject.put("members_can_create_pages", true);
        jsonObject.put("members_can_fork_private_repositories", true);
        jsonObject.put("web_commit_signoff_required", true);
        jsonObject.put("members_can_create_public_pages", true);
        jsonObject.put("members_can_create_private_pages", true);
        jsonObject.put("advanced_security_enabled_for_new_repositories", true);
        jsonObject.put("dependabot_alerts_enabled_for_new_repositories", true);
        jsonObject.put("dependabot_security_updates_enabled_for_new_repositories", true);
        jsonObject.put("dependency_graph_enabled_for_new_repositories", true);
        jsonObject.put("secret_scanning_enabled_for_new_repositories", true);
        jsonObject.put("secret_scanning_push_protection_enabled_for_new_repositories", true);
        jsonObject.put("secret_scanning_push_protection_custom_link_enabled", true);
        jsonObject.put("secret_scanning_push_protection_custom_link", "https://test.org/custom_link");
        jsonObject.put("secret_scanning_validity_checks_enabled", true);
        jsonObject.put("plan", new JSONObject().put("name", "free").put("space", 976562499).put("collaborators", 0).put("private_repos", 0));

        organisation = new GHOrganisation(github, jsonObject, "testorg");
    }

    @Test
    void testGetName() {
        assertEquals("testorg", organisation.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("Test Organization", organisation.getDescription());
    }

    @Test
    void testGetCompany() {
        assertEquals("Test Company", organisation.getCompany());
    }

    @Test
    void testGetHooksUrl() {
        assertEquals("https://api.github.com/orgs/test/hooks", organisation.getHooksUrl());
    }

    @Test
    void testGetIssuesUrl() {
        assertEquals("https://api.github.com/orgs/test/issues", organisation.getIssuesUrl());
    }

    @Test
    void testGetMembersUrl() {
        assertEquals("https://api.github.com/orgs/test/members{/member}", organisation.getMembersUrl());
    }

    @Test
    void testGetPublicMembersUrl() {
        assertEquals("https://api.github.com/orgs/test/public_members{/member}", organisation.getPublicMembersUrl());
    }

    @Test
    void testGetFollowers() {
        assertEquals(100, organisation.getFollowers());
    }

    @Test
    void testGetBlog() {
        assertEquals("https://blog.test.org", organisation.getBlog());
    }

    @Test
    void testGetFollowing() {
        assertEquals(50, organisation.getFollowing());
    }

    @Test
    void testGetPublicGists() {
        assertEquals(5, organisation.getPublicGists());
    }

    @Test
    void testGetPublicRepos() {
        assertEquals(10, organisation.getPublicRepos());
    }

    @Test
    void testGetEmail() {
        assertEquals("contact@test.org", organisation.getEmail());
    }

    @Test
    void testGetArchivedAt() {
        assertNotNull(organisation.getArchivedAt());
    }

    @Test
    void testGetLocation() {
        assertEquals("Test Location", organisation.getLocation());
    }

    @Test
    void testGetCreatedAt() {
        assertNotNull(organisation.getCreatedAt());
    }

    @Test
    void testGetTwitterUsername() {
        assertEquals("testorg", organisation.getTwitterUsername());
    }

    @Test
    void testGetUpdatedAt() {
        assertNotNull(organisation.getUpdatedAt());
    }

    @Test
    void testIsHasOrganizationProjects() {
        assertTrue(organisation.isHasOrganizationProjects());
    }

    @Test
    void testIsHasRepositoryProjects() {
        assertTrue(organisation.isHasRepositoryProjects());
    }

    @Test
    void testIsVerified() {
        assertTrue(organisation.isVerified());
    }

    @Test
    void testGetPlan() {
        assertNotNull(organisation.getPlan());
        assertEquals("free", organisation.getPlan().getName());
    }

    @Test
    void testGetCollaborators() {
        assertEquals(30, organisation.getCollaborators());
    }

    @Test
    void testGetOwnedPrivateRepos() {
        assertEquals(15, organisation.getOwnedPrivateRepos());
    }

    @Test
    void testGetPrivateGists() {
        assertEquals(2, organisation.getPrivateGists());
    }

    @Test
    void testGetTotalPrivateRepos() {
        assertEquals(20, organisation.getTotalPrivateRepos());
    }

    @Test
    void testGetDiskUsage() {
        assertEquals(1024, organisation.getDiskUsage());
    }

    @Test
    void testGetBillingEmail() {
        assertEquals("billing@test.org", organisation.getBillingEmail());
    }

    @Test
    void testGetDefaultRepositoryPermission() {
        assertEquals("read", organisation.getDefaultRepositoryPermission());
    }

    @Test
    void testGetMembersAllowedRepositoryCreationType() {
        assertEquals("all", organisation.getMembersAllowedRepositoryCreationType());
    }

    @Test
    void testGetSecretScanningPushProtectionCustomLink() {
        assertEquals("https://test.org/custom_link", organisation.getSecretScanningPushProtectionCustomLink());
    }

    @Test
    void testIsAdvancedSecurityEnabledForNewRepositories() {
        assertTrue(organisation.isAdvancedSecurityEnabledForNewRepositories());
    }

    @Test
    void testIsDependabotAlertsEnabledForNewRepositories() {
        assertTrue(organisation.isDependabotAlertsEnabledForNewRepositories());
    }

    @Test
    void testIsDependabotSecurityUpdatesEnabledForNewRepositories() {
        assertTrue(organisation.isDependabotSecurityUpdatesEnabledForNewRepositories());
    }

    @Test
    void testIsDependencyGraphEnabledForNewRepositories() {
        assertTrue(organisation.isDependencyGraphEnabledForNewRepositories());
    }

    @Test
    void testIsMembersCanCreateInternalRepositories() {
        assertTrue(organisation.isMembersCanCreateInternalRepositories());
    }

    @Test
    void testIsMembersCanCreatePages() {
        assertTrue(organisation.isMembersCanCreatePages());
    }

    @Test
    void testIsMembersCanCreatePrivatePages() {
        assertTrue(organisation.isMembersCanCreatePrivatePages());
    }

    @Test
    void testIsMembersCanCreatePrivateRepositories() {
        assertTrue(organisation.isMembersCanCreatePrivateRepositories());
    }

    @Test
    void testIsMembersCanCreatePublicPages() {
        assertTrue(organisation.isMembersCanCreatePublicPages());
    }

    @Test
    void testIsMembersCanCreatePublicRepositories() {
        assertTrue(organisation.isMembersCanCreatePublicRepositories());
    }

    @Test
    void testIsMembersCanCreateRepositories() {
        assertTrue(organisation.isMembersCanCreateRepositories());
    }

    @Test
    void testIsMembersCanForkPrivateRepositories() {
        assertTrue(organisation.isMembersCanForkPrivateRepositories());
    }

    @Test
    void testIsSecretScanningEnabledForNewRepositories() {
        assertTrue(organisation.isSecretScanningEnabledForNewRepositories());
    }

    @Test
    void testIsSecretScanningPushProtectionCustomLinkEnabled() {
        assertTrue(organisation.isSecretScanningPushProtectionCustomLinkEnabled());
    }

    @Test
    void testIsSecretScanningPushProtectionEnabledForNewRepositories() {
        assertTrue(organisation.isSecretScanningPushProtectionEnabledForNewRepositories());
    }

    @Test
    void testIsSecretScanningValidityChecksEnabled() {
        assertTrue(organisation.isSecretScanningValidityChecksEnabled());
    }

    @Test
    void testIsTwoFactorRequirementEnabled() {
        assertTrue(organisation.isTwoFactorRequirementEnabled());
    }

    @Test
    void testIsWebCommitSignoffRequired() {
        assertTrue(organisation.isWebCommitSignoffRequired());
    }

    @Test
    void testFindRepositoryByName() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("name", "test-repo").put("topics", new JSONArray(List.of("test1", "test2"))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(response);

            GHRepository repo = organisation.findRepositoryByName("test-repo");
            assertNotNull(repo);
            assertEquals("test-repo", repo.getName());
        }
    }

    @Test
    void testGetRepositories() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray(List.of(new JSONObject().put("name", "repo1").put("topics", new JSONArray(List.of("test1", "test2"))), new JSONObject().put("name", "repo2").put("topics", new JSONArray(List.of("test1", "test2"))))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString())).thenReturn(new String[]{response, null});

            List<GHRepository> repos = organisation.getRepositories();
            assertNotNull(repos);
            assertEquals(2, repos.size());
        }
    }

    @Test
    void testGetTeams() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray(List.of(new JSONObject().put("name", "team1"), new JSONObject().put("name", "team2"))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString())).thenReturn(new String[]{response, null});

            List<GHTeam> teams = organisation.getTeams();
            assertNotNull(teams);
            assertEquals(2, teams.size());
        }
    }

    @Test
    void testFindTeamByName() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("name", "test-team").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(response);

            GHTeam team = organisation.findTeamByName("test-team");
            assertNotNull(team);
            assertEquals("test-team", team.getName());
        }
    }

    @Test
    void testHasMember() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithResponseCode(anyString(), anyString(), anyInt())).thenReturn(true);
            GHUser user = new GHUser(github, new JSONObject().put("login", "testuser"));
            assertTrue(organisation.hasMember(user));
        }
    }

    @Test
    void testRemoveMember() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequest(anyString(), anyString())).thenAnswer(invocation -> null);

            GHUser user = new GHUser(github, new JSONObject().put("login", "testuser"));
            assertDoesNotThrow(() -> organisation.removeMember(user));
        }
    }

    @Test
    void testListMembers() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray(List.of(new JSONObject().put("login", "user1"), new JSONObject().put("login", "user2"))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString())).thenReturn(new String[]{response, null});

            List<GHUser> members = organisation.listMembers();
            assertNotNull(members);
            assertEquals(2, members.size());
        }
    }

    @Test
    void testListEvents() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray(List.of(new JSONObject().put("type", "event1"), new JSONObject().put("type", "event2"))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString())).thenReturn(new String[]{response, null});

            List<GHEvent> events = organisation.listEvents();
            assertNotNull(events);
            assertEquals(2, events.size());
        }
    }

    @Test
    void testListRepositories() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray(List.of(new JSONObject().put("name", "repo1").put("topics", new JSONArray(List.of("test1", "test2"))), new JSONObject().put("name", "repo2").put("topics", new JSONArray(List.of("test1", "test2"))))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString())).thenReturn(new String[]{response, null});

            List<GHRepository> repos = organisation.getRepositories();
            assertNotNull(repos);
            assertEquals(2, repos.size());
        }
    }
}
