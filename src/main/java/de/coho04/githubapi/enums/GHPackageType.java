package de.coho04.githubapi.enums;

public enum GHPackageType {
    MAVEN, DOCKER, NPM, RUBYGEMS, NUGET, CONTAINER;

    public String toURL() {
        return this.name().toLowerCase();
    }
}
