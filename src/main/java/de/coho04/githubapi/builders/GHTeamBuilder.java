package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.GHTeam;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class GHTeamBuilder extends GHBase {

    private final Github github;
    private String name;
    private String description;
    private List<String> maintainers;
    private List<String> repoNames;
    private String privacy;
    private String notificationSetting;
    private String permission;
    private int parentTeamId;

    private final String url;

    public GHTeamBuilder(Github github, String url) {
        this.github = github;
        this.url = url;
    }

    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("name", name)
                .put("description", description)
                .put("maintainers", new JSONArray(maintainers))
                .put("repo_names", new JSONArray(repoNames))
                .put("privacy", privacy)
                .put("notification_setting", notificationSetting)
                .put("permission", permission)
                .put("parent_team_id", parentTeamId);
    }


    public GHTeamBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GHTeamBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public GHTeamBuilder setMaintainers(List<String> maintainers) {
        this.maintainers = maintainers;
        return this;
    }

    public GHTeamBuilder setNotificationSetting(String notificationSetting) {
        this.notificationSetting = notificationSetting;
        return this;
    }

    public GHTeamBuilder setParentTeamId(int parentTeamId) {
        this.parentTeamId = parentTeamId;
        return this;
    }

    public GHTeamBuilder setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public GHTeamBuilder setPrivacy(String privacy) {
        this.privacy = privacy;
        return this;
    }

    public GHTeamBuilder setRepoNames(List<String> repoNames) {
        this.repoNames = repoNames;
        return this;
    }


    public GHTeam build() {
        String response = sendPostRequest(url, github.getToken(), toJSONObject());
        return new GHTeam(new JSONObject(response));
    }
}
