package de.coho04.githubapi.enums;

public enum GHRole {

    ADMIN, DIRECT_MEMBER, BILLING_MANAGER, REINSTATE;

    public String toString() {
        return this.name().toLowerCase();
    }
}
