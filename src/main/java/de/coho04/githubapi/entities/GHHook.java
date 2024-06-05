package de.coho04.githubapi.entities;

import de.coho04.githubapi.interfaces.JSONHelper;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class GHHook implements JSONHelper {

    public final int id;
    public final String url;
    public final String pingUrl;
    public final String deliveriesUrl;
    public final String name;
    public final List<String> events;
    public final boolean active;
    public final JSONObject config;
    public final OffsetDateTime updatedAt;
    public final OffsetDateTime createdAt;
    public final String type;

    public GHHook(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject,"id");
        this.url = getStringOrNull(jsonObject,"url");
        this.pingUrl = getStringOrNull(jsonObject,"ping_url");
        this.deliveriesUrl = getStringOrNull(jsonObject,"deliveries_url");
        this.name = getStringOrNull(jsonObject,"name");
        this.events = getArrayOrNull(jsonObject,"events", JSONObject::toString);
        this.active = getBooleanOrNull(jsonObject,"active");
        this.config = getJSONObjectOrNull(jsonObject,"config");
        this.updatedAt = getLocalDateOrNull(jsonObject,"updated_at");
        this.createdAt = getLocalDateOrNull(jsonObject,"created_at");
        this.type = getStringOrNull(jsonObject,"type");
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public JSONObject getConfig() {
        return config;
    }

    public List<String> getEvents() {
        return events;
    }

    public String getDeliveriesUrl() {
        return deliveriesUrl;
    }

    public String getPingUrl() {
        return pingUrl;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
