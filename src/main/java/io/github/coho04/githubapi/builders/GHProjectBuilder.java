package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.bases.GHBase;
import io.github.coho04.githubapi.entities.GHProject;
import org.json.JSONObject;

/**
 * This class is used to build a new GitHub project.
 * It extends GHBase and provides methods for setting the project's name and body, and for building the project.
 */
public class GHProjectBuilder extends GHBase {

    private final Github github;
    private final String url;
    private String name;
    private String body;

    /**
     * Constructs a new GHProjectBuilder instance with the provided GitHub instance and URL.
     *
     * @param github the GitHub instance
     * @param url the URL
     */
    public GHProjectBuilder(Github github, String url) {
        this.github = github;
        this.url = url;
    }

    /**
     * Sets the body of the project.
     *
     * @param body the body of the project
     * @return the GHProjectBuilder instance
     */
    public GHProjectBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * Sets the name of the project.
     *
     * @param name the name of the project
     * @return the GHProjectBuilder instance
     */
    public GHProjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Converts the GHProjectBuilder instance to a JSONObject.
     *
     * @return the JSONObject
     */
    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("name", name)
                .put("body", body);
    }

    /**
     * Builds the project and returns a new GHProject instance.
     *
     * @return the GHProject instance
     */
    public GHProject build() {
        String response = sendPostRequest(url, github.getToken(), toJSONObject());
        return new GHProject(github, new JSONObject(response));
    }

    /**
     * Returns the name of the project.
     *
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the GitHub instance.
     *
     * @return the GitHub instance
     */
    public Github getGithub() {
        return github;
    }

    /**
     * Returns the body of the project.
     *
     * @return the body of the project
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the URL.
     *
     * @return the URL
     */
    public String getUrl() {
        return url;
    }
}