package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub Repository Cache.
 * It provides methods for fetching data about the repository cache such as its full name, active caches size in bytes, and active caches count.
 */
public class GHRepositoryCache extends GHBase {

    private final String fullName;
    private final int activeCachesSizeInBytes;
    private final int activeCachesCount;

    /**
     * Constructs a new GHRepositoryCache instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the repository cache data
     */
    public GHRepositoryCache(JSONObject jsonObject) {
        this.fullName = getStringOrNull(jsonObject, "full_name");
        this.activeCachesSizeInBytes = getIntOrNull(jsonObject, "active_caches_size_in_bytes");
        this.activeCachesCount = getIntOrNull(jsonObject, "active_caches_count");
    }

    /**
     * Returns the active caches count of the repository cache.
     *
     * @return the active caches count of the repository cache
     */
    public int getActiveCachesCount() {
        return activeCachesCount;
    }

    /**
     * Returns the active caches size in bytes of the repository cache.
     *
     * @return the active caches size in bytes of the repository cache
     */
    public int getActiveCachesSizeInBytes() {
        return activeCachesSizeInBytes;
    }

    /**
     * Returns the full name of the repository cache.
     *
     * @return the full name of the repository cache
     */
    public String getFullName() {
        return fullName;
    }
}