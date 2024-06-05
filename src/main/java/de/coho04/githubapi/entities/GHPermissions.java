package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHPermissions extends GHBase {

    private final boolean pull;
    private final boolean push;
    private final boolean admin;
    private final boolean triage;
    private final boolean maintain;

    public GHPermissions(JSONObject jsonObject) {
        this.pull = getBooleanOrNull(jsonObject, "pull");
        this.maintain = getBooleanOrNull(jsonObject, "maintain");
        this.admin = getBooleanOrNull(jsonObject, "admin");
        this.triage = getBooleanOrNull(jsonObject, "triage");
        this.push = getBooleanOrNull(jsonObject, "push");
    }

    public boolean isPull() {
        return pull;
    }

    public boolean isMaintain() {
        return maintain;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isTriage() {
        return triage;
    }

    public boolean isPush() {
        return push;
    }
}
