package io.github.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHActionsCacheTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("ref", "ref1");
        jsonObject.put("key", "key1");
        jsonObject.put("version", "v1");
        jsonObject.put("last_accessed_at", "2022-01-01T00:00:00Z");
        jsonObject.put("created_at", "2022-01-01T00:00:00Z");
        jsonObject.put("size_in_bytes", 100);
    }

    @Test
    void shouldReturnCorrectId() {
        assertEquals(1, new GHActionsCache(jsonObject).getId());
    }

    @Test
    void shouldReturnZeroWhenIdIsNotPresent() {
        jsonObject.remove("id");
        assertEquals(0, new GHActionsCache(jsonObject).getId());
    }

    @Test
    void shouldReturnCorrectRef() {
        assertEquals("ref1", new GHActionsCache(jsonObject).getRef());
    }

    @Test
    void shouldReturnNullWhenRefIsNotPresent() {
        jsonObject.remove("ref");
        assertNull(new GHActionsCache(jsonObject).getRef());
    }

    @Test
    void shouldReturnCorrectKey() {
        assertEquals("key1", new GHActionsCache(jsonObject).getKey());
    }

    @Test
    void shouldReturnNullWhenKeyIsNotPresent() {
        jsonObject.remove("key");
        assertNull(new GHActionsCache(jsonObject).getKey());
    }

    @Test
    void shouldReturnCorrectVersion() {
        assertEquals("v1", new GHActionsCache(jsonObject).getVersion());
    }

    @Test
    void shouldReturnNullWhenVersionIsNotPresent() {
        jsonObject.remove("version");
        assertNull(new GHActionsCache(jsonObject).getVersion());
    }

    @Test
    void shouldReturnCorrectLastAccessedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHActionsCache(jsonObject).getLastAccessedAt());
    }

    @Test
    void shouldReturnNullWhenLastAccessedAtIsNotPresent() {
        jsonObject.remove("last_accessed_at");
        assertNull(new GHActionsCache(jsonObject).getLastAccessedAt());
    }

    @Test
    void shouldReturnCorrectCreatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHActionsCache(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenCreatedAtIsNotPresent() {
        jsonObject.remove("created_at");
        assertNull(new GHActionsCache(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnCorrectSizeInBytes() {
        assertEquals(100, new GHActionsCache(jsonObject).getSizeInBytes());
    }

    @Test
    void shouldReturnZeroWhenSizeInBytesIsNotPresent() {
        jsonObject.remove("size_in_bytes");
        assertEquals(0, new GHActionsCache(jsonObject).getSizeInBytes());
    }
}