package de.coho04.githubapi.repositories;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.entities.repositories.GHIssue;
import de.coho04.githubapi.entities.repositories.GHMilestone;
import de.coho04.githubapi.enums.GHState;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GHIssueTest {

    private JSONObject jsonObject;
    private GHMilestone milestone;
    private GHUser assignee;
    private Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
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


    public GHIssue getIssue(JSONObject jsonObject) {
        Github github = Mockito.mock(Github.class);
        return new GHIssue(github, jsonObject);
    }

    private GHMilestone setupMileStone() {
        return new GHMilestone(Mockito.mock(Github.class), new JSONObject()
                .put("closed_at", "2011-04-10T20:09:31Z")
                .put("description", "Passion or serendipity stands upon somebody else's legs.")
                .put("created_at", "2011-04-10T20:09:31Z")
                .put("title", "A cranky old lady stands upon somebody else's legs.")
                .put("closed_issues", String.valueOf(random.nextInt()))
                .put("due_on", "fczvgubhijokpl")
                .put("creator", this.assignee.toJSONObject())
                .put("state", GHState.CLOSED.toString())
                .put("labels_url", "https://example.com/labels_url")
                .put("number", String.valueOf(random.nextInt()))
                .put("updated_at", "2011-04-10T20:09:31Z")
                .put("open_issues", String.valueOf(random.nextInt()))
        );
    }

    private GHUser setupUser() {
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