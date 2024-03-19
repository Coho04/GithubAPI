package de.goldendeveloper.githubapi.bases;

import org.json.JSONObject;

@SuppressWarnings("unused")
public class EntityBase extends ClassBase {

    private final String type;
    private final String login;
    private final String reposUrl;
    private final String avatarUrl;

    public EntityBase(JSONObject jsonObject) {
        super(jsonObject);
        this.type = getStringOrNull(jsonObject, "type");
        this.login = getStringOrNull(jsonObject, "login");
        this.reposUrl = getStringOrNull(jsonObject, "repos_url");
        this.avatarUrl = getStringOrNull(jsonObject, "avatar_url");
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("type", type)
                .put("login", login)
                .put("repos_url", reposUrl)
                .put("avatar_url", avatarUrl);
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getType() {
        return type;
    }
}
