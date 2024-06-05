package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class HttpsCertificate extends GHBase {

    public String state;
    public String description;
    public List<String> domains;
    public OffsetDateTime expiresAt;

    public HttpsCertificate(JSONObject jsonObject) {
        this.state = getStringOrNull(jsonObject, "state");
        this.description = getStringOrNull(jsonObject, "description");
        this.domains = getArrayOrNull(jsonObject, "domains", JSONObject::toString);
        this.expiresAt = getLocalDateOrNull(jsonObject, "expires_at");
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDomains() {
        return domains;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }
}
