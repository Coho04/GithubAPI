package de.goldendeveloper.githubapi.bases;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassBaseTest {

    @Test
    void shouldReturnCorrectNodeId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("node_id", "123");
        ClassBase classBase = new ClassBase(jsonObject);
        assertEquals("123", classBase.getNodeId());
    }

    @Test
    void shouldReturnNullWhenNodeIdIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        ClassBase classBase = new ClassBase(jsonObject);
        assertNull(classBase.getNodeId());
    }

    @Test
    void shouldReturnCorrectHtmlUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("html_url", "https://html.github.com");
        ClassBase classBase = new ClassBase(jsonObject);
        assertEquals("https://html.github.com", classBase.getHtmlUrl());
    }

    @Test
    void shouldReturnNullWhenHtmlUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        ClassBase classBase = new ClassBase(jsonObject);
        assertNull(classBase.getHtmlUrl());
    }

    @Test
    void shouldReturnCorrectUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "https://github.com");
        ClassBase classBase = new ClassBase(jsonObject);
        assertEquals("https://github.com", classBase.getUrl());
    }

    @Test
    void shouldReturnNullWhenUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        ClassBase classBase = new ClassBase(jsonObject);
        assertNull(classBase.getUrl());
    }

    @Test
    void shouldReturnCorrectEventsUrl() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("events_url", "https://events.github.com");
        ClassBase classBase = new ClassBase(jsonObject);
        assertEquals("https://events.github.com", classBase.getEventsUrl());
    }

    @Test
    void shouldReturnNullWhenEventsUrlIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        ClassBase classBase = new ClassBase(jsonObject);
        assertNull(classBase.getEventsUrl());
    }

    @Test
    void shouldReturnCorrectId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        ClassBase classBase = new ClassBase(jsonObject);
        assertEquals(1, classBase.getId());
    }

    @Test
    void shouldReturnZeroWhenIdIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        ClassBase classBase = new ClassBase(jsonObject);
        assertEquals(0, classBase.getId());
    }
}