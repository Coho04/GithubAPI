package de.goldendeveloper.githubapi.repositories;

import de.goldendeveloper.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHLabels extends GHBase {

    private final int id;
    private final String url;
    private final String name;
    private final String color;
    private final String nodeId;
    private final boolean isDefault;
    private final String description;

    public GHLabels(JSONObject jsonObject) {
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
