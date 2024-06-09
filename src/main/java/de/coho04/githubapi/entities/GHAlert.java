package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.repositories.GHRepository;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * This class represents a GitHub Alert.
 * It provides methods for fetching data about the alert such as its number, creation date, url, html url, locations url, state, resolution, resolved at date, resolved by user, secret type, secret type display name, secret, repository, push protection bypassed by user, push protection bypassed status, push protection bypassed at date, resolution comment, and validity.
 */
public class GHAlert extends GHBase {

    private final Github github;
    private final int number;
    private final OffsetDateTime createdAt;
    private final String url;
    private final String htmlUrl;
    private final String locationsUrl;
    private final String state;
    private final String resolution;
    private final OffsetDateTime resolvedAt;
    private GHUser resolvedBy;
    private final String secretType;
    private final String secretTypeDisplayName;
    private final String secret;
    private GHRepository repository;
    private GHUser pushProtectionBypassedBy;
    private final boolean pushProtectionBypassed;
    private final OffsetDateTime pushProtectionBypassedAt;
    private final String resolutionComment;
    private final String validity;

    /**
     * Constructs a new GHAlert instance with the provided JSON object and GitHub instance.
     *
     * @param jsonObject the JSON object containing the alert data
     * @param github     the GitHub instance associated with this alert
     */
    public GHAlert(JSONObject jsonObject, Github github) {
        this.github = github;
        this.number = getIntOrNull(jsonObject, "number");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.url = getStringOrNull(jsonObject, "url");
        this.htmlUrl = getStringOrNull(jsonObject, "html_url");
        this.locationsUrl = getStringOrNull(jsonObject, "locations_url");
        this.state = getStringOrNull(jsonObject, "state");
        this.resolution = getStringOrNull(jsonObject, "resolution");
        this.resolvedAt = getLocalDateOrNull(jsonObject, "resolved_at");

        if (jsonObject.has("resolved_by") && !jsonObject.isNull("resolved_by")) {
            this.resolvedBy = new GHUser(github, jsonObject.getJSONObject("resolved_by"));
        }
        this.secretType = getStringOrNull(jsonObject, "secret_type");
        this.secretTypeDisplayName = getStringOrNull(jsonObject, "secret_type_display_name");
        this.secret = getStringOrNull(jsonObject, "secret");
        if (jsonObject.has("repository") && !jsonObject.isNull("repository")) {
            this.repository = new GHRepository(jsonObject.getJSONObject("repository"), github);
        }
        if (jsonObject.has("push_protection_bypassed_by") && !jsonObject.isNull("push_protection_bypassed_by")) {
            this.pushProtectionBypassedBy = new GHUser(github, jsonObject.getJSONObject("push_protection_bypassed_by"));
        }
        this.pushProtectionBypassed = getBooleanOrNull(jsonObject, "push_protection_bypassed");
        this.pushProtectionBypassedAt = getLocalDateOrNull(jsonObject, "push_protection_bypassed_at");
        this.resolutionComment = getStringOrNull(jsonObject, "resolution_comment");
        this.validity = getStringOrNull(jsonObject, "validity");
    }

    /**
     * Returns the creation date of the alert.
     *
     * @return the creation date of the alert
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the number of the alert.
     *
     * @return the number of the alert
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the state of the alert.
     *
     * @return the state of the alert
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the html url of the alert.
     *
     * @return the html url of the alert
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Returns the url of the alert.
     *
     * @return the url of the alert
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the repository of the alert.
     *
     * @return the repository of the alert
     */
    public GHRepository getRepository() {
        return repository;
    }

    /**
     * Returns the user who bypassed the push protection of the alert.
     *
     * @return the user who bypassed the push protection of the alert
     */
    public GHUser getPushProtectionBypassedBy() {
        return pushProtectionBypassedBy;
    }

    /**
     * Returns the user who resolved the alert.
     *
     * @return the user who resolved the alert
     */
    public GHUser getResolvedBy() {
        return resolvedBy;
    }

    /**
     * Returns the GitHub instance associated with the alert.
     *
     * @return the GitHub instance associated with the alert
     */
    public Github getGithub() {
        return github;
    }

    /**
     * Returns the date when the push protection was bypassed for the alert.
     *
     * @return the date when the push protection was bypassed for the alert
     */
    public OffsetDateTime getPushProtectionBypassedAt() {
        return pushProtectionBypassedAt;
    }

    /**
     * Returns the date when the alert was resolved.
     *
     * @return the date when the alert was resolved
     */
    public OffsetDateTime getResolvedAt() {
        return resolvedAt;
    }

    /**
     * Returns the locations url of the alert.
     *
     * @return the locations url of the alert
     */
    public String getLocationsUrl() {
        return locationsUrl;
    }

    /**
     * Returns the resolution of the alert.
     *
     * @return the resolution of the alert
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Returns the resolution comment of the alert.
     *
     * @return the resolution comment of the alert
     */
    public String getResolutionComment() {
        return resolutionComment;
    }

    /**
     * Returns the secret of the alert.
     *
     * @return the secret of the alert
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Returns the secret type of the alert.
     *
     * @return the secret type of the alert
     */
    public String getSecretType() {
        return secretType;
    }

    /**
     * Returns the display name of the secret type of the alert.
     *
     * @return the display name of the secret type of the alert
     */
    public String getSecretTypeDisplayName() {
        return secretTypeDisplayName;
    }

    /**
     * Returns the validity of the alert.
     *
     * @return the validity of the alert
     */
    public String getValidity() {
        return validity;
    }

    /**
     * Returns the push protection bypassed status of the alert.
     *
     * @return the push protection bypassed status of the alert
     */
    public boolean isPushProtectionBypassed() {
        return pushProtectionBypassed;
    }
}