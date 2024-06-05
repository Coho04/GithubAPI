package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.ClassBase;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

import java.time.OffsetDateTime;

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

    public static GHTeam getTeamByName(Github github, String name, GHOrganisation organisation) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/orgs/" + organisation.getGivenName() + "/teams/" + name.replace(" ", "-"), github.getToken());
        assert response != null;
        System.out.println(response);
        return new GHTeam(new JSONObject(response));
    }

    public String getDescription() {
        return description;
    }

    public String getMembersUrl() {
        return membersUrl;
    }

    public String getName() {
        return name;
    }

    public String getNotificationSetting() {
        return notificationSetting;
    }

    public GHTeam getParent() {
        return parent;
    }

    public String getPermission() {
        return permission;
    }

    public String getPrivacy() {
        return privacy;
    }

    public String getRepositoriesUrl() {
        return repositoriesUrl;
    }

    public String getSlug() {
        return slug;
    }
}
