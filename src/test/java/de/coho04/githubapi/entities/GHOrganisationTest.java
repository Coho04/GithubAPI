package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.enums.GHPackageType;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.repositories.GHRepository;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.net.HttpURLConnection;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
        Random random = new Random();
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
            assertEquals("repo1", repositories.get(0).getName());
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
            assertEquals("variable1", variables.get(0).getName());
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
            assertEquals("repo1", repositories.get(0).getName());
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
            assertEquals("docker_conflict1", packages.get(0).getName());
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
            assertEquals("package1", packages.get(0).getName());
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
            assertEquals(3, alerts.get(0).getNumber());
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
            assertEquals("repo1", caches.get(0).getFullName());
            assertEquals(1024, caches.get(0).getActiveCachesSizeInBytes());
            assertEquals(5, caches.get(0).getActiveCachesCount());
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
            assertEquals("secret1", secrets.get(0).getName());
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
            assertEquals(1, hooks.get(0).getId());
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
            Mockito.doReturn(List.of(repository)).when(organisation).getRepositories();

            List<GHRepository> reposWithOpenPRs = organisation.getRepositorysWithOpenPullRequests();

            assertNotNull(reposWithOpenPRs);
            assertEquals(1, reposWithOpenPRs.size());
            assertEquals(repository, reposWithOpenPRs.get(0));
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
            assertEquals(1, projects.get(0).getId());
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
            assertEquals("user1", publicMembers.get(0).getLogin());
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
            assertEquals("collab1", collaborators.get(0).getLogin());
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
            assertEquals("team_slug", teams.get(0).getSlug());
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

    private boolean jsonSimilar(JSONObject actual, JSONObject expected) {
        if (actual == null || expected == null) return false;
        System.out.println(actual.toString());
        System.out.println(expected.toString());
        //if (actual.length() != expected.length()) return false;
        for (String key : expected.keySet()) {
            if (!actual.has(key)) {
                System.out.println("Key not found: " + key);
                return false;
            }
            if (actual.isNull(key) && expected.isNull(key)) continue;
            if (actual.isNull(key) || expected.isNull(key)) return false;
            if (!actual.get(key).toString().equals(expected.get(key).toString())) {
                System.out.println("Value not equal: " + key + " " + actual.get(key).toString() + " " + expected.get(key).toString());
                return false;
            }
        }
        return true;
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


    private GHUser setupUser() {
        Random random = new Random();
        return new GHUser(Mockito.mock(Github.class), new JSONObject()
                .put("contributions", String.valueOf(random.nextInt()))
                .put("gists_url", "https://example.com/gists_url")
                .put("site_admin", true)
                .put("gravatar_id", "A principal idea stands upon somebody else's legs.")
                .put("starred_url", "https://example.com/starred_url")
                .put("followers_url", "https://example.com/followers_url")
                .put("following_url", "https://example.com/following_url")
                .put("subscriptions_url", "https://example.com/subscriptions_url")
                .put("received_events_url", "https://example.com/received_events_url")
                .put("organizations_url", "https://example.com/organizations_url")
                .put("type", "An idea stands upon somebody else's legs.")
                .put("login", "Sex stands upon somebody else's legs.")
                .put("repos_url", "https://example.com/repos_url")
                .put("avatar_url", "https://example.com/avatar_url")
                .put("id", String.valueOf(random.nextInt()))
                .put("node_id", "A token of gratitude stands upon somebody else's legs.")
                .put("html_url", "https://example.com/html_url")
                .put("url", "https://example.com/url")
                .put("events_url", "https://example.com/events_url")
        );
    }
}
