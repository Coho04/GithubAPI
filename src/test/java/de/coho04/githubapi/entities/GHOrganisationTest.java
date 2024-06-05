package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHOrganisationTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        JSONObject plan = new JSONObject();
        plan.put("name", "Test Plan");
        plan.put("space", 100);
        plan.put("private_repos", 10);
        plan.put("seats", 5);
        plan.put("filled_seats", 50);
        jsonObject = new JSONObject();
        jsonObject.put("name", "Organisation1");
        jsonObject.put("plan", plan);
        jsonObject.put("description", "This is a test organisation");
    }

    @Test
    void shouldReturnCorrectName() {
        assertEquals("Organisation1", new GHOrganisation(null, jsonObject, "Organisation1").getName());
    }

    @Test
    void shouldReturnNullWhenNameIsNotPresent() {
        jsonObject.remove("name");
        assertNull(new GHOrganisation(null, jsonObject, "Organisation1").getName());
    }

    @Test
    void shouldReturnCorrectDescription() {
        assertEquals("This is a test organisation", new GHOrganisation(null, jsonObject, "Organisation1").getDescription());
    }

    @Test
    void shouldReturnNullWhenDescriptionIsNotPresent() {
        jsonObject.remove("description");
        assertNull(new GHOrganisation(null, jsonObject, "Organisation1").getDescription());
    }

    // Similar tests can be written for the remaining fields: company, blog, location, email, twitterUsername, isVerified, hasOrganizationProjects, hasRepositoryProjects, publicRepos, publicGists, followers, following, createdAt, updatedAt, archivedAt, totalPrivateRepos, ownedPrivateRepos, privateGists, diskUsage, collaborators, billingEmail, defaultRepositoryPermission, membersCanCreateRepositories, twoFactorRequirementEnabled, membersAllowedRepositoryCreationType, membersCanCreatePublicRepositories, membersCanCreatePrivateRepositories, membersCanCreateInternalRepositories, membersCanCreatePages, membersCanForkPrivateRepositories, webCommitSignoffRequired, membersCanCreatePublicPages, membersCanCreatePrivatePages, advancedSecurityEnabledForNewRepositories, dependabotAlertsEnabledForNewRepositories, dependabotSecurityUpdatesEnabledForNewRepositories, dependencyGraphEnabledForNewRepositories, secretScanningEnabledForNewRepositories, secretScanningPushProtectionEnabledForNewRepositories, secretScanningPushProtectionCustomLinkEnabled, secretScanningPushProtectionCustomLink, secretScanningValidityChecksEnabled
}