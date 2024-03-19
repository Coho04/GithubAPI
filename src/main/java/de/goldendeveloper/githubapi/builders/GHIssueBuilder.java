package de.goldendeveloper.githubapi.builders;

import de.goldendeveloper.githubapi.bases.GHBase;
import de.goldendeveloper.githubapi.entities.GHUser;
import de.goldendeveloper.githubapi.repositories.GHIssue;
import de.goldendeveloper.githubapi.repositories.GHMilestone;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@SuppressWarnings("unused")
public class GHIssueBuilder extends GHBase {

    private String title;
    private String body;
    private String assignee;
    private GHMilestone milestone;
    private List<String> labels;
    private List<GHUser> assignees;

    public GHIssueBuilder(String title) {
        this.title = title;
    }

    public GHIssueBuilder(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GHIssueBuilder assignee(String user) {
        this.assignee = user;
        return this;
    }

    public GHIssue create() {
        return new GHIssue(this.toJSONObject());
    }

    public String getTitle() {
        return title;
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("title", title)
                .put("body", body)
                .put("assignee", assignee)
                .put("milestone", milestone)
                .put("labels", new JSONArray(labels))
                .put("assignees", new JSONArray(assignees));
    }
}
