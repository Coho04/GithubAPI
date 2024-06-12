package io.github.coho04.githubapi.enums;

/**
 * This enum represents the possible types of a GitHub package.
 * It provides a method for converting the package type to a string that can be used in a URL.
 */
public enum GHPackageType {
    MAVEN, DOCKER, NPM, RUBYGEMS, NUGET, CONTAINER;

    /**
     * Returns the string representation of the package type.
     * The string is the name of the enum constant in lower case.
     *
     * @return the string representation of the package type
     */
    public String toURL() {
        return this.name().toLowerCase();
    }
}