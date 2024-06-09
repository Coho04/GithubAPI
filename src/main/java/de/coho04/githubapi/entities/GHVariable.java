package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * Represents a GitHub variable.
 * This class provides methods and properties to access information about a variable in GitHub Actions.
 */
public class GHVariable extends GHBase {

    private final String name;
    private final String value;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final String visibility;
    private final String selectedRepositoriesUrl;

    /**
     * Constructs a new GHVariable instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the variable data
     */
    public GHVariable(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.value = getStringOrNull(jsonObject, "value");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.visibility = getStringOrNull(jsonObject, "visibility");
        this.selectedRepositoriesUrl = getStringOrNull(jsonObject, "selected_repositories_url");
    }

    /**
     * Returns the visibility of the variable.
     *
     * @return the visibility of the variable
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Returns the name of the variable.
     *
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the URL of the selected repositories for this variable.
     *
     * @return the selected repositories URL
     */
    public String getSelectedRepositoriesUrl() {
        return selectedRepositoriesUrl;
    }

    /**
     * Returns the date and time when the variable was last updated.
     *
     * @return the last updated date and time
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the date and time when the variable was created.
     *
     * @return the creation date and time
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the value of the variable.
     *
     * @return the value of the variable
     */
    public String getValue() {
        return value;
    }
}
