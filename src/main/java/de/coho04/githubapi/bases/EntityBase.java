package de.coho04.githubapi.bases;

import org.json.JSONObject;

/**
 * EntityBase is a class that extends ClassBase. It represents a base entity for GitHub objects.
 * It contains several properties such as type, login, reposUrl, and avatarUrl.
 * These properties are initialized through a JSONObject.
 *
 * @author Coho04
 * @version 1.0
 * @since 2024-1.2
 */
@SuppressWarnings("unused")
public class EntityBase extends ClassBase {

    private final String type;
    private final String login;
    private final String reposUrl;
    private final String avatarUrl;

    /**
     * Constructs a new EntityBase object.
     *
     * @param jsonObject a JSONObject containing the data for initializing the EntityBase object.
     */
    public EntityBase(JSONObject jsonObject) {
        super(jsonObject);
        this.type = getStringOrNull(jsonObject, "type");
        this.login = getStringOrNull(jsonObject, "login");
        this.reposUrl = getStringOrNull(jsonObject, "repos_url");
        this.avatarUrl = getStringOrNull(jsonObject, "avatar_url");
    }

    /**
     * Converts this EntityBase object to a JSONObject.
     *
     * @return a JSONObject representation of this EntityBase object.
     */
    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("type", type)
                .put("login", login)
                .put("repos_url", reposUrl)
                .put("avatar_url", avatarUrl);
    }

    /**
     * Returns the reposUrl of this EntityBase object.
     *
     * @return the reposUrl of this EntityBase object.
     */
    public String getReposUrl() {
        return reposUrl;
    }

    /**
     * Returns the login of this EntityBase object.
     *
     * @return the login of this EntityBase object.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Returns the avatarUrl of this EntityBase object.
     *
     * @return the avatarUrl of this EntityBase object.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Returns the type of this EntityBase object.
     *
     * @return the type of this EntityBase object.
     */
    public String getType() {
        return type;
    }
}