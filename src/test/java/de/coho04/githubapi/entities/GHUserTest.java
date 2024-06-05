package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHUserTest {

    @Test
    void shouldReturnCorrectContributions() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contributions", 100);

        GHUser ghUser = new GHUser(jsonObject);

        assertEquals(100, ghUser.getContributions());
    }

    @Test
    void shouldReturnZeroWhenContributionsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHUser ghUser = new GHUser(jsonObject);

        assertEquals(0, ghUser.getContributions());
    }

    @Test
    void shouldReturnCorrectGistsUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gists_url", "https://gists.github.com/octocat");

        GHUser ghUser = new GHUser(jsonObject);

        assertEquals("https://gists.github.com/octocat", ghUser.getGistsUrl());
    }

    @Test
    void shouldReturnNullWhenGistsUrlNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHUser ghUser = new GHUser(jsonObject);

        assertNull(ghUser.getGistsUrl());
    }

    @Test
    void shouldReturnCorrectSiteAdminStatus() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("site_admin", true);

        GHUser ghUser = new GHUser(jsonObject);

        assertTrue(ghUser.isSiteAdmin());
    }

    @Test
    void shouldReturnFalseWhenSiteAdminStatusNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHUser ghUser = new GHUser(jsonObject);

        assertFalse(ghUser.isSiteAdmin());
    }

    // Similar tests can be written for the remaining fields: gravatarId, starredUrl, followersUrl, followingUrl, subscriptionsUrl, receivedEventsUrl, organizationsUrl
}