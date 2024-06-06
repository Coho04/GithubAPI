package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.ClassBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a GitHub Workflow Job.
 * This class provides methods and properties to access information about a workflow job in GitHub Actions.
 */
@SuppressWarnings("unused")
public class GHWorkflowJob extends ClassBase {

    private final int runId;
    private final String runUrl;
    private final String headSha;
    private final String status;
    private final String conclusion;
    private final OffsetDateTime startedAt;
    private final OffsetDateTime completedAt;
    private final String name;
    private List<GHStep> steps;
    private final String checkRunUrl;
    private List<String> labels;
    private final int runnerId;
    private final String runnerName;
    private final int runnerGroupId;
    private final String runnerGroupName;
    private final String workflowName;
    private final String headBranch;

    /**
     * Constructs a new GHWorkflowJob instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the workflow job data
     */
    public GHWorkflowJob(JSONObject jsonObject) {
        super(jsonObject);
        this.runId = getIntOrNull(jsonObject, "run_id");
        this.runUrl = getStringOrNull(jsonObject, "run_url");
        this.headSha = getStringOrNull(jsonObject, "head_sha");
        this.status = getStringOrNull(jsonObject, "status");
        this.conclusion = getStringOrNull(jsonObject, "conclusion");
        this.startedAt = getLocalDateOrNull(jsonObject, "started_at");
        this.completedAt = getLocalDateOrNull(jsonObject, "completed_at");
        this.name = getStringOrNull(jsonObject, "name");
        if (jsonObject.has("steps")) {
            jsonObject.getJSONArray("steps").toList().stream().map(JSONObject::new).map(GHStep::new).forEach(steps::add);
        }
        this.checkRunUrl = getStringOrNull(jsonObject, "check_run_url");
        if (jsonObject.has("labels")) {
            jsonObject.getJSONArray("labels").toList().stream().map(Object::toString).forEach(labels::add);
        }
        this.runnerId = getIntOrNull(jsonObject, "runner_id");
        this.runnerName = getStringOrNull(jsonObject, "runner_name");
        this.runnerGroupId = getIntOrNull(jsonObject, "runner_group_id");
        this.runnerGroupName = getStringOrNull(jsonObject, "runner_group_name");
        this.workflowName = getStringOrNull(jsonObject, "workflow_name");
        this.headBranch = getStringOrNull(jsonObject, "head_branch");
    }

    /**
     * Returns the name of the workflow job.
     *
     * @return the name of the workflow job
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the start time of the workflow job.
     *
     * @return the start time of the workflow job
     */
    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    /**
     * Returns the completion time of the workflow job.
     *
     * @return the completion time of the workflow job
     */
    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    /**
     * Returns the status of the workflow job.
     *
     * @return the status of the workflow job
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the conclusion of the workflow job.
     *
     * @return the conclusion of the workflow job
     */
    public String getConclusion() {
        return conclusion;
    }

    /**
     * Returns the head SHA of the workflow job.
     *
     * @return the head SHA of the workflow job
     */
    public String getHeadSha() {
        return headSha;
    }

    /**
     * Returns the head branch of the workflow job.
     *
     * @return the head branch of the workflow job
     */
    public String getHeadBranch() {
        return headBranch;
    }

    /**
     * Returns the run ID of the workflow job.
     *
     * @return the run ID of the workflow job
     */
    public int getRunId() {
        return runId;
    }

    /**
     * Returns the runner group ID of the workflow job.
     *
     * @return the runner group ID of the workflow job
     */
    public int getRunnerGroupId() {
        return runnerGroupId;
    }

    /**
     * Returns the list of steps in the workflow job.
     *
     * @return the list of steps
     */
    public List<GHStep> getSteps() {
        return steps;
    }

    /**
     * Returns the runner ID of the workflow job.
     *
     * @return the runner ID of the workflow job
     */
    public int getRunnerId() {
        return runnerId;
    }

    /**
     * Returns the list of labels associated with the workflow job.
     *
     * @return the list of labels
     */
    public List<String> getLabels() {
        return labels;
    }

    /**
     * Returns the URL for the check run associated with the workflow job.
     *
     * @return the check run URL
     */
    public String getCheckRunUrl() {
        return checkRunUrl;
    }

    /**
     * Returns the runner group name of the workflow job.
     *
     * @return the runner group name
     */
    public String getRunnerGroupName() {
        return runnerGroupName;
    }

    /**
     * Returns the runner name of the workflow job.
     *
     * @return the runner name
     */
    public String getRunnerName() {
        return runnerName;
    }

    /**
     * Returns the run URL of the workflow job.
     *
     * @return the run URL
     */
    public String getRunUrl() {
        return runUrl;
    }

    /**
     * Returns the workflow name of the workflow job.
     *
     * @return the workflow name
     */
    public String getWorkflowName() {
        return workflowName;
    }
}
