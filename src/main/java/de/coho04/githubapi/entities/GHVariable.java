package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

@SuppressWarnings("unused")
public class GHVariable extends GHBase {

    public String name;
    public String value;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
    public String visibility;
    public String selectedRepositoriesUrl;

    public GHVariable(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.value = getStringOrNull(jsonObject, "value");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.visibility = getStringOrNull(jsonObject, "visibility");
        this.selectedRepositoriesUrl = getStringOrNull(jsonObject, "selected_repositories_url");
    }

    public String getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public String getSelectedRepositoriesUrl() {
        return selectedRepositoriesUrl;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getValue() {
        return value;
    }
}