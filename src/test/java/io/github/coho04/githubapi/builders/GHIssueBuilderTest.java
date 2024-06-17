package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.enums.GHState;
import io.github.coho04.githubapi.entities.repositories.GHIssue;
import io.github.coho04.githubapi.entities.repositories.GHMilestone;
import io.github.coho04.githubapi.entities.repositories.GHRepository;
import io.github.coho04.githubapi.TestBase;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GHIssueBuilderTest extends TestBase {

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
}
