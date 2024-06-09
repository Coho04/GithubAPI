package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a GitHub Security Advisory.
 * This class provides methods and properties to access information about a security advisory on GitHub.
 */
public class GHAdvisory extends GHBase {

    private final int id;
    private final String ghsaId;
    private final String cveId;
    private final String url;
    private final String htmlUrl;
    private final String repositoryAdvisoryUrl;
    private final String summary;
    private final String description;
    private final String type;
    private final String severity;
    private final String sourceCodeLocation;
    private final List<JSONObject> identifiers;
    private final List<String> references;
    private final OffsetDateTime publishedAt;
    private final OffsetDateTime updatedAt;
    private final OffsetDateTime githubReviewedAt;
    private final OffsetDateTime nvdPublishedAt;
    private final OffsetDateTime withdrawnAt;
    private final List<GHVulnerability> vulnerabilities;
    private final JSONObject cvss;
    private final JSONArray cwes;
    private final List<GHUser> credits;

    /**
     * Constructs a new GHAdvisory instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the advisory data
     */
    public GHAdvisory(Github github, JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.ghsaId = getStringOrNull(jsonObject, "ghsa_id");
        this.cveId = getStringOrNull(jsonObject, "cve_id");
        this.url = getStringOrNull(jsonObject, "url");
        this.htmlUrl = getStringOrNull(jsonObject, "html_url");
        this.repositoryAdvisoryUrl = getStringOrNull(jsonObject, "repository_advisory_url");
        this.summary = getStringOrNull(jsonObject, "summary");
        this.description = getStringOrNull(jsonObject, "description");
        this.type = getStringOrNull(jsonObject, "type");
        this.severity = getStringOrNull(jsonObject, "severity");
        this.sourceCodeLocation = getStringOrNull(jsonObject, "source_code_location");
        this.identifiers = getArrayOrNull(jsonObject, "identifiers", JSONObject::new).stream().toList();
        this.references = getJSONArrayToStringList(jsonObject, "references");
        this.publishedAt = getLocalDateOrNull(jsonObject, "published_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.githubReviewedAt = getLocalDateOrNull(jsonObject, "github_reviewed_at");
        this.nvdPublishedAt = getLocalDateOrNull(jsonObject, "nvd_published_at");
        this.withdrawnAt = getLocalDateOrNull(jsonObject, "withdrawn_at");
        this.vulnerabilities = getArrayOrNull(jsonObject, "vulnerabilities", json -> new GHVulnerability(github, json));
        this.cvss = jsonObject.getJSONObject("cvss");
        this.cwes = jsonObject.getJSONArray("cwes");
        this.credits = getArrayOrNull(jsonObject, "credits", json -> new GHUser(github, json));
    }

    /**
     * Returns the description of the advisory.
     *
     * @return the description of the advisory
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the URL of the advisory.
     *
     * @return the URL of the advisory
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the type of the advisory.
     *
     * @return the type of the advisory
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the CWEs (Common Weakness Enumerations) of the advisory.
     *
     * @return the CWEs of the advisory
     */
    public JSONArray getCwes() {
        return cwes;
    }

    /**
     * Returns the ID of the advisory.
     *
     * @return the ID of the advisory
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the CVSS (Common Vulnerability Scoring System) data of the advisory.
     *
     * @return the CVSS data of the advisory
     */
    public JSONObject getCvss() {
        return cvss;
    }

    /**
     * Returns the identifiers of the advisory.
     *
     * @return the identifiers of the advisory
     */
    public List<JSONObject> getIdentifiers() {
        return identifiers;
    }

    /**
     * Returns the publication date and time of the advisory.
     *
     * @return the publication date and time of the advisory
     */
    public OffsetDateTime getPublishedAt() {
        return publishedAt;
    }

    /**
     * Returns the vulnerabilities associated with the advisory.
     *
     * @return a list of GHVulnerability instances
     */
    public List<GHVulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    /**
     * Returns the credits for the advisory.
     *
     * @return a list of GHUser instances representing the credits
     */
    public List<GHUser> getCredits() {
        return credits;
    }

    /**
     * Returns the GitHub review date and time of the advisory.
     *
     * @return the GitHub review date and time of the advisory
     */
    public OffsetDateTime getGithubReviewedAt() {
        return githubReviewedAt;
    }

    /**
     * Returns the NVD (National Vulnerability Database) publication date and time of the advisory.
     *
     * @return the NVD publication date and time of the advisory
     */
    public OffsetDateTime getNvdPublishedAt() {
        return nvdPublishedAt;
    }

    /**
     * Returns the last updated date and time of the advisory.
     *
     * @return the last updated date and time of the advisory
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the withdrawal date and time of the advisory.
     *
     * @return the withdrawal date and time of the advisory
     */
    public OffsetDateTime getWithdrawnAt() {
        return withdrawnAt;
    }

    /**
     * Returns the CVE ID of the advisory.
     *
     * @return the CVE ID of the advisory
     */
    public String getCveId() {
        return cveId;
    }

    /**
     * Returns the GHSA ID of the advisory.
     *
     * @return the GHSA ID of the advisory
     */
    public String getGhsaId() {
        return ghsaId;
    }

    /**
     * Returns the HTML URL of the advisory.
     *
     * @return the HTML URL of the advisory
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Returns the repository advisory URL.
     *
     * @return the repository advisory URL
     */
    public String getRepositoryAdvisoryUrl() {
        return repositoryAdvisoryUrl;
    }

    /**
     * Returns the severity of the advisory.
     *
     * @return the severity of the advisory
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Returns the source code location of the advisory.
     *
     * @return the source code location of the advisory
     */
    public String getSourceCodeLocation() {
        return sourceCodeLocation;
    }

    /**
     * Returns the summary of the advisory.
     *
     * @return the summary of the advisory
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Returns the references of the advisory.
     *
     * @return the references of the advisory
     */
    public List<String> getReferences() {
        return references;
    }
}
