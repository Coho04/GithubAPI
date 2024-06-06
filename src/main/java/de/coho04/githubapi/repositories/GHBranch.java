package de.coho04.githubapi.repositories;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub branch.
 * It provides methods for fetching data about the branch such as its name, commit SHA, commit URL, and protection status.
 */
@SuppressWarnings("unused")
public class GHBranch extends GHBase {

    private final boolean isProtected;
    private final String name;
    private final String commitSha;
    private final String commitUrl;

    /**
     * Constructs a new GHBranch instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the branch data
     */
    public GHBranch(JSONObject jsonObject) {
        this.isProtected = getBooleanOrNull(jsonObject, "protected");
        this.name = getStringOrNull(jsonObject, "name");
        this.commitSha = getStringOrNull(jsonObject.getJSONObject("commit"), "sha");
        this.commitUrl = getStringOrNull(jsonObject.getJSONObject("commit"), "url");
    }

    /**
     * Converts this GHBranch instance to a JSONObject.
     *
     * @return the JSONObject representation of this GHBranch instance
     */
    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("protected", isProtected)
                .put("name", name)
                .put("commit", new JSONObject()
                        .put("sha", commitSha)
                        .put("url", commitUrl));
    }

    /**
     * Returns the branch name.
     *
     * @return the branch name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the commit SHA.
     *
     * @return the commit SHA
     */
    public String getCommitSha() {
        return commitSha;
    }

    /**
     * Returns the commit URL.
     *
     * @return the commit URL
     */
    public String getCommitUrl() {
        return commitUrl;
    }

    /**
     * Returns the branch protection status.
     *
     * @return true if the branch is protected, false otherwise
     */
    public boolean isProtected() {
        return isProtected;
    }
}