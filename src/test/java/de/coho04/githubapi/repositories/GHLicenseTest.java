package de.coho04.githubapi.repositories;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Random;

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

    @Test
    void shouldReturnCorrectKey() {
        JSONObject jsonObject = new JSONObject();
        int key = new Random().nextInt();
        jsonObject.put("key", String.valueOf(key));
        GHLicense ghLicense = new GHLicense(jsonObject);
        assertEquals(String.valueOf(key), ghLicense.getKey());
    }

    @Test
    void shouldReturnCorrectNodeId() {
        JSONObject jsonObject = new JSONObject();
        int nodeId = new Random().nextInt();
        jsonObject.put("node_id", String.valueOf(nodeId));
        GHLicense ghLicense = new GHLicense(jsonObject);
        assertEquals(String.valueOf(nodeId), ghLicense.getNodeId());
    }


    @Test
    void shouldReturnCorrectSpdxId() {
        JSONObject jsonObject = new JSONObject();
        int spdxId = new Random().nextInt();
        jsonObject.put("spdx_id", String.valueOf(spdxId));
        GHLicense ghLicense = new GHLicense(jsonObject);
        assertEquals(String.valueOf(spdxId), ghLicense.getSpdxId());
    }


    @Test
    void shouldReturnNullWhenKeyIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHLicense ghLicense = new GHLicense(jsonObject);
        assertNull(ghLicense.getKey());
    }

    @Test
    void shouldReturnNullWhenNodeIdIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHLicense ghLicense = new GHLicense(jsonObject);
        assertNull(ghLicense.getNodeId());
    }

    @Test
    void shouldReturnNullWhenSpdxIdIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHLicense ghLicense = new GHLicense(jsonObject);
        assertNull(ghLicense.getSpdxId());
    }
}