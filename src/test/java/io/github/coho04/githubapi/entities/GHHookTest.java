package io.github.coho04.githubapi.entities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GHHookTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("url", "https://github.com/hook/1");
        jsonObject.put("ping_url", "https://github.com/hook/1/ping");
        jsonObject.put("deliveries_url", "https://github.com/hook/1/deliveries");
        jsonObject.put("name", "Hook1");
        jsonObject.put("events", new JSONArray(List.of("event1", "event2")));
        jsonObject.put("active", true);
        jsonObject.put("config", new JSONObject().put("url", "https://github.com/hook/1/config"));
        jsonObject.put("updated_at", "2022-01-01T00:00:00Z");
        jsonObject.put("created_at", "2022-01-01T00:00:00Z");
        jsonObject.put("type", "Type1");
    }

    @Test
    void shouldReturnCorrectId() {
        assertEquals(1, new GHHook(jsonObject).getId());
    }

    @Test
    void shouldReturnZeroWhenIdIsNotPresent() {
        jsonObject.remove("id");
        assertEquals(0, new GHHook(jsonObject).getId());
    }

    @Test
    void shouldReturnCorrectUrl() {
        assertEquals("https://github.com/hook/1", new GHHook(jsonObject).getUrl());
    }

    @Test
    void shouldReturnNullWhenUrlIsNotPresent() {
        jsonObject.remove("url");
        assertNull(new GHHook(jsonObject).getUrl());
    }

    @Test
    void shouldReturnCorrectPingUrl() {
        assertEquals("https://github.com/hook/1/ping", new GHHook(jsonObject).getPingUrl());
    }

    @Test
    void shouldReturnNullWhenPingUrlIsNotPresent() {
        jsonObject.remove("ping_url");
        assertNull(new GHHook(jsonObject).getPingUrl());
    }

    @Test
    void shouldReturnCorrectDeliveriesUrl() {
        assertEquals("https://github.com/hook/1/deliveries", new GHHook(jsonObject).getDeliveriesUrl());
    }

    @Test
    void shouldReturnNullWhenDeliveriesUrlIsNotPresent() {
        jsonObject.remove("deliveries_url");
        assertNull(new GHHook(jsonObject).getDeliveriesUrl());
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals("Hook1", new GHHook(jsonObject).getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        jsonObject.remove("name");
        assertNull(new GHHook(jsonObject).getName());
    }

    @Test
    void shouldReturnCorrectEvents() {
        assertEquals(jsonObject.getJSONArray("events").toList().stream().map(Object::toString).toList(), new GHHook(jsonObject).getEvents());
    }

    @Test
    void shouldReturnNullWhenEventsAreNotPresent() {
        jsonObject.remove("events");
        assertNull(new GHHook(jsonObject).getEvents());
    }

    @Test
    void shouldReturnCorrectActiveStatus() {
        assertTrue(new GHHook(jsonObject).isActive());
    }

    @Test
    void shouldReturnFalseWhenActiveStatusIsNotPresent() {
        jsonObject.remove("active");
        assertFalse(new GHHook(jsonObject).isActive());
    }

    @Test
    void shouldReturnCorrectConfig() {
        assertEquals(new JSONObject().put("url", "https://github.com/hook/1/config").toString(), new GHHook(jsonObject).getConfig().toString());
    }

    @Test
    void shouldReturnNullWhenConfigIsNotPresent() {
        jsonObject.remove("config");
        assertNull(new GHHook(jsonObject).getConfig());
    }

    @Test
    void shouldReturnCorrectUpdatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHHook(jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenUpdatedAtIsNotPresent() {
        jsonObject.remove("updated_at");
        assertNull(new GHHook(jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnCorrectCreatedAt() {
        assertEquals(OffsetDateTime.parse("2022-01-01T00:00:00Z"), new GHHook(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenCreatedAtIsNotPresent() {
        jsonObject.remove("created_at");
        assertNull(new GHHook(jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnCorrectType() {
        assertEquals("Type1", new GHHook(jsonObject).getType());
    }

    @Test
    void shouldReturnNullWhenTypeIsNotPresent() {
        jsonObject.remove("type");
        assertNull(new GHHook(jsonObject).getType());
    }
}