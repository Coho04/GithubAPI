package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHPublicKey extends GHBase {

    public String keyId;
    public String key;

    public GHPublicKey(JSONObject jsonObject) {
        this.keyId = getStringOrNull(jsonObject, "key_id");
        this.key = getStringOrNull(jsonObject, "key");
    }

    public String getKey() {
        return key;
    }

    public String getKeyId() {
        return keyId;
    }
}
