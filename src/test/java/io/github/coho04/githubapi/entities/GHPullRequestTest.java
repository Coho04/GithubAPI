package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.enums.GHState;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GHPullRequestTest {

    private GHPullRequest pullRequest;

    @BeforeEach
    void setUp() {
        Github github = mock(Github.class);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("diff_url", "https://api.github.com/repos/octocat/Hello-World/pull/1347.diff");
        jsonObject.put("patch_url", "https://api.github.com/repos/octocat/Hello-World/pull/1347.patch");
        jsonObject.put("issue_url", "https://api.github.com/repos/octocat/Hello-World/issues/1347");
        jsonObject.put("commits_url", "https://api.github.com/repos/octocat/Hello-World/pulls/1347/commits");
        jsonObject.put("review_comments_url", "https://api.github.com/repos/octocat/Hello-World/pulls/1347/comments");
        jsonObject.put("review_comment_url", "https://api.github.com/repos/octocat/Hello-World/pulls/comments{/number}");
        jsonObject.put("comments_url", "https://api.github.com/repos/octocat/Hello-World/issues/1347/comments");
        jsonObject.put("statuses_url", "https://api.github.com/repos/octocat/Hello-World/statuses/{sha}");
        jsonObject.put("number", 1347);
        jsonObject.put("state", "open");
        jsonObject.put("locked", false);
        jsonObject.put("title", "Amazing new feature");
        jsonObject.put("user", new JSONObject().put("login", "octocat"));
        jsonObject.put("body", "Please pull these awesome changes");
        jsonObject.put("labels", new JSONArray(List.of(new JSONObject().put("name", "bug"))));
        jsonObject.put("active_lock_reason", "too heated");
        jsonObject.put("created_at", OffsetDateTime.now().toString());
        jsonObject.put("updated_at", OffsetDateTime.now().toString());
        jsonObject.put("closed_at", JSONObject.NULL);
        jsonObject.put("merged_at", JSONObject.NULL);
        jsonObject.put("merge_commit_sha", "e5bd3914e2e596debea16f433f57875b5b90bcd6");
        jsonObject.put("assignees", new JSONArray(List.of(new JSONObject().put("login", "octocat"))));
        jsonObject.put("requested_reviewers", new JSONArray(List.of(new JSONObject().put("login", "octocat"))));
        jsonObject.put("requested_teams", new JSONArray(List.of(new JSONObject().put("login", "octocat"))));
        jsonObject.put("author_association", "OWNER");
        jsonObject.put("auto_merge", JSONObject.NULL);
        jsonObject.put("draft", false);

        pullRequest = new GHPullRequest(github, jsonObject);
    }

    @Test
    void testGetCreatedAt() {
        assertNotNull(pullRequest.getCreatedAt());
    }

    @Test
    void testGetUpdatedAt() {
        assertNotNull(pullRequest.getUpdatedAt());
    }

    @Test
    void testGetState() {
        assertEquals(GHState.OPEN, pullRequest.getState());
    }

    @Test
    void testGetBody() {
        assertEquals("Please pull these awesome changes", pullRequest.getBody());
    }

    @Test
    void testGetNumber() {
        assertEquals(1347, pullRequest.getNumber());
    }

    @Test
    void testGetMilestone() {
        assertNull(pullRequest.getMilestone());
    }

    @Test
    void testGetAssignee() {
        assertNull(pullRequest.getAssignee());
    }

    @Test
    void testGetUser() {
        assertNotNull(pullRequest.getUser());
        assertEquals("octocat", pullRequest.getUser().getLogin());
    }

    @Test
    void testGetLabels() {
        assertNotNull(pullRequest.getLabels());
        assertFalse(pullRequest.getLabels().isEmpty());
        assertEquals("bug", pullRequest.getLabels().get(0).getName());
    }

    @Test
    void testGetActiveLockReason() {
        assertEquals("too heated", pullRequest.getActiveLockReason());
    }

    @Test
    void testGetIssueUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World/issues/1347", pullRequest.getIssueUrl());
    }

    @Test
    void testGetAssignees() {
        assertNotNull(pullRequest.getAssignees());
        assertFalse(pullRequest.getAssignees().isEmpty());
        assertEquals("octocat", pullRequest.getAssignees().get(0).getLogin());
    }

    @Test
    void testGetRequestedTeams() {
        assertNotNull(pullRequest.getRequestedTeams());
        assertFalse(pullRequest.getRequestedTeams().isEmpty());
        assertEquals("octocat", pullRequest.getRequestedTeams().get(0).getLogin());
    }

    @Test
    void testGetRequestedReviewers() {
        assertNotNull(pullRequest.getRequestedReviewers());
        assertFalse(pullRequest.getRequestedReviewers().isEmpty());
        assertEquals("octocat", pullRequest.getRequestedReviewers().get(0).getLogin());
    }

    @Test
    void testGetClosedAt() {
        assertNull(pullRequest.getClosedAt());
    }

    @Test
    void testGetCommentsUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World/issues/1347/comments", pullRequest.getCommentsUrl());
    }

    @Test
    void testGetCommitsUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World/pulls/1347/commits", pullRequest.getCommitsUrl());
    }

    @Test
    void testGetDiffUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World/pull/1347.diff", pullRequest.getDiffUrl());
    }

    @Test
    void testGetMergedAt() {
        assertNull(pullRequest.getMergedAt());
    }

    @Test
    void testGetAuthorAssociation() {
        assertEquals("OWNER", pullRequest.getAuthorAssociation());
    }

    @Test
    void testGetMergeCommitSha() {
        assertEquals("e5bd3914e2e596debea16f433f57875b5b90bcd6", pullRequest.getMergeCommitSha());
    }

    @Test
    void testGetPatchUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World/pull/1347.patch", pullRequest.getPatchUrl());
    }

    @Test
    void testGetReviewCommentsUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World/pulls/1347/comments", pullRequest.getReviewCommentsUrl());
    }

    @Test
    void testGetReviewCommentUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World/pulls/comments{/number}", pullRequest.getReviewCommentUrl());
    }

    @Test
    void testGetAutoMerge() {
        assertNull(pullRequest.getAutoMerge());
    }

    @Test
    void testGetStatusesUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World/statuses/{sha}", pullRequest.getStatusesUrl());
    }

    @Test
    void testGetTitle() {
        assertEquals("Amazing new feature", pullRequest.getTitle());
    }

    @Test
    void testIsLocked() {
        assertFalse(pullRequest.isLocked());
    }

    @Test
    void testIsDraft() {
        assertFalse(pullRequest.isDraft());
    }
}
