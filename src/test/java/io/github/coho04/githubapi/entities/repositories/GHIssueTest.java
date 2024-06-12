package io.github.coho04.githubapi.entities.repositories;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.TestBase;
import io.github.coho04.githubapi.entities.GHUser;
import io.github.coho04.githubapi.enums.GHState;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class GHIssueTest extends TestBase {

    private JSONObject jsonObject;
    private GHMilestone milestone;
    private GHUser assignee;

    @BeforeEach
    void setUp() {
        assignee = setupUser();
        milestone = setupMileStone();
        jsonObject = new JSONObject();
        jsonObject.put("number", 123);
        jsonObject.put("comments", 10);
        jsonObject.put("body", "Test Body");
        jsonObject.put("state", "open");
        jsonObject.put("title", "Test Title");
        jsonObject.put("draft", false);
        jsonObject.put("locked", false);
        jsonObject.put("created_at", "2023-06-07T10:15:30+01:00");
        jsonObject.put("updated_at", "2023-06-11T19:15:30+01:00");
        jsonObject.put("closed_at", "2023-06-08T11:15:30+01:00");
        jsonObject.put("timeline_url", "https://example.com/timeline");
        jsonObject.put("repository_url", "https://example.com/repository_url");
        jsonObject.put("comments_url", "https://example.com/comments_url");
        jsonObject.put("labels_url", "https://example.com/labels_url");
        jsonObject.put("author_association", "viverra tellus in hac habitasse");
        jsonObject.put("active_lock_reason", "Lorem ipsum dolor sit amet,");
        jsonObject.put("performed_via_github_app", "A cranky old lady stands upon somebody else's legs.");
        jsonObject.put("milestone", milestone.toJSONObject());
        jsonObject.put("state_reason", "state_reason");
        jsonObject.put("user", new JSONObject().put("login", "TestUser"));
        jsonObject.put("labels", Collections.singletonList(new JSONObject().put("name", "TestLabel")));
        jsonObject.put("assignees", Collections.singletonList(new JSONObject().put("login", "TestAssignee")));
        jsonObject.put("assignee", assignee.toJSONObject());
    }

    @Test
    void shouldReturnCorrectNumber() {
        assertEquals(123, getIssue(jsonObject).getNumber());
    }

    @Test
    void shouldReturnCorrectComments() {
        assertEquals(10, getIssue(jsonObject).getComments());
    }

    @Test
    void shouldReturnCorrectBody() {
        assertEquals("Test Body", getIssue(jsonObject).getBody());
    }

    @Test
    void shouldReturnCorrectState() {
        assertEquals(GHState.OPEN, getIssue(jsonObject).getState());
    }

    @Test
    void shouldReturnCorrectUser() {
        assertEquals("TestUser", getIssue(jsonObject).getUser().getLogin());
    }

    @Test
    void shouldReturnCorrectLabels() {
        assertEquals("TestLabel", getIssue(jsonObject).getLabels().get(0).getName());
    }

    @Test
    void shouldReturnCorrectAssignees() {
        assertEquals("TestAssignee", getIssue(jsonObject).getAssignees().get(0).getLogin());
    }

    /**
     * Testing the fromString method with a String that matches a GHState enum, expecting OPEN.
     * Test case is straightforward and simple, the input matches one of the GHState enums.
     */
    @Test
    void shouldReturnOpenWhenFromStringCalledWithOpen() {
        assertEquals(GHState.OPEN, GHState.fromString("open"));
    }

    /**
     * Testing the fromString method with a String that matches a GHState enum, expecting CLOSED.
     * Test case is straightforward and simple, the input matches one of the GHState enums.
     */
    @Test
    void shouldReturnClosedWhenFromStringCalledWithClosed() {
        assertEquals(GHState.CLOSED, GHState.fromString("closed"));
    }

    /**
     * Testing the fromString method with a String that matches a GHState enum, expecting ALL.
     * Test case is straightforward and simple, the input matches one of the GHState enums.
     */
    @Test
    void shouldReturnAllWhenFromStringCalledWithAll() {
        assertEquals(GHState.ALL, GHState.fromString("all"));
    }

    /**
     * Testing the fromString method with a String that does not match any GHState enum.
     * In this case we are testing the error handling of the method, and the expected outcome is that an IllegalArgumentException is thrown.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenFromStringCalledWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> GHState.fromString("invalid"));
    }

    @Test
    void shouldReturnCorrectTitle() {
        assertEquals(jsonObject.getString("title"), getIssue(jsonObject).getTitle());
    }

    @Test
    void shouldReturnCorrectDraft() {
        assertFalse(getIssue(jsonObject).isDraft());
    }

    @Test
    void shouldReturnCorrectLocked() {
        assertFalse(getIssue(jsonObject).isLocked());
    }

    @Test
    void shouldReturnCorrectTimelineUrl() {
        assertEquals(jsonObject.getString("timeline_url"), getIssue(jsonObject).getTimelineUrl());
    }

    @Test
    void shouldReturnCorrectStateReason() {
        assertEquals(jsonObject.getString("state_reason"), getIssue(jsonObject).getStateReason());
    }

    @Test
    void shouldReturnCorrectUpdatedAt() {
        assertEquals(OffsetDateTime.parse(jsonObject.getString("updated_at")), getIssue(jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnCorrectCreatedAt() {
        assertEquals(OffsetDateTime.parse(jsonObject.getString("created_at")), getIssue(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnCorrectClosedAt() {
        assertEquals(OffsetDateTime.parse(jsonObject.getString("closed_at")), getIssue(jsonObject).getClosedAt());
    }

    @Test
    void shouldReturnCorrectRepositoryUrl() {
        assertEquals(jsonObject.getString("repository_url"), getIssue(jsonObject).getRepositoryUrl());
    }

    @Test
    void shouldReturnCorrectCommentsUrl() {
        assertEquals(jsonObject.getString("comments_url"), getIssue(jsonObject).getCommentsUrl());
    }

    @Test
    void shouldReturnCorrectLabelsUrl() {
        assertEquals(jsonObject.getString("labels_url"), getIssue(jsonObject).getLabelsUrl());
    }

    @Test
    void shouldReturnCorrectAuthorAssociation() {
        assertEquals(jsonObject.getString("author_association"), getIssue(jsonObject).getAuthorAssociation());
    }

    @Test
    void shouldReturnCorrectActiveLockReason() {
        assertEquals(jsonObject.getString("active_lock_reason"), getIssue(jsonObject).getActiveLockReason());
    }

    @Test
    void shouldReturnCorrectPerformedViaGithubApp() {
        assertEquals(jsonObject.getString("performed_via_github_app"), getIssue(jsonObject).getPerformedViaGithubApp());
    }

    @Test
    void shouldReturnCorrectMilestone() {
        assertEquals(milestone.toJSONObject().toString(), getIssue(jsonObject).getMilestone().toJSONObject().toString());
    }

    @Test
    void shouldReturnCorrectAssignee() {
        assertEquals(assignee.toJSONObject().toString(), getIssue(jsonObject).getAssignee().toJSONObject().toString());
    }

    @Test
    void shouldConvertToJSONObject() {
        GHIssue issue = getIssue(jsonObject);
        JSONObject jsonObject = issue.toJSONObject();
        assertNotNull(jsonObject);
        assertEquals(123, jsonObject.getInt("number"));
        assertEquals("Test Body", jsonObject.getString("body"));
        assertEquals("Test Title", jsonObject.getString("title"));
        assertEquals("open", jsonObject.getString("state"));
    }


    @Test
    void shouldCloseIssue() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedStatic.when(() -> HttpRequestHelper.sendPatchRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenAnswer(invocation -> {
                        String url = invocation.getArgument(0);
                        String token = invocation.getArgument(1);
                        JSONObject payload = invocation.getArgument(2);

                        assertEquals("https://api.github.com/repos/test_owner/test_repo/issues/123", url);
                        assertEquals("test_token", token);
                        assertEquals(GHState.CLOSED.toString(), payload.getString("state"));
                        return null;
                    });
            Github github = Mockito.mock(Github.class);
            when(github.getToken()).thenReturn("test_token");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("number", 123);
            jsonObject.put("url", "https://api.github.com/repos/test_owner/test_repo/issues/123");
            GHIssue issue = new GHIssue(github, jsonObject);
            issue.close();

            mockedStatic.verify(() -> HttpRequestHelper.sendPatchRequest(anyString(), anyString(), any(JSONObject.class)), times(1));
        }
    }
    public GHIssue getIssue(JSONObject jsonObject) {
        Github github = Mockito.mock(Github.class);
        return new GHIssue(github, jsonObject);
    }
}