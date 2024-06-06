package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.EntityBase;
import de.coho04.githubapi.repositories.GHRepository;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

/**
 * Represents a GitHub user.
 * This class provides methods and properties to access information about a user on GitHub.
 */
@SuppressWarnings("unused")
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
     * @param jsonObject the JSON object containing the user data
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
        return new GHUser(github,json);
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
     * Converts this user instance to a JSONObject.
     *
     * @return a JSONObject representation of this user
     */
    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("gistsUrl", gistsUrl)
                .put("gravatarId", gravatarId)
                .put("siteAdmin", siteAdmin)
                .put("starredUrl", starredUrl)
                .put("contributions", contributions)
                .put("followingUrl", followingUrl)
                .put("followersUrl", followersUrl)
                .put("subscriptionsUrl", subscriptionsUrl)
                .put("receivedEventsUrl", receivedEventsUrl)
                .put("organizationsUrl", organizationsUrl);
    }

    public GHRepository findRepositoryByName(String name) {
        return GHRepository.getRepository(github, this.getLogin(), name);
    }
}
