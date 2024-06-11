package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.repositories.GHBranch;
import de.coho04.githubapi.entities.repositories.GHRepository;
import org.json.JSONObject;

import java.util.Base64;

/**
 * GHFileBuilder is a class that extends GHBase. It represents a builder for creating and committing files to a GitHub repository.
 * It contains several properties such as branch, path, content, message, and repository.
 * These properties are initialized through the constructor and can be set using the provided setter methods.
 *
 * @author Coho04
 * @version 1.0
 * @since 2024-1.2
 */
public class GHFileBuilder extends GHBase {

    private GHBranch branch;
    private String path;
    private String content;
    private String message;
    private final GHRepository repository;
    private final Github github;

    /**
     * Constructs a new GHFileBuilder object for a given repository.
     *
     * @param repository a GHRepository object representing the repository to which the file will be committed.
     */
    public GHFileBuilder(GHRepository repository, Github github) {
        this.repository = repository;
        this.github = github;
    }

    /**
     * Constructs a new GHFileBuilder object for a given repository and branch.
     *
     * @param repository a GHRepository object representing the repository to which the file will be committed.
     * @param branch a GHBranch object representing the branch to which the file will be committed.
     */
    public GHFileBuilder(GHRepository repository, GHBranch branch, Github github) {
        this.branch = branch;
        this.github = github;
        this.repository = repository;
    }

    /**
     * Constructs a new GHFileBuilder object with all properties set.
     *
     * @param repository a GHRepository object representing the repository to which the file will be committed.
     * @param branch a GHBranch object representing the branch to which the file will be committed.
     * @param path a String representing the path of the file.
     * @param content a String representing the content of the file.
     * @param message a String representing the commit message.
     */
    public GHFileBuilder(GHRepository repository, GHBranch branch, String path, String content, String message, Github github) {
        this.repository = repository;
        this.branch = branch;
        this.path = path;
        this.content = content;
        this.message = message;
        this.github = github;
    }

    /**
     * Sets the branch to which the file will be committed.
     *
     * @param branch a GHBranch object representing the branch to which the file will be committed.
     * @return the current GHFileBuilder object.
     */
    public GHFileBuilder setBranch(GHBranch branch) {
        this.branch = branch;
        return this;
    }

    /**
     * Sets the branch to which the file will be committed.
     *
     * @param branch a String representing the name of the branch to which the file will be committed.
     * @return the current GHFileBuilder object.
     */
    public GHFileBuilder setBranch(String branch) {
        this.branch = repository.getBranches().get(branch);
        return this;
    }

    /**
     * Sets the content of the file.
     *
     * @param content a String representing the content of the file.
     * @return the current GHFileBuilder object.
     */
    public GHFileBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Sets the commit message.
     *
     * @param message a String representing the commit message.
     * @return the current GHFileBuilder object.
     */
    public GHFileBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Sets the path of the file.
     *
     * @param path a String representing the path of the file.
     * @return the current GHFileBuilder object.
     */
    public GHFileBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Returns the GitHub instance associated with this file builder.
     *
     * @return the GitHub instance
     */
    public Github getGithub() {
        return github;
    }

    /**
     * Returns the repository where this file will be committed.
     *
     * @return the GHRepository instance
     */
    public GHRepository getRepository() {
        return repository;
    }

    /**
     * Returns the branch where this file will be committed.
     *
     * @return the GHBranch instance
     */
    public GHBranch getBranch() {
        return branch;
    }

    /**
     * Returns the content of the file to be committed.
     *
     * @return the content of the file
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the commit message for the file commit.
     *
     * @return the commit message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the path where the file will be committed.
     *
     * @return the path of the file
     */
    public String getPath() {
        return path;
    }

    /**
     * Commits the file to the repository.
     * This method is not yet implemented.
     */
    public void commit(String email, String name) {
        String url = repository.getUrl() + "/contents/" + path;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        JSONObject committer = new JSONObject();
        committer.put("name", name);
        committer.put("email", email);
        jsonObject.put("committer", committer);
        String encodedString = Base64.getEncoder().encodeToString(content.getBytes());
        jsonObject.put("content", encodedString);
        sendPutRequest(url,github.getToken(), jsonObject);
    }

    /**
     * Commits the file to the repository using the authenticated user's email and login as the committer's details.
     * This method first sends a GET request to retrieve the authenticated user's email.
     * Then, it calls the overloaded commit method with the retrieved email and the authenticated user's login.
     */
    public void commit() {
        String response =  sendGetRequest(getBaseUrl() + "/user/email", github.getToken());
        if (response == null) {
            response = new JSONObject().put("email", "example@example.com").toString();
        }
        JSONObject jsonObject = new JSONObject(response);
        commit(jsonObject.getString("email"), github.getSelfUser().getLogin());
    }
}