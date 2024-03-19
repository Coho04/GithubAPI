package de.goldendeveloper.githubapi.repositories;

import de.goldendeveloper.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHLicense extends GHBase {

    private final String name;
    private final String spdxId;
    private final String key;
    private final String url;
    private final String nodeId;

    public GHLicense(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.spdxId = getStringOrNull(jsonObject, "spdx_id");
        this.key = getStringOrNull(jsonObject, "key");
        this.url = getStringOrNull(jsonObject, "url");
        this.nodeId = getStringOrNull(jsonObject, "node_id");
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
