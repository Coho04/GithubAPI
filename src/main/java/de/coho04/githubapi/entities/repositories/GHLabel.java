package de.coho04.githubapi.entities.repositories;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub label.
 * It provides methods for fetching data about the label such as its ID, URL, name, color, node ID, default status, and description.
 */
public class GHLabel extends GHBase {

    private final int id;
    private final String url;
    private final String name;
    private final String color;
    private final String nodeId;
    private final boolean isDefault;
    private final String description;

    /**
     * Constructs a new GHLabel instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the label data
     */
    public GHLabel(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.url = getStringOrNull(jsonObject, "url");
        this.name = getStringOrNull(jsonObject, "name");
        this.color = getStringOrNull(jsonObject, "color");
        this.nodeId = getStringOrNull(jsonObject, "node_id");
        this.isDefault = getBooleanOrNull(jsonObject, "default");
        this.description = getStringOrNull(jsonObject, "description");
    }

    /**
     * Returns the node ID of the label.
     *
     * @return the node ID of the label
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Returns the URL of the label.
     *
     * @return the URL of the label
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the ID of the label.
     *
     * @return the ID of the label
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the label.
     *
     * @return the name of the label
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the color of the label.
     *
     * @return the color of the label
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the description of the label.
     *
     * @return the description of the label
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the label is the default.
     *
     * @return true if the label is the default, false otherwise
     */
    public boolean isDefault() {
        return isDefault;
    }
}