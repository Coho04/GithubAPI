package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class GHAdvisory extends GHBase {

    public int id;
    public String ghsaId;
    public String cveId;
    public String url;
    public String htmlUrl;
    public String repositoryAdvisoryUrl;
    public String summary;
    public String description;
    public String type;
    public String severity;
    public String sourceCodeLocation;
    public JSONObject[] identifiers;
    public String[] references;
    public OffsetDateTime publishedAt;
    public OffsetDateTime updatedAt;
    public OffsetDateTime githubReviewedAt;
    public OffsetDateTime nvdPublishedAt;
    public OffsetDateTime withdrawnAt;
    public List<GHVulnerability> vulnerabilities;
    public JSONObject cvss;
    public JSONArray cwes;
    public List<GHUser> credits;

    public GHAdvisory(JSONObject jsonObject) {
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
        this.identifiers = getArrayOrNull(jsonObject, "identifiers", JSONObject::new).toArray(new JSONObject[0]);
        this.references = getArrayOrNull(jsonObject, "references", JSONObject::toString).toArray(new String[0]);
        this.publishedAt = getLocalDateOrNull(jsonObject, "published_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.githubReviewedAt = getLocalDateOrNull(jsonObject, "github_reviewed_at");
        this.nvdPublishedAt = getLocalDateOrNull(jsonObject, "nvd_published_at");
        this.withdrawnAt = getLocalDateOrNull(jsonObject, "withdrawn_at");
        this.vulnerabilities = getArrayOrNull(jsonObject, "vulnerabilities", GHVulnerability::new);
        this.cvss = jsonObject.getJSONObject("cvss");
        this.cwes = jsonObject.getJSONArray("cwes");
        this.credits = getArrayOrNull(jsonObject, "credits", GHUser::new);
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public JSONArray getCwes() {
        return cwes;
    }

    public int getId() {
        return id;
    }

    public JSONObject getCvss() {
        return cvss;
    }

    public JSONObject[] getIdentifiers() {
        return identifiers;
    }

    public OffsetDateTime getPublishedAt() {
        return publishedAt;
    }

    public List<GHVulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    public List<GHUser> getCredits() {
        return credits;
    }

    public OffsetDateTime getGithubReviewedAt() {
        return githubReviewedAt;
    }

    public OffsetDateTime getNvdPublishedAt() {
        return nvdPublishedAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OffsetDateTime getWithdrawnAt() {
        return withdrawnAt;
    }

    public String getCveId() {
        return cveId;
    }

    public String getGhsaId() {
        return ghsaId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getRepositoryAdvisoryUrl() {
        return repositoryAdvisoryUrl;
    }

    public String getSeverity() {
        return severity;
    }

    public String getSourceCodeLocation() {
        return sourceCodeLocation;
    }

    public String getSummary() {
        return summary;
    }

    public String[] getReferences() {
        return references;
    }
}
