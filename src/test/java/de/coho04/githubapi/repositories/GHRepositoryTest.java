package de.coho04.githubapi.repositories;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.entities.repositories.GHBranch;
import de.coho04.githubapi.entities.repositories.GHFile;
import de.coho04.githubapi.entities.repositories.GHIssue;
import de.coho04.githubapi.entities.repositories.GHRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import de.coho04.githubapi.builders.GHFileBuilder;
import de.coho04.githubapi.builders.GHIssueBuilder;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.entities.*;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.mockito.MockedStatic;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class GHRepositoryTest {

    private Github github;
    private GHRepository repository;
    private JSONObject jsonObject;
    private OffsetDateTime now;

    @BeforeEach
    void setUp() {
        now = OffsetDateTime.now();
        github = mock(Github.class);
        when(github.getToken()).thenReturn("test_token");
        jsonObject = new JSONObject();
        jsonObject.put("owner", new JSONObject().put("login", "octocat"));
        jsonObject.put("permissions", new JSONObject().put("login", "octocat")
                .put("pull", false)
                .put("maintain", false)
                .put("admin", false)
                .put("triage", false)
                .put("push", false));
        jsonObject.put("name", "test-repo");
        jsonObject.put("full_name", "test-owner/test-repo");
        jsonObject.put("forks", 5);
        jsonObject.put("open_issues_count", 10);
        jsonObject.put("stargazers_count", 20);
        jsonObject.put("size", 100);
        jsonObject.put("branches_url", "expected_branches_url");
        jsonObject.put("language", "Java");
        jsonObject.put("default_branch", "main");
        jsonObject.put("url", "https://api.github.com/repos/test/repo");
        jsonObject.put("topics", new JSONArray(List.of("topic1", "topic2")));

        jsonObject.put("issue_comment_url", "expected_issue_comment_url");
        jsonObject.put("archive_url", "expected_archive_url");
        jsonObject.put("labels_url", "expected_labels_url");
        jsonObject.put("language", "expected_language");
        jsonObject.put("releases_url", "expected_releases_url");
        jsonObject.put("subscribers_url", "expected_subscribers_url");
        jsonObject.put("subscription_url", "expected_subscription_url");
        jsonObject.put("forks_url", "expected_forks_url");
        jsonObject.put("git_refs_url", "expected_git_refs_url");
        jsonObject.put("watchers", 123);
        jsonObject.put("svn_url", "expected_svn_url");
        jsonObject.put("created_at", now.toString());
        jsonObject.put("updated_at", now.toString());
        jsonObject.put("blobs_url", "expected_blobs_url");
        jsonObject.put("clone_url", "expected_clone_url");
        jsonObject.put("collaborators_url", "expected_collaborators_url");
        jsonObject.put("comments_url", "expected_comments_url");
        jsonObject.put("commits_url", "expected_commits_url");
        jsonObject.put("visibility", "expected_visibility");
        jsonObject.put("compare_url", "expected_compare_url");
        jsonObject.put("contents_url", "expected_contents_url");
        jsonObject.put("contributors_url", "expected_contributors_url");
        jsonObject.put("default_branch", "expected_default_branch");
        jsonObject.put("deployments_url", "expected_deployments_url");
        jsonObject.put("description", "expected_description");
        jsonObject.put("downloads_url", "expected_downloads_url");
        jsonObject.put("statuses_url", "expected_statuses_url");
        jsonObject.put("full_name", "expected_full_name");
        jsonObject.put("git_commits_url", "expected_git_commits_url");
        jsonObject.put("languages_url", "expected_languages_url");
        jsonObject.put("git_tags_url", "expected_git_tags_url");
        jsonObject.put("git_url", "expected_git_url");
        jsonObject.put("ssh_url", "expected_ssh_url");
        jsonObject.put("assignees_url", "expected_assignees_url");
        jsonObject.put("open_issues", 10);
        jsonObject.put("watchers_count", 20);
        jsonObject.put("hooks_url", "expected_hooks_url");
        jsonObject.put("issue_events_url", "expected_issue_events_url");
        jsonObject.put("homepage", "expected_homepage");
        jsonObject.put("issues_url", "expected_issues_url");
        jsonObject.put("forks_count", 5);
        jsonObject.put("keys_url", "expected_keys_url");
        jsonObject.put("web_commit_signoff_required", true);
        jsonObject.put("has_projects", true);
        jsonObject.put("has_downloads", true);
        jsonObject.put("archived", true);
        jsonObject.put("disabled", true);
        jsonObject.put("has_pages", true);
        jsonObject.put("has_discussions", true);
        jsonObject.put("has_wiki", true);
        jsonObject.put("allow_forking", true);
        jsonObject.put("has_issues", true);
        jsonObject.put("fork", true);
        jsonObject.put("private", true);
        jsonObject.put("is_template", true);

        jsonObject.put("notifications_url", "https://example.com/notifications_url");
        jsonObject.put("pulls_url", "https://example.com/pulls_url");
        jsonObject.put("stargazers_url", "https://example.com/stargazers_url");
        jsonObject.put("tags_url", "https://example.com/tags_url");
        jsonObject.put("teams_url", "https://example.com/teams_url");
        jsonObject.put("trees_url", "https://example.com/trees_url");
        jsonObject.put("merges_url", "https://example.com/merges_url");
        jsonObject.put("milestones_url", "https://example.com/milestones_url");
        jsonObject.put("mirror_url", "https://example.com/mirror_url");
        jsonObject.put("pushed_at", now.toString());


        JSONObject license = new JSONObject();
        license.put("name", "Organisational culture stands upon somebody else's legs.");
        license.put("spdx_id", "Trickery stands upon somebody else's legs.");
        license.put("key", "Chair number eleven stands upon somebody else's legs.");
        license.put("url", "https://example.com/url");
        license.put("node_id", "Nothingness stands upon somebody else's legs.");
        jsonObject.put("license", license);
        repository = new GHRepository(jsonObject, github);
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals(jsonObject.getString("name"), new GHRepository(jsonObject, github).getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        jsonObject.remove("name");
        assertNull(new GHRepository(jsonObject, github).getName());
    }

    @Test
    void shouldReturnCorrectOwnerLogin() {
        assertEquals("octocat", new GHRepository(jsonObject, github).getOwner().getLogin());
    }

    @Test
    void shouldReturnNullWhenOwnerIsNotPresent() {
        jsonObject.remove("owner");
        assertNull(new GHRepository(jsonObject, github).getOwner());
    }


    @Test
    void testGetRepository() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = jsonObject.toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHRepository repo = GHRepository.getRepository(github, "test-owner", "test-repo");
            assertNotNull(repo);
            assertEquals("test-repo", repo.getName());
        }
    }

    @Test
    void testCreateIssue() {
        GHIssueBuilder issueBuilder = repository.createIssue("New Issue");
        assertNotNull(issueBuilder);
        assertEquals("New Issue", issueBuilder.getTitle());
    }

    @Test
    void testGetBranches() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject main = new JSONObject().put("name", "main").put("commit", new JSONObject());
            JSONObject master = new JSONObject().put("name", "master").put("commit", new JSONObject());
            String response = new JSONArray(List.of(main, master)).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenAnswer(invocation -> {
                        String url = invocation.getArgument(0);
                        String token = invocation.getArgument(1);
                        if (url.equals("https://api.github.com/repos/test/repo/branches") && token.equals("test_token")) {
                            return response;
                        }
                        return null;
                    });

            System.out.println("Calling getBranches...");
            Map<String, GHBranch> branches = repository.getBranches();
            System.out.println("Branches returned: " + branches);

            assertNotNull(branches);
            assertTrue(branches.containsKey("main"));
            assertTrue(branches.containsKey("master"));
        }
    }

    @Test
    void testSendGetRequest() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String url = "https://api.github.com/repos/test/repo/branches";
            String token = "test_token";
            String response = "[{\"name\":\"main\",\"commit\":{}},{\"name\":\"master\",\"commit\":{}}]";

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(url, token)).thenReturn(response);

            String actualResponse = HttpRequestHelper.sendGetRequest(url, token);
            assertEquals(response, actualResponse);
        }
    }


    @Test
    void testGetContributors() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray(List.of(new JSONObject().put("login", "contributor1"))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHUser> contributors = repository.getContributors();
            assertNotNull(contributors);
            assertFalse(contributors.isEmpty());
            assertEquals("contributor1", contributors.get(0).getLogin());
        }
    }

    @Test
    void testGetIssues() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray(List.of(new JSONObject().put("title", "Issue 1"))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHIssue> issues = repository.getIssues();
            assertNotNull(issues);
            assertFalse(issues.isEmpty());
            assertEquals("Issue 1", issues.get(0).getTitle());
        }
    }

    @Test
    void testAddFile() {
        GHFileBuilder fileBuilder = repository.addFile();
        assertNotNull(fileBuilder);
        assertEquals(repository, fileBuilder.getRepository());
    }

    @Test
    void testAddFileWithBranch() {
        GHBranch branch = new GHBranch(new JSONObject().put("name", "main").put("commit", new JSONObject()));
        GHFileBuilder fileBuilder = repository.addFile(branch);
        assertNotNull(fileBuilder);
        assertEquals(repository, fileBuilder.getRepository());
        assertEquals(branch, fileBuilder.getBranch());
    }

    @Test
    void testAddFileWithDetails() {
        GHBranch branch = new GHBranch(new JSONObject().put("name", "main").put("commit", new JSONObject()));
        GHFileBuilder fileBuilder = repository.addFile(branch, "path/to/file.txt", "Test content", "Test commit message");
        assertNotNull(fileBuilder);
        assertEquals(repository, fileBuilder.getRepository());
        assertEquals(branch, fileBuilder.getBranch());
        assertEquals("path/to/file.txt", fileBuilder.getPath());
        assertEquals("Test content", fileBuilder.getContent());
        assertEquals("Test commit message", fileBuilder.getMessage());
    }

    @Test
    void testGetAllFilenames() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONArray(List.of(new JSONObject().put("type", "file").put("name", "file1.txt"))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(new String[]{response, null});

            List<String> filenames = repository.getAllFilenames();
            assertNotNull(filenames);
            assertFalse(filenames.isEmpty());
            assertEquals("file1.txt", filenames.get(0));
        }
    }

    @Test
    void testGetForks() {
        assertEquals(5, repository.getForks());
    }

    @Test
    void testGetOpenIssuesCount() {
        assertEquals(10, repository.getOpenIssuesCount());
    }

    @Test
    void testGetSize() {
        assertEquals(100, repository.getSize());
    }

    @Test
    void testGetStargazersCount() {
        assertEquals(20, repository.getStargazersCount());
    }

    @Test
    void testGetTopics() {
        List<String> topics = repository.getTopics();
        assertNotNull(topics);
        assertFalse(topics.isEmpty());
        assertTrue(topics.contains("topic1"));
        assertTrue(topics.contains("topic2"));
    }


    @Test
    void testListPullRequests() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject pr1 = new JSONObject().put("id", 1).put("state", "open").put("user", setupUser().toJSONObject());
            JSONObject pr2 = new JSONObject().put("id", 2).put("state", "closed").put("user", setupUser().toJSONObject());
            String response = new JSONArray(List.of(pr1, pr2)).toString();

            String[] responseAndLink = {response, null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);

            List<GHPullRequest> pullRequests = repository.listPullRequests();
            assertNotNull(pullRequests);
            assertEquals(2, pullRequests.size());
        }
    }

    @Test
    void testHasPullRequestsWithState() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject pr1 = new JSONObject().put("id", 1).put("state", "open").put("user", setupUser().toJSONObject());
            JSONObject pr2 = new JSONObject().put("id", 2).put("state", "closed").put("user", setupUser().toJSONObject());
            String response = new JSONArray(List.of(pr1, pr2)).toString();
            String[] responseAndLink = {response, null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);
            boolean hasOpenPRs = repository.hasPullRequestsWithState(GHState.OPEN);
            boolean hasClosedPRs = repository.hasPullRequestsWithState(GHState.CLOSED);

            assertTrue(hasOpenPRs);
            assertTrue(hasClosedPRs);
        }
    }

    @Test
    void testUpdateTopics() {
        List<String> topics = List.of("java", "github");
        repository.updateTopics(topics);
        assertEquals(topics, repository.getTopics());
    }

    @Test
    void testUpdateHomePage() {
        assertDoesNotThrow(() -> repository.updateHomePage());
    }

    @Test
    void testListEvents() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject event1 = new JSONObject().put("id", "1");
            JSONObject event2 = new JSONObject().put("id", "2");
            String response = new JSONArray(List.of(event1, event2)).toString();
            String[] responseAndLink = {response, null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);


            List<GHEvent> events = repository.listEvents();
            assertNotNull(events);
            assertEquals(2, events.size());
        }
    }

    @Test
    void testListArtifacts() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject workflowRun = new JSONObject().put("id", "1");
            workflowRun.put("repository_id", "1");
            workflowRun.put("head_repository_id", "1");
            workflowRun.put("head_branch", "main");
            workflowRun.put("head_sha", "123456");
            JSONObject artifact1 = new JSONObject().put("id", "1").put("workflow_run", workflowRun);
            JSONObject artifact2 = new JSONObject().put("id", "2").put("workflow_run", workflowRun);
            String response = new JSONArray(List.of(artifact1, artifact2)).toString();
            String[] responseAndLink = {response, null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);

            List<GHArtifact> artifacts = repository.listArtifacts();
            assertNotNull(artifacts);
            assertEquals(2, artifacts.size());
        }
    }

    @Test
    void testGetArtifact() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject workflowRun = new JSONObject().put("id", "1");
            workflowRun.put("repository_id", "1");
            workflowRun.put("head_repository_id", "1");
            workflowRun.put("head_branch", "main");
            workflowRun.put("head_sha", "123456");
            JSONObject artifact = new JSONObject().put("id", "1").put("workflow_run", workflowRun);
            String response = artifact.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHArtifact fetchedArtifact = repository.getArtifact(1);
            assertNotNull(fetchedArtifact);
            assertEquals(1, fetchedArtifact.getId());
        }
    }

    @Test
    void testDeleteArtifact() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequestWithResponseCode(anyString(), anyString(), anyInt()))
                    .thenReturn(true);

            boolean deleted = repository.deleteArtifact(1);
            assertTrue(deleted);
        }
    }

    @Test
    void testGetCache() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject cache = new JSONObject().put("size", "100MB");
            String response = cache.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHRepositoryCache repositoryCache = repository.getCache();
            assertNotNull(repositoryCache);
        }
    }

    @Test
    void testListActionsCaches() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject cache1 = new JSONObject().put("id", "1");
            JSONObject cache2 = new JSONObject().put("id", "2");
            String response = new JSONArray(List.of(cache1, cache2)).toString();
            String[] responseAndLink = {response, null};

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(anyString(), anyString()))
                    .thenReturn(responseAndLink);

            List<GHActionsCache> caches = repository.listActionsCaches();
            assertNotNull(caches);
            assertEquals(2, caches.size());
        }
    }

    @Test
    void testDeleteActionsCacheByKey() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequest(anyString(), anyString()))
                    .thenAnswer(invocation -> null); // Verwenden Sie `thenAnswer` anstelle von `thenReturn` für void-Methoden

            assertDoesNotThrow(() -> repository.deleteActionsCache("key1"));

            mockedStatic.verify(() -> HttpRequestHelper.sendDeleteRequest(
                    eq("https://api.github.com/repos/test/repo/actions/caches?key=key1"), eq("test_token")
            ));
        }
    }



    @Test
    void testDeleteActionsCacheById() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendDeleteRequestWithResponseCode(anyString(), anyString(), anyInt()))
                    .thenReturn(true);

            boolean deleted = repository.deleteActionsCache(1);
            assertTrue(deleted);
        }
    }

    @Test
    void testListOrganisationSecrets() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject secret1 = new JSONObject().put("name", "secret1");
            JSONObject secret2 = new JSONObject().put("name", "secret2");
            String response = new JSONObject().put("secrets", new JSONArray(List.of(secret1, secret2))).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHSecret> secrets = repository.listOrganisationSecrets();
            assertNotNull(secrets);
            assertEquals(2, secrets.size());
        }
    }

    @Test
    void testListRepositorySecrets() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject secret1 = new JSONObject().put("name", "secret1");
            JSONObject secret2 = new JSONObject().put("name", "secret2");
            String response = new JSONObject().put("secrets", new JSONArray(List.of(secret1, secret2))).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHSecret> secrets = repository.listRepositorySecrets();
            assertNotNull(secrets);
            assertEquals(2, secrets.size());
        }
    }

    @Test
    void testGetPublicKey() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject publicKey = new JSONObject().put("key", "publicKey");
            String response = publicKey.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHPublicKey fetchedPublicKey = repository.getPublicKey();
            assertNotNull(fetchedPublicKey);
            assertEquals("publicKey", fetchedPublicKey.getKey());
        }
    }

    @Test
    void testGetSecret() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject secret = new JSONObject().put("name", "secret1");
            String response = secret.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHSecret fetchedSecret = repository.getSecret("secret1");
            assertNotNull(fetchedSecret);
            assertEquals("secret1", fetchedSecret.getName());
        }
    }

    @Test
    void testListEnvironmentSecrets() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject secret1 = new JSONObject().put("name", "secret1");
            JSONObject secret2 = new JSONObject().put("name", "secret2");
            String response = new JSONObject().put("secrets", new JSONArray(List.of(secret1, secret2))).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHSecret> secrets = repository.listEnvironmentSecrets("environment1");
            assertNotNull(secrets);
            assertEquals(2, secrets.size());
        }
    }

    @Test
    void testGetEnvironmentPublicKey() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject publicKey = new JSONObject().put("key", "environmentPublicKey");
            String response = publicKey.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHPublicKey fetchedPublicKey = repository.getEnvironmentPublicKey("environment1");
            assertNotNull(fetchedPublicKey);
            assertEquals("environmentPublicKey", fetchedPublicKey.getKey());
        }
    }

    @Test
    void testGetEnvironmentSecret() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject secret = new JSONObject().put("name", "secret1");
            String response = secret.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHSecret fetchedSecret = repository.getEnvironmentSecret("environment1", "secret1");
            assertNotNull(fetchedSecret);
            assertEquals("secret1", fetchedSecret.getName());
        }
    }

    @Test
    void testListOrganisationVariables() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject variable1 = new JSONObject().put("name", "variable1");
            JSONObject variable2 = new JSONObject().put("name", "variable2");
            String response = new JSONObject().put("secrets", new JSONArray(List.of(variable1, variable2))).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHVariable> variables = repository.listOrganisationVariables();
            assertNotNull(variables);
            assertEquals(2, variables.size());
        }
    }

    @Test
    void testListRepositoryVariables() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject variable1 = new JSONObject().put("name", "variable1");
            JSONObject variable2 = new JSONObject().put("name", "variable2");
            String response = new JSONObject().put("variables", new JSONArray(List.of(variable1, variable2))).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHVariable> variables = repository.listRepositoryVariables();
            assertNotNull(variables);
            assertEquals(2, variables.size());
        }
    }

    @Test
    void testGetVariable() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject variable = new JSONObject().put("name", "variable1");
            String response = variable.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHVariable fetchedVariable = repository.getVariable("variable1");
            assertNotNull(fetchedVariable);
            assertEquals("variable1", fetchedVariable.getName());
        }
    }

    @Test
    void testListEnvironmentVariables() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject variable1 = new JSONObject().put("name", "variable1");
            JSONObject variable2 = new JSONObject().put("name", "variable2");
            String response = new JSONObject()
                    .put("variables", new JSONArray(List.of(variable1, variable2)))
                    .toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);
            List<GHVariable> variables = repository.listEnvironmentVariables("environment1");
            assertNotNull(variables);
            assertEquals(2, variables.size());
        }
    }

    @Test
    void testGetEnvironmentVariable() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject variable = new JSONObject().put("name", "variable1");
            String response = variable.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHVariable fetchedVariable = repository.getEnvironmentVariable("environment1", "variable1");
            assertNotNull(fetchedVariable);
            assertEquals("variable1", fetchedVariable.getName());
        }
    }

    @Test
    void testGetJobFromWorkflowRun() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject job = new JSONObject().put("id", "1");
            String response = job.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHWorkflowJob fetchedJob = repository.getJobFromWorkflowRun(1);
            assertNotNull(fetchedJob);
            assertEquals(1, fetchedJob.getId());
        }
    }

    @Test
    void testListJobsFromWorkflowRun() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject job1 = new JSONObject().put("id", "1");
            JSONObject job2 = new JSONObject().put("id", "2");
            String response = new JSONObject().put("jobs", new JSONArray(List.of(job1, job2))).toString();
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHWorkflowJob> jobs = repository.listJobsFromWorkflowRun(1);
            assertNotNull(jobs);
            assertEquals(2, jobs.size());
        }
    }

    @Test
    void testListJobsForWorkflowAttempt() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject job1 = new JSONObject().put("id", "1");
            JSONObject job2 = new JSONObject().put("id", "2");
            String response = new JSONObject().put("jobs", new JSONArray(List.of(job1, job2))).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHWorkflowJob> jobs = repository.listJobsForWorkflowAttempt(1, 1);
            assertNotNull(jobs);
            assertEquals(2, jobs.size());
        }
    }

    @Test
    void testGetWorkflowRun() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject run = new JSONObject().put("id", "1");
            String response = run.toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            GHWorkflowRun fetchedRun = repository.getWorkflowRun(1);
            assertNotNull(fetchedRun);
            assertEquals(1, fetchedRun.getId());
        }
    }

    @Test
    void testGetDirectoryContent() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject file1 = new JSONObject().put("name", "file1.txt");
            JSONObject file2 = new JSONObject().put("name", "file2.txt");
            String response = new JSONArray(List.of(file1, file2)).toString();

            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString()))
                    .thenReturn(response);

            List<GHFile> files = repository.getDirectoryContent("path/to/dir");
            assertNotNull(files);
            assertEquals(2, files.size());
        }
    }

    @Test
    void testGetPermissions() {
        assertNotNull(repository.getPermissions());
    }

    @Test
    void testGetPushedAt() {
        OffsetDateTime pushedAt = repository.getPushedAt();
        assertNotNull(pushedAt);
    }

    @Test
    void testGetBranchesUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_branches_url", repository.getBranchesUrl());
    }

    @Test
    void testGetIssueCommentUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_issue_comment_url", repository.getIssueCommentUrl());
    }

    @Test
    void testGetArchiveUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_archive_url", repository.getArchiveUrl());
    }

    @Test
    void testGetLabelsUrl() {

        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_labels_url", repository.getLabelsUrl());
    }

    @Test
    void testGetLanguage() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_language", repository.getLanguage());
    }

    @Test
    void testGetReleasesUrl() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_releases_url", repository.getReleasesUrl());
    }

    @Test
    void testGetSubscribersUrl() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_subscribers_url", repository.getSubscribersUrl());
    }

    @Test
    void testGetSubscriptionUrl() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_subscription_url", repository.getSubscriptionUrl());
    }

    @Test
    void testGetForksUrl() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_forks_url", repository.getForksUrl());
    }

    @Test
    void testGetGitRefsUrl() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_git_refs_url", repository.getGitRefsUrl());
    }

    @Test
    void testGetWatchers() {

        repository = new GHRepository(jsonObject, github);

        assertEquals(123, repository.getWatchers());
    }

    @Test
    void testGetSvnUrl() {

        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_svn_url", repository.getSvnUrl());
    }

    @Test
    void testGetCreatedAt() {


        repository = new GHRepository(jsonObject, github);

        assertEquals(now, repository.getCreatedAt());
    }

    @Test
    void testGetUpdatedAt() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(now, repository.getUpdatedAt());
    }

    @Test
    void testGetBlobsUrl() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_blobs_url", repository.getBlobsUrl());
    }

    @Test
    void testGetCloneUrl() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_clone_url", repository.getCloneUrl());
    }

    @Test
    void testGetCollaboratorsUrl() {

        repository = new GHRepository(jsonObject, github);

        assertEquals("expected_collaborators_url", repository.getCollaboratorsUrl());
    }

    @Test
    void testGetCommentsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_comments_url", repository.getCommentsUrl());
    }

    @Test
    void testGetCommitsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_commits_url", repository.getCommitsUrl());
    }

    @Test
    void testGetVisibility() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_visibility", repository.getVisibility());
    }

    @Test
    void testGetCompareUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_compare_url", repository.getCompareUrl());
    }

    @Test
    void testGetContentsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_contents_url", repository.getContentsUrl());
    }

    @Test
    void testGetContributorsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_contributors_url", repository.getContributorsUrl());
    }

    @Test
    void testGetDefaultBranch() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_default_branch", repository.getDefaultBranch());
    }

    @Test
    void testGetDeploymentsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_deployments_url", repository.getDeploymentsUrl());
    }

    @Test
    void testGetDescription() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_description", repository.getDescription());
    }

    @Test
    void testGetDownloadsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_downloads_url", repository.getDownloadsUrl());
    }

    @Test
    void testGetStatusesUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_statuses_url", repository.getStatusesUrl());
    }

    @Test
    void testGetFullName() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_full_name", repository.getFullName());
    }

    @Test
    void testGetGitCommitsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_git_commits_url", repository.getGitCommitsUrl());
    }

    @Test
    void testGetLanguagesUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_languages_url", repository.getLanguagesUrl());
    }

    @Test
    void testGetGitTagsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_git_tags_url", repository.getGitTagsUrl());
    }

    @Test
    void testGetGitUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_git_url", repository.getGitUrl());
    }

    @Test
    void testGetSshUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_ssh_url", repository.getSshUrl());
    }

    @Test
    void testGetAssigneesUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_assignees_url", repository.getAssigneesUrl());
    }

    @Test
    void testGetOpenIssues() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(10, repository.getOpenIssues());
    }

    @Test
    void testGetWatchersCount() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(20, repository.getWatchersCount());
    }

    @Test
    void testGetHooksUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_hooks_url", repository.getHooksUrl());
    }

    @Test
    void testGetIssueEventsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_issue_events_url", repository.getIssueEventsUrl());
    }

    @Test
    void testGetHomepage() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_homepage", repository.getHomepage());
    }

    @Test
    void testGetIssuesUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_issues_url", repository.getIssuesUrl());
    }

    @Test
    void testGetForksCount() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(5, repository.getForksCount());
    }

    @Test
    void testGetLicense() {
        repository = new GHRepository(jsonObject, github);
        JSONObject license = jsonObject.getJSONObject("license");
        assertEquals(license.toString(), repository.getLicense().toJSONObject().toString());
    }

    @Test
    void testGetKeysUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals("expected_keys_url", repository.getKeysUrl());
    }

    @Test
    void testGetNotificationsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("notifications_url"), repository.getNotificationsUrl());
    }


    @Test
    void testGetPullsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("pulls_url"), repository.getPullsUrl());
    }


    @Test
    void testGetStargazersUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("stargazers_url"), repository.getStargazersUrl());
    }


    @Test
    void testGetTagsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("tags_url"), repository.getTagsUrl());
    }


    @Test
    void testGetTeamsUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("teams_url"), repository.getTeamsUrl());
    }


    @Test
    void testGetTreesUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("trees_url"), repository.getTreesUrl());
    }

    @Test
    void testGetMergesUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("merges_url"), repository.getMergesUrl());
    }

    @Test
    void testGetMilestoneUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("milestones_url"), repository.getMilestonesUrl());
    }

    @Test
    void testGetMirrorUrl() {
        repository = new GHRepository(jsonObject, github);
        assertEquals(jsonObject.getString("mirror_url"), repository.getMirrorUrl());
    }

    @Test
    void testIsWebCommitSignoffRequired() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isWebCommitSignoffRequired());
    }

    @Test
    void testIsHasDiscussions() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isHasDiscussions());
    }

    @Test
    void testIsAllowForking() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isAllowForking());
    }

    @Test
    void testIsTemplate() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isTemplate());
    }

    @Test
    void testIsArchived() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isArchived());
    }

    @Test
    void testIsDisabled() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isDisabled());
    }

    @Test
    void testIsFork() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isFork());
    }

    @Test
    void testIsHasPage() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isHasPages());
    }

    @Test
    void testIsHasDownloads() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isHasDownloads());
    }

    @Test
    void testIsHasProjects() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isHasProjects());
    }

    @Test
    void testIsHasWiki() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isHasWiki());
    }

    @Test
    void testIsPrivate() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isPrivate());
    }

    @Test
    void testIsHasIssues() {
        repository = new GHRepository(jsonObject, github);
        assertTrue(repository.isHasIssues());
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
