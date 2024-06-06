package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.repositories.GHIssue;
import de.coho04.githubapi.repositories.GHMilestone;
import de.coho04.githubapi.repositories.GHRepository;
import org.json.JSONObject;

import java.util.ArrayList;
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
    private final GHRepository repository;
    private String title;
    private String body;
    private GHMilestone milestone;
    private final List<String> labels = new ArrayList<>();
    private final List<String> assignees = new ArrayList<>();
    private final GHState state = GHState.OPEN;

    /**
     * Constructs a new GHIssueBuilder instance with the specified GitHub instance and title.
     *
     * @param github the GitHub instance
     * @param title  the title of the issue
     */
    public GHIssueBuilder(Github github, GHRepository repository, String title) {
        this.title = title;
        this.repository = repository;
        this.github = github;
    }

    /**
     * Constructs a new GHIssueBuilder instance with the specified GitHub instance, title, and body.
     *
     * @param github the GitHub instance
     * @param title  the title of the issue
     * @param body   the body of the issue
     */
    public GHIssueBuilder(Github github, GHRepository repository, String title, String body) {
        this.title = title;
        this.body = body;
        this.repository = repository;
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
        this.assignees.add(user);
        return this;
    }

    /**
     * Adds a label to the issue.
     *
     * @param label the label to be added to the issue
     * @return the current instance of GHIssueBuilder
     */
    public GHIssueBuilder addLabel(String label) {
        this.labels.add(label);
        return this;
    }

    /**
     * Creates a new GHIssue instance based on the current state of the builder.
     *
     * @return a new GHIssue instance
     */
    public GHIssue create() {
        JSONObject object = this.toJSONObject();
        object.remove("state");
        String response = sendPostRequest(repository.getUrl() + "/issues", github.getToken(), object);
        return new GHIssue(github, new JSONObject(response));
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
        return super.toJSONObject()
                .put("title", title)
                .put("body", body)
                .put("milestone", milestone)
                .put("labels", labels.toArray())
                .put("state", state.toString())
                .put("assignees", assignees.toArray());
    }
}
