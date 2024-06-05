package de.coho04.githubapi.repositories;

import de.coho04.githubapi.enums.GHState;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class GHIssueTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("number", 123);
        jsonObject.put("comments", 10);
        jsonObject.put("body", "Test Body");
        jsonObject.put("state", "open");
        jsonObject.put("user", new JSONObject().put("login", "TestUser"));
        jsonObject.put("labels", Collections.singletonList(new JSONObject().put("name", "TestLabel")));
        jsonObject.put("assignees", Collections.singletonList(new JSONObject().put("login", "TestAssignee")));
    }

    @Test
    void shouldReturnCorrectNumber() {
        assertEquals(123, new GHIssue(jsonObject).getNumber());
    }

    @Test
    void shouldReturnCorrectComments() {
        assertEquals(10, new GHIssue(jsonObject).getComments());
    }

    @Test
    void shouldReturnCorrectBody() {
        assertEquals("Test Body", new GHIssue(jsonObject).getBody());
    }

    @Test
    void shouldReturnCorrectState() {
        assertEquals(GHState.OPEN, new GHIssue(jsonObject).getState());
    }

    @Test
    void shouldReturnCorrectUser() {
        assertEquals("TestUser", new GHIssue(jsonObject).getUser().getLogin());
    }

    @Test
    void shouldReturnCorrectLabels() {
        assertEquals("TestLabel", new GHIssue(jsonObject).getLabels().get(0).getName());
    }

    @Test
    void shouldReturnCorrectAssignees() {
        assertEquals("TestAssignee", new GHIssue(jsonObject).getAssignees().get(0).getLogin());
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
}