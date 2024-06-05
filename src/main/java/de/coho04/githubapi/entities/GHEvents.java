package de.coho04.githubapi.entities;

import de.coho04.githubapi.interfaces.JSONHelper;
import org.json.JSONObject;

import java.time.OffsetDateTime;

@SuppressWarnings("unused")
public class GHEvents implements JSONHelper {

    public String id;
    public String type;
    public JSONObject actor;
    public JSONObject repo;
    public JSONObject payload;
    public boolean isPublic;
    public OffsetDateTime createdAt;


    public GHEvents(JSONObject jsonObject) {
        this.id = getStringOrNull(jsonObject, "id");
        this.type = getStringOrNull(jsonObject, "type");
        this.actor = getJSONObjectOrNull(jsonObject, "actor");
        this.repo = getJSONObjectOrNull(jsonObject, "repo");
        this.payload = getJSONObjectOrNull(jsonObject, "payload");
        this.isPublic = getBooleanOrNull(jsonObject, "public");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
    }

    public String getType() {
        return type;
    }

    public JSONObject getActor() {
        return actor;
    }

    public JSONObject getPayload() {
        return payload;
    }

    public JSONObject getRepo() {
        return repo;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }
}
