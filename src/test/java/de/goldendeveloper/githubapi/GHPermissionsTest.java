package de.goldendeveloper.githubapi;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GHPermissionsTest {

    @Test
    public void shouldReturnCorrectPullPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pull", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isPull());
    }

    @Test
    public void shouldReturnCorrectMaintainPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("maintain", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isMaintain());
    }

    @Test
    public void shouldReturnCorrectAdminPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("admin", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isAdmin());
    }

    @Test
    public void shouldReturnCorrectTriagePermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("triage", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isTriage());
    }

    @Test
    public void shouldReturnCorrectPushPermission() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("push", true);
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertTrue(ghPermissions.isPush());
    }

    @Test
    public void shouldReturnFalseWhenPullPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isPull());
    }

    @Test
    public void shouldReturnFalseWhenMaintainPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isMaintain());
    }

    @Test
    public void shouldReturnFalseWhenAdminPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isAdmin());
    }

    @Test
    public void shouldReturnFalseWhenTriagePermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isTriage());
    }

    @Test
    public void shouldReturnFalseWhenPushPermissionIsNotPresent() {
        JSONObject jsonObject = new JSONObject();
        GHPermissions ghPermissions = new GHPermissions(jsonObject);
        assertFalse(ghPermissions.isPush());
    }
}