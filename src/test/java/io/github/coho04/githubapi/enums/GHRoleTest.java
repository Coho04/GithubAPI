package io.github.coho04.githubapi.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GHRoleTest {

    @Test
    void shouldReturnCorrectStringForAdmin() {
        GHRole ghRole = GHRole.ADMIN;
        assertEquals("admin", ghRole.toString());
    }

    @Test
    void shouldReturnCorrectStringForDirectMember() {
        GHRole ghRole = GHRole.DIRECT_MEMBER;
        assertEquals("direct_member", ghRole.toString());
    }

    @Test
    void shouldReturnCorrectStringForBillingManager() {
        GHRole ghRole = GHRole.BILLING_MANAGER;
        assertEquals("billing_manager", ghRole.toString());
    }

    @Test
    void shouldReturnCorrectStringForReinstate() {
        GHRole ghRole = GHRole.REINSTATE;
        assertEquals("reinstate", ghRole.toString());
    }
}