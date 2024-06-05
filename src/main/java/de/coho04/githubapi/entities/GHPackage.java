package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.ClassBase;
import org.json.JSONObject;


public class GHPackage extends ClassBase {

    public String name;
    public String packageType;
    public GHUser owner;
    public String versionCount;
    public String visibility;
    public String createdAt;
    public String updatedAt;

    public GHPackage(JSONObject jsonObject) {
        super(jsonObject);
        this.name = getStringOrNull(jsonObject, "name");
        this.packageType = getStringOrNull(jsonObject, "package_type");
        this.owner = new GHUser(jsonObject.getJSONObject("owner"));
        this.versionCount = getStringOrNull(jsonObject, "version_count");
        this.visibility = getStringOrNull(jsonObject, "visibility");
        this.createdAt = getStringOrNull(jsonObject, "created_at");
        this.updatedAt = getStringOrNull(jsonObject, "updated_at");
    }

    public String getName() {
        return name;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public GHUser getOwner() {
        return owner;
    }

    public String getPackageType() {
        return packageType;
    }

    public String getVersionCount() {
        return versionCount;
    }

    public String getVisibility() {
        return visibility;
    }
}
