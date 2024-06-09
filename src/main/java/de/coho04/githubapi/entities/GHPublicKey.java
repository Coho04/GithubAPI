package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub Public Key.
 * It provides methods for fetching data about the public key such as its key id and key.
 */
public class GHPublicKey extends GHBase {

    private final String keyId;
    private final String key;

    /**
     * Constructs a new GHPublicKey instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the public key data
     */
    public GHPublicKey(JSONObject jsonObject) {
        this.keyId = getStringOrNull(jsonObject, "key_id");
        this.key = getStringOrNull(jsonObject, "key");
    }

    /**
     * Returns the key of the public key.
     *
     * @return the key of the public key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the key id of the public key.
     *
     * @return the key id of the public key
     */
    public String getKeyId() {
        return keyId;
    }
}