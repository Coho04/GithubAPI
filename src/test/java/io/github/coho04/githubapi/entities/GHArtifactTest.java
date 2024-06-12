package io.github.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHArtifactTest {

    private JSONObject jsonObject;
    private GHArtifact artifact;
    private OffsetDateTime now;

    @BeforeEach
    void setUp() {
        now = OffsetDateTime.now();
        jsonObject = new JSONObject();
        jsonObject.put("name", "test-artifact");
        jsonObject.put("size_in_bytes", 12345);
        jsonObject.put("archive_download_url", "http://example.com/archive.zip");
        jsonObject.put("expired", true);
        jsonObject.put("created_at", now.toString());
        jsonObject.put("expires_at", now.plusDays(1).toString());
        jsonObject.put("updated_at", now.minusDays(1).toString());
        jsonObject.put("workflow_run", new JSONObject().put("id", 1));

        artifact = new GHArtifact(jsonObject);
    }

    @Test
    void testGetName() {
        assertEquals("test-artifact", artifact.getName());
    }

    @Test
    void testGetUpdatedAt() {
        assertEquals(now.minusDays(1), artifact.getUpdatedAt());
    }

    @Test
    void testGetCreatedAt() {
        assertEquals(now, artifact.getCreatedAt());
    }

    @Test
    void testGetSizeInBytes() {
        assertEquals(12345, artifact.getSizeInBytes());
    }

    @Test
    void testGetWorkflowRun() {
        assertNotNull(artifact.getWorkflowRun());
        assertEquals(1, artifact.getWorkflowRun().getId());
    }

    @Test
    void testGetExpiresAt() {
        assertEquals(now.plusDays(1), artifact.getExpiresAt());
    }

    @Test
    void testGetArchiveDownloadUrl() {
        assertEquals("http://example.com/archive.zip", artifact.getArchiveDownloadUrl());
    }

    @Test
    void testIsExpired() {
        assertTrue(artifact.isExpired());
    }
}
