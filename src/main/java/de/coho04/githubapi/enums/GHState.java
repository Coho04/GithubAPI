package de.coho04.githubapi.enums;

@SuppressWarnings("unused")
public enum GHState {

    OPEN, CLOSED, ALL;

    public String toString() {
        return this.name().toLowerCase();
    }

    public static GHState fromString(String state) {
        return GHState.valueOf(state.toUpperCase());
    }
}
