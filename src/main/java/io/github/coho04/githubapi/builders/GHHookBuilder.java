package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.bases.GHBase;
import io.github.coho04.githubapi.entities.GHHook;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GHHookBuilder extends GHBase {

    private final Github github;
    private final String url;

    private String name;

    private boolean active;

    private String configURL;
    private String configContentType;
    private String configSecret;
    private String configInsecureSSL;
    private String configUsername;
    private String configPassword;

    private List<String> events;

    public GHHookBuilder(Github github, String url) {
        this.github = github;
        this.url = url;
        this.events = new ArrayList<>();
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("name", name)
                .put("active", active)
                .put("config", new JSONObject()
                        .put("url", configURL)
                        .put("content_type", configContentType)
                        .put("secret", configSecret)
                        .put("insecure_ssl", configInsecureSSL)
                        .put("username", configUsername)
                        .put("password", configPassword))
                .put("events", events)
                .put("active", active);
    }

    public void setConfigContentType(String configContentType) {
        this.configContentType = configContentType;
    }

    public void setConfigInsecureSSL(String configInsecureSSL) {
        this.configInsecureSSL = configInsecureSSL;
    }

    public void setConfigPassword(String configPassword) {
        this.configPassword = configPassword;
    }

    public void setConfigSecret(String configSecret) {
        this.configSecret = configSecret;
    }

    public void setConfigURL(String configURL) {
        this.configURL = configURL;
    }

    public void setConfigUsername(String configUsername) {
        this.configUsername = configUsername;
    }

    public void setEvents(List<String> events) {
        this.events = new ArrayList<>(events);
    }

    public void addEvent(String event) {
        if (this.events == null) {
            this.events = new ArrayList<>();
        }
        this.events.add(event);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Github getGithub() {
        return github;
    }

    public List<String> getEvents() {
        return events;
    }

    public String getConfigContentType() {
        return configContentType;
    }

    public String getConfigInsecureSSL() {
        return configInsecureSSL;
    }

    public String getConfigPassword() {
        return configPassword;
    }

    public String getConfigSecret() {
        return configSecret;
    }

    public String getConfigURL() {
        return configURL;
    }

    public String getConfigUsername() {
        return configUsername;
    }

    public boolean isActive() {
        return active;
    }

    public GHHook build() {
        String response = sendPostRequest(url, github.getToken(), this.toJSONObject());
        assert response != null;
        return new GHHook(new JSONObject(response));
    }
}
