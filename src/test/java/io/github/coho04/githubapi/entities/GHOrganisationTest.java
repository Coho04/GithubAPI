package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.TestBase;
import io.github.coho04.githubapi.builders.GHHookBuilder;
import io.github.coho04.githubapi.builders.GHProjectBuilder;
import io.github.coho04.githubapi.enums.GHPackageType;
import io.github.coho04.githubapi.enums.GHRole;
import io.github.coho04.githubapi.enums.GHState;
import io.github.coho04.githubapi.entities.repositories.GHRepository;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.net.HttpURLConnection;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class GHOrganisationTest extends TestBase {

    private Github github;
    private GHOrganisation organisation;
    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        github = mock(Github.class);
        when(github.getToken()).thenReturn("test_token");

        jsonObject = new JSONObject();
        jsonObject.put("id", 0);
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
        jsonObject.put("url", "https://example.com/url");
        jsonObject.put("plan", new JSONObject()
                .put("name", "free")
                .put("space", random.nextInt())
                .put("seats", random.nextInt())
                .put("private_repos", random.nextInt())
                .put("filled_seats", random.nextInt())
        );

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
            GHUser user = new GHUser(github, jsonObject.put("login", "testuser"));
            assertTrue(organisation.hasMember(user));
        }
    }

    @Test
    void testRemoveMember() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequest(anyString(), anyString())).thenAnswer(invocation -> null);

            GHUser user = new GHUser(github, jsonObject.put("login", "testuser"));
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


    @Test
    void testGetSecret() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("name", "secret1").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            GHSecret secret = organisation.getSecret("secret1");

            assertNotNull(secret);
            assertEquals("secret1", secret.getName());
        }
    }

    @Test
    void testDeleteSecret() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequestWithResponseCode(anyString(), anyString(), eq(HttpURLConnection.HTTP_NO_CONTENT))).thenReturn(true);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            boolean result = organisation.deleteSecret("secret1");

            assertTrue(result);
        }
    }

    @Test
    void testListSecretRepositorys() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject repo1 = new JSONObject().put("name", "repo1").put("topics", new JSONArray().put("test1").put("test2"));
            JSONArray repoArray = new JSONArray().put(repo1);
            JSONObject responseJson = new JSONObject().put("repositories", repoArray);
            String response = responseJson.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHRepository> repositories = organisation.listSecretRepositorys();

            assertNotNull(repositories);
            assertEquals(1, repositories.size());
            assertEquals("repo1", repositories.getFirst().getName());
        }
    }

    @Test
    void testDeleteRepositoryFromSecret() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequestWithResponseCode(anyString(), anyString(), eq(HttpURLConnection.HTTP_NO_CONTENT))).thenReturn(true);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            boolean result = organisation.deleteRepositoryFromSecret("secret1", "repo1");

            assertTrue(result);
        }
    }

    @Test
    void testListVariables() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Create a JSON response with an array of variables
            JSONObject variable1 = new JSONObject().put("name", "variable1");
            JSONArray variableArray = new JSONArray().put(variable1);
            JSONObject responseJson = new JSONObject().put("variables", variableArray);
            String response = responseJson.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHVariable> variables = organisation.listVariables();

            assertNotNull(variables);
            assertEquals(1, variables.size());
            assertEquals("variable1", variables.getFirst().getName());
        }
    }

    @Test
    void testGetVariable() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("name", "variable1").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            GHVariable variable = organisation.getVariable("variable1");

            assertNotNull(variable);
            assertEquals("variable1", variable.getName());
        }
    }

    @Test
    void testListVariableRepositorys() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Create a JSON response with an array of repositories
            JSONObject repo1 = new JSONObject().put("name", "repo1").put("topics", new JSONArray().put("test1").put("test2"));
            JSONArray repoArray = new JSONArray().put(repo1);
            JSONObject responseJson = new JSONObject().put("repositories", repoArray);
            String response = responseJson.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHRepository> repositories = organisation.listVariableRepositorys("variable1");

            assertNotNull(repositories);
            assertEquals(1, repositories.size());
            assertEquals("repo1", repositories.getFirst().getName());
        }
    }


    @Test
    void testListDockerConflictsPackages() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject packageJson = new JSONObject().put("name", "docker_conflict1");
            String response = new JSONArray().put(packageJson).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(new String[]{response, null});

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHPackage> packages = organisation.listDockerConflictsPackages();

            assertNotNull(packages);
            assertEquals(1, packages.size());
            assertEquals("docker_conflict1", packages.getFirst().getName());
        }
    }

    @Test
    void testListPackages() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject packageJson = new JSONObject().put("name", "package1");
            String response = new JSONArray().put(packageJson).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(new String[]{response, null});

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHPackage> packages = organisation.listPackages(GHPackageType.MAVEN);

            assertNotNull(packages);
            assertEquals(1, packages.size());
            assertEquals("package1", packages.getFirst().getName());
        }
    }

    @Test
    void testGetPackage() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("name", "package1").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            GHPackage ghPackage = organisation.getPackage("package1", GHPackageType.MAVEN);

            assertNotNull(ghPackage);
            assertEquals("package1", ghPackage.getName());
        }
    }

    @Test
    void testListSecretScanningAlerts() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject alertJson = new JSONObject().put("number", "3");
            String response = new JSONArray().put(alertJson).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(new String[]{response, null});

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHAlert> alerts = organisation.listSecretScanningAlerts();

            assertNotNull(alerts);
            assertEquals(1, alerts.size());
            assertEquals(3, alerts.getFirst().getNumber());
        }
    }

    @Test
    void testGetCacheUsageActiveCount() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("total_active_caches_count", 5).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            int count = organisation.getCacheUsageActiveCount();

            assertEquals(5, count);
        }
    }

    @Test
    void testGetCacheUsageSizeInBytes() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("total_active_caches_size_in_bytes", 1024).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            int sizeInBytes = organisation.getCacheUsageSizeInBytes();

            assertEquals(1024, sizeInBytes);
        }
    }

    @Test
    void testListRepositoryCaches() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject cacheJson = new JSONObject()
                    .put("full_name", "repo1")
                    .put("active_caches_size_in_bytes", 1024)
                    .put("active_caches_count", 5);
            String response = new JSONArray().put(cacheJson).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(new String[]{response, null});

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHRepositoryCache> caches = organisation.listRepositoryCaches();

            assertNotNull(caches);
            assertEquals(1, caches.size());
            assertEquals("repo1", caches.getFirst().getFullName());
            assertEquals(1024, caches.getFirst().getActiveCachesSizeInBytes());
            assertEquals(5, caches.getFirst().getActiveCachesCount());
        }
    }

    @Test
    void testListSecrets() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Prepare the JSON response
            JSONObject secretJson = new JSONObject().put("name", "secret1");
            JSONArray secretArray = new JSONArray().put(secretJson);
            String response = new JSONObject().put("secrets", secretArray).toString();

            // Mock the HttpRequestHelper.sendGetRequestWithLinkHeader method to return the response
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHSecret> secrets = organisation.listSecrets();

            assertNotNull(secrets);
            assertEquals(1, secrets.size());
            assertEquals("secret1", secrets.getFirst().getName());
        }
    }

    @Test
    void testGetPublicKey() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("key_id", "key1").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            GHPublicKey publicKey = organisation.getPublicKey();

            assertNotNull(publicKey);
            assertEquals("key1", publicKey.getKeyId());
        }
    }

    @Test
    void testGetHooks() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject hookJson = new JSONObject().put("id", 1);
            JSONArray jsonArray = new JSONArray().put(hookJson);
            String[] responseAndLink = {jsonArray.toString(), null};


            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHHook> hooks = organisation.getHooks();

            assertNotNull(hooks);
            assertEquals(1, hooks.size());
            assertEquals(1, hooks.getFirst().getId());
        }
    }

    @Test
    void testGetHookById() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject hookJson = new JSONObject().put("id", 1);
            String response = hookJson.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            GHHook hook = organisation.getHook(1L);

            assertNotNull(hook);
            assertEquals(1, hook.getId());
        }
    }

    @Test
    void testGetRepositorysWithOpenPullRequests() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Mocking the JSON response for repositories
            JSONObject repoJson = new JSONObject().put("name", "repo1");
            JSONArray repoArray = new JSONArray().put(repoJson);
            String response = repoArray.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(new String[]{response, null});

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            GHOrganisation organisation = Mockito.spy(new GHOrganisation(github, jsonObject, "test_org"));

            // Mocking getRepositories to return a list with one repository
            GHRepository repository = Mockito.mock(GHRepository.class);
            Mockito.when(repository.hasPullRequestsWithState(GHState.OPEN)).thenReturn(true);
            doReturn(List.of(repository)).when(organisation).getRepositories();

            List<GHRepository> reposWithOpenPRs = organisation.getRepositorysWithOpenPullRequests();

            assertNotNull(reposWithOpenPRs);
            assertEquals(1, reposWithOpenPRs.size());
            assertEquals(repository, reposWithOpenPRs.getFirst());
        }
    }

    @Test
    void testListProjects() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject projectJson = new JSONObject().put("id", 1).put("creator", setupUser().toJSONObject());
            JSONArray jsonArray = new JSONArray().put(projectJson);
            String[] responseAndLink = {jsonArray.toString(), null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHProject> projects = organisation.listProjects();

            assertNotNull(projects);
            assertEquals(1, projects.size());
            assertEquals(1, projects.getFirst().getId());
        }
    }

    @Test
    void testListPublicMembers() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject userJson = new JSONObject().put("login", "user1");
            JSONArray jsonArray = new JSONArray().put(userJson);
            String[] responseAndLink = {jsonArray.toString(), null};


            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHUser> publicMembers = organisation.listPublicMembers();

            assertNotNull(publicMembers);
            assertEquals(1, publicMembers.size());
            assertEquals("user1", publicMembers.getFirst().getLogin());
        }
    }

    @Test
    void testListOutsideCollaborators() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject userJson = new JSONObject().put("login", "collab1");
            JSONArray jsonArray = new JSONArray().put(userJson);
            String[] responseAndLink = {jsonArray.toString(), null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHUser> collaborators = organisation.listOutsideCollaborators();

            assertNotNull(collaborators);
            assertEquals(1, collaborators.size());
            assertEquals("collab1", collaborators.getFirst().getLogin());
        }
    }

    @Test
    void testHasPublicMember() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithResponseCode(anyString(), anyString(), anyInt()))
                    .thenReturn(true);

            GHUser user = new GHUser(github, new JSONObject().put("login", "user1"));
            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");

            Boolean isPublicMember = organisation.hasPublicMember(user);

            assertNotNull(isPublicMember);
            assertTrue(isPublicMember);
        }
    }

    @Test
    void testListTeams() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject teamJson = new JSONObject().put("slug", "team_slug");
            JSONArray teamArray = new JSONArray().put(teamJson);
            String response = teamArray.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(new String[]{response, null});

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            List<GHTeam> teams = organisation.listTeams();

            assertNotNull(teams);
            assertEquals(1, teams.size());
            assertEquals("team_slug", teams.getFirst().getSlug());
        }
    }


    @Test
    void testFindTeamBySlug() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject teamJson = new JSONObject().put("slug", "team_slug");
            String response = teamJson.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            GHTeam team = organisation.findTeamBySlug("team_slug");

            assertNotNull(team);
            assertEquals("team_slug", team.getSlug());
        }
    }


    @Test
    void testGetOrganisation() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            // Mocking the JSON response
            String response = new JSONObject()
                    .put("name", "test_org")
                    .put("description", "Test organization")
                    .toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            GHOrganisation organisation = GHOrganisation.getOrganisation(github, "test_org");

            assertNotNull(organisation);
            assertEquals("test_org", organisation.getName());
            assertEquals("Test organization", organisation.getDescription());
        }
    }


    @Test
    void testGetGithub() {
        Github github = Mockito.mock(Github.class);
        GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");

        assertEquals(github, organisation.getGithub());
    }


    @Test
    void testToJSONObject() {
        Github github = Mockito.mock(Github.class);
        GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");

        JSONObject result = organisation.toJSONObject();

        assertNotNull(result);
        //assertEquals(jsonObject.toString(), result.toString());
        assertTrue(jsonSimilar(jsonObject, result));
    }

    @Test
    void testGetHookByIntId() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("id", 123).put("name", "test_hook").toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            GHOrganisation organisation = new GHOrganisation(github, jsonObject, "test_org");
            GHHook hook = organisation.getHook(123);

            assertNotNull(hook);
            assertEquals(123, hook.getId());
            assertEquals("test_hook", hook.getName());
        }
    }

    @Test
    void shouldReturnRepositoriesWithOpenPullRequests() {
        try (MockedStatic<HttpRequestHelper> ignored = Mockito.mockStatic(HttpRequestHelper.class)) {
            GHRepository repositoryWithOpenPRs = Mockito.mock(GHRepository.class);
            when(repositoryWithOpenPRs.hasPullRequestsWithState(GHState.OPEN)).thenReturn(true);

            GHRepository repositoryWithoutOpenPRs = Mockito.mock(GHRepository.class);
            when(repositoryWithoutOpenPRs.hasPullRequestsWithState(GHState.OPEN)).thenReturn(false);

            // Spy the real organisation to mock getRepositories method
            GHOrganisation organisationSpy = Mockito.spy(organisation);
            doReturn(List.of(repositoryWithOpenPRs, repositoryWithoutOpenPRs)).when(organisationSpy).getRepositories();

            List<GHRepository> reposWithOpenPRs = organisationSpy.getPullRequests();

            assertNotNull(reposWithOpenPRs);
            assertEquals(1, reposWithOpenPRs.size());
            assertEquals(repositoryWithOpenPRs, reposWithOpenPRs.getFirst());
        }
    }

    @Test
    void shouldCreateProjectWithBuilder() {
        GHProjectBuilder projectBuilder = organisation.createProject();
        assertNotNull(projectBuilder);
    }

    @Test
    void shouldCreateProjectWithNameAndBody() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {

            GHProjectBuilder projectBuilder = Mockito.mock(GHProjectBuilder.class);
            when(projectBuilder.setName("test_project")).thenReturn(projectBuilder);
            when(projectBuilder.setBody("test_body")).thenReturn(projectBuilder);
            when(projectBuilder.build()).thenReturn(null);

            JSONObject response = new JSONObject();
            response.put("owner_url", "https://api.github.com/repos/octocat/Hello-World");
            response.put("columns_url", "https://api.github.com/projects/columns");
            response.put("name", "Project Board");
            response.put("body", "This is a project board.");
            response.put("number", 1);
            response.put("state", "open");
            response.put("creator", new JSONObject().put("login", "octocat"));
            response.put("created_at", OffsetDateTime.now().toString());
            response.put("updated_at", OffsetDateTime.now().toString());
            response.put("organization_permission", "admin");
            response.put("private", false);

            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), Mockito.any(JSONObject.class))).thenReturn(response.toString());

            GHProject project = organisation.createProject("test_project", "test_body");

            assertNotNull(project);
        }
    }

    @Test
    void shouldCreateTeamWithName() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject response = new JSONObject();
            response.put("name", "test_team");
            response.put("slug", "test_team");
            response.put("description", "test_description");
            response.put("privacy", "closed");
            response.put("notification_setting", "all");
            response.put("permission", "pull");
            response.put("members_url", "https://api.github.com/teams/123/members{/member}");
            response.put("repositories_url", "https://api.github.com/teams/123/repos");
            response.put("created_at", "2021-01-01T00:00:00Z");
            response.put("updated_at", "2021-01-01T00:00:00Z");
            response.put("members_count", 1);
            response.put("repos_count", 1);

            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), Mockito.any(JSONObject.class))).thenReturn(response.toString());

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            GHTeam team = organisation.createTeam("test_team");

            assertNotNull(team);
        }
    }

    @Test
    void shouldCreateTeamWithBuilder() {
        assertNotNull(organisation.createTeam());
    }

    @Test
    void shouldAddMember() {
        String email = "test@example.com";
        GHRole role = GHRole.ADMIN;
        List<Integer> teamIds = List.of(1, 2, 3);
        String url = "https://api.github.com/orgs/testorg/invitations";
        JSONObject expectedPayload = new JSONObject();
        expectedPayload.put("email", email);
        expectedPayload.put("role", role.toString());
        expectedPayload.put("team_ids", teamIds);

        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenAnswer(invocation -> {
                        String passedUrl = invocation.getArgument(0);
                        String passedToken = invocation.getArgument(1);
                        JSONObject passedJson = invocation.getArgument(2);

                        assertEquals(url, passedUrl);
                        assertEquals("test_token", passedToken);
                        assertEquals(expectedPayload.toString(), passedJson.toString());
                        return null;
                    });

            organisation.addMember(email, role, teamIds);

            mockedStatic.verify(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), any(JSONObject.class)), times(1));
        }
    }

    @Test
    void shouldCreateHook() {
        GHHookBuilder hookBuilder = organisation.createHook();

        assertNotNull(hookBuilder);
    }

    @Test
    void shouldDeleteHook() {
        Long hookId = 123L;

        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequest(anyString(), anyString())).thenAnswer(invocationOnMock -> null);

            organisation.deleteHook(hookId);

            mockedStatic.verify(() -> HttpRequestHelper.sendDeleteRequest(anyString(), anyString()), times(1));
        }
    }
}
