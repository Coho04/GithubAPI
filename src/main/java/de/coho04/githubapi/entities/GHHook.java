package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * This class represents a GitHub Hook.
 * It provides methods for fetching data about the hook such as its id, url, pingUrl, deliveriesUrl, name, events, active status, config, updatedAt, createdAt, and type.
 */
public class GHHook extends GHBase {

    private final int id;
    private final String url;
    private final String pingUrl;
    private final String deliveriesUrl;
    private final String name;
    private List<String> events;
    private final boolean active;
    private final JSONObject config;
    private final OffsetDateTime updatedAt;
    private final OffsetDateTime createdAt;
    private final String type;

    /**
     * Constructs a new GHHook instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the hook data
     */
    public GHHook(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject,"id");
        this.url = getStringOrNull(jsonObject,"url");
        this.pingUrl = getStringOrNull(jsonObject,"ping_url");
        this.deliveriesUrl = getStringOrNull(jsonObject,"deliveries_url");
        this.name = getStringOrNull(jsonObject,"name");
        if (jsonObject.has("events") && !jsonObject.isNull("events")) {
            this.events = getJSONArrayToStringList(jsonObject,"events");
        }
        this.active = getBooleanOrNull(jsonObject,"active");
        this.config = getJSONObjectOrNull(jsonObject,"config");
        this.updatedAt = getLocalDateOrNull(jsonObject,"updated_at");
        this.createdAt = getLocalDateOrNull(jsonObject,"created_at");
        this.type = getStringOrNull(jsonObject,"type");
    }

    /**
     * Returns the creation date of the hook.
     *
     * @return the creation date of the hook
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the update date of the hook.
     *
     * @return the update date of the hook
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the name of the hook.
     *
     * @return the name of the hook
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the id of the hook.
     *
     * @return the id of the hook
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the config of the hook.
     *
     * @return the config of the hook
     */
    public JSONObject getConfig() {
        return config;
    }

    /**
     * Returns the events of the hook.
     *
     * @return the events of the hook
     */
    public List<String> getEvents() {
        return events;
    }

    /**
     * Returns the deliveries url of the hook.
     *
     * @return the deliveries url of the hook
     */
    public String getDeliveriesUrl() {
        return deliveriesUrl;
    }

    /**
     * Returns the ping url of the hook.
     *
     * @return the ping url of the hook
     */
    public String getPingUrl() {
        return pingUrl;
    }

    /**
     * Returns the type of the hook.
     *
     * @return the type of the hook
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the url of the hook.
     *
     * @return the url of the hook
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns whether the hook is active.
     *
     * @return whether the hook is active
     */
    public boolean isActive() {
        return active;
    }
}