package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

import java.time.OffsetDateTime;

/**
 * Represents the GitHub Pages configuration for a repository.
 * This class provides methods and properties to access information about the GitHub Pages configuration.
 */
public class GHPages extends GHBase {

    private final String url;
    private final String status;
    private final String cname;
    private final boolean custom404;
    private final String htmlUrl;
    private final JSONObject source;
    private final boolean isPublic;
    private final OffsetDateTime pendingDomainUnverifiedAt;
    private final String protectedDomainState;
    private final HttpsCertificate httpsCertificate;
    private final boolean httpsEnforced;

    /**
     * Constructs a new GHPages instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the GitHub Pages data
     */
    public GHPages(JSONObject jsonObject) {
        this.url = getStringOrNull(jsonObject, "url");
        this.status = getStringOrNull(jsonObject, "status");
        this.cname = getStringOrNull(jsonObject, "cname");
        this.custom404 = getBooleanOrNull(jsonObject, "custom_404");
        this.htmlUrl = getStringOrNull(jsonObject, "html_url");
        this.source = getJSONObjectOrNull(jsonObject, "source");
        this.isPublic = getBooleanOrNull(jsonObject, "is_public");
        this.pendingDomainUnverifiedAt = getLocalDateOrNull(jsonObject, "pending_domain_unverified_at");
        this.protectedDomainState = getStringOrNull(jsonObject, "protected_domain_state");
        this.httpsCertificate = new HttpsCertificate(jsonObject.getJSONObject("https_certificate"));
        this.httpsEnforced = getBooleanOrNull(jsonObject, "https_enforced");
    }

    /**
     * Returns the URL of the GitHub Pages site.
     *
     * @return the URL of the GitHub Pages site
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the HTTPS certificate information for the GitHub Pages site.
     *
     * @return the HTTPS certificate information
     */
    public HttpsCertificate getHttpsCertificate() {
        return httpsCertificate;
    }

    /**
     * Returns the source configuration for the GitHub Pages site.
     *
     * @return the source configuration as a JSONObject
     */
    public JSONObject getSource() {
        return source;
    }

    /**
     * Returns the date and time when the domain was pending verification.
     *
     * @return the date and time when the domain was pending verification
     */
    public OffsetDateTime getPendingDomainUnverifiedAt() {
        return pendingDomainUnverifiedAt;
    }

    /**
     * Returns the CNAME for the GitHub Pages site.
     *
     * @return the CNAME for the GitHub Pages site
     */
    public String getCname() {
        return cname;
    }

    /**
     * Returns the HTML URL of the GitHub Pages site.
     *
     * @return the HTML URL of the GitHub Pages site
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Returns the protected domain state of the GitHub Pages site.
     *
     * @return the protected domain state
     */
    public String getProtectedDomainState() {
        return protectedDomainState;
    }

    /**
     * Returns the status of the GitHub Pages site.
     *
     * @return the status of the GitHub Pages site
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns whether a custom 404 page is used for the GitHub Pages site.
     *
     * @return true if a custom 404 page is used, false otherwise
     */
    public boolean isCustom404() {
        return custom404;
    }

    /**
     * Returns whether HTTPS is enforced for the GitHub Pages site.
     *
     * @return true if HTTPS is enforced, false otherwise
     */
    public boolean isHttpsEnforced() {
        return httpsEnforced;
    }

    /**
     * Returns whether the GitHub Pages site is public.
     *
     * @return true if the site is public, false otherwise
     */
    public boolean isPublic() {
        return isPublic;
    }
}
