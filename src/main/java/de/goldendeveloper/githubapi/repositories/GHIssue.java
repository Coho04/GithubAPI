package de.goldendeveloper.githubapi.repositories;

import de.goldendeveloper.githubapi.bases.ClassBase;
import de.goldendeveloper.githubapi.entities.GHUser;
import de.goldendeveloper.githubapi.enums.GHState;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

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
    private final LocalDateTime createdAt; //Date
    private final LocalDateTime updatedAt; //Date
    private final LocalDateTime closedAt;
    private final String commentsUrl;
    private final String timelineUrl;
    private final String repositoryUrl;
    private final String activeLockReason;
    private final String authorAssociation;
    private final String performedViaGithubApp;
    private final String stateReason;
    private GHMilestone milestone;
    private final List<GHLabels> labels;
    private final List<GHUser> assignees;
    private final GHUser user;
    private GHUser assignee;

        /*
  "pull_request": {
    "patch_url": "https://github.com/Golden-Developer/MC-FootBackpack/pull/8.patch",
    "html_url": "https://github.com/Golden-Developer/MC-FootBackpack/pull/8",
    "merged_at": null,
    "diff_url": "https://github.com/Golden-Developer/MC-FootBackpack/pull/8.diff",
    "url": "https://api.github.com/repos/Golden-Developer/MC-FootBackpack/pulls/8"
  },
  "reactions": {
    "confused": 0,
    "-1": 0,
    "total_count": 0,
    "+1": 0,
    "rocket": 0,
    "hooray": 0,
    "eyes": 0,
    "url": "https://api.github.com/repos/Golden-Developer/MC-FootBackpack/issues/8/reactions",
    "laugh": 0,
    "heart": 0
  },
}*/

    public GHIssue(JSONObject jsonObject) {
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
            this.milestone = new GHMilestone(milestoneJSONObject);
        }
        this.labels = getArrayOrNull(jsonObject, "labels", GHLabels::new);
        this.assignees = getArrayOrNull(jsonObject, "assignees", GHUser::new);
        this.user = new GHUser(getJSONObjectOrNull(jsonObject, "user"));
        JSONObject assigneeJSONObject = getJSONObjectOrNull(jsonObject, "assignee");
        if (assigneeJSONObject != null) {
            this.assignee = new GHUser(assigneeJSONObject);
        }
    }

    public void close() {
        //TODO: Implement
    }

    public int getNumber() {
        return number;
    }

    public int getComments() {
        return comments;
    }

    public String getActiveLockReason() {
        return activeLockReason;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public GHMilestone getMilestone() {
        return milestone;
    }

    public String getPerformedViaGithubApp() {
        return performedViaGithubApp;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public GHState getState() {
        return state;
    }

    public String getStateReason() {
        return stateReason;
    }

    public String getTimelineUrl() {
        return timelineUrl;
    }


    public String getTitle() {
        return title;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDraft() {
        return draft;
    }

    public boolean isLocked() {
        return locked;
    }

    public GHUser getUser() {
        return user;
    }

    public GHUser getAssignee() {
        return assignee;
    }

    public List<GHLabels> getLabels() {
        return labels;
    }

    public List<GHUser> getAssignees() {
        return assignees;
    }
}
