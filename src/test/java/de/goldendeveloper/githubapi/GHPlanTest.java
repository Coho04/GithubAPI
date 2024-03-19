package de.goldendeveloper.githubapi;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GHPlanTest {

    @Test
    public void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Test Plan");
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals("Test Plan", ghPlan.getName());
    }

    @Test
    public void shouldReturnCorrectFilledSeats() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("filled_seats", 5);
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(5, ghPlan.getFilledSeats());
    }

    @Test
    public void shouldReturnCorrectPrivateRepos() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("private_repos", 10);

        GHPlan ghPlan = new GHPlan(jsonObject);

        assertEquals(10, ghPlan.getPrivateRepos());
    }

    @Test
    public void shouldReturnCorrectSeats() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("seats", 20);
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(20, ghPlan.getSeats());
    }

    @Test
    public void shouldReturnCorrectSpace() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("space", 1000L);
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(1000L, ghPlan.getSpace());
    }

    @Test
    public void shouldReturnNullWhenNameIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertNull(ghPlan.getName());
    }

    @Test
    public void shouldReturnZeroWhenSeatsAreNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(0, ghPlan.getSeats());
    }

    @Test
    public void shouldReturnZeroWhenFilledSeatsAreNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(0, ghPlan.getFilledSeats());
    }

    @Test
    public void shouldReturnZeroWhenPrivateReposAreNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(0, ghPlan.getPrivateRepos());
    }

    @Test
    public void shouldReturnZeroWhenSpaceIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(0L, ghPlan.getSpace());
    }
}