package de.goldendeveloper.githubapi;

import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHLicense {

    private final String name;
    private final String spdxId;
    private final String key;
    private final String url;
    private final String nodeId;

    public GHLicense(JSONObject jsonObject) {
        this.name = jsonObject.getString("name");
        this.spdxId = jsonObject.getString("spdx_id");
        this.key = jsonObject.getString("key");
        this.url = jsonObject.getString("url");
        this.nodeId = jsonObject.getString("node_id");
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getSpdxId() {
        return spdxId;
    }
}
