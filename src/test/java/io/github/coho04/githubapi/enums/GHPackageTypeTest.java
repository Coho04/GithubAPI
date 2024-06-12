package io.github.coho04.githubapi.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GHPackageTypeTest {

    @Test
    void shouldReturnCorrectStringForContainer() {
        GHPackageType ghPackageType = GHPackageType.CONTAINER;
        assertEquals("CONTAINER", ghPackageType.toString());
    }


    @Test
    void shouldReturnCorrectStringForDocker() {
        GHPackageType ghPackageType = GHPackageType.DOCKER;
        assertEquals("DOCKER", ghPackageType.toString());
    }


    @Test
    void shouldReturnCorrectStringForNpm() {
        GHPackageType ghPackageType = GHPackageType.NPM;
        assertEquals("NPM", ghPackageType.toString());
    }


    @Test
    void shouldReturnCorrectStringForNuget() {
        GHPackageType ghPackageType = GHPackageType.NUGET;
        assertEquals("NUGET", ghPackageType.toString());
    }

    @Test
    void shouldReturnCorrectStringForRubygems() {
        GHPackageType ghPackageType = GHPackageType.RUBYGEMS;
        assertEquals("RUBYGEMS", ghPackageType.toString());
    }

    @Test
    void shouldReturnCorrectStringForMaven() {
        GHPackageType ghPackageType = GHPackageType.MAVEN;
        assertEquals("MAVEN", ghPackageType.toString());
    }

    @Test
    void shouldReturnCorrectStringForUrlMethod() {
        GHPackageType ghPackageType = GHPackageType.MAVEN;
        assertEquals("maven", ghPackageType.toURL());
    }
}