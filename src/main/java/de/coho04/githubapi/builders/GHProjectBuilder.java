package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.GHProject;
import org.json.JSONObject;

public class GHProjectBuilder extends GHBase {

    private final Github github;
    private final String url;
    private String name;
    private String body;

    public GHProjectBuilder(Github github, String url) {
        this.github = github;
        this.url = url;
    }

    public GHProjectBuilder setBody(String body) {
        this.body = body;
        return this;
    }


    public GHProjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("name", name)
                .put("body", body);
    }

    public GHProject build() {
        String response = sendPostRequest(url, github.getToken(), toJSONObject());
        return new GHProject(github, new JSONObject(response));
    }
}
