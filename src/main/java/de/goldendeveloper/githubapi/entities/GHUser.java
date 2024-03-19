package de.goldendeveloper.githubapi.entities;

import de.goldendeveloper.githubapi.Github;
import de.goldendeveloper.githubapi.bases.EntityBase;
import de.goldendeveloper.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

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

    public GHUser(JSONObject jsonObject) {
        super(jsonObject);
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

    public static GHUser getUser(Github github, String name) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/users/" + name, github.getToken());
        assert response != null;
        JSONObject json = new JSONObject(response);
        return new GHUser(json);
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public int getContributions() {
        return contributions;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

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
}
