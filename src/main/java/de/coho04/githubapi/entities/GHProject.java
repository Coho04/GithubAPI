package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.ClassBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * GHProject is a class that extends ClassBase. It represents a GitHub project.
 * It contains several properties such as ownerUrl, columnsUrl, name, body, number, state, creator, createdAt, updatedAt, organizationPermission, and isPrivate.
 * These properties are initialized through a JSONObject.
 *
 * @author Coho04
 * @version 1.0
 * @since 2024-1.2
 */
@SuppressWarnings("unused")
public class GHProject extends ClassBase {

    public String ownerUrl;
    public String columnsUrl;
    public String name;
    public String body;
    public int number;
    public String state;
    public GHUser creator;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
    public String organizationPermission;
    public boolean isPrivate;

    /**
     * Constructs a new GHProject object.
     *
     * @param jsonObject a JSONObject containing the data for initializing the GHProject object.
     */
    public GHProject(JSONObject jsonObject) {
        super(jsonObject);
        this.ownerUrl = getStringOrNull(jsonObject, "owner_url");
        this.columnsUrl = getStringOrNull(jsonObject, "columns_url");
        this.name = getStringOrNull(jsonObject, "name");
        this.body = getStringOrNull(jsonObject, "body");
        this.number = getIntOrNull(jsonObject, "number");
        this.state = getStringOrNull(jsonObject, "state");
        this.creator = new GHUser(jsonObject.getJSONObject("creator"));
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.organizationPermission = getStringOrNull(jsonObject, "organization_permission");
        this.isPrivate = getBooleanOrNull(jsonObject, "private");
    }

    /**
     * Returns the name of this GHProject object.
     *
     * @return the name of this GHProject object.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the creator of this GHProject object.
     *
     * @return the creator of this GHProject object.
     */
    public GHUser getCreator() {
        return creator;
    }

    /**
     * Returns the number of this GHProject object.
     *
     * @return the number of this GHProject object.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the body of this GHProject object.
     *
     * @return the body of this GHProject object.
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the columnsUrl of this GHProject object.
     *
     * @return the columnsUrl of this GHProject object.
     */
    public String getColumnsUrl() {
        return columnsUrl;
    }

    /**
     * Returns the createdAt time of this GHProject object.
     *
     * @return the createdAt time of this GHProject object.
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the organizationPermission of this GHProject object.
     *
     * @return the organizationPermission of this GHProject object.
     */
    public String getOrganizationPermission() {
        return organizationPermission;
    }

    /**
     * Returns the ownerUrl of this GHProject object.
     *
     * @return the ownerUrl of this GHProject object.
     */
    public String getOwnerUrl() {
        return ownerUrl;
    }

    /**
     * Returns the state of this GHProject object.
     *
     * @return the state of this GHProject object.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the updatedAt time of this GHProject object.
     *
     * @return the updatedAt time of this GHProject object.
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}