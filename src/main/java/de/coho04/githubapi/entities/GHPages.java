package de.coho04.githubapi.entities;

import de.coho04.githubapi.interfaces.JSONHelper;
import org.json.JSONObject;

import java.time.OffsetDateTime;

@SuppressWarnings("unused")
public class GHPages implements JSONHelper {

    public String url;
    public String status;
    public String cname;
    public boolean custom404;
    public String htmlUrl;
    public JSONObject source;
    public boolean isPublic;
    public OffsetDateTime pendingDomainUnverifiedAt;
    public String protectedDomainState;
    public HttpsCertificate httpsCertificate;
    public boolean httpsEnforced;

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

    public String getUrl() {
        return url;
    }

    public HttpsCertificate getHttpsCertificate() {
        return httpsCertificate;
    }

    public JSONObject getSource() {
        return source;
    }

    public OffsetDateTime getPendingDomainUnverifiedAt() {
        return pendingDomainUnverifiedAt;
    }

    public String getCname() {
        return cname;
    }

    public String getHtmlUrl() {
        return protectedDomainState;
    }

    public String getProtectedDomainState() {
        return protectedDomainState;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCustom404() {
        return custom404;
    }

    public boolean isHttpsEnforced() {
        return httpsEnforced;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
