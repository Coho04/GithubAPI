package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

@SuppressWarnings("unused")
public class GHActionsCache extends GHBase {

    public int id;
    public String ref;
    public String key;
    public String version;
    public OffsetDateTime lastAccessedAt;
    public OffsetDateTime createdAt;
    public int sizeInBytes;


    public GHActionsCache(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.ref = getStringOrNull(jsonObject, "ref");
        this.key = getStringOrNull(jsonObject, "key");
        this.version = getStringOrNull(jsonObject, "version");
        this.lastAccessedAt = getLocalDateOrNull(jsonObject, "last_accessed_at");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.sizeInBytes = getIntOrNull(jsonObject, "size_in_bytes");
    }

    public int getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public OffsetDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public String getKey() {
        return key;
    }

    public String getRef() {
        return ref;
    }

    public String getVersion() {
        return version;
    }
}
