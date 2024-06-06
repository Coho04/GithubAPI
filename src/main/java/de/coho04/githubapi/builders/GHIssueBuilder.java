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

/**
 * The GHIssueBuilder class is used to build and create GitHub issues.
 * This class provides methods to set various properties of an issue such as title, body, assignee, milestone, and labels.
 * It extends the GHBase class and implements the JSONHelper and HttpRequestInterface interfaces.
 * This class is part of the builder pattern to simplify issue creation on GitHub.
 */
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

    /**
     * Constructs a new GHIssueBuilder instance with the specified GitHub instance and title.
     *
     * @param github the GitHub instance
     * @param title  the title of the issue
     */
    public GHIssueBuilder(Github github, String title) {
        this.title = title;
        this.github = github;
    }

    /**
     * Constructs a new GHIssueBuilder instance with the specified GitHub instance, title, and body.
     *
     * @param github the GitHub instance
     * @param title  the title of the issue
     * @param body   the body of the issue
     */
    public GHIssueBuilder(Github github, String title, String body) {
        this.title = title;
        this.body = body;
        this.github = github;
    }

    /**
     * Sets the title of the issue.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the assignee of the issue.
     *
     * @param user the username of the assignee
     * @return the current instance of GHIssueBuilder
     */
    public GHIssueBuilder assignee(String user) {
        this.assignee = github.findUserByName(user);
        return this;
    }

    /**
     * Creates a new GHIssue instance based on the current state of the builder.
     *
     * @return a new GHIssue instance
     */
    public GHIssue create() {
        return new GHIssue(this.toJSONObject());
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
     * Converts the current state of the builder to a JSONObject.
     *
     * @return a JSONObject representing the current state of the builder
     */
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
