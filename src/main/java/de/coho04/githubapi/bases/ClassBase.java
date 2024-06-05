package de.coho04.githubapi.bases;

import org.json.JSONObject;

@SuppressWarnings("unused")
public class ClassBase extends GHBase {

    private final int id;
    private final String nodeId;
    private final String htmlUrl;
    private final String url;
    private final String eventsUrl;

    public ClassBase(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.nodeId = getStringOrNull(jsonObject, "node_id");
        this.htmlUrl = getStringOrNull(jsonObject, "html_url");
        this.url = getStringOrNull(jsonObject, "url");
        this.eventsUrl = getStringOrNull(jsonObject, "events_url");
    }

    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("id", id)
                .put("node_id", nodeId)
                .put("html_url", htmlUrl)
                .put("url", url)
                .put("events_url", eventsUrl);
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public int getId() {
        return id;
    }
}
