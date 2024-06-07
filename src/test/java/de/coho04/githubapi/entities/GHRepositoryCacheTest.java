package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHRepositoryCacheTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("full_name", "Repo1");
        jsonObject.put("active_caches_size_in_bytes", 100);
        jsonObject.put("active_caches_count", 10);
    }

    @Test
    void shouldReturnCorrectFullName() {
        assertEquals("Repo1", new GHRepositoryCache(jsonObject).getFullName());
    }

    @Test
    void shouldReturnNullWhenFullNameIsNotPresent() {
        jsonObject.remove("full_name");
        assertNull(new GHRepositoryCache(jsonObject).getFullName());
    }

    @Test
    void shouldReturnCorrectActiveCachesSizeInBytes() {
        assertEquals(100, new GHRepositoryCache(jsonObject).getActiveCachesSizeInBytes());
    }

    @Test
    void shouldReturnZeroWhenActiveCachesSizeInBytesIsNotPresent() {
        jsonObject.remove("active_caches_size_in_bytes");
        assertEquals(0, new GHRepositoryCache(jsonObject).getActiveCachesSizeInBytes());
    }

    @Test
    void shouldReturnCorrectActiveCachesCount() {
        assertEquals(10, new GHRepositoryCache(jsonObject).getActiveCachesCount());
    }

    @Test
    void shouldReturnZeroWhenActiveCachesCountIsNotPresent() {
        jsonObject.remove("active_caches_count");
        assertEquals(0, new GHRepositoryCache(jsonObject).getActiveCachesCount());
    }
}