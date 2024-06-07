package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GHAdvisoryTest {

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

    private GHUser setupUser() {
        Random random = new Random();
        return new GHUser(Mockito.mock(Github.class), new JSONObject()
                .put("contributions", String.valueOf(random.nextInt()))
                .put("gists_url", "https://example.com/gists_url")
                .put("site_admin", true)
                .put("gravatar_id", "A principal idea stands upon somebody else's legs.")
                .put("starred_url", "https://example.com/starred_url")
                .put("followers_url", "https://example.com/followers_url")
                .put("following_url", "https://example.com/following_url")
                .put("subscriptions_url", "https://example.com/subscriptions_url")
                .put("received_events_url", "https://example.com/received_events_url")
                .put("organizations_url", "https://example.com/organizations_url")
                .put("type", "An idea stands upon somebody else's legs.")
                .put("login", "Sex stands upon somebody else's legs.")
                .put("repos_url", "https://example.com/repos_url")
                .put("avatar_url", "https://example.com/avatar_url")
                .put("id", String.valueOf(random.nextInt()))
                .put("node_id", "A token of gratitude stands upon somebody else's legs.")
                .put("html_url", "https://example.com/html_url")
                .put("url", "https://example.com/url")
                .put("events_url", "https://example.com/events_url")
        );
    }


    private GHVulnerability setupVulnerabilities() {
        Random random = new Random();
        return new GHVulnerability(Mockito.mock(Github.class), new JSONObject()
                .put("contributions", String.valueOf(random.nextInt()))
                .put("first_patched_version", "https://example.com/gists_url")
                .put("vulnerable_version_range", String.valueOf(random.nextBoolean()))
                .put("vulnerable_functions", new JSONArray())
        );
    }
}