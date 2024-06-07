package de.coho04.githubapi.entities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpsCertificateTest {

    private HttpsCertificate certificate;

    @BeforeEach
    void setUp() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", "active");
        jsonObject.put("description", "Certificate is active and valid.");
        jsonObject.put("domains", new JSONArray(List.of("example.com", "www.example.com")));
        jsonObject.put("expires_at", OffsetDateTime.now().toString());

        certificate = new HttpsCertificate(jsonObject);
    }

    @Test
    void testGetState() {
        assertEquals("active", certificate.getState());
    }

    @Test
    void testGetDescription() {
        assertEquals("Certificate is active and valid.", certificate.getDescription());
    }

    @Test
    void testGetDomains() {
        List<String> domains = certificate.getDomains();
        assertNotNull(domains);
        assertEquals(2, domains.size());
        assertTrue(domains.contains("example.com"));
        assertTrue(domains.contains("www.example.com"));
    }

    @Test
    void testGetExpiresAt() {
        assertNotNull(certificate.getExpiresAt());
    }
}
