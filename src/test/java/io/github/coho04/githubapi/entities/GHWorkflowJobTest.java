package io.github.coho04.githubapi.entities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GHWorkflowJobTest {

    private GHWorkflowJob workflowJob;

    @BeforeEach
    void setUp() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("run_id", 123);
        jsonObject.put("run_url", "https://api.github.com/repos/test/repo/actions/runs/123");
        jsonObject.put("head_sha", "abcdef1234567890");
        jsonObject.put("status", "completed");
        jsonObject.put("conclusion", "success");
        jsonObject.put("started_at", OffsetDateTime.now().toString());
        jsonObject.put("completed_at", OffsetDateTime.now().toString());
        jsonObject.put("name", "Test Workflow Job");
        jsonObject.put("steps", new JSONArray(List.of(new JSONObject().put("name", "Step 1"))));
        jsonObject.put("check_run_url", "https://api.github.com/repos/test/repo/check-runs/456");
        jsonObject.put("labels", new JSONArray(List.of("label1", "label2")));
        jsonObject.put("runner_id", 789);
        jsonObject.put("runner_name", "test-runner");
        jsonObject.put("runner_group_id", 101112);
        jsonObject.put("runner_group_name", "test-runner-group");
        jsonObject.put("workflow_name", "Test Workflow");
        jsonObject.put("head_branch", "main");

        workflowJob = new GHWorkflowJob(jsonObject);
    }

    @Test
    void testGetRunId() {
        assertEquals(123, workflowJob.getRunId());
    }

    @Test
    void testGetRunUrl() {
        assertEquals("https://api.github.com/repos/test/repo/actions/runs/123", workflowJob.getRunUrl());
    }

    @Test
    void testGetHeadSha() {
        assertEquals("abcdef1234567890", workflowJob.getHeadSha());
    }

    @Test
    void testGetStatus() {
        assertEquals("completed", workflowJob.getStatus());
    }

    @Test
    void testGetConclusion() {
        assertEquals("success", workflowJob.getConclusion());
    }

    @Test
    void testGetStartedAt() {
        assertNotNull(workflowJob.getStartedAt());
    }

    @Test
    void testGetCompletedAt() {
        assertNotNull(workflowJob.getCompletedAt());
    }

    @Test
    void testGetName() {
        assertEquals("Test Workflow Job", workflowJob.getName());
    }

    @Test
    void testGetSteps() {
        assertNotNull(workflowJob.getSteps());
        assertEquals(1, workflowJob.getSteps().size());
        assertEquals("Step 1", workflowJob.getSteps().getFirst().getName());
    }

    @Test
    void testGetCheckRunUrl() {
        assertEquals("https://api.github.com/repos/test/repo/check-runs/456", workflowJob.getCheckRunUrl());
    }

    @Test
    void testGetLabels() {
        assertNotNull(workflowJob.getLabels());
        assertEquals(2, workflowJob.getLabels().size());
        assertTrue(workflowJob.getLabels().contains("label1"));
        assertTrue(workflowJob.getLabels().contains("label2"));
    }

    @Test
    void testGetRunnerId() {
        assertEquals(789, workflowJob.getRunnerId());
    }

    @Test
    void testGetRunnerName() {
        assertEquals("test-runner", workflowJob.getRunnerName());
    }

    @Test
    void testGetRunnerGroupId() {
        assertEquals(101112, workflowJob.getRunnerGroupId());
    }

    @Test
    void testGetRunnerGroupName() {
        assertEquals("test-runner-group", workflowJob.getRunnerGroupName());
    }

    @Test
    void testGetWorkflowName() {
        assertEquals("Test Workflow", workflowJob.getWorkflowName());
    }

    @Test
    void testGetHeadBranch() {
        assertEquals("main", workflowJob.getHeadBranch());
    }
}
