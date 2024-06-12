package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.bases.ClassBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * This class represents a GitHub Artifact.
 * It provides methods for fetching data about the artifact such as its name, size in bytes, url, archive download url, expired status, creation date, expiry date, update date, and associated workflow run.
 */
public class GHArtifact extends ClassBase {

    private final String name;
    private final int sizeInBytes;
    private final String archiveDownloadUrl;
    private final boolean expired;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime expiresAt;
    private final OffsetDateTime updatedAt;
    private final GHWorkflowRun workflowRun;

    /**
     * Constructs a new GHArtifact instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the artifact data
     */
    public GHArtifact(JSONObject jsonObject) {
        super(jsonObject);
        this.name = getStringOrNull(jsonObject, "name");
        this.sizeInBytes = getIntOrNull(jsonObject, "size_in_bytes");
        this.archiveDownloadUrl = getStringOrNull(jsonObject, "archive_download_url");
        this.expired = getBooleanOrNull(jsonObject, "expired");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.expiresAt = getLocalDateOrNull(jsonObject, "expires_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.workflowRun = new GHWorkflowRun(getJSONObjectOrNull(jsonObject, "workflow_run"));
    }

    /**
     * Returns the name of the artifact.
     *
     * @return the name of the artifact
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the update date of the artifact.
     *
     * @return the update date of the artifact
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the creation date of the artifact.
     *
     * @return the creation date of the artifact
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the size in bytes of the artifact.
     *
     * @return the size in bytes of the artifact
     */
    public int getSizeInBytes() {
        return sizeInBytes;
    }

    /**
     * Returns the workflow run of the artifact.
     *
     * @return the workflow run of the artifact
     */
    public GHWorkflowRun getWorkflowRun() {
        return workflowRun;
    }

    /**
     * Returns the expiry date of the artifact.
     *
     * @return the expiry date of the artifact
     */
    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * Returns the archive download url of the artifact.
     *
     * @return the archive download url of the artifact
     */
    public String getArchiveDownloadUrl() {
        return archiveDownloadUrl;
    }

    /**
     * Returns the expired status of the artifact.
     *
     * @return the expired status of the artifact
     */
    public boolean isExpired() {
        return expired;
    }
}