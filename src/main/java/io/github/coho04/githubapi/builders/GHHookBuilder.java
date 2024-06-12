package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.bases.GHBase;
import io.github.coho04.githubapi.entities.GHHook;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for creating GitHub webhooks.
 */
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

    /**
     * Constructs a GHHookBuilder.
     *
     * @param github the GitHub instance
     * @param url    the URL for the webhook
     */
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

    /**
     * Sets the content type for the webhook config.
     *
     * @param configContentType the content type
     */
    public void setConfigContentType(String configContentType) {
        this.configContentType = configContentType;
    }

    /**
     * Sets whether SSL verification is enabled for the webhook config.
     *
     * @param configInsecureSSL the insecure SSL setting
     */
    public void setConfigInsecureSSL(String configInsecureSSL) {
        this.configInsecureSSL = configInsecureSSL;
    }

    /**
     * Sets the password for the webhook config.
     *
     * @param configPassword the password
     */
    public void setConfigPassword(String configPassword) {
        this.configPassword = configPassword;
    }

    /**
     * Sets the secret for the webhook config.
     *
     * @param configSecret the secret
     */
    public void setConfigSecret(String configSecret) {
        this.configSecret = configSecret;
    }

    /**
     * Sets the URL for the webhook config.
     *
     * @param configURL the URL
     */
    public void setConfigURL(String configURL) {
        this.configURL = configURL;
    }

    /**
     * Sets the username for the webhook config.
     *
     * @param configUsername the username
     */
    public void setConfigUsername(String configUsername) {
        this.configUsername = configUsername;
    }

    /**
     * Sets the events that trigger the webhook.
     *
     * @param events the list of events
     */
    public void setEvents(List<String> events) {
        this.events = new ArrayList<>(events);
    }

    /**
     * Adds an event that triggers the webhook.
     *
     * @param event the event to add
     */
    public void addEvent(String event) {
        this.events.add(event);
    }

    /**
     * Sets the name of the webhook.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets whether the webhook is active.
     *
     * @param active the active state
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the URL for the webhook.
     *
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the name of the webhook.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the GitHub instance.
     *
     * @return the GitHub instance
     */
    public Github getGithub() {
        return github;
    }

    /**
     * Returns the list of events that trigger the webhook.
     *
     * @return the list of events
     */
    public List<String> getEvents() {
        return events;
    }

    /**
     * Returns the content type for the webhook config.
     *
     * @return the content type
     */
    public String getConfigContentType() {
        return configContentType;
    }

    /**
     * Returns whether SSL verification is enabled for the webhook config.
     *
     * @return the insecure SSL setting
     */
    public String getConfigInsecureSSL() {
        return configInsecureSSL;
    }

    /**
     * Returns the password for the webhook config.
     *
     * @return the password
     */
    public String getConfigPassword() {
        return configPassword;
    }

    /**
     * Returns the secret for the webhook config.
     *
     * @return the secret
     */
    public String getConfigSecret() {
        return configSecret;
    }

    /**
     * Returns the URL for the webhook config.
     *
     * @return the URL
     */
    public String getConfigURL() {
        return configURL;
    }

    /**
     * Returns the username for the webhook config.
     *
     * @return the username
     */
    public String getConfigUsername() {
        return configUsername;
    }

    /**
     * Returns whether the webhook is active.
     *
     * @return the active state
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Builds and returns a GHHook object.
     *
     * @return the built GHHook
     */
    public GHHook build() {
        String response = sendPostRequest(url, github.getToken(), this.toJSONObject());
        assert response != null;
        return new GHHook(new JSONObject(response));
    }
}
