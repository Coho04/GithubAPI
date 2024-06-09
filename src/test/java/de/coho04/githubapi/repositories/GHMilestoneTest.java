package de.coho04.githubapi.repositories;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.entities.repositories.GHMilestone;
import de.coho04.githubapi.enums.GHState;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GHMilestoneTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        Random random = new Random();
        jsonObject = new JSONObject();
        jsonObject.put("closed_at", "2011-04-10T20:09:31Z");
        jsonObject.put("description", "Passion or serendipity stands upon somebody else's legs.");
        jsonObject.put("created_at", "2011-04-10T20:09:31Z");
        jsonObject.put("title", "A cranky old lady stands upon somebody else's legs.");
        jsonObject.put("closed_issues", String.valueOf(random.nextInt()));
        jsonObject.put("due_on", "fczvgubhijokpl");
        jsonObject.put("labels_url", "https://example.com/labels_url");
        jsonObject.put("number", String.valueOf(random.nextInt()));
        jsonObject.put("updated_at", "2011-04-10T20:09:31Z");
        jsonObject.put("state", GHState.OPEN.toString());
        jsonObject.put("creator", new JSONObject().put("login", "octocat"));
        jsonObject.put("open_issues", String.valueOf(random.nextInt()));
    }

    @Test
    void shouldReturnCorrectTitle() {
        assertEquals(jsonObject.getString("title"), getMilestone(jsonObject).getTitle());
    }

    @Test
    void shouldReturnNullWhenTitleIsNotPresent() {
        jsonObject.remove("title");
        assertNull(getMilestone(jsonObject).getTitle());
    }

    @Test
    void shouldReturnCorrectState() {
        assertEquals(GHState.OPEN.toString(), getMilestone(jsonObject).getState().toString());
    }

    @Test
    void shouldReturnNullWhenStateIsNotPresent() {
        jsonObject.remove("state");
        assertNull(getMilestone(jsonObject).getState());
    }

    @Test
    void shouldReturnCorrectCreator() {
        assertEquals("octocat", getMilestone(jsonObject).getCreator().getLogin());
    }

    @Test
    void shouldReturnNullWhenCreatorIsNotPresent() {
        jsonObject.remove("creator");
        assertNull(getMilestone(jsonObject).getCreator());
    }

    @Test
    void shouldReturnCorrectLabelsUrl() {
        assertEquals(jsonObject.getString("labels_url"), getMilestone(jsonObject).getLabelsUrl());
    }

    @Test
    void shouldReturnNullWhenLabelsUrlIsNotPresent() {
        jsonObject.remove("labels_url");
        assertNull(getMilestone(jsonObject).getLabelsUrl());
    }

    @Test
    void shouldReturnCorrectDescription() {
        assertEquals(jsonObject.getString("description"), getMilestone(jsonObject).getDescription());
    }

    @Test
    void shouldReturnNullWhenDescriptionIsNotPresent() {
        jsonObject.remove("description");
        assertNull(getMilestone(jsonObject).getDescription());
    }

    @Test
    void shouldReturnCorrectNumber() {
        assertEquals(jsonObject.getInt("number"), getMilestone(jsonObject).getNumber());
    }

    @Test
    void shouldReturnNullWhenNumberIsNotPresent() {
        jsonObject.remove("number");
        assertEquals(0, getMilestone(jsonObject).getNumber());
    }

    @Test
    void shouldReturnCorrectClosedIssues() {
        assertEquals(jsonObject.getInt("closed_issues"), getMilestone(jsonObject).getClosedIssues());
    }

    @Test
    void shouldReturnNullWhenOpenIssuesIsNotPresent() {
        jsonObject.remove("open_issues");
        assertEquals(0, getMilestone(jsonObject).getOpenIssues());
    }

    @Test
    void shouldReturnCorrectOpenIssues() {
        assertEquals(jsonObject.getInt("open_issues"), getMilestone(jsonObject).getOpenIssues());
    }

    @Test
    void shouldReturnNullWhenClosedIssuesIsNotPresent() {
        jsonObject.remove("closed_issues");
        assertEquals(0, getMilestone(jsonObject).getClosedIssues());
    }

    @Test
    void shouldReturnCorrectDueOn() {
        assertEquals(jsonObject.getString("due_on"), getMilestone(jsonObject).getDueOn());
    }

    @Test
    void shouldReturnNullWhenDueOnIsNotPresent() {
        jsonObject.remove("due_on");
        assertNull(getMilestone(jsonObject).getDueOn());
    }

    @Test
    void shouldReturnCorrectJsonObject() {
        GHMilestone milestone = new GHMilestone(Mockito.mock(Github.class), jsonObject);
        assertEquals(milestone.toJSONObject().toString(), getMilestone(jsonObject).toJSONObject().toString());
    }


    @Test
    void shouldReturnCorrectUpdatedAt() {
        assertEquals(OffsetDateTime.parse(jsonObject.getString("updated_at")), getMilestone(jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnCorrectCreatedAt() {
        assertEquals(OffsetDateTime.parse(jsonObject.getString("created_at")), getMilestone(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnCorrectClosedAt() {
        assertEquals(OffsetDateTime.parse(jsonObject.getString("closed_at")), getMilestone(jsonObject).getClosedAt());
    }

    private @NotNull GHMilestone getMilestone(JSONObject jsonObject) {
        Github github = Mockito.mock(Github.class);
        return new GHMilestone(github, jsonObject);
    }
}