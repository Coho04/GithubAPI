package de.coho04.githubapi;

import de.coho04.githubapi.entities.GHPermission;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GHPermissionsTest {

    @Test
    void shouldReturnCorrectPullPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pull", true);
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertTrue(ghPermission.isPull());
    }

    @Test
    void shouldReturnCorrectMaintainPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("maintain", true);
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertTrue(ghPermission.isMaintain());
    }

    @Test
    void shouldReturnCorrectAdminPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("admin", true);
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertTrue(ghPermission.isAdmin());
    }

    @Test
    void shouldReturnCorrectTriagePermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("triage", true);
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertTrue(ghPermission.isTriage());
    }

    @Test
    void shouldReturnCorrectPushPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("push", true);
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertTrue(ghPermission.isPush());
    }

    @Test
    void shouldReturnFalseWhenPullPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertFalse(ghPermission.isPull());
    }

    @Test
    void shouldReturnFalseWhenMaintainPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertFalse(ghPermission.isMaintain());
    }

    @Test
    void shouldReturnFalseWhenAdminPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertFalse(ghPermission.isAdmin());
    }

    @Test
    void shouldReturnFalseWhenTriagePermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertFalse(ghPermission.isTriage());
    }

    @Test
    void shouldReturnFalseWhenPushPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermission ghPermission = new GHPermission(jsonObject);
        assertFalse(ghPermission.isPush());
    }
}