package de.coho04.githubapi.repositories;

import de.coho04.githubapi.enums.GHState;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHMilestoneTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("title", "Milestone 1");
        jsonObject.put("state", "open");
        jsonObject.put("creator", new JSONObject().put("login", "octocat"));
    }

    @Test
    void shouldReturnCorrectTitle() {
        assertEquals("Milestone 1", new GHMilestone(jsonObject).getTitle());
    }

    @Test
    void shouldReturnNullWhenTitleIsNotPresent() {
        jsonObject.remove("title");
        assertNull(new GHMilestone(jsonObject).getTitle());
    }

    @Test
    void shouldReturnCorrectState() {
        assertEquals(GHState.OPEN, new GHMilestone(jsonObject).getState());
    }

    @Test
    void shouldReturnNullWhenStateIsNotPresent() {
        jsonObject.remove("state");
        assertNull(new GHMilestone(jsonObject).getState());
    }

    @Test
    void shouldReturnCorrectCreator() {
        assertEquals("octocat", new GHMilestone(jsonObject).getCreator().getLogin());
    }

    @Test
    void shouldReturnNullWhenCreatorIsNotPresent() {
        jsonObject.remove("creator");
        assertNull(new GHMilestone(jsonObject).getCreator());
    }

    // Similar tests can be written for the remaining fields: number, dueOn, openIssues, closedAt, createdAt, updatedAt, closedIssues, labelsUrl, description
}