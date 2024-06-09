package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * Represents the permissions for a GitHub repository.
 * This class provides methods to access the permissions associated with a repository.
 */
public class GHPermission extends GHBase {

    private final boolean pull;
    private final boolean push;
    private final boolean admin;
    private final boolean triage;
    private final boolean maintain;

    /**
     * Constructs a new GHPermission instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the permission data
     */
    public GHPermission(JSONObject jsonObject) {
        this.pull = getBooleanOrNull(jsonObject, "pull");
        this.maintain = getBooleanOrNull(jsonObject, "maintain");
        this.admin = getBooleanOrNull(jsonObject, "admin");
        this.triage = getBooleanOrNull(jsonObject, "triage");
        this.push = getBooleanOrNull(jsonObject, "push");
    }

    /**
     * Returns whether the pull permission is granted.
     *
     * @return true if the pull permission is granted, false otherwise
     */
    public boolean isPull() {
        return pull;
    }

    /**
     * Returns whether the maintain permission is granted.
     *
     * @return true if the maintain permission is granted, false otherwise
     */
    public boolean isMaintain() {
        return maintain;
    }

    /**
     * Returns whether the admin permission is granted.
     *
     * @return true if the admin permission is granted, false otherwise
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Returns whether the triage permission is granted.
     *
     * @return true if the triage permission is granted, false otherwise
     */
    public boolean isTriage() {
        return triage;
    }

    /**
     * Returns whether the push permission is granted.
     *
     * @return true if the push permission is granted, false otherwise
     */
    public boolean isPush() {
        return push;
    }
}
