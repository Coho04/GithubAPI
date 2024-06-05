package de.coho04.githubapi.repositories;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHLabel extends GHBase {

    private final int id;
    private final String url;
    private final String name;
    private final String color;
    private final String nodeId;
    private final boolean isDefault;
    private final String description;

    public GHLabel(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.url = getStringOrNull(jsonObject, "url");
        this.name = getStringOrNull(jsonObject, "name");
        this.color = getStringOrNull(jsonObject, "color");
        this.nodeId = getStringOrNull(jsonObject, "node_id");
        this.isDefault = getBooleanOrNull(jsonObject, "default");
        this.description = getStringOrNull(jsonObject, "description");
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDefault() {
        return isDefault;
    }
}
