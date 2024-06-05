package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.ClassBase;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.repositories.GHLabel;
import de.coho04.githubapi.repositories.GHMilestone;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class GHPullRequest extends ClassBase {

    public String diffUrl;
    public String patchUrl;
    public String issueUrl;
    public String commitsUrl;
    public String reviewCommentsUrl;
    public String reviewCommentUrl;
    public String commentsUrl;
    public String statusesUrl;
    public int number;
    public GHState state;
    public boolean locked;
    public String title;
    public GHUser user;
    public String body;
    public List<GHLabel> labels;
    public GHMilestone milestone;
    public String activeLockReason;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
    public OffsetDateTime closedAt;
    public OffsetDateTime mergedAt;
    public String mergeCommitSha;
    public GHUser assignee;
    public List<GHUser> assignees;
    public List<GHUser> requestedReviewers;
    public List<GHUser> requestedTeams;
//    public GHHead head;
//    public GHBase base;
    public String authorAssociation;
    public String autoMerge;
    public boolean draft;

    public GHPullRequest(JSONObject jsonObject) {
        super(jsonObject);
        this.diffUrl = getStringOrNull(jsonObject, "diff_url");
        this.patchUrl = getStringOrNull(jsonObject, "patch_Url");
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
        this.user = new GHUser(jsonObject.getJSONObject("user"));
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
        this.assignees = getArrayOrNull(jsonObject, "assignees", GHUser::new);
        this.requestedReviewers = getArrayOrNull(jsonObject, "requested_reviewers", GHUser::new);
        this.requestedTeams = getArrayOrNull(jsonObject, "requested_teams", GHUser::new);
        this.authorAssociation = getStringOrNull(jsonObject, "author_association");
        this.autoMerge = getStringOrNull(jsonObject, "auto_merge");
        this.draft = getBooleanOrNull(jsonObject, "draft");
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public GHState getState() {
        return state;
    }

    public String getBody() {
        return body;
    }

    public int getNumber() {
        return number;
    }

    public GHMilestone getMilestone() {
        return milestone;
    }

    public GHUser getAssignee() {
        return assignee;
    }

    public GHUser getUser() {
        return user;
    }

    public List<GHLabel> getLabels() {
        return labels;
    }

    public String getActiveLockReason() {
        return activeLockReason;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public List<GHUser> getAssignees() {
        return assignees;
    }

    public List<GHUser> getRequestedTeams() {
        return requestedTeams;
    }

    public List<GHUser> getRequestedReviewers() {
        return requestedReviewers;
    }

    public OffsetDateTime getClosedAt() {
        return closedAt;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getDiffUrl() {
        return diffUrl;
    }


    public OffsetDateTime getMergedAt() {
        return mergedAt;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public String getReviewCommentsUrl() {
        return reviewCommentsUrl;
    }

    public String getReviewCommentUrl() {
        return reviewCommentUrl;
    }

    public String getAutoMerge() {
        return autoMerge;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public String getTitle() {
        return title;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isDraft() {
        return draft;
    }
}

