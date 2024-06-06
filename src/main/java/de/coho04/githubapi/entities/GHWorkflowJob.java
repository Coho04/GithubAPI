package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.ClassBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class GHWorkflowJob extends ClassBase {

    public int runId;
    public String runUrl;
    public String headSha;
    public String status;
    public String conclusion;
    public OffsetDateTime startedAt;
    public OffsetDateTime completedAt;
    public String name;
    public List<GHStep> steps;
    public String checkRunUrl;
    public List<String> labels;
    public int runnerId;
    public String runnerName;
    public int runnerGroupId;
    public String runnerGroupName;
    public String workflowName;
    public String headBranch;

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

    public String getName() {
        return name;
    }

    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    public String getStatus() {
        return status;
    }

    public String getConclusion() {
        return conclusion;
    }

    public String getHeadSha() {
        return headSha;
    }

    public String getHeadBranch() {
        return headBranch;
    }

    public int getRunId() {
        return runId;
    }

    public int getRunnerGroupId() {
        return runnerGroupId;
    }

    public List<GHStep> getSteps() {
        return steps;
    }

    public int getRunnerId() {
        return runnerId;
    }

    public List<String> getLabels() {
        return labels;
    }

    public String getCheckRunUrl() {
        return checkRunUrl;
    }

    public String getRunnerGroupName() {
        return runnerGroupName;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public String getRunUrl() {
        return runUrl;
    }

    public String getWorkflowName() {
        return workflowName;
    }
}
