package de.coho04.githubapi;

import de.coho04.githubapi.builders.GHPublicKeyBuilder;
import de.coho04.githubapi.entities.GHPublicKey;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.entities.repositories.GHIssue;
import de.coho04.githubapi.entities.repositories.GHRepository;
import de.coho04.githubapi.utilities.HttpRequestHelper;
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
     * @param jsonObject the JSON object containing the user data
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
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/user", github.getToken());
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
        sendPutRequest(getBaseUrl() + "/user/following/" + username, github.getToken(), null);
    }

    /**
     * Unfollows the user with the provided username.
     *
     * @param username the username of the user to unfollow
     */
    public void unfollowUser(String username) {
        sendDeleteRequest(getBaseUrl() + "/user/following/" + username, github.getToken());
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
        String response = sendGetRequest(getBaseUrl() + "/user/keys/" + id, github.getToken());
        assert response != null;
        return new GHPublicKey(new JSONObject(response));
    }

    /**
     * Deletes the public key with the provided id.
     *
     * @param id the id of the public key
     */
    public void deletePublicKey(int id) {
        sendDeleteRequest(getBaseUrl() + "/user/keys/" + id, github.getToken());
    }

    public List<GHRepository> listCreatedRepositories() {
        return fetchPaginatedData(getBaseUrl(), "/user/repos", jsonObject -> new GHRepository(jsonObject, github), github.getToken()).stream().filter(repo -> repo.getOwner().getLogin().equals(this.getLogin())).toList();
    }

    public List<GHRepository> listRepositoriesWithAccess() {
        return fetchPaginatedData(getBaseUrl(), "/user/repos", jsonObject -> new GHRepository(jsonObject, github), github.getToken());
    }
}