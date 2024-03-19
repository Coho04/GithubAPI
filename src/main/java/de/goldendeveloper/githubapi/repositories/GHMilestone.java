package de.goldendeveloper.githubapi.repositories;

import de.goldendeveloper.githubapi.bases.ClassBase;
import de.goldendeveloper.githubapi.entities.GHUser;
import de.goldendeveloper.githubapi.enums.GHState;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@SuppressWarnings("unused")
public class GHMilestone extends ClassBase {

    private final int number;
    private final String title;
    private final String dueOn; //Date?
    private GHState state;
    private GHUser creator;
    private final int openIssues;
    private final OffsetDateTime closedAt;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final int closedIssues;
    private final String labelsUrl;
    private final String description;

    public GHMilestone(JSONObject jsonObject) {
        super(jsonObject);
        if (jsonObject.has("creator")) {
            this.creator = new GHUser(jsonObject.getJSONObject("creator"));
        }
        this.closedAt = getLocalDateOrNull(jsonObject, "closed_at");
        this.description = getStringOrNull(jsonObject, "description");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.title = getStringOrNull(jsonObject, "title");
        this.closedIssues = getIntOrNull(jsonObject, "closed_issues");
        this.dueOn = getStringOrNull(jsonObject, "due_on");
        this.labelsUrl = getStringOrNull(jsonObject, "labels_url");
        this.number = getIntOrNull(jsonObject, "number");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        if (jsonObject.has("state")) {
            this.state = GHState.fromString(getStringOrNull(jsonObject, "state"));
        }
        this.openIssues = getIntOrNull(jsonObject, "open_issues");
    }

    public String getDescription() {
        return description;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public String getTitle() {
        return title;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getClosedAt() {
        return closedAt;
    }

    public int getNumber() {
        return number;
    }

    public GHState getState() {
        return state;
    }

    public GHUser getCreator() {
        return creator;
    }

    public int getClosedIssues() {
        return closedIssues;
    }

    public int getOpenIssues() {
        return openIssues;
    }

    public String getDueOn() {
        return dueOn;
    }
}
