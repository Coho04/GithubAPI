package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.ClassBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

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

    public String getName() {
        return name;
    }

    public GHUser getCreator() {
        return creator;
    }

    public int getNumber() {
        return number;
    }

    public String getBody() {
        return body;
    }

    public String getColumnsUrl() {
        return columnsUrl;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getOrganizationPermission() {
        return organizationPermission;
    }

    public String getOwnerUrl() {
        return ownerUrl;
    }

    public String getState() {
        return state;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
