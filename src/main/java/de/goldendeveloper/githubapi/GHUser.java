package de.goldendeveloper.githubapi;

import de.goldendeveloper.githubapi.interfaces.JSONHelper;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHUser implements JSONHelper {

    private final int id;
    private final String url;
    private final String type;
    private final String login;
    private final String nodeId;
    private final String htmlUrl;
    private final String gistsUrl;
    private final String reposUrl;
    private final String avatarUrl;
    private final String eventsUrl;
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
        this.id = getIntOrNull(jsonObject, "id");
        this.url = getStringOrNull(jsonObject, "url");
        this.type = getStringOrNull(jsonObject, "type");
        this.login = getStringOrNull(jsonObject, "login");
        this.nodeId = getStringOrNull(jsonObject, "node_id");
        this.htmlUrl = getStringOrNull(jsonObject, "html_url");
        this.contributions = getIntOrNull(jsonObject, "contributions");
        this.gistsUrl = getStringOrNull(jsonObject, "gists_url");
        this.reposUrl = getStringOrNull(jsonObject, "repos_url");
        this.eventsUrl = getStringOrNull(jsonObject, "events_url");
        this.avatarUrl = getStringOrNull(jsonObject, "avatar_url");
        this.siteAdmin = getBooleanOrNull(jsonObject, "site_admin");
        this.gravatarId = getStringOrNull(jsonObject, "gravatar_id");
        this.starredUrl = getStringOrNull(jsonObject, "starred_url");
        this.followersUrl = getStringOrNull(jsonObject, "followers_url");
        this.followingUrl = getStringOrNull(jsonObject, "following_url");
        this.subscriptionsUrl = getStringOrNull(jsonObject, "subscriptions_url");
        this.receivedEventsUrl = getStringOrNull(jsonObject, "received_events_url");
        this.organizationsUrl = getStringOrNull(jsonObject, "organizations_url");
    }

    public String getUrl() {
        return url;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }


    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public int getId() {
        return id;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getType() {
        return type;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }
}
