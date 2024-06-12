package io.github.coho04.githubapi.enums;

/**
 * This enum represents the possible states of a GitHub issue or pull request.
 * It provides methods for converting the state to a string and for creating a GHState from a string.
 */
public enum GHState {

    OPEN, CLOSED, ALL;

    /**
     * Returns the string representation of the state.
     * The string is the name of the enum constant in lower case.
     *
     * @return the string representation of the state
     */
    public String toString() {
        return this.name().toLowerCase();
    }

    /**
     * Returns the GHState that corresponds to the specified string.
     * The string is converted to upper case and then used to get the enum constant with that name.
     *
     * @param state the string
     * @return the GHState
     */
    public static GHState fromString(String state) {
        if (state == null || state.isEmpty()) {
            return null;
        }
        return GHState.valueOf(state.toUpperCase());
    }
}