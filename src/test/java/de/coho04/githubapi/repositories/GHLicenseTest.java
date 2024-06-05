package de.coho04.githubapi.repositories;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHLicenseTest {

    @Test
    void shouldReturnCorrectUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "https://api.github.com/licenses/mit");

        GHLicense ghLicense = new GHLicense(jsonObject);

        assertEquals("https://api.github.com/licenses/mit", ghLicense.getUrl());
    }

    @Test
    void shouldReturnNullWhenUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHLicense ghLicense = new GHLicense(jsonObject);

        assertNull(ghLicense.getUrl());
    }

    @Test
    void shouldReturnCorrectName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "MIT License");

        GHLicense ghLicense = new GHLicense(jsonObject);

        assertEquals("MIT License", ghLicense.getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        JSONObject jsonObject = new JSONObject();

        GHLicense ghLicense = new GHLicense(jsonObject);

        assertNull(ghLicense.getName());
    }

    // Similar tests can be written for the remaining fields: key, nodeId, spdxId
}