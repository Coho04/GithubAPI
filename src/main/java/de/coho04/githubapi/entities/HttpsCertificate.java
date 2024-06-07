package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * This class represents an HTTPS certificate.
 * It provides methods for fetching data about the certificate such as its state, description, domains, and expiration date.
 */
@SuppressWarnings("unused")
public class HttpsCertificate extends GHBase {

    private final String state;
    private final String description;
    private final List<String> domains;
    private final OffsetDateTime expiresAt;

    /**
     * Constructs a new HttpsCertificate instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the certificate data
     */
    public HttpsCertificate(JSONObject jsonObject) {
        this.state = getStringOrNull(jsonObject, "state");
        this.description = getStringOrNull(jsonObject, "description");
        this.domains = getJSONArrayToStringList(jsonObject, "domains");
        this.expiresAt = getLocalDateOrNull(jsonObject, "expires_at");
    }

    /**
     * Returns the state of the certificate.
     *
     * @return the state of the certificate
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the description of the certificate.
     *
     * @return the description of the certificate
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the domains of the certificate.
     *
     * @return the domains of the certificate
     */
    public List<String> getDomains() {
        return domains;
    }

    /**
     * Returns the expiration date of the certificate.
     *
     * @return the expiration date of the certificate
     */
    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }
}