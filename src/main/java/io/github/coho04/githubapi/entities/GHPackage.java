package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.bases.ClassBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub Package.
 * It provides methods for fetching data about the package such as its name, package type, owner, version count, visibility, creation date, and update date.
 */
public class GHPackage extends ClassBase {

    private final String name;
    private final String packageType;
    private GHUser owner;
    private final String versionCount;
    private final String visibility;
    private final String createdAt;
    private final String updatedAt;

    /**
     * Constructs a new GHPackage instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the package data
     */
    public GHPackage(Github github, JSONObject jsonObject) {
        super(jsonObject);
        this.name = getStringOrNull(jsonObject, "name");
        this.packageType = getStringOrNull(jsonObject, "package_type");
        if (jsonObject.has("owner") && !jsonObject.isNull("owner")) {
            this.owner = new GHUser(github, jsonObject.getJSONObject("owner"));
        }
        this.versionCount = getStringOrNull(jsonObject, "version_count");
        this.visibility = getStringOrNull(jsonObject, "visibility");
        this.createdAt = getStringOrNull(jsonObject, "created_at");
        this.updatedAt = getStringOrNull(jsonObject, "updated_at");
    }

    /**
     * Returns the name of the package.
     *
     * @return the name of the package
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the update date of the package.
     *
     * @return the update date of the package
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the creation date of the package.
     *
     * @return the creation date of the package
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the owner of the package.
     *
     * @return the owner of the package
     */
    public GHUser getOwner() {
        return owner;
    }

    /**
     * Returns the package type of the package.
     *
     * @return the package type of the package
     */
    public String getPackageType() {
        return packageType;
    }

    /**
     * Returns the version count of the package.
     *
     * @return the version count of the package
     */
    public String getVersionCount() {
        return versionCount;
    }

    /**
     * Returns the visibility of the package.
     *
     * @return the visibility of the package
     */
    public String getVisibility() {
        return visibility;
    }
}