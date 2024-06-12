package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.bases.GHBase;
import io.github.coho04.githubapi.entities.GHTeam;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * This class is used to build a new GitHub team.
 * It extends GHBase and provides methods for setting the team's properties, and for building the team.
 */
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

    /**
     * Constructs a new GHTeamBuilder instance with the provided GitHub instance and URL.
     *
     * @param github the GitHub instance
     * @param url the URL
     */
    public GHTeamBuilder(Github github, String url) {
        this.github = github;
        this.url = url;
    }

    /**
     * Converts the GHTeamBuilder instance to a JSONObject.
     *
     * @return the JSONObject
     */
    @Override
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

    /**
     * Sets the name of the team.
     *
     * @param name the name of the team
     * @return the GHTeamBuilder instance
     */
    public GHTeamBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the description of the team.
     *
     * @param description the description of the team
     * @return the GHTeamBuilder instance
     */
    public GHTeamBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the maintainers of the team.
     *
     * @param maintainers the maintainers of the team
     * @return the GHTeamBuilder instance
     */
    public GHTeamBuilder setMaintainers(List<String> maintainers) {
        this.maintainers = List.copyOf(maintainers);
        return this;
    }

    /**
     * Sets the notification setting of the team.
     *
     * @param notificationSetting the notification setting of the team
     * @return the GHTeamBuilder instance
     */
    public GHTeamBuilder setNotificationSetting(String notificationSetting) {
        this.notificationSetting = notificationSetting;
        return this;
    }

    /**
     * Sets the parent team id of the team.
     *
     * @param parentTeamId the parent team id of the team
     * @return the GHTeamBuilder instance
     */
    public GHTeamBuilder setParentTeamId(int parentTeamId) {
        this.parentTeamId = parentTeamId;
        return this;
    }

    /**
     * Sets the permission of the team.
     *
     * @param permission the permission of the team
     * @return the GHTeamBuilder instance
     */
    public GHTeamBuilder setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    /**
     * Sets the privacy of the team.
     *
     * @param privacy the privacy of the team
     * @return the GHTeamBuilder instance
     */
    public GHTeamBuilder setPrivacy(String privacy) {
        this.privacy = privacy;
        return this;
    }

    /**
     * Sets the repo names of the team.
     *
     * @param repoNames the repo names of the team
     * @return the GHTeamBuilder instance
     */
    public GHTeamBuilder setRepoNames(List<String> repoNames) {
        this.repoNames = List.copyOf(repoNames);
        return this;
    }

    /**
     * Builds the team and returns a new GHTeam instance.
     *
     * @return the GHTeam instance
     */
    public GHTeam build() {
        String response = sendPostRequest(url, github.getToken(), toJSONObject());
        return new GHTeam(new JSONObject(response));
    }

    /**
     * Returns the GitHub instance.
     *
     * @return the GitHub instance
     */
    public Github getGithub() {
        return github;
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
     * Returns the description of the team.
     *
     * @return the description of the team
     */
    public String getDescription() {
        return description;
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
     * Returns the maintainers of the team.
     *
     * @return the maintainers of the team
     */
    public List<String> getMaintainers() {
        return maintainers;
    }

    /**
     * Returns the repo names of the team.
     *
     * @return the repo names of the team
     */
    public List<String> getRepoNames() {
        return repoNames;
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
     * Returns the URL.
     *
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the parent team id of the team.
     *
     * @return the parent team id of the team
     */
    public int getParentTeamId() {
        return parentTeamId;
    }

    /**
     * Returns the notification setting of the team.
     *
     * @return the notification setting of the team
     */
    public String getNotificationSetting() {
        return notificationSetting;
    }
}