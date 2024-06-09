package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.ClassBase;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.entities.repositories.GHLabel;
import de.coho04.githubapi.entities.repositories.GHMilestone;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a GitHub Pull Request.
 * This class provides methods and properties to access information about a pull request in a GitHub repository.
 */
public class GHPullRequest extends ClassBase {

    private final String diffUrl;
    private final String patchUrl;
    private final String issueUrl;
    private final String commitsUrl;
    private final String reviewCommentsUrl;
    private final String reviewCommentUrl;
    private final String commentsUrl;
    private final String statusesUrl;
    private final int number;
    private GHState state;
    private final boolean locked;
    private final String title;
    private final GHUser user;
    private final String body;
    private final List<GHLabel> labels;
    private GHMilestone milestone;
    private final String activeLockReason;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final OffsetDateTime closedAt;
    private final OffsetDateTime mergedAt;
    private final String mergeCommitSha;
    private GHUser assignee;
    private final List<GHUser> assignees;
    private final List<GHUser> requestedReviewers;
    private final List<GHUser> requestedTeams;
    //    public GHHead head;
//    public GHBase base;
    private final String authorAssociation;
    private final String autoMerge;
    private final boolean draft;

    /**
     * Constructs a new GHPullRequest instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the pull request data
     */
    public GHPullRequest(Github github, JSONObject jsonObject) {
        super(jsonObject);
        this.diffUrl = getStringOrNull(jsonObject, "diff_url");
        this.patchUrl = getStringOrNull(jsonObject, "patch_url");
        this.issueUrl = getStringOrNull(jsonObject, "issue_url");
        this.commitsUrl = getStringOrNull(jsonObject, "commits_url");
        this.reviewCommentsUrl = getStringOrNull(jsonObject, "review_comments_url");
        this.reviewCommentUrl = getStringOrNull(jsonObject, "review_comment_url");
        this.commentsUrl = getStringOrNull(jsonObject, "comments_url");
        this.statusesUrl = getStringOrNull(jsonObject, "statuses_url");
        this.number = getIntOrNull(jsonObject, "number");
        if (jsonObject.has("state")) {
            this.state = GHState.fromString(getStringOrNull(jsonObject, "state"));
        }
        this.locked = getBooleanOrNull(jsonObject, "locked");
        this.title = getStringOrNull(jsonObject, "title");
        this.user = new GHUser(github, jsonObject.getJSONObject("user"));
        this.body = getStringOrNull(jsonObject, "body");
        this.labels = getArrayOrNull(jsonObject, "labels", GHLabel::new);
//        this.milestone = new GHMilestone(jsonObject.getJSONObject("milestone")); //TODO: FIX
        this.activeLockReason = getStringOrNull(jsonObject, "active_lock_reason");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.closedAt = getLocalDateOrNull(jsonObject, "closed_at");
        this.mergedAt = getLocalDateOrNull(jsonObject, "merged_at");
        this.mergeCommitSha = getStringOrNull(jsonObject, "merge_commit_sha");
//        this.assignee = new GHUser(jsonObject.getJSONObject("assignee")); //TODO: FIX
        this.assignees = getArrayOrNull(jsonObject, "assignees", json -> new GHUser(github, json));
        this.requestedReviewers = getArrayOrNull(jsonObject, "requested_reviewers", json -> new GHUser(github, json));
        this.requestedTeams = getArrayOrNull(jsonObject, "requested_teams", json -> new GHUser(github, json));
        this.authorAssociation = getStringOrNull(jsonObject, "author_association");
        this.autoMerge = getStringOrNull(jsonObject, "auto_merge");
        this.draft = getBooleanOrNull(jsonObject, "draft");
    }

    /**
     * Returns the creation date and time of the pull request.
     *
     * @return the creation date and time of the pull request
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the last updated date and time of the pull request.
     *
     * @return the last updated date and time of the pull request
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the state of the pull request.
     *
     * @return the state of the pull request
     */
    public GHState getState() {
        return state;
    }

