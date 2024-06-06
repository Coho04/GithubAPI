package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

@SuppressWarnings("unused")
public class GHStep extends GHBase {

    public String name;
    public String status;
    public String conclusion;
    public int number;
    public OffsetDateTime startedAt;
    public OffsetDateTime completedAt;

    public GHStep(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.status = getStringOrNull(jsonObject, "status");
        this.conclusion = getStringOrNull(jsonObject, "conclusion");
        this.number = getIntOrNull(jsonObject, "number");
        this.startedAt = getLocalDateOrNull(jsonObject, "started_at");
        this.completedAt = getLocalDateOrNull(jsonObject, "completed_at");
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    public String getConclusion() {
        return conclusion;
    }
}
