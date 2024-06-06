package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * This class represents a GitHub Actions Cache.
 * It provides methods for fetching data about the actions cache such as its id, ref, key, version, last accessed date, creation date, and size in bytes.
 */
@SuppressWarnings("unused")
public class GHActionsCache extends GHBase {

    public int id;
    public String ref;
    public String key;
    public String version;
    public OffsetDateTime lastAccessedAt;
    public OffsetDateTime createdAt;
    public int sizeInBytes;

    /**
     * Constructs a new GHActionsCache instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the actions cache data
     */
    public GHActionsCache(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.ref = getStringOrNull(jsonObject, "ref");
        this.key = getStringOrNull(jsonObject, "key");
        this.version = getStringOrNull(jsonObject, "version");
        this.lastAccessedAt = getLocalDateOrNull(jsonObject, "last_accessed_at");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.sizeInBytes = getIntOrNull(jsonObject, "size_in_bytes");
    }

    /**
     * Returns the id of the actions cache.
     *
     * @return the id of the actions cache
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the creation date of the actions cache.
     *
     * @return the creation date of the actions cache
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the size in bytes of the actions cache.
     *
     * @return the size in bytes of the actions cache
     */
    public int getSizeInBytes() {
        return sizeInBytes;
    }

    /**
     * Returns the last accessed date of the actions cache.
     *
     * @return the last accessed date of the actions cache
     */
    public OffsetDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    /**
     * Returns the key of the actions cache.
     *
     * @return the key of the actions cache
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the ref of the actions cache.
     *
     * @return the ref of the actions cache
     */
    public String getRef() {
        return ref;
    }

    /**
     * Returns the version of the actions cache.
     *
     * @return the version of the actions cache
     */
    public String getVersion() {
        return version;
    }
}