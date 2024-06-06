package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHRepositoryCache extends GHBase {

    public String fullName;
    public int activeCachesSizeInBytes;
    public int activeCachesCount;

    public GHRepositoryCache(JSONObject jsonObject) {
        this.fullName = getStringOrNull(jsonObject, "full_name");
        this.activeCachesSizeInBytes = getIntOrNull(jsonObject, "active_caches_size_in_bytes");
        this.activeCachesCount = getIntOrNull(jsonObject, "active_caches_count");
    }

    public int getActiveCachesCount() {
        return activeCachesCount;
    }

    public int getActiveCachesSizeInBytes() {
        return activeCachesSizeInBytes;
    }

    public String getFullName() {
        return fullName;
    }
}
