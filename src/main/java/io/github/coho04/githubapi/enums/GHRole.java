package io.github.coho04.githubapi.enums;

/**
 * Enum representing the different roles a GitHub user can have.
 * The roles include ADMIN, DIRECT_MEMBER, BILLING_MANAGER, and REINSTATE.
 */
public enum GHRole {

    /**
     * Represents an admin role.
     */
    ADMIN,

    /**
     * Represents a direct member role.
     */
    DIRECT_MEMBER,

    /**
     * Represents a billing manager role.
     */
    BILLING_MANAGER,

    /**
     * Represents a reinstate role.
     */
    REINSTATE;

    /**
     * Converts the enum value to a lowercase string.
     *
     * @return The lowercase string representation of the enum value.
     */
    public String toString() {
        return this.name().toLowerCase();
    }
}