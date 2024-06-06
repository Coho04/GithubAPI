package de.coho04.githubapi.repositories;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.ClassBase;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.enums.GHState;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a GitHub issue.
 * This class is a part of the GitHub API library.
 */
@SuppressWarnings("unused")
public class GHIssue extends ClassBase {

    private final int number;
    private final String body;
    private final String title;
    private final GHState state;
    private final int comments;
    private final boolean draft;
    private final boolean locked;
    private final String labelsUrl;
    private final OffsetDateTime createdAt; //Date
    private final OffsetDateTime updatedAt; //Date
    private final OffsetDateTime closedAt;
    private final String commentsUrl;
    private final String timelineUrl;
    private final String repositoryUrl;
    private final String activeLockReason;
    private final String authorAssociation;
    private final String performedViaGithubApp;
    private final String stateReason;
    private GHMilestone milestone;
    private final List<GHLabel> labels;
    private final List<GHUser> assignees;
    private GHUser user;
    private GHUser assignee;

    /**
     * Constructs a GHIssue object from a JSONObject.
     * @param jsonObject JSONObject representing a GitHub issue.
     */
    public GHIssue(Github github, JSONObject jsonObject) {
        super(jsonObject);
        this.number = getIntOrNull(jsonObject, "number");
        this.body = getStringOrNull(jsonObject, "body");
        this.title = getStringOrNull(jsonObject, "title");
        this.state = GHState.fromString(getStringOrNull(jsonObject, "state"));
        this.draft = getBooleanOrNull(jsonObject, "draft");
        this.locked = getBooleanOrNull(jsonObject, "locked");
        this.labelsUrl = getStringOrNull(jsonObject, "labels_url");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.authorAssociation = getStringOrNull(jsonObject, "author_association");
        this.commentsUrl = getStringOrNull(jsonObject, "comments_url");
        this.activeLockReason = getStringOrNull(jsonObject, "active_lock_reason");
        this.repositoryUrl = getStringOrNull(jsonObject, "repository_url");
        this.timelineUrl = getStringOrNull(jsonObject, "timeline_url");
        this.performedViaGithubApp = getStringOrNull(jsonObject, "performed_via_github_app");
        this.stateReason = getStringOrNull(jsonObject, "state_reason");
        this.comments = getIntOrNull(jsonObject, "comments");
        this.closedAt = getLocalDateOrNull(jsonObject, "closed_at");
        JSONObject milestoneJSONObject = getJSONObjectOrNull(jsonObject, "milestone");
        if (milestoneJSONObject != null) {
            this.milestone = new GHMilestone(github, milestoneJSONObject);
        }
        this.labels = getArrayOrNull(jsonObject, "labels", GHLabel::new);
        this.assignees = getArrayOrNull(jsonObject, "assignees",jsonObject1 -> new GHUser(github, jsonObject1));
        if (jsonObject.has("user")) {
            this.user = new GHUser(github, getJSONObjectOrNull(jsonObject, "user"));
        }
        JSONObject assigneeJSONObject = getJSONObjectOrNull(jsonObject, "assignee");
        if (assigneeJSONObject != null) {
            this.assignee = new GHUser(github, assigneeJSONObject);
        }
    }

    /**
     * Closes the issue.
     */
    public void close() {
        //TODO: Implement
    }

    /**
     * Returns the issue number.
     *
     * @return the issue number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the number of comments on the issue.
     *
     * @return the number of comments
     */
    public int getComments() {
        return comments;
    }

    /**
     * Returns the reason for the issue being locked.
     *
     * @return the reason for the issue being locked
     */
    public String getActiveLockReason() {
        return activeLockReason;
    }

    /**
     * Returns the author association of the issue.
     *
     * @return the author association
     */
    public String getAuthorAssociation() {
        return authorAssociation;
    }

    /**
     * Returns the body of the issue.
     *
     * @return the body of the issue
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the date and time when the issue was closed.
     *
     * @return the date and time when the issue was closed
     */
    public OffsetDateTime getClosedAt() {
        return closedAt;
    }

    /**
     * Returns the URL of the issue comments.
     *
     * @return the URL of the issue comments
     */
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * Returns the date and time when the issue was created.
     *
     * @return the date and time when the issue was created
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the URL of the issue labels.
     *
     * @return the URL of the issue labels
     */
    public String getLabelsUrl() {
        return labelsUrl;
    }

    /**
     * Returns the milestone of the issue.
     *
     * @return the milestone of the issue
     */
    public GHMilestone getMilestone() {
        return milestone;
    }

    /**
     * Returns the app via which the issue was performed.
     *
     * @return the app via which the issue was performed
     */
    public String getPerformedViaGithubApp() {
        return performedViaGithubApp;
    }

    /**
     * Returns the URL of the repository where the issue is located.
     *
     * @return the URL of the repository where the issue is located
     */
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    /**
     * Returns the state of the issue.
     *
     * @return the state of the issue
     */
    public GHState getState() {
        return state;
    }

    /**
     * Returns the reason for the state of the issue.
     *
     * @return the reason for the state of the issue
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     * Returns the URL of the issue timeline.
     *
     * @return the URL of the issue timeline
     */
    public String getTimelineUrl() {
        return timelineUrl;
    }

    /**
     * Returns the title of the issue.
     *
     * @return the title of the issue
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the date and time when the issue was last updated.
     *
     * @return the date and time when the issue was last updated
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns whether the issue is a draft.
     *
     * @return true if the issue is a draft, false otherwise
     */
    public boolean isDraft() {
        return draft;
    }

    /**
     * Returns whether the issue is locked.
     *
     * @return true if the issue is locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Returns the user who created the issue.
     *
     * @return the user who created the issue
     */
    public GHUser getUser() {
        return user;
    }

    /**
     * Returns the user assigned to the issue.
     *
     * @return the user assigned to the issue
     */
    public GHUser getAssignee() {
        return assignee;
    }

    /**
     * Returns the labels of the issue.
     *
     * @return the labels of the issue
     */
    public List<GHLabel> getLabels() {
        return labels;
    }

    /**
     * Returns the users assigned to the issue.
     *
     * @return the users assigned to the issue
     */
    public List<GHUser> getAssignees() {
        return assignees;
    }
}