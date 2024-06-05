package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.repositories.GHRepository;
import org.json.JSONObject;

import java.time.OffsetDateTime;

public class GHAlert extends GHBase {

    public final Github github;
    public final int number;
    public final OffsetDateTime created_at;
    public final String url;
    public final String html_url;
    public final String locations_url;
    public final String state;
    public final String resolution;
    public final OffsetDateTime resolved_at;
    public final GHUser resolved_by;
    public final String secret_type;
    public final String secret_type_display_name;
    public final String secret;
    public final GHRepository repository;
    public final GHUser push_protection_bypassed_by;
    public final boolean push_protection_bypassed;
    public final OffsetDateTime push_protection_bypassed_at;
    public final String resolution_comment;
    public final String validity;

    public GHAlert(JSONObject jsonObject, Github github) {
        this.github = github;
        this.number = getIntOrNull(jsonObject, "number");
        this.created_at = getLocalDateOrNull(jsonObject, "created_at");
        this.url = getStringOrNull(jsonObject, "url");
        this.html_url = getStringOrNull(jsonObject, "html_url");
        this.locations_url = getStringOrNull(jsonObject, "locations_url");
        this.state = getStringOrNull(jsonObject, "state");
        this.resolution = getStringOrNull(jsonObject, "resolution");
        this.resolved_at = getLocalDateOrNull(jsonObject, "resolved_at");
        this.resolved_by = new GHUser(jsonObject.getJSONObject("resolved_by"));
        this.secret_type = getStringOrNull(jsonObject, "secret_type");
        this.secret_type_display_name = getStringOrNull(jsonObject, "secret_type_display_name");
        this.secret = getStringOrNull(jsonObject, "secret");
        this.repository = new GHRepository(jsonObject.getJSONObject("repository"), github);
        this.push_protection_bypassed_by = new GHUser(jsonObject.getJSONObject("push_protection_bypassed_by"));
        this.push_protection_bypassed = getBooleanOrNull(jsonObject, "push_protection_bypassed");
        this.push_protection_bypassed_at = getLocalDateOrNull(jsonObject, "push_protection_bypassed_at");
        this.resolution_comment = getStringOrNull(jsonObject, "resolution_comment");
        this.validity = getStringOrNull(jsonObject, "validity");
    }


}
