package io.github.coho04.githubapi;

import io.github.coho04.githubapi.builders.GHProjectBuilder;
import io.github.coho04.githubapi.builders.GHPublicKeyBuilder;
import io.github.coho04.githubapi.entities.GHProject;
import io.github.coho04.githubapi.entities.GHPublicKey;
import io.github.coho04.githubapi.entities.GHUser;
import io.github.coho04.githubapi.entities.repositories.GHIssue;
import io.github.coho04.githubapi.entities.repositories.GHRepository;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import io.github.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.util.List;

/**
 * This class represents a GitHub user.
 * It provides methods for fetching data about the user such as their issues, blocked users, and public keys, and for following and unfollowing users.
 */
public class SelfUser extends GHUser {

    /**
     * Constructs a new GHUser instance with the provided JSON object.
     *
     * @param jsonObject github
     */
    public SelfUser(Github github, JSONObject jsonObject) {
        super(github, jsonObject);
    }

    /**
     * Returns the GitHub user associated with the provided GitHub instance.
     *
     * @param github the GitHub instance
     * @return the GitHub user
     */
    public static SelfUser getSelfUser(Github github) {
        String response = HttpRequestHelper.sendGetRequest(GHBase.getBaseUrl() + "/user", github.getToken());
        assert response != null;
        return new SelfUser(github, new JSONObject(response));
    }

    /**
     * Returns the issues of the user.
     *
     * @return the issues of the user
     */
    public List<GHIssue> getIssues() {
        return fetchPaginatedData("/issues", jsonObject -> new GHIssue(github, jsonObject), github.getToken());
    }

    /**
     * Returns the users blocked by the user.
     *
     * @return the users blocked by the user
     */
    public List<GHUser> getBlockedUsers() {
        return fetchPaginatedData("/user/blocks", jsonObject -> new GHUser(github, jsonObject), github.getToken());
    }

    /**
     * Follows the user with the provided username.
     *
     * @param username the username of the user to follow
     */
    public void followUser(String username) {
        sendPutRequest(GHBase.getBaseUrl() + "/user/following/" + username, github.getToken(), null);
    }

    /**
     * Unfollows the user with the provided username.
     *
     * @param username the username of the user to unfollow
     */
    public void unfollowUser(String username) {
        sendDeleteRequest(GHBase.getBaseUrl() + "/user/following/" + username, github.getToken());
    }

    /**
     * Returns a new GHPublicKeyBuilder instance associated with the user's GitHub instance.
     *
     * @return a new GHPublicKeyBuilder instance
     */
    public GHPublicKeyBuilder addPublicKey() {
        return new GHPublicKeyBuilder(github);
    }

    /**
     * Returns a new GHPublicKey instance with the provided title and key, associated with the user's GitHub instance.
     *
     * @param title the title of the public key
     * @param key the key of the public key
     * @return a new GHPublicKey instance
     */
    public GHPublicKey addPublicKey(String title, String key) {
        return new GHPublicKeyBuilder(title, key, github).build();
    }

    /**
     * Returns the public key with the provided id.
     *
     * @param id the id of the public key
     * @return the public key
     */
    public GHPublicKey getPublicKey(int id) {
        String response = sendGetRequest(GHBase.getBaseUrl() + "/user/keys/" + id, github.getToken());
        assert response != null;
        return new GHPublicKey(new JSONObject(response));
    }

    /**
     * Deletes the public key with the provided id.
     *
     * @param id the id of the public key
     */
    public void deletePublicKey(int id) {
        sendDeleteRequest(GHBase.getBaseUrl() + "/user/keys/" + id, github.getToken());
    }

    /**
     * Returns a list of repositories created by the user.
     *
     * @return a list of repositories created by the user
     */
    public List<GHRepository> listCreatedRepositories() {
        return fetchPaginatedData(GHBase.getBaseUrl(), "/user/repos", jsonObject -> new GHRepository(jsonObject, github), github.getToken()).stream().filter(repo -> repo.getOwner().getLogin().equals(this.getLogin())).toList();
    }

    /**
     * Returns a list of repositories the user has access to.
     *
     * @return a list of repositories the user has access to
     */
    public List<GHRepository> listRepositoriesWithAccess() {
        return fetchPaginatedData(GHBase.getBaseUrl(), "/user/repos", jsonObject -> new GHRepository(jsonObject, github), github.getToken());
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return  sendGetRequest(GHBase.getBaseUrl() + "/user/email", github.getToken());
    }

    /**
     * Returns a new GHProjectBuilder instance for creating a project in the organization.
     *
     * @return a new GHProjectBuilder instance
     */
    public GHProjectBuilder createProject() {
        return new GHProjectBuilder(this.github, getUrl() + "/projects");
    }

    /**
     * Creates a project in the organization with the provided name and body.
     *
     * @param name the name of the project
     * @param body the body of the project
     * @return the created project
     */
    public GHProject createProject(String name, String body) {
        return new GHProjectBuilder(this.github, getUrl() + "/projects")
                .setName(name)
                .setBody(body)
                .build();
    }
}