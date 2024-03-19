package de.goldendeveloper.githubapi.repositories;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GHBranchTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        JSONObject commit = new JSONObject();
        commit.put("sha", "abc123").put("url", "https://test.com");
        jsonObject = new JSONObject();
        jsonObject.put("name", "Test Branch");
        jsonObject.put("commit", commit);
        jsonObject.put("protected", true);
    }

    @Test
    public void shouldReturnCorrectName() {
        assertEquals("Test Branch", new GHBranch(jsonObject).getName());
    }

    @Test
    public void shouldReturnCorrectCommitSha() {
        assertEquals("abc123",new GHBranch(jsonObject).getCommitSha() );
    }

    @Test
    public void shouldReturnCorrectCommitUrl() {
        assertEquals("https://test.com", new GHBranch(jsonObject).getCommitUrl());
    }

    @Test
    public void shouldReturnCorrectProtectionStatus() {
        assertTrue(new GHBranch(jsonObject).isProtected());
    }

    @Test
    public void shouldReturnFalseWhenProtectionStatusIsNotPresent() {
        jsonObject.remove("protected");
        assertFalse(new GHBranch(jsonObject).isProtected());
    }
}