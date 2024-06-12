package io.github.coho04.githubapi.bases;

import org.json.JSONObject;

/**
 * ClassBase is a class that extends GHBase. It represents a base class for GitHub objects.
 * It contains several properties such as id, nodeId, htmlUrl, url, and eventsUrl.
 * These properties are initialized through a JSONObject.
 *
 * @author Coho04
 * @version 1.0
 * @since 2024-1.2
 */
public class ClassBase extends GHBase {

    private final int id;
    private final String nodeId;
    private final String htmlUrl;
    private final String url;
    private final String eventsUrl;

    /**
     * Constructs a new ClassBase object.
     *
     * @param jsonObject a JSONObject containing the data for initializing the ClassBase object.
     */
    public ClassBase(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.nodeId = getStringOrNull(jsonObject, "node_id");
        this.htmlUrl = getStringOrNull(jsonObject, "html_url");
        this.url = getStringOrNull(jsonObject, "url");
        this.eventsUrl = getStringOrNull(jsonObject, "events_url");
    }

    /**
     * Converts this ClassBase object to a JSONObject.
     *
     * @return a JSONObject representation of this ClassBase object.
     */
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("id", id)
                .put("node_id", nodeId)
                .put("html_url", htmlUrl)
                .put("url", url)
                .put("events_url", eventsUrl);
    }

    /**
     * Returns the nodeId of this ClassBase object.
     *
     * @return the nodeId of this ClassBase object.
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Returns the htmlUrl of this ClassBase object.
     *
     * @return the htmlUrl of this ClassBase object.
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Returns the url of this ClassBase object.
     *
     * @return the url of this ClassBase object.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the eventsUrl of this ClassBase object.
     *
     * @return the eventsUrl of this ClassBase object.
     */
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     * Returns the id of this ClassBase object.
     *
     * @return the id of this ClassBase object.
     */
    public int getId() {
        return id;
    }
}