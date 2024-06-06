package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * Represents a GitHub Action workflow step.
 * This class provides methods and properties to access information about a specific step in a GitHub Action workflow job.
 */
@SuppressWarnings("unused")
public class GHStep extends GHBase {

    public String name;
    public String status;
    public String conclusion;
    public int number;
    public OffsetDateTime startedAt;
    public OffsetDateTime completedAt;

    /**
     * Constructs a new GHStep instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the step data
     */
    public GHStep(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.status = getStringOrNull(jsonObject, "status");
        this.conclusion = getStringOrNull(jsonObject, "conclusion");
        this.number = getIntOrNull(jsonObject, "number");
        this.startedAt = getLocalDateOrNull(jsonObject, "started_at");
        this.completedAt = getLocalDateOrNull(jsonObject, "completed_at");
    }

    /**
     * Returns the name of the step.
     *
     * @return the name of the step
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the step number.
     *
     * @return the step number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the status of the step.
     *
     * @return the status of the step
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the completion time of the step.
     *
     * @return the completion time of the step
     */
    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    /**
     * Returns the start time of the step.
     *
     * @return the start time of the step
     */
    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    /**
     * Returns the conclusion of the step.
     *
     * @return the conclusion of the step
     */
    public String getConclusion() {
        return conclusion;
    }
}
