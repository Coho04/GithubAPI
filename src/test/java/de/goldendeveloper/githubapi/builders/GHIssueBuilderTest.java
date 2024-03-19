package de.goldendeveloper.githubapi.builders;

import de.goldendeveloper.githubapi.Github;
import de.goldendeveloper.githubapi.repositories.GHIssue;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GHIssueBuilderTest {

    private Github github;

    @BeforeEach
    void setUp() {
        github = Mockito.mock(Github.class);
    }

    @Test
    void shouldCreateIssueWithCorrectTitle() {
        GHIssueBuilder builder = new GHIssueBuilder(github, "Issue 1");
        GHIssue issue = builder.create();
        assertEquals("Issue 1", issue.getTitle());
    }

    @Test
    void shouldCreateIssueWithCorrectTitleAndBody() {
        GHIssueBuilder builder = new GHIssueBuilder(github, "Issue 1", "This is a test issue");
        GHIssue issue = builder.create();
        assertEquals("Issue 1", issue.getTitle());
        assertEquals("This is a test issue", issue.getBody());
    }
}