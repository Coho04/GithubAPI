package io.github.coho04.githubapi.entities.repositories;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.entities.*;
import io.github.coho04.githubapi.TestBase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import io.github.coho04.githubapi.builders.GHFileBuilder;
import io.github.coho04.githubapi.builders.GHIssueBuilder;
import io.github.coho04.githubapi.enums.GHState;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.mockito.MockedStatic;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class GHRepositoryTest extends TestBase {

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

        GHUser assignee = setupUser();
        GHMilestone milestone = setupMileStone();
        JSONObject issue = new JSONObject();
        issue.put("number", 123);
        issue.put("comments", 10);
        issue.put("body", "Test Body");
        issue.put("state", "open");
        issue.put("title", "Test Title");
        issue.put("draft", false);
        issue.put("locked", false);
        issue.put("created_at", "2023-06-07T10:15:30+01:00");
        issue.put("updated_at", "2023-06-11T19:15:30+01:00");
        issue.put("closed_at", "2023-06-08T11:15:30+01:00");
        issue.put("timeline_url", "https://example.com/timeline");
        issue.put("repository_url", "https://example.com/repository_url");
        issue.put("comments_url", "https://example.com/comments_url");
        issue.put("labels_url", "https://example.com/labels_url");
        issue.put("author_association", "viverra tellus in hac habitasse");
        issue.put("active_lock_reason", "Lorem ipsum dolor sit amet,");
        issue.put("performed_via_github_app", "A cranky old lady stands upon somebody else's legs.");
        issue.put("milestone", milestone.toJSONObject());
        issue.put("state_reason", "state_reason");
        issue.put("user", new JSONObject().put("login", "TestUser"));
        issue.put("labels", Collections.singletonList(new JSONObject().put("name", "TestLabel")));
        issue.put("assignees", Collections.singletonList(new JSONObject().put("login", "TestAssignee")));
        issue.put("assignee", assignee.toJSONObject());
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

            Map<String, GHBranch> branches = repository.getBranches();

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
        assertNotNull(repository.getPushedAt());
    }

    @Test
    void testGetBranchesUrl() {
        assertEquals("expected_branches_url", repository.getBranchesUrl());
    }

    @Test
    void testGetIssueCommentUrl() {
        assertEquals("expected_issue_comment_url", repository.getIssueCommentUrl());
    }

    @Test
    void testGetArchiveUrl() {
        assertEquals("expected_archive_url", repository.getArchiveUrl());
    }

    @Test
    void testGetLabelsUrl() {
        assertEquals("expected_labels_url", repository.getLabelsUrl());
    }

    @Test
    void testGetLanguage() {
        assertEquals("expected_language", repository.getLanguage());
    }

    @Test
    void testGetReleasesUrl() {
        assertEquals("expected_releases_url", repository.getReleasesUrl());
    }

    @Test
    void testGetSubscribersUrl() {
        assertEquals("expected_subscribers_url", repository.getSubscribersUrl());
    }

    @Test
    void testGetSubscriptionUrl() {
        assertEquals("expected_subscription_url", repository.getSubscriptionUrl());
    }

    @Test
    void testGetForksUrl() {
        assertEquals("expected_forks_url", repository.getForksUrl());
    }

    @Test
    void testGetGitRefsUrl() {
        assertEquals("expected_git_refs_url", repository.getGitRefsUrl());
    }

    @Test
    void testGetWatchers() {
        assertEquals(123, repository.getWatchers());
    }

    @Test
    void testGetSvnUrl() {
        assertEquals("expected_svn_url", repository.getSvnUrl());
    }

    @Test
    void testGetCreatedAt() {
        assertEquals(now, repository.getCreatedAt());
    }

    @Test
    void testGetUpdatedAt() {
        assertEquals(now, repository.getUpdatedAt());
    }

    @Test
    void testGetBlobsUrl() {
        assertEquals("expected_blobs_url", repository.getBlobsUrl());
    }

    @Test
    void testGetCloneUrl() {
        assertEquals("expected_clone_url", repository.getCloneUrl());
    }

    @Test
    void testGetCollaboratorsUrl() {
        assertEquals("expected_collaborators_url", repository.getCollaboratorsUrl());
    }

    @Test
    void testGetCommentsUrl() {
        assertEquals("expected_comments_url", repository.getCommentsUrl());
    }

    @Test
    void testGetCommitsUrl() {
        assertEquals("expected_commits_url", repository.getCommitsUrl());
    }

    @Test
    void testGetVisibility() {
        assertEquals("expected_visibility", repository.getVisibility());
    }

    @Test
    void testGetCompareUrl() {
        assertEquals("expected_compare_url", repository.getCompareUrl());
    }

    @Test
    void testGetContentsUrl() {
        assertEquals("expected_contents_url", repository.getContentsUrl());
    }

    @Test
    void testGetContributorsUrl() {
        assertEquals("expected_contributors_url", repository.getContributorsUrl());
    }

    @Test
    void testGetDefaultBranch() {
        assertEquals("expected_default_branch", repository.getDefaultBranch());
    }

    @Test
    void testGetDeploymentsUrl() {
        assertEquals("expected_deployments_url", repository.getDeploymentsUrl());
    }

    @Test
    void testGetDescription() {
        assertEquals("expected_description", repository.getDescription());
    }

    @Test
    void testGetDownloadsUrl() {
        assertEquals("expected_downloads_url", repository.getDownloadsUrl());
    }

    @Test
    void testGetStatusesUrl() {
        assertEquals("expected_statuses_url", repository.getStatusesUrl());
    }

    @Test
    void testGetFullName() {
        assertEquals("expected_full_name", repository.getFullName());
    }

    @Test
    void testGetGitCommitsUrl() {
        assertEquals("expected_git_commits_url", repository.getGitCommitsUrl());
    }

    @Test
    void testGetLanguagesUrl() {
        assertEquals("expected_languages_url", repository.getLanguagesUrl());
    }

    @Test
    void testGetGitTagsUrl() {
        assertEquals("expected_git_tags_url", repository.getGitTagsUrl());
    }

    @Test
    void testGetGitUrl() {
        assertEquals("expected_git_url", repository.getGitUrl());
    }

    @Test
    void testGetSshUrl() {
        assertEquals("expected_ssh_url", repository.getSshUrl());
    }

    @Test
    void testGetAssigneesUrl() {
        assertEquals("expected_assignees_url", repository.getAssigneesUrl());
    }

    @Test
    void testGetOpenIssues() {
        assertEquals(10, repository.getOpenIssues());
    }

    @Test
    void testGetWatchersCount() {
        assertEquals(20, repository.getWatchersCount());
    }

    @Test
    void testGetHooksUrl() {
        assertEquals("expected_hooks_url", repository.getHooksUrl());
    }

    @Test
    void testGetIssueEventsUrl() {
        assertEquals("expected_issue_events_url", repository.getIssueEventsUrl());
    }

    @Test
    void testGetHomepage() {
        assertEquals("expected_homepage", repository.getHomepage());
    }

    @Test
    void testGetIssuesUrl() {
        assertEquals("expected_issues_url", repository.getIssuesUrl());
    }

    @Test
    void testGetForksCount() {
        assertEquals(5, repository.getForksCount());
    }

    @Test
    void testGetLicense() {
        JSONObject license = jsonObject.getJSONObject("license");
        assertEquals(license.toString(), repository.getLicense().toJSONObject().toString());
    }

    @Test
    void testGetKeysUrl() {
        assertEquals("expected_keys_url", repository.getKeysUrl());
    }

    @Test
    void testGetNotificationsUrl() {
        assertEquals(jsonObject.getString("notifications_url"), repository.getNotificationsUrl());
    }

    @Test
    void testGetPullsUrl() {
        assertEquals(jsonObject.getString("pulls_url"), repository.getPullsUrl());
    }

    @Test
    void testGetStargazersUrl() {
        assertEquals(jsonObject.getString("stargazers_url"), repository.getStargazersUrl());
    }

    @Test
    void testGetTagsUrl() {
        assertEquals(jsonObject.getString("tags_url"), repository.getTagsUrl());
    }

    @Test
    void testGetTeamsUrl() {
        assertEquals(jsonObject.getString("teams_url"), repository.getTeamsUrl());
    }

    @Test
    void testGetTreesUrl() {
        assertEquals(jsonObject.getString("trees_url"), repository.getTreesUrl());
    }

    @Test
    void testGetMergesUrl() {
        assertEquals(jsonObject.getString("merges_url"), repository.getMergesUrl());
    }

    @Test
    void testGetMilestoneUrl() {
        assertEquals(jsonObject.getString("milestones_url"), repository.getMilestonesUrl());
    }

    @Test
    void testGetMirrorUrl() {
        assertEquals(jsonObject.getString("mirror_url"), repository.getMirrorUrl());
    }

    @Test
    void testIsWebCommitSignoffRequired() {
        assertTrue(repository.isWebCommitSignoffRequired());
    }

    @Test
    void testIsHasDiscussions() {
        assertTrue(repository.isHasDiscussions());
    }

    @Test
    void testIsAllowForking() {
        assertTrue(repository.isAllowForking());
    }

    @Test
    void testIsTemplate() {
        assertTrue(repository.isTemplate());
    }

    @Test
    void testIsArchived() {
        assertTrue(repository.isArchived());
    }

    @Test
    void testIsDisabled() {
        assertTrue(repository.isDisabled());
    }

    @Test
    void testIsFork() {
        assertTrue(repository.isFork());
    }

    @Test
    void testIsHasPage() {
        assertTrue(repository.isHasPages());
    }

    @Test
    void testIsHasDownloads() {
        assertTrue(repository.isHasDownloads());
    }

    @Test
    void testIsHasProjects() {
        assertTrue(repository.isHasProjects());
    }

    @Test
    void testIsHasWiki() {
        assertTrue(repository.isHasWiki());
    }

    @Test
    void testIsPrivate() {
        assertTrue(repository.isPrivate());
    }

    @Test
    void testIsHasIssues() {
        assertTrue(repository.isHasIssues());
    }

    @Test
    void shouldReturnNullWhenResponseIsNull() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(null);

            Github github = Mockito.mock(Github.class);
            when(github.getToken()).thenReturn("mockToken");

            GHRepository repository = new GHRepository(new JSONObject(), github);
            GHIssue issue = repository.getIssueById(1);

            assertNull(issue, "The issue should be null when the response is null");
        }
    }

    @Test
    void shouldReturnIssueWhenResponseIsValid() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String issueJson = "{\"number\": 123, \"title\": \"Issue Title\"}";
            mockedStatic.when(() -> HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenReturn(issueJson);

            Github github = Mockito.mock(Github.class);
            when(github.getToken()).thenReturn("mockToken");

            GHRepository repository = new GHRepository(new JSONObject(), github);
            GHIssue issue = repository.getIssueById(123);

            assertNotNull(issue, "The issue should not be null when the response is valid");
            assertEquals(123, issue.getNumber(), "The issue ID should match the expected value");
            assertEquals("Issue Title", issue.getTitle(), "The issue title should match the expected value");
        }
    }

    @Test
    void shouldCreateProjectWithBuilder() {
        assertNotNull(repository.createProject());
    }

    @Test
    void shouldCreateProject() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            String response = new JSONObject().put("name", "project1").put("body", "project body").toString();
            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), Mockito.any(JSONObject.class))).thenReturn(response);

            Github github = Mockito.mock(Github.class);
            Mockito.when(github.getToken()).thenReturn("test_token");

            GHProject project = repository.createProject("project1", "project body");

            assertNotNull(project);
            assertEquals("project1", project.getName());
            assertEquals("project body", project.getBody());
        }
    }

}
