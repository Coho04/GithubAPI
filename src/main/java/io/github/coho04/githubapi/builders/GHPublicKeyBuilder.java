package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.bases.GHBase;
import io.github.coho04.githubapi.entities.GHPublicKey;
import org.json.JSONObject;

/**
 * This class is a builder for creating a new GHPublicKey instance.
 * It provides methods for setting the title and key of the public key, and for building the public key.
 */
public class GHPublicKeyBuilder extends GHBase {

    private String title;
    private String key;
    private final Github github;

    /**
     * Constructs a new GHPublicKeyBuilder instance with the provided GitHub instance.
     *
     * @param github the GitHub instance associated with this public key builder
     */
    public GHPublicKeyBuilder(Github github) {
        this.github = github;
    }

    /**
     * Constructs a new GHPublicKeyBuilder instance with the provided title, key, and GitHub instance.
     *
     * @param title the title of the public key
     * @param key the key of the public key
     * @param github the GitHub instance associated with this public key builder
     */
    public GHPublicKeyBuilder(String title, String key, Github github) {
        this.title = title;
        this.key = key;
        this.github = github;
    }

    /**
     * Sets the title of the public key.
     *
     * @param title the title of the public key
     * @return this GHPublicKeyBuilder instance
     */
    public GHPublicKeyBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the key of the public key.
     *
     * @param key the key of the public key
     * @return this GHPublicKeyBuilder instance
     */
    public GHPublicKeyBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Returns the title of the public key.
     *
     * @return the title of the public key
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the GitHub instance associated with this public key builder.
     *
     * @return the GitHub instance
     */
    public Github getGithub() {
        return github;
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
     * Builds a new GHPublicKey instance with the set title and key.
     *
     * @return a new GHPublicKey instance
     */
    public GHPublicKey build() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("key", key);
        String response = sendPostRequest("https://api.github.com/user/keys", github.getToken(), jsonObject);
        return new GHPublicKey(new JSONObject(response));
    }
}