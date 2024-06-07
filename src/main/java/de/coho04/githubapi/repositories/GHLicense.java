package de.coho04.githubapi.repositories;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub license.
 * It provides methods for fetching data about the license such as its name, SPDX ID, key, URL, and node ID.
 */
@SuppressWarnings("unused")
public class GHLicense extends GHBase {

    private final String name;
    private final String spdxId;
    private final String key;
    private final String url;
    private final String nodeId;

    /**
     * Constructs a new GHLicense instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the license data
     */
    public GHLicense(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.spdxId = getStringOrNull(jsonObject, "spdx_id");
        this.key = getStringOrNull(jsonObject, "key");
        this.url = getStringOrNull(jsonObject, "url");
        this.nodeId = getStringOrNull(jsonObject, "node_id");
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("name", name)
                .put("spdx_id", spdxId)
                .put("key", key)
                .put("url", url)
                .put("node_id", nodeId);
    }
    /**
     * Returns the URL of the license.
     *
     * @return the URL of the license
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the name of the license.
     *
     * @return the name of the license
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the key of the license.
     *
     * @return the key of the license
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the node ID of the license.
     *
     * @return the node ID of the license
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Returns the SPDX ID of the license.
     *
     * @return the SPDX ID of the license
     */
    public String getSpdxId() {
        return spdxId;
    }
}