package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.TestBase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHAdvisoryTest extends TestBase {

    private JSONObject jsonObject;
    private Github github;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("ghsa_id", "GHSA-1234");
        jsonObject.put("cve_id", "CVE-2022-1234");
        jsonObject.put("url", "https://github.com/advisories/GHSA-1234");
        jsonObject.put("html_url", "https://github.com/advisories/GHSA-1234.html");
        jsonObject.put("repository_advisory_url", "https://github.com/advisories/GHSA-1234/repo");
        jsonObject.put("summary", "Test summary");
        jsonObject.put("description", "Test description");
        jsonObject.put("type", "Test type");
        jsonObject.put("severity", "Test severity");
        jsonObject.put("source_code_location", "Test location");
        jsonObject.put("published_at", "2022-01-01T00:00:00Z");
        jsonObject.put("updated_at", "2022-01-01T00:00:00Z");
        jsonObject.put("github_reviewed_at", "2022-01-01T00:00:00Z");
        jsonObject.put("nvd_published_at", "2022-01-01T00:00:00Z");
        jsonObject.put("withdrawn_at", "2022-01-01T00:00:00Z");
        jsonObject.put("cvss", new JSONObject());
        jsonObject.put("cwes", new JSONArray());
        jsonObject.put("references", new JSONArray().put("https://example.com/references1").put("https://example.com/references2"));
        jsonObject.put("identifiers", new JSONArray().put(new JSONObject()));
        jsonObject.put("credits", new JSONArray().put(setupUser().toJSONObject()).put(setupUser().toJSONObject()));
        jsonObject.put("vulnerabilities", new JSONArray().put(setupVulnerabilities().toJSONObject()).put(setupVulnerabilities().toJSONObject()));
        github = Mockito.mock(Github.class);
    }

    @Test
    void shouldReturnCorrectId() {
        assertEquals(1, new GHAdvisory(github, jsonObject).getId());
    }

    @Test
    void shouldReturnZeroWhenIdIsNotPresent() {
        jsonObject.remove("id");
        assertEquals(0, new GHAdvisory(github, jsonObject).getId());
    }

    @Test
    void shouldReturnCorrectGhsaId() {
        assertEquals("GHSA-1234", new GHAdvisory(github, jsonObject).getGhsaId());
    }

    @Test
    void shouldReturnNullWhenGhsaIdIsNotPresent() {
        jsonObject.remove("ghsa_id");
        assertNull(new GHAdvisory(github, jsonObject).getGhsaId());
    }

    @Test
    void shouldReturnCorrectCveId() {
        assertEquals("CVE-2022-1234", new GHAdvisory(github, jsonObject).getCveId());
    }

    @Test
    void shouldReturnNullWhenCveIdIsNotPresent() {
        jsonObject.remove("cve_id");
        assertNull(new GHAdvisory(github, jsonObject).getCveId());
    }

    @Test
    void shouldReturnCorrectUrl() {
        assertEquals("https://github.com/advisories/GHSA-1234", new GHAdvisory(github, jsonObject).getUrl());
    }

    @Test
    void shouldReturnNullWhenUrlIsNotPresent() {
        jsonObject.remove("url");
        assertNull(new GHAdvisory(github, jsonObject).getUrl());
    }

    @Test
    void shouldReturnCorrectHtmlUrl() {
        assertEquals("https://github.com/advisories/GHSA-1234.html", new GHAdvisory(github, jsonObject).getHtmlUrl());
    }

    @Test
    void shouldReturnNullWhenHtmlUrlIsNotPresent() {
        jsonObject.remove("html_url");
        assertNull(new GHAdvisory(github, jsonObject).getHtmlUrl());
    }

    @Test
    void shouldReturnCorrectRepositoryAdvisoryUrl() {
        assertEquals("https://github.com/advisories/GHSA-1234/repo", new GHAdvisory(github, jsonObject).getRepositoryAdvisoryUrl());
    }

    @Test
    void shouldReturnNullWhenRepositoryAdvisoryUrlIsNotPresent() {
        jsonObject.remove("repository_advisory_url");
        assertNull(new GHAdvisory(github, jsonObject).getRepositoryAdvisoryUrl());
    }

    @Test
    void shouldReturnCorrectSummary() {
        assertEquals("Test summary", new GHAdvisory(github, jsonObject).getSummary());
    }

    @Test
    void shouldReturnNullWhenSummaryIsNotPresent() {
        jsonObject.remove("summary");
        assertNull(new GHAdvisory(github, jsonObject).getSummary());
    }

    @Test
    void shouldReturnCorrectDescription() {
        assertEquals("Test description", new GHAdvisory(github, jsonObject).getDescription());
    }

    @Test
    void shouldReturnNullWhenDescriptionIsNotPresent() {
        jsonObject.remove("description");
        assertNull(new GHAdvisory(github, jsonObject).getDescription());
    }

    @Test
    void shouldReturnCorrectType() {
        assertEquals("Test type", new GHAdvisory(github, jsonObject).getType());
    }

    @Test
    void shouldReturnNullWhenTypeIsNotPresent() {
        jsonObject.remove("type");
        assertNull(new GHAdvisory(github, jsonObject).getType());
    }

    @Test
    void shouldReturnCorrectSeverity() {
        assertEquals("Test severity", new GHAdvisory(github, jsonObject).getSeverity());
    }

    @Test
    void shouldReturnNullWhenSeverityIsNotPresent() {
        jsonObject.remove("severity");
        assertNull(new GHAdvisory(github, jsonObject).getSeverity());
    }

    @Test
    void shouldReturnCorrectSourceCodeLocation() {
        assertEquals("Test location", new GHAdvisory(github, jsonObject).getSourceCodeLocation());
    }

    @Test
    void shouldReturnNullWhenSourceCodeLocationIsNotPresent() {
        jsonObject.remove("source_code_location");
        assertNull(new GHAdvisory(github, jsonObject).getSourceCodeLocation());
    }

    @Test
    void shouldReturnCorrectPublishedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHAdvisory(github, jsonObject).getPublishedAt());
    }

    @Test
    void shouldReturnNullWhenPublishedAtIsNotPresent() {
        jsonObject.remove("published_at");
        assertNull(new GHAdvisory(github, jsonObject).getPublishedAt());
    }

    @Test
    void shouldReturnCorrectUpdatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHAdvisory(github, jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenUpdatedAtIsNotPresent() {
        jsonObject.remove("updated_at");
        assertNull(new GHAdvisory(github, jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnCorrectGithubReviewedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHAdvisory(github, jsonObject).getGithubReviewedAt());
    }

    @Test
    void shouldReturnNullWhenGithubReviewedAtIsNotPresent() {
        jsonObject.remove("github_reviewed_at");
        assertNull(new GHAdvisory(github, jsonObject).getGithubReviewedAt());
    }

    @Test
    void shouldReturnCorrectNvdPublishedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHAdvisory(github, jsonObject).getNvdPublishedAt());
    }

    @Test
    void shouldReturnNullWhenNvdPublishedAtIsNotPresent() {
        jsonObject.remove("nvd_published_at");
        assertNull(new GHAdvisory(github, jsonObject).getNvdPublishedAt());
    }

    @Test
    void shouldReturnCorrectWithdrawnAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHAdvisory(github, jsonObject).getWithdrawnAt());
    }

    @Test
    void shouldReturnNullWhenWithdrawnAtIsNotPresent() {
        jsonObject.remove("withdrawn_at");
        assertNull(new GHAdvisory(github, jsonObject).getWithdrawnAt());
    }

    @Test
    void shouldReturnCorrectCvss() {
        assertNotNull(new GHAdvisory(github, jsonObject).getCvss());
    }

    @Test
    void shouldReturnCorrectCwes() {
        assertNotNull(new GHAdvisory(github, jsonObject).getCwes());
    }

    @Test
    void shouldReturnCorrectIdentifiers() {
        assertNotNull(new GHAdvisory(github, jsonObject).getIdentifiers());
    }

    @Test
    void shouldReturnCorrectCredits() {
        assertEquals(2, new GHAdvisory(github, jsonObject).getCredits().size());
    }

    @Test
    void shouldReturnCorrectVulnerabilities() {
        assertEquals(2, new GHAdvisory(github, jsonObject).getVulnerabilities().size());
    }

    @Test
    void shouldReturnCorrectReferences() {
        assertEquals(2, new GHAdvisory(github, jsonObject).getReferences().size());
    }
}