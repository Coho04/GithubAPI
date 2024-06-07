package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GHEventTest {

    private JSONObject jsonObject;
    private GHEvent event;
    private OffsetDateTime now;

    @BeforeEach
    void setUp() {
        now = OffsetDateTime.now();
        jsonObject = new JSONObject();
        jsonObject.put("id", "12345");
        jsonObject.put("type", "PushEvent");
        jsonObject.put("actor", new JSONObject().put("login", "octocat"));
        jsonObject.put("repo", new JSONObject().put("name", "octocat/Hello-World"));
        jsonObject.put("payload", new JSONObject().put("size", 2));
        jsonObject.put("public", true);
        jsonObject.put("created_at", now.toString());

        event = new GHEvent(jsonObject);
    }

    @Test
    void testGetId() {
        assertEquals("12345", event.getId());
    }

    @Test
    void testGetType() {
        assertEquals("PushEvent", event.getType());
    }

    @Test
    void testGetActor() {
        JSONObject actor = event.getActor();
        assertNotNull(actor);
        assertEquals("octocat", actor.getString("login"));
    }

    @Test
    void testGetRepo() {
        JSONObject repo = event.getRepo();
        assertNotNull(repo);
        assertEquals("octocat/Hello-World", repo.getString("name"));
    }

    @Test
    void testGetPayload() {
        JSONObject payload = event.getPayload();
        assertNotNull(payload);
        assertEquals(2, payload.getInt("size"));
    }

    @Test
    void testIsPublic() {
        assertTrue(event.isPublic());
    }

    @Test
    void testGetCreatedAt() {
        assertEquals(now, event.getCreatedAt());
    }
}
