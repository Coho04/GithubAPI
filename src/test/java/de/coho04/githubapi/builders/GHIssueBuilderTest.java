package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.repositories.GHIssue;
import de.coho04.githubapi.repositories.GHMilestone;
import de.coho04.githubapi.repositories.GHRepository;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GHIssueBuilderTest {

    private final Github github = mock(Github.class);
    private GHIssueBuilder issueBuilder;
    private final GHRepository repository = mock(GHRepository.class);

    @BeforeEach
    void setUp() {
        when(github.getToken()).thenReturn("test_token");
        when(repository.getUrl()).thenReturn("https://api.github.com/repos/test/repo");

        issueBuilder = new GHIssueBuilder(github, repository.getUrl(), "Test Issue");
        issueBuilder.setMilestone(setupMileStone());
    }

    @Test
    void testGHIssueBuilder() {
        String title = "Test Issue";
        String body = "Test Body";
        issueBuilder = new GHIssueBuilder(github, repository.getUrl(), title, body);
        assertNotNull(issueBuilder);
        assertEquals("Test Issue", issueBuilder.getTitle());
        assertNull(issueBuilder.getMilestone());
        assertTrue(issueBuilder.getLabels().isEmpty());
        assertTrue(issueBuilder.getAssignees().isEmpty());
        assertEquals(GHState.OPEN, issueBuilder.getState());
    }

    @Test
    void testSetTitle() {
        String newTitle = "New Test Issue";
        issueBuilder.setTitle(newTitle);
        assertEquals(newTitle, issueBuilder.getTitle());
    }

    @Test
    void testAssignee() {
        String assignee = "test_user";
        issueBuilder.assignee(assignee);
        JSONObject jsonObject = issueBuilder.toJSONObject();
        assertTrue(jsonObject.getJSONArray("assignees").toList().contains(assignee));
    }

    @Test
    void testAddLabel() {
        String label = "bug";
        issueBuilder.addLabel(label);
        JSONObject jsonObject = issueBuilder.toJSONObject();
        assertTrue(jsonObject.getJSONArray("labels").toList().contains(label));
    }

    @Test
    void testAddBody() {
        String body = "body";
        issueBuilder.setBody(body);
        JSONObject jsonObject = issueBuilder.toJSONObject();
        assertEquals(jsonObject.getString("body"), body);
        assertEquals(issueBuilder.getBody(), body);
    }


    @Test
    void testGithub() {
        assertEquals(issueBuilder.getGithub(), github);
    }

    @Test
    void testGetMilestone() {
        GHMilestone ghMilestone = setupMileStone();
        issueBuilder.setMilestone(ghMilestone);
        JSONObject jsonObject = issueBuilder.toJSONObject();
        assertEquals(jsonObject.getJSONObject("milestone").toString(), ghMilestone.toJSONObject().toString());
    }

    @Test
    void testCreateIssue() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject expectedJson = new JSONObject()
                    .put("title", "Test Issue")
                    .put("labels", new JSONArray())
                    .put("state", GHState.OPEN.toString())
                    .put("assignees", new JSONArray());

            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenReturn("{\"id\": 1, \"title\": \"Test Issue\"}");

            GHIssue issue = issueBuilder.create();
            assertNotNull(issue);
            assertEquals("Test Issue", issue.getTitle());

            mockedStatic.verify(() -> HttpRequestHelper.sendPostRequest(
                    eq("https://api.github.com/repos/test/repo/issues"),
                    eq("test_token"),
                    argThat(json -> {
                        JSONObject actualJson = new JSONObject(json.toString());
                        expectedJson.remove("state");
                        actualJson.remove("milestone");
                        return jsonSimilar(actualJson, expectedJson);
                    })
            ));
        }
    }

    private boolean jsonSimilar(JSONObject actual, JSONObject expected) {
        if (actual == null || expected == null) return false;
        if (actual.length() != expected.length()) return false;
        for (String key : expected.keySet()) {
            if (!actual.has(key)) return false;
            if (actual.isNull(key) && expected.isNull(key)) continue;
            if (actual.isNull(key) || expected.isNull(key)) return false;
            if (!actual.get(key).toString().equals(expected.get(key).toString())) return false;
        }
        return true;
    }

    @Test
    void testToJSONObject() {
        issueBuilder.assignee("test_user")
                .addLabel("bug");

        JSONObject jsonObject = issueBuilder.toJSONObject();
        assertEquals("Test Issue", jsonObject.getString("title"));
        assertEquals(GHState.OPEN.toString(), jsonObject.getString("state"));
        assertTrue(jsonObject.getJSONArray("assignees").toList().contains("test_user"));
        assertTrue(jsonObject.getJSONArray("labels").toList().contains("bug"));
    }

    private GHMilestone setupMileStone() {
        Random random = new Random();
        return new GHMilestone(Mockito.mock(Github.class), new JSONObject()
                .put("closed_at", "2011-04-10T20:09:31Z")
                .put("description", "Passion or serendipity stands upon somebody else's legs.")
                .put("created_at", "2011-04-10T20:09:31Z")
                .put("title", "A cranky old lady stands upon somebody else's legs.")
                .put("closed_issues", String.valueOf(random.nextInt()))
                .put("due_on", "fczvgubhijokpl")
                .put("creator", setupUser().toJSONObject())
                .put("state", GHState.CLOSED.toString())
                .put("labels_url", "https://example.com/labels_url")
                .put("number", String.valueOf(random.nextInt()))
                .put("updated_at", "2011-04-10T20:09:31Z")
                .put("open_issues", String.valueOf(random.nextInt()))
        );
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
