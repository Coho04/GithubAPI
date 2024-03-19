package de.goldendeveloper.githubapi.repositories;

import de.goldendeveloper.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHBranch extends GHBase {

    private final boolean isProtected;
    private final String name;
    private final String commitSha;
    private final String commitUrl;

    public GHBranch(JSONObject jsonObject) {
        this.isProtected = getBooleanOrNull(jsonObject, "protected");
        this.name = getStringOrNull(jsonObject, "name");
        this.commitSha = getStringOrNull(jsonObject.getJSONObject("commit"), "sha");
        this.commitUrl = getStringOrNull(jsonObject.getJSONObject("commit"), "url");
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("protected", isProtected)
                .put("name", name)
                .put("commit", new JSONObject()
                        .put("sha", commitSha)
                        .put("url", commitUrl));
    }

    public String getName() {
        return name;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public String getCommitUrl() {
        return commitUrl;
    }

    public boolean isProtected() {
        return isProtected;
    }
}
