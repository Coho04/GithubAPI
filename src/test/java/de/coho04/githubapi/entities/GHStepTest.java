package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHStepTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("name", "Step1");
        jsonObject.put("status", "completed");
        jsonObject.put("conclusion", "success");
        jsonObject.put("number", 1);
        jsonObject.put("started_at", "2022-01-01T00:00:00Z");
        jsonObject.put("completed_at", "2022-01-01T01:00:00Z");
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals("Step1", new GHStep(jsonObject).getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        jsonObject.remove("name");
        assertNull(new GHStep(jsonObject).getName());
    }

    @Test
    void shouldReturnCorrectStatus() {
        assertEquals("completed", new GHStep(jsonObject).getStatus());
    }

    @Test
    void shouldReturnNullWhenStatusIsNotPresent() {
        jsonObject.remove("status");
        assertNull(new GHStep(jsonObject).getStatus());
    }

    @Test
    void shouldReturnCorrectConclusion() {
        assertEquals("success", new GHStep(jsonObject).getConclusion());
    }

    @Test
    void shouldReturnNullWhenConclusionIsNotPresent() {
        jsonObject.remove("conclusion");
        assertNull(new GHStep(jsonObject).getConclusion());
    }

    @Test
    void shouldReturnCorrectNumber() {
        assertEquals(1, new GHStep(jsonObject).getNumber());
    }

    @Test
    void shouldReturnZeroWhenNumberIsNotPresent() {
        jsonObject.remove("number");
        assertEquals(0, new GHStep(jsonObject).getNumber());
    }

    @Test
    void shouldReturnCorrectStartedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHStep(jsonObject).getStartedAt());
    }

    @Test
    void shouldReturnNullWhenStartedAtIsNotPresent() {
        jsonObject.remove("started_at");
        assertNull(new GHStep(jsonObject).getStartedAt());
    }

    @Test
    void shouldReturnCorrectCompletedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T01:00:00Z"), new GHStep(jsonObject).getCompletedAt());
    }

    @Test
    void shouldReturnNullWhenCompletedAtIsNotPresent() {
        jsonObject.remove("completed_at");
        assertNull(new GHStep(jsonObject).getCompletedAt());
    }
}