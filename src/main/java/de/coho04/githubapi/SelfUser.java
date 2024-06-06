package de.coho04.githubapi;

import de.coho04.githubapi.builders.GHPublicKeyBuilder;
import de.coho04.githubapi.entities.GHPublicKey;
import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.repositories.GHIssue;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

import java.util.List;

@SuppressWarnings("unused")
public class SelfUser extends GHUser {

    /**
     * Constructs a new GHUser instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the user data
     */
    public SelfUser(Github github, JSONObject jsonObject) {
        super(github, jsonObject);
    }

    public static SelfUser getSelfUser(Github github) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/user", github.getToken());
        assert response != null;
        return new SelfUser(github, new JSONObject(response));
    }

    public List<GHIssue> getIssues() {
        return fetchPaginatedData("/issues", jsonObject -> new GHIssue(github, jsonObject), github.getToken());
    }

    public List<GHUser> getBlockedUsers() {
        return fetchPaginatedData("/user/blocks", jsonObject -> new GHUser(github, jsonObject), github.getToken());
    }

    public void followUser(String username) {
        sendPutRequest(getBaseUrl() + "/user/following/" + username, github.getToken(), null);
    }

    public void unfollowUser(String username) {
        sendDeleteRequest(getBaseUrl() + "/user/following/" + username, github.getToken());
    }

    public GHPublicKeyBuilder addPublicKey() {
        return new GHPublicKeyBuilder(github);
    }

    public GHPublicKey addPublicKey(String title, String key) {
        return new GHPublicKeyBuilder(title, key, github).build();
    }

    public GHPublicKey getPublicKey(int id) {
        String response = sendGetRequest(getBaseUrl() + "/user/keys/" + id, github.getToken());
        assert response != null;
        return new GHPublicKey(new JSONObject(response));
    }

    public void deletePublicKey(int id) {
        sendDeleteRequest(getBaseUrl() + "/user/keys/" + id, github.getToken());
    }
}