    /**
     * Returns the body content of the pull request.
     *
     * @return the body content of the pull request
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the number of the pull request.
     *
     * @return the number of the pull request
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the milestone associated with the pull request.
     *
     * @return the milestone associated with the pull request
     */
    public GHMilestone getMilestone() {
        return milestone;
    }

    /**
     * Returns the assignee of the pull request.
     *
     * @return the assignee of the pull request
     */
    public GHUser getAssignee() {
        return assignee;
    }

    /**
     * Returns the user who created the pull request.
     *
     * @return the user who created the pull request
     */
    public GHUser getUser() {
        return user;
    }

    /**
     * Returns the list of labels associated with the pull request.
     *
     * @return the list of labels
     */
    public List<GHLabel> getLabels() {
        return labels;
    }

    /**
     * Returns the reason for locking the pull request.
     *
     * @return the active lock reason
     */
    public String getActiveLockReason() {
        return activeLockReason;
    }

    /**
     * Returns the URL of the issue associated with the pull request.
     *
     * @return the issue URL
     */
    public String getIssueUrl() {
        return issueUrl;
    }

    /**
     * Returns the list of assignees of the pull request.
     *
     * @return the list of assignees
     */
    public List<GHUser> getAssignees() {
        return assignees;
    }

    /**
     * Returns the list of teams requested for review of the pull request.
     *
     * @return the list of requested teams
     */
    public List<GHUser> getRequestedTeams() {
        return requestedTeams;
    }

    /**
     * Returns the list of users requested for review of the pull request.
     *
     * @return the list of requested reviewers
     */
    public List<GHUser> getRequestedReviewers() {
        return requestedReviewers;
    }

    /**
     * Returns the closing date and time of the pull request.
     *
     * @return the closing date and time of the pull request
     */
    public OffsetDateTime getClosedAt() {
        return closedAt;
    }

    /**
     * Returns the URL for fetching comments on the pull request.
     *
     * @return the comments URL
     */
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * Returns the URL for fetching commits on the pull request.
     *
     * @return the commits URL
     */
    public String getCommitsUrl() {
        return commitsUrl;
    }

    /**
     * Returns the URL for fetching the diff of the pull request.
     *
     * @return the diff URL
     */
    public String getDiffUrl() {
        return diffUrl;
    }

    /**
     * Returns the merging date and time of the pull request.
     *
     * @return the merging date and time of the pull request
     */
    public OffsetDateTime getMergedAt() {
        return mergedAt;
    }

    /**
     * Returns the author association of the pull request.
     *
     * @return the author association of the pull request
     */
    public String getAuthorAssociation() {
        return authorAssociation;
    }

    /**
     * Returns the merge commit SHA of the pull request.
     *
     * @return the merge commit SHA
     */
    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    /**
     * Returns the URL for fetching the patch of the pull request.
     *
     * @return the patch URL
     */
    public String getPatchUrl() {
        return patchUrl;
    }

    /**
     * Returns the URL for fetching review comments on the pull request.
     *
     * @return the review comments URL
     */
    public String getReviewCommentsUrl() {
        return reviewCommentsUrl;
    }

    /**
     * Returns the URL for fetching a specific review comment on the pull request.
     *
     * @return the review comment URL
     */
    public String getReviewCommentUrl() {
        return reviewCommentUrl;
    }

    /**
     * Returns the auto merge setting of the pull request.
     *
     * @return the auto merge setting
     */
    public String getAutoMerge() {
        return autoMerge;
    }

    /**
     * Returns the URL for fetching statuses of the pull request.
     *
     * @return the statuses URL
     */
    public String getStatusesUrl() {
        return statusesUrl;
    }

    /**
     * Returns the title of the pull request.
     *
     * @return the title of the pull request
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns whether the pull request is locked.
     *
     * @return true if the pull request is locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Returns whether the pull request is in draft state.
     *
     * @return true if the pull request is in draft state, false otherwise
     */
    public boolean isDraft() {
        return draft;
    }
}
