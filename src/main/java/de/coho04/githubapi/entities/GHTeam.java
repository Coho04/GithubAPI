package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.ClassBase;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * This class represents a GitHub Team.
 * It provides methods for fetching data about the team such as its name, slug, description, privacy, notification setting, permission, members URL, repositories URL, creation date, update date, members count, repos count, and parent team.
 */
@SuppressWarnings("unused")
public class GHTeam extends ClassBase {

    public final String name;
    public final String slug;
    public final String description;
    public final String privacy;
    public final String notificationSetting;
    public final String permission;
    public final String membersUrl;
    public final String repositoriesUrl;
    public final OffsetDateTime createdAt;
    public final OffsetDateTime updatedAt;
    public final int membersCount;
    public final int reposCount;
    public GHTeam parent;

    /**
     * Constructs a new GHTeam instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the team data
     */
    public GHTeam(JSONObject jsonObject) {
        super(jsonObject);
        this.name = getStringOrNull(jsonObject, "name");
        this.slug = getStringOrNull(jsonObject, "slug");
        this.description = getStringOrNull(jsonObject, "description");
        this.privacy = getStringOrNull(jsonObject, "privacy");
        this.notificationSetting = getStringOrNull(jsonObject, "notification_setting");
        this.permission = getStringOrNull(jsonObject, "permission");
        this.membersUrl = getStringOrNull(jsonObject, "members_url");
        this.repositoriesUrl = getStringOrNull(jsonObject, "repositories_url");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.membersCount = getIntOrNull(jsonObject, "members_count");
        this.reposCount = getIntOrNull(jsonObject, "repos_count");
        JSONObject parentJSONObject = getJSONObjectOrNull(jsonObject, "parent");
        if (parentJSONObject != null) {
            this.parent = new GHTeam(parentJSONObject);
        }
    }

    /**
     * Returns a GHTeam instance by team name.
     *
     * @param github the GitHub instance
     * @param name the team name
     * @param organisation the GHOrganisation instance
     * @return the GHTeam instance
     */
    public static GHTeam getTeamByName(Github github, String name, GHOrganisation organisation) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/orgs/" + organisation.getGivenName() + "/teams/" + name.replace(" ", "-"), github.getToken());
        assert response != null;
        return new GHTeam(new JSONObject(response));
    }

    /**
     * Returns the description of the team.
     *
     * @return the description of the team
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the members URL of the team.
     *
     * @return the members URL of the team
     */
    public String getMembersUrl() {
        return membersUrl;
    }

    /**
     * Returns the name of the team.
     *
     * @return the name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the notification setting of the team.
     *
     * @return the notification setting of the team
     */
    public String getNotificationSetting() {
        return notificationSetting;
    }

    /**
     * Returns the parent team of the team.
     *
     * @return the parent team of the team
     */
    public GHTeam getParent() {
        return parent;
    }

    /**
     * Returns the permission of the team.
     *
     * @return the permission of the team
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Returns the privacy of the team.
     *
     * @return the privacy of the team
     */
    public String getPrivacy() {
        return privacy;
    }

    /**
     * Returns the repositories URL of the team.
     *
     * @return the repositories URL of the team
     */
    public String getRepositoriesUrl() {
        return repositoriesUrl;
    }

    /**
     * Returns the slug of the team.
     *
     * @return the slug of the team
     */
    public String getSlug() {
        return slug;
    }
}