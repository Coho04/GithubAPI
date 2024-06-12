package io.github.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHPlanTest {

    private GHPlan plan;

    @BeforeEach
    void setUp() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Pro Plan");
        jsonObject.put("space", 100000);
        jsonObject.put("seats", 10);
        jsonObject.put("filled_seats", 5);
        jsonObject.put("private_repos", 20);

        plan = new GHPlan(jsonObject);
    }

    @Test
    void testGetName() {
        assertEquals("Pro Plan", plan.getName());
    }

    @Test
    void testGetSpace() {
        assertEquals(100000, plan.getSpace());
    }

    @Test
    void testGetSeats() {
        assertEquals(10, plan.getSeats());
    }

    @Test
    void testGetFilledSeats() {
        assertEquals(5, plan.getFilledSeats());
    }

    @Test
    void testGetPrivateRepos() {
        assertEquals(20, plan.getPrivateRepos());
    }

    @Test
    void testToJSONObject() {
        JSONObject json = plan.toJSONObject();
        assertEquals("Pro Plan", json.getString("name"));
        assertEquals(100000, json.getLong("space"));
        assertEquals(10, json.getInt("seats"));
        assertEquals(5, json.getInt("filled_seats"));
        assertEquals(20, json.getInt("private_repos"));
    }
}
