package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.bases.EntityBase;
import io.github.coho04.githubapi.entities.repositories.GHRepository;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

import java.util.List;

/**
 * Represents a GitHub user.
 * This class provides methods and properties to access information about a user on GitHub.
 */
public class GHUser extends EntityBase {

    private final String gistsUrl;
    private final String gravatarId;
    private final boolean siteAdmin;
    private final String starredUrl;
    private final int contributions;
    private final String followingUrl;
    private final String followersUrl;
    private final String subscriptionsUrl;
    private final String receivedEventsUrl;
    private final String organizationsUrl;
    protected final Github github;

    /**
     * Constructs a new GHUser instance with the provided JSON object.
     *
     * @param github,jsonObject the JSON object containing the user data
     */
    public GHUser(Github github, JSONObject jsonObject) {
        super(jsonObject);
        this.github = github;
        this.contributions = getIntOrNull(jsonObject, "contributions");
        this.gistsUrl = getStringOrNull(jsonObject, "gists_url");
        this.siteAdmin = getBooleanOrNull(jsonObject, "site_admin");
        this.gravatarId = getStringOrNull(jsonObject, "gravatar_id");
        this.starredUrl = getStringOrNull(jsonObject, "starred_url");
        this.followersUrl = getStringOrNull(jsonObject, "followers_url");
        this.followingUrl = getStringOrNull(jsonObject, "following_url");
        this.subscriptionsUrl = getStringOrNull(jsonObject, "subscriptions_url");
        this.receivedEventsUrl = getStringOrNull(jsonObject, "received_events_url");
        this.organizationsUrl = getStringOrNull(jsonObject, "organizations_url");
    }

    /**
     * Converts this user instance to a JSONObject.
     *
     * @return a JSONObject representation of this user
     */
    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("gists_url", gistsUrl)
                .put("gravatar_id", gravatarId)
                .put("site_admin", siteAdmin)
                .put("starred_url", starredUrl)
                .put("contributions", contributions)
                .put("following_url", followingUrl)
                .put("followers_url", followersUrl)
                .put("subscriptions_url", subscriptionsUrl)
                .put("received_events_url", receivedEventsUrl)
                .put("organizations_url", organizationsUrl);
    }

    /**
     * Fetches a GitHub user by their username.
     *
     * @param github the GitHub instance
     * @param name   the username of the user to fetch
     * @return a GHUser instance representing the fetched user
     */
    public static GHUser getUser(Github github, String name) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/users/" + name, github.getToken());
        assert response != null;
        JSONObject json = new JSONObject(response);
        return new GHUser(github, json);
    }

    /**
     * Returns the URL for fetching the followers of this user.
     *
     * @return the followers URL
     */
    public String getFollowersUrl() {
        return followersUrl;
    }

    /**
     * Returns the URL for fetching the users followed by this user.
     *
     * @return the following URL
     */
    public String getFollowingUrl() {
        return followingUrl;
    }

    /**
     * Returns the URL for fetching the gists of this user.
     *
     * @return the gists URL
     */
    public String getGistsUrl() {
        return gistsUrl;
    }

    /**
     * Returns the URL for fetching the events received by this user.
     *
     * @return the received events URL
     */
    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    /**
     * Returns the URL for fetching the repositories starred by this user.
     *
     * @return the starred URL
     */
    public String getStarredUrl() {
        return starredUrl;
    }

    /**
     * Returns the URL for fetching the subscriptions of this user.
     *
     * @return the subscriptions URL
     */
    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    /**
     * Returns the gravatar ID of this user.
     *
     * @return the gravatar ID
     */
    public String getGravatarId() {
        return gravatarId;
    }

    /**
     * Returns the URL for fetching the organizations of this user.
     *
     * @return the organizations URL
     */
    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    /**
     * Returns the number of contributions made by this user.
     *
     * @return the number of contributions
     */
    public int getContributions() {
        return contributions;
    }

    /**
     * Returns whether this user is a site administrator.
     *
     * @return true if the user is a site administrator, false otherwise
     */
    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    /**
     * Finds and returns a repository by its name for the current user.
     *
     * @param name the name of the repository to find
     * @return the GHRepository instance representing the found repository
     */
    public GHRepository findRepositoryByName(String name) {
        return GHRepository.getRepository(github, this.getLogin(), name);
    }

    /**
     * Fetches and returns a list of users who are followers of the current user.
     *
     * @return a list of GHUser instances representing the followers of the current user
     */
    public List<GHUser> getFollowers() {
        return fetchPaginatedData(getUrl(), "/followers", jsonObject -> new GHUser(github, jsonObject), github.getToken());
    }

    /**
     * Fetches and returns a list of users who the current user is following.
     *
     * @return a list of GHUser instances representing the users the current user is following
     */
    public List<GHUser> getFollowing() {
        return fetchPaginatedData(getUrl(), "/following", jsonObject -> new GHUser(github, jsonObject), github.getToken());
    }

    /**
     * Fetches and returns a list of public keys associated with the current user.
     *
     * @return a list of GHPublicKey instances representing the public keys of the current user
     */
    public List<GHPublicKey> getPublicKeys() {
        return fetchPaginatedData(getUrl(), "/keys", GHPublicKey::new, github.getToken());
    }

    /**
     * Fetches and returns a list of repositories owned by the current user.
     *
     * @return a list of GHRepository instances representing the repositories owned by the current user
     */
    public List<GHRepository> listRepositories() {
        return fetchPaginatedData(getUrl(), "/repos", jsonObject -> new GHRepository(jsonObject, github), github.getToken());
    }
}
