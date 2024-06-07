package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GHPackageTest {

    private JSONObject jsonObject;
    private Github github;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("name", "Package1");
        jsonObject.put("package_type", "Type1");
        jsonObject.put("owner", new JSONObject().put("login", "user1"));
        jsonObject.put("version_count", "10");
        jsonObject.put("visibility", "public");
        jsonObject.put("created_at", "2022-01-01T00:00:00Z");
        jsonObject.put("updated_at", "2022-01-02T00:00:00Z");
        github = mock(Github.class);
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals("Package1", new GHPackage(github, jsonObject).getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        jsonObject.remove("name");
        assertNull(new GHPackage(github, jsonObject).getName());
    }

    @Test
    void shouldReturnCorrectPackageType() {
        assertEquals("Type1", new GHPackage(github, jsonObject).getPackageType());
    }

    @Test
    void shouldReturnNullWhenPackageTypeIsNotPresent() {
        jsonObject.remove("package_type");
        assertNull(new GHPackage(github, jsonObject).getPackageType());
    }

    @Test
    void shouldReturnCorrectVersionCount() {
        assertEquals("10", new GHPackage(github, jsonObject).getVersionCount());
    }

    @Test
    void shouldReturnNullWhenVersionCountIsNotPresent() {
        jsonObject.remove("version_count");
        assertNull(new GHPackage(github, jsonObject).getVersionCount());
    }

    @Test
    void shouldReturnCorrectVisibility() {
        assertEquals("public", new GHPackage(github, jsonObject).getVisibility());
    }

    @Test
    void shouldReturnNullWhenVisibilityIsNotPresent() {
        jsonObject.remove("visibility");
        assertNull(new GHPackage(github, jsonObject).getVisibility());
    }

    @Test
    void shouldReturnCorrectCreatedAt() {
        assertEquals("2022-01-01T00:00:00Z", new GHPackage(github, jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenCreatedAtIsNotPresent() {
        jsonObject.remove("created_at");
        assertNull(new GHPackage(github, jsonObject).getCreatedAt());
    }

    @Test
    void shouldReturnCorrectUpdatedAt() {
        assertEquals("2022-01-02T00:00:00Z", new GHPackage(github, jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenUpdatedAtIsNotPresent() {
        jsonObject.remove("updated_at");
        assertNull(new GHPackage(github, jsonObject).getUpdatedAt());
    }

    @Test
    void shouldReturnCorrectOwner() {
        assertEquals("user1", new GHPackage(github, jsonObject).getOwner().getLogin());
    }

    @Test
    void shouldReturnNullWhenOwnerIsNotPresent() {
        jsonObject.remove("owner");
        assertNull(new GHPackage(github, jsonObject).getOwner());
    }
}