package io.github.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHPermissionTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("pull", true);
        jsonObject.put("push", true);
        jsonObject.put("admin", true);
        jsonObject.put("triage", true);
        jsonObject.put("maintain", true);
    }

    @Test
    void shouldReturnCorrectPullPermission() {
        assertTrue(new GHPermission(jsonObject).isPull());
    }

    @Test
    void shouldReturnFalseWhenPullPermissionIsNotPresent() {
        jsonObject.remove("pull");
        assertFalse(new GHPermission(jsonObject).isPull());
    }

    @Test
    void shouldReturnCorrectPushPermission() {
        assertTrue(new GHPermission(jsonObject).isPush());
    }

    @Test
    void shouldReturnFalseWhenPushPermissionIsNotPresent() {
        jsonObject.remove("push");
        assertFalse(new GHPermission(jsonObject).isPush());
    }

    @Test
    void shouldReturnCorrectAdminPermission() {
        assertTrue(new GHPermission(jsonObject).isAdmin());
    }

    @Test
    void shouldReturnFalseWhenAdminPermissionIsNotPresent() {
        jsonObject.remove("admin");
        assertFalse(new GHPermission(jsonObject).isAdmin());
    }

    @Test
    void shouldReturnCorrectTriagePermission() {
        assertTrue(new GHPermission(jsonObject).isTriage());
    }

    @Test
    void shouldReturnFalseWhenTriagePermissionIsNotPresent() {
        jsonObject.remove("triage");
        assertFalse(new GHPermission(jsonObject).isTriage());
    }

    @Test
    void shouldReturnCorrectMaintainPermission() {
        assertTrue(new GHPermission(jsonObject).isMaintain());
    }

    @Test
    void shouldReturnFalseWhenMaintainPermissionIsNotPresent() {
        jsonObject.remove("maintain");
        assertFalse(new GHPermission(jsonObject).isMaintain());
    }
}