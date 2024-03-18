package de.goldendeveloper.githubapi;

import org.json.JSONObject;

public class GHPermissions {

    private final boolean pull;
    private final boolean maintain;
    private final boolean admin;
    private final boolean triage;
    private final boolean push;

    public GHPermissions(JSONObject jsonObject) {
        this.pull = jsonObject.getBoolean("pull");
        this.maintain = jsonObject.getBoolean("maintain");
        this.admin = jsonObject.getBoolean("admin");
        this.triage = jsonObject.getBoolean("triage");
        this.push = jsonObject.getBoolean("push");
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
