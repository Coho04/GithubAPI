package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHAlertTest {

    private JSONObject jsonObject;
    private Github github;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("number", 1);
        jsonObject.put("created_at", "2022-01-01T00:00:00Z");
        jsonObject.put("url", "https://github.com/alerts/1");
        jsonObject.put("html_url", "https://github.com/alerts/1.html");
        jsonObject.put("locations_url", "https://github.com/alerts/1/locations");
        jsonObject.put("state", "open");
        jsonObject.put("resolution", "resolved");
        jsonObject.put("resolved_at", "2022-01-02T00:00:00Z");
        jsonObject.put("resolved_by", new JSONObject().put("login", "user1"));
        jsonObject.put("secret_type", "secret_type1");
        jsonObject.put("secret_type_display_name", "Secret Type 1");
        jsonObject.put("secret", "secret1");
        jsonObject.put("repository", new JSONObject().put("name", "repo1").put("topics", new JSONArray()));
        jsonObject.put("push_protection_bypassed_by", new JSONObject().put("login", "user2"));
        jsonObject.put("push_protection_bypassed", true);
        jsonObject.put("push_protection_bypassed_at", "2022-01-03T00:00:00Z");
        jsonObject.put("resolution_comment", "Test comment");
        jsonObject.put("validity", "valid");
        github = Mockito.mock(Github.class);
    }

    @Test
    void shouldReturnCorrectNumber() {
        assertEquals(1, new GHAlert(jsonObject, github).getNumber());
    }

    @Test
    void shouldReturnZeroWhenNumberIsNotPresent() {
        jsonObject.remove("number");
        assertEquals(0, new GHAlert(jsonObject, github).getNumber());
    }

    @Test
    void shouldReturnCorrectCreatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHAlert(jsonObject, github).getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenCreatedAtIsNotPresent() {
        jsonObject.remove("created_at");
        assertNull(new GHAlert(jsonObject, github).getCreatedAt());
    }

    @Test
    void shouldReturnCorrectUrl() {
        assertEquals("https://github.com/alerts/1", new GHAlert(jsonObject, github).getUrl());
    }

    @Test
    void shouldReturnNullWhenUrlIsNotPresent() {
        jsonObject.remove("url");
        assertNull(new GHAlert(jsonObject, github).getUrl());
    }

    @Test
    void shouldReturnCorrectHtmlUrl() {
        assertEquals("https://github.com/alerts/1.html", new GHAlert(jsonObject, github).getHtmlUrl());
    }

    @Test
    void shouldReturnNullWhenHtmlUrlIsNotPresent() {
        jsonObject.remove("html_url");
        assertNull(new GHAlert(jsonObject, github).getHtmlUrl());
    }

    @Test
    void shouldReturnCorrectLocationsUrl() {
        assertEquals("https://github.com/alerts/1/locations", new GHAlert(jsonObject, github).getLocationsUrl());
    }

    @Test
    void shouldReturnNullWhenLocationsUrlIsNotPresent() {
        jsonObject.remove("locations_url");
        assertNull(new GHAlert(jsonObject, github).getLocationsUrl());
    }

    @Test
    void shouldReturnCorrectState() {
        assertEquals("open", new GHAlert(jsonObject, github).getState());
    }

    @Test
    void shouldReturnNullWhenStateIsNotPresent() {
        jsonObject.remove("state");
        assertNull(new GHAlert(jsonObject, github).getState());
    }

    @Test
    void shouldReturnCorrectResolution() {
        assertEquals("resolved", new GHAlert(jsonObject, github).getResolution());
    }

    @Test
    void shouldReturnNullWhenResolutionIsNotPresent() {
        jsonObject.remove("resolution");
        assertNull(new GHAlert(jsonObject, github).getResolution());
    }

    @Test
    void shouldReturnCorrectResolvedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-02T00:00:00Z"), new GHAlert(jsonObject, github).getResolvedAt());
    }

    @Test
    void shouldReturnNullWhenResolvedAtIsNotPresent() {
        jsonObject.remove("resolved_at");
        assertNull(new GHAlert(jsonObject, github).getResolvedAt());
    }

    @Test
    void shouldReturnCorrectSecretType() {
        assertEquals("secret_type1", new GHAlert(jsonObject, github).getSecretType());
    }

    @Test
    void shouldReturnNullWhenSecretTypeIsNotPresent() {
        jsonObject.remove("secret_type");
        assertNull(new GHAlert(jsonObject, github).getSecretType());
    }

    @Test
    void shouldReturnCorrectSecretTypeDisplayName() {
        assertEquals("Secret Type 1", new GHAlert(jsonObject, github).getSecretTypeDisplayName());
    }

    @Test
    void shouldReturnNullWhenSecretTypeDisplayNameIsNotPresent() {
        jsonObject.remove("secret_type_display_name");
        assertNull(new GHAlert(jsonObject, github).getSecretTypeDisplayName());
    }

    @Test
    void shouldReturnCorrectSecret() {
        assertEquals("secret1", new GHAlert(jsonObject, github).getSecret());
    }

    @Test
    void shouldReturnNullWhenSecretIsNotPresent() {
        jsonObject.remove("secret");
        assertNull(new GHAlert(jsonObject, github).getSecret());
    }

    @Test
    void shouldReturnCorrectPushProtectionBypassed() {
        assertTrue(new GHAlert(jsonObject, github).isPushProtectionBypassed());
    }

    @Test
    void shouldReturnFalseWhenPushProtectionBypassedIsNotPresent() {
        jsonObject.remove("push_protection_bypassed");
        assertFalse(new GHAlert(jsonObject, github).isPushProtectionBypassed());
    }

    @Test
    void shouldReturnCorrectPushProtectionBypassedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-03T00:00:00Z"), new GHAlert(jsonObject, github).getPushProtectionBypassedAt());
    }

    @Test
    void shouldReturnNullWhenPushProtectionBypassedAtIsNotPresent() {
        jsonObject.remove("push_protection_bypassed_at");
        assertNull(new GHAlert(jsonObject, github).getPushProtectionBypassedAt());
    }

    @Test
    void shouldReturnCorrectResolutionComment() {
        assertEquals("Test comment", new GHAlert(jsonObject, github).getResolutionComment());
    }

    @Test
    void shouldReturnNullWhenResolutionCommentIsNotPresent() {
        jsonObject.remove("resolution_comment");
        assertNull(new GHAlert(jsonObject, github).getResolutionComment());
    }

    @Test
    void shouldReturnCorrectValidity() {
        assertEquals("valid", new GHAlert(jsonObject, github).getValidity());
    }

    @Test
    void shouldReturnNullWhenValidityIsNotPresent() {
        jsonObject.remove("validity");
        assertNull(new GHAlert(jsonObject, github).getValidity());
    }

    @Test
    void shouldReturnCorrectResolvedBy() {
        assertEquals("user1", new GHAlert(jsonObject, github).getResolvedBy().getLogin());
    }

    @Test
    void shouldReturnNullWhenResolvedByIsNotPresent() {
        jsonObject.remove("resolved_by");
        assertNull(new GHAlert(jsonObject, github).getResolvedBy());
    }

    @Test
    void shouldReturnCorrectRepository() {
        assertEquals("repo1", new GHAlert(jsonObject, github).getRepository().getName());
    }

    @Test
    void shouldReturnNullWhenRepositoryIsNotPresent() {
        jsonObject.remove("repository");
        assertNull(new GHAlert(jsonObject, github).getRepository());
    }

    @Test
    void shouldReturnCorrectPushProtectionBypassedBy() {
        assertEquals("user2", new GHAlert(jsonObject, github).getPushProtectionBypassedBy().getLogin());
    }

    @Test
    void shouldReturnNullWhenPushProtectionBypassedByIsNotPresent() {
        jsonObject.remove("push_protection_bypassed_by");
        assertNull(new GHAlert(jsonObject, github).getPushProtectionBypassedBy());
    }

    @Test
    void shouldReturnNullWhenGithubIsNotPresent() {
        assertNull(new GHAlert(jsonObject, null).getGithub());
    }

    @Test
    void shouldReturnCorrectGithub() {
        assertEquals(github, new GHAlert(jsonObject, github).getGithub());
    }
}