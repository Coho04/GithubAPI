package de.coho04.githubapi.repositories;

import de.coho04.githubapi.bases.ClassBase;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.enums.GHState;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * This class represents a GitHub milestone.
 * It provides methods for fetching data about the milestone such as its number, title, due date, state, creator, open issues, closed issues, labels URL, and description.
 */
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

    /**
     * Constructs a new GHMilestone instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the milestone data
     */
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

    /**
     * Returns the description of the milestone.
     *
     * @return the description of the milestone
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date and time when the milestone was last updated.
     *
     * @return the date and time when the milestone was last updated
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the URL of the milestone labels.
     *
     * @return the URL of the milestone labels
     */
    public String getLabelsUrl() {
        return labelsUrl;
    }

    /**
     * Returns the title of the milestone.
     *
     * @return the title of the milestone
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the date and time when the milestone was created.
     *
     * @return the date and time when the milestone was created
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the date and time when the milestone was closed.
     *
     * @return the date and time when the milestone was closed
     */
    public OffsetDateTime getClosedAt() {
        return closedAt;
    }

    /**
     * Returns the number of the milestone.
     *
     * @return the number of the milestone
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the state of the milestone.
     *
     * @return the state of the milestone
     */
    public GHState getState() {
        return state;
    }

    /**
     * Returns the user who created the milestone.
     *
     * @return the user who created the milestone
     */
    public GHUser getCreator() {
        return creator;
    }

    /**
     * Returns the number of closed issues in the milestone.
     *
     * @return the number of closed issues in the milestone
     */
    public int getClosedIssues() {
        return closedIssues;
    }

    /**
     * Returns the number of open issues in the milestone.
     *
     * @return the number of open issues in the milestone
     */
    public int getOpenIssues() {
        return openIssues;
    }

    /**
     * Returns the due date of the milestone.
     *
     * @return the due date of the milestone
     */
    public String getDueOn() {
        return dueOn;
    }
}