package io.github.coho04.githubapi;

import io.github.coho04.githubapi.entities.GHPlan;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GHPlanTest {

    @Test
    void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Test Plan");
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals("Test Plan", ghPlan.getName());
    }

    @Test
    void shouldReturnCorrectFilledSeats() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("filled_seats", 5);
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(5, ghPlan.getFilledSeats());
    }

    @Test
    void shouldReturnCorrectPrivateRepos() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("private_repos", 10);
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(10, ghPlan.getPrivateRepos());
    }

    @Test
    void shouldReturnCorrectSeats() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("seats", 20);
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(20, ghPlan.getSeats());
    }

    @Test
    void shouldReturnCorrectSpace() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("space", 1000L);
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(1000L, ghPlan.getSpace());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertNull(ghPlan.getName());
    }

    @Test
    void shouldReturnZeroWhenSeatsAreNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(0, ghPlan.getSeats());
    }

    @Test
    void shouldReturnZeroWhenFilledSeatsAreNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(0, ghPlan.getFilledSeats());
    }

    @Test
    void shouldReturnZeroWhenPrivateReposAreNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(0, ghPlan.getPrivateRepos());
    }

    @Test
    void shouldReturnZeroWhenSpaceIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPlan ghPlan = new GHPlan(jsonObject);
        assertEquals(0L, ghPlan.getSpace());
    }
}