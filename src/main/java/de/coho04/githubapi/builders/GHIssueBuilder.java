package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.repositories.GHIssue;
import de.coho04.githubapi.repositories.GHMilestone;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@SuppressWarnings("unused")
public class GHIssueBuilder extends GHBase {

    private final Github github;
    private String title;
    private String body;
    private GHUser assignee;
    private GHMilestone milestone;
    private List<String> labels;
    private List<GHUser> assignees;
    private final GHState state = GHState.OPEN;

    public GHIssueBuilder(Github github, String title) {
        this.title = title;
        this.github = github;
    }

    public GHIssueBuilder(Github github, String title, String body) {
        this.title = title;
        this.body = body;
        this.github = github;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GHIssueBuilder assignee(String user) {
        this.assignee = github.findUserByName(user);
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
        JSONObject jsonObject = super.toJSONObject()
                .put("title", title)
                .put("body", body)
                .put("milestone", milestone)
                .put("labels", new JSONArray(labels))
                .put("state", state.toString())
                .put("assignees", new JSONArray(assignees));

        if (assignee != null) {
            jsonObject.put("assignee", assignee.toJSONObject());
        }
        return jsonObject;
    }
}
