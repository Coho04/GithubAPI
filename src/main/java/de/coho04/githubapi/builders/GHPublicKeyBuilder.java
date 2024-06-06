package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.GHPublicKey;
import org.json.JSONObject;

public class GHPublicKeyBuilder extends GHBase {

    private String title;
    private String key;
    private final Github github;

    public GHPublicKeyBuilder(Github github) {
        this.github = github;
    }

    public GHPublicKeyBuilder(String title, String key, Github github) {
        this.title = title;
        this.key = key;
        this.github = github;
    }

    public GHPublicKeyBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public GHPublicKeyBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public GHPublicKey build() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("key", key);
        String response = sendPostRequest(" https://api.github.com/user/keys", github.getToken(), jsonObject);
        return new GHPublicKey(new JSONObject(response));
    }
}
