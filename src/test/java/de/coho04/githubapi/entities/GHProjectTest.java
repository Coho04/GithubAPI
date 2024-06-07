package de.coho04.githubapi.entities;

import de.coho04.githubapi.Github;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GHProjectTest {

    private JSONObject jsonObject;
    private GHProject project;
    private Github github;

    @BeforeEach
    void setUp() {
        github = mock(Github.class);

        jsonObject = new JSONObject();
        jsonObject.put("owner_url", "https://api.github.com/repos/octocat/Hello-World");
        jsonObject.put("columns_url", "https://api.github.com/projects/columns");
        jsonObject.put("name", "Project Board");
        jsonObject.put("body", "This is a project board.");
        jsonObject.put("number", 1);
        jsonObject.put("state", "open");
        jsonObject.put("creator", new JSONObject().put("login", "octocat"));
        jsonObject.put("created_at", OffsetDateTime.now().toString());
        jsonObject.put("updated_at", OffsetDateTime.now().toString());
        jsonObject.put("organization_permission", "admin");
        jsonObject.put("private", false);

        project = new GHProject(github, jsonObject);
    }

    @Test
    void testGetName() {
        assertEquals("Project Board", project.getName());
    }

    @Test
    void testGetCreator() {
        assertNotNull(project.getCreator());
        assertEquals("octocat", project.getCreator().getLogin());
    }

    @Test
    void testGetNumber() {
        assertEquals(1, project.getNumber());
    }

    @Test
    void testGetBody() {
        assertEquals("This is a project board.", project.getBody());
    }

    @Test
    void testGetColumnsUrl() {
        assertEquals("https://api.github.com/projects/columns", project.getColumnsUrl());
    }

    @Test
    void testGetCreatedAt() {
        assertNotNull(project.getCreatedAt());
    }

    @Test
    void testGetOrganizationPermission() {
        assertEquals("admin", project.getOrganizationPermission());
    }

    @Test
    void testGetOwnerUrl() {
        assertEquals("https://api.github.com/repos/octocat/Hello-World", project.getOwnerUrl());
    }

    @Test
    void testGetState() {
        assertEquals("open", project.getState());
    }

    @Test
    void testGetUpdatedAt() {
        assertNotNull(project.getUpdatedAt());
    }
}
