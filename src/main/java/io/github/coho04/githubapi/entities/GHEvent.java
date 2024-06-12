package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * This class represents a GitHub Event.
 * It provides methods for fetching data about the event such as its id, type, actor, repo, payload, public status, and creation date.
 */
public class GHEvent extends GHBase {

    private final String id;
    private final String type;
    private final JSONObject actor;
    private final JSONObject repo;
    private final JSONObject payload;
    private final boolean isPublic;
    private final OffsetDateTime createdAt;

    /**
     * Constructs a new GHEvent instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the event data
     */
    public GHEvent(JSONObject jsonObject) {
        this.id = getStringOrNull(jsonObject, "id");
        this.type = getStringOrNull(jsonObject, "type");
        this.actor = getJSONObjectOrNull(jsonObject, "actor");
        this.repo = getJSONObjectOrNull(jsonObject, "repo");
        this.payload = getJSONObjectOrNull(jsonObject, "payload");
        this.isPublic = getBooleanOrNull(jsonObject, "public");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
    }

    /**
     * Returns the type of the event.
     *
     * @return the type of the event
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the actor of the event.
     *
     * @return the actor of the event
     */
    public JSONObject getActor() {
        return actor;
    }

    /**
     * Returns the payload of the event.
     *
     * @return the payload of the event
     */
    public JSONObject getPayload() {
        return payload;
    }

    /**
     * Returns the repo of the event.
     *
     * @return the repo of the event
     */
    public JSONObject getRepo() {
        return repo;
    }

    /**
     * Returns the creation date of the event.
     *
     * @return the creation date of the event
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the id of the event.
     *
     * @return the id of the event
     */
    public String getId() {
        return id;
    }

    /**
     * Returns whether the event is public.
     *
     * @return whether the event is public
     */
    public boolean isPublic() {
        return isPublic;
    }
}