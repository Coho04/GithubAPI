package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

@SuppressWarnings("unused")
public class GHSecret extends GHBase {

    public String name;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
    public String visibility;
    public String selectedRepositoriesUrl;


    public GHSecret(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.visibility = getStringOrNull(jsonObject, "visibility");
        this.selectedRepositoriesUrl = getStringOrNull(jsonObject, "selected_repositories_url");
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getSelectedRepositoriesUrl() {
        return selectedRepositoriesUrl;
    }
}
