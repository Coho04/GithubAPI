package de.goldendeveloper.githubapi.repositories;

import de.goldendeveloper.githubapi.Github;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GHRepositoryTest {

    private Github github;

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        github = Mockito.mock(Github.class);
        jsonObject = new JSONObject();
        jsonObject.put("name", "Repo1");
        jsonObject.put("owner", new JSONObject().put("login", "octocat"));
        jsonObject.put("permissions", new JSONObject().put("login", "octocat")
                .put("pull", false)
                .put("maintain", false)
                .put("admin", false)
                .put("triage", false)
                .put("push", false));
        jsonObject.put("topics", new JSONArray(new String[]{"topic1", "topic2"}));
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals("Repo1", new GHRepository(jsonObject, github).getName());
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

    // Similar tests can be written for the remaining fields: size, forks, license, fork, watchers, sshUrl, svnUrl, keysUrl, tagsUrl, cloneUrl, pushedAt, createdAt, updatedAt, language, fullname, forksUrl, treesUrl, pullsUrl, hooksUrl, labelsUrl, archived, isPrivate, gitRefsUrl, visibility, archiveUrl, statusesUrl, isTemplate, description, branchesUrl, releasesUrl, topics, languagesUrl, stargazersCount, openIssuesCount, defaultBranch, hasDownloads, allowForking, subscribersUrl, subscriptionUrl, issueCommentUrl, hasDiscussions, contributorsUrl, collaboratorsUrl, hasProjects, deploymentsUrl, hasWiki, commentsUrl, stargazersUrl, disabled, gitUrl, hasPages, commitsUrl, compareUrl, gitCommitsUrl, blobsUrl, gitTagsUrl, mergesUrl, downloadsUrl, hasIssues, notificationsUrl, contentsUrl, mirrorUrl, milestonesUrl, teamsUrl, issuesUrl, issueEventsUrl, assigneesUrl, openIssues, watchersCount, homepage, forksCount, permissions, webCommitSignoffRequired
}