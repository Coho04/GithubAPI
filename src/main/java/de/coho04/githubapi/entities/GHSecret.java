package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * Represents a GitHub secret.
 * This class provides methods and properties to access information about a secret in GitHub Actions.
 */
public class GHSecret extends GHBase {

    private final String name;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final String visibility;
    private final String selectedRepositoriesUrl;

    /**
     * Constructs a new GHSecret instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the secret data
     */
    public GHSecret(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.visibility = getStringOrNull(jsonObject, "visibility");
        this.selectedRepositoriesUrl = getStringOrNull(jsonObject, "selected_repositories_url");
    }

    /**
     * Returns the creation date and time of the secret.
     *
     * @return the creation date and time of the secret
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the last updated date and time of the secret.
     *
     * @return the last updated date and time of the secret
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the name of the secret.
     *
     * @return the name of the secret
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the visibility of the secret.
     *
     * @return the visibility of the secret
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Returns the URL of the selected repositories for this secret.
     *
     * @return the selected repositories URL
     */
    public String getSelectedRepositoriesUrl() {
        return selectedRepositoriesUrl;
    }
}
