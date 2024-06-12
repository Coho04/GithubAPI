package io.github.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHPagesTest {

    private JSONObject jsonObject;
    private GHPages pages;
    private OffsetDateTime now;

    @BeforeEach
    void setUp() {
        now = OffsetDateTime.now();
        jsonObject = new JSONObject();
        jsonObject.put("url", "https://example.github.io");
        jsonObject.put("status", "active");
        jsonObject.put("cname", "example.com");
        jsonObject.put("custom_404", true);
        jsonObject.put("html_url", "https://example.github.io/index.html");
        jsonObject.put("source", new JSONObject().put("branch", "main").put("path", "/"));
        jsonObject.put("is_public", true);
        jsonObject.put("pending_domain_unverified_at", now.toString());
        jsonObject.put("protected_domain_state", "verified");
        jsonObject.put("https_certificate", new JSONObject().put("state", "valid").put("description", "Certificate is valid"));
        jsonObject.put("https_enforced", true);

        pages = new GHPages(jsonObject);
    }

    @Test
    void testGetUrl() {
        assertEquals("https://example.github.io", pages.getUrl());
    }

    @Test
    void testGetStatus() {
        assertEquals("active", pages.getStatus());
    }

    @Test
    void testGetCname() {
        assertEquals("example.com", pages.getCname());
    }

    @Test
    void testIsCustom404() {
        assertTrue(pages.isCustom404());
    }

    @Test
    void testGetHtmlUrl() {
        assertEquals("https://example.github.io/index.html", pages.getHtmlUrl());
    }

    @Test
    void testGetSource() {
        JSONObject source = pages.getSource();
        assertNotNull(source);
        assertEquals("main", source.getString("branch"));
        assertEquals("/", source.getString("path"));
    }

    @Test
    void testIsPublic() {
        assertTrue(pages.isPublic());
    }

    @Test
    void testGetPendingDomainUnverifiedAt() {
        assertEquals(now, pages.getPendingDomainUnverifiedAt());
    }

    @Test
    void testGetProtectedDomainState() {
        assertEquals("verified", pages.getProtectedDomainState());
    }

    @Test
    void testGetHttpsCertificate() {
        HttpsCertificate httpsCertificate = pages.getHttpsCertificate();
        assertNotNull(httpsCertificate);
        assertEquals("valid", httpsCertificate.getState());
        assertEquals("Certificate is valid", httpsCertificate.getDescription());
    }

    @Test
    void testIsHttpsEnforced() {
        assertTrue(pages.isHttpsEnforced());
    }
}
