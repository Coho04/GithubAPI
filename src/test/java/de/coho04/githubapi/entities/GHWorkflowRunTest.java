package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHWorkflowRunTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("repository_id", 2);
        jsonObject.put("head_repository_id", 3);
        jsonObject.put("head_branch", "branch1");
        jsonObject.put("head_sha", "sha1");
    }

    @Test
    void shouldReturnCorrectId() {
        assertEquals(1, new GHWorkflowRun(jsonObject).getId());
    }

    @Test
    void shouldReturnZeroWhenIdIsNotPresent() {
        jsonObject.remove("id");
        assertEquals(0, new GHWorkflowRun(jsonObject).getId());
    }

    @Test
    void shouldReturnCorrectRepositoryId() {
        assertEquals(2, new GHWorkflowRun(jsonObject).getRepositoryId());
    }

    @Test
    void shouldReturnZeroWhenRepositoryIdIsNotPresent() {
        jsonObject.remove("repository_id");
        assertEquals(0, new GHWorkflowRun(jsonObject).getRepositoryId());
    }

    @Test
    void shouldReturnCorrectHeadRepositoryId() {
        assertEquals(3, new GHWorkflowRun(jsonObject).getHeadRepositoryId());
    }

    @Test
    void shouldReturnZeroWhenHeadRepositoryIdIsNotPresent() {
        jsonObject.remove("head_repository_id");
        assertEquals(0, new GHWorkflowRun(jsonObject).getHeadRepositoryId());
    }

    @Test
    void shouldReturnCorrectHeadBranch() {
        assertEquals("branch1", new GHWorkflowRun(jsonObject).getHeadBranch());
    }

    @Test
    void shouldReturnNullWhenHeadBranchIsNotPresent() {
        jsonObject.remove("head_branch");
        assertNull(new GHWorkflowRun(jsonObject).getHeadBranch());
    }

    @Test
    void shouldReturnCorrectHeadSha() {
        assertEquals("sha1", new GHWorkflowRun(jsonObject).getHeadSha());
    }

    @Test
    void shouldReturnNullWhenHeadShaIsNotPresent() {
        jsonObject.remove("head_sha");
        assertNull(new GHWorkflowRun(jsonObject).getHeadSha());
    }
}