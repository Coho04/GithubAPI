package de.coho04.githubapi;

import de.coho04.githubapi.entities.GHPermissions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GHPermissionsTest {

    @Test
    void shouldReturnCorrectPullPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pull", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isPull());
    }

    @Test
    void shouldReturnCorrectMaintainPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("maintain", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isMaintain());
    }

    @Test
    void shouldReturnCorrectAdminPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("admin", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isAdmin());
    }

    @Test
    void shouldReturnCorrectTriagePermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("triage", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isTriage());
    }

    @Test
    void shouldReturnCorrectPushPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("push", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isPush());
    }

    @Test
    void shouldReturnFalseWhenPullPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isPull());
    }

    @Test
    void shouldReturnFalseWhenMaintainPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isMaintain());
    }

    @Test
    void shouldReturnFalseWhenAdminPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isAdmin());
    }

    @Test
    void shouldReturnFalseWhenTriagePermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isTriage());
    }

    @Test
    void shouldReturnFalseWhenPushPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isPush());
    }
}