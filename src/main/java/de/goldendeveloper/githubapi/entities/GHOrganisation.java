package de.goldendeveloper.githubapi.entities;

import de.goldendeveloper.githubapi.GHPlan;
import de.goldendeveloper.githubapi.bases.EntityBase;
import de.goldendeveloper.githubapi.repositories.GHRepository;
import de.goldendeveloper.githubapi.Github;
import de.goldendeveloper.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.time.OffsetDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class GHOrganisation extends EntityBase {

    private final Github github;
    private final GHPlan plan;
    private final String name;
    private final String hooksUrl;
    private final String issuesUrl;
    private final String membersUrl;
    private final String description;
    private final String givenName;
    private final String company;
    private final String blog;
    private final String location;
    private final String email;
    private final String publicMembersUrl;
    private final String twitterUsername;
    private final boolean isVerified;
    private final boolean hasOrganizationProjects;
    private final boolean hasRepositoryProjects;
    private final int publicRepos;
    private final int publicGists;
    private final int followers;
    private final int following;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final OffsetDateTime archivedAt;
    private final int totalPrivateRepos;
    private final int ownedPrivateRepos;
    private final int privateGists;
    private final long diskUsage;
    private final int collaborators;
    private final String billingEmail;
    private final String defaultRepositoryPermission;
    private final boolean membersCanCreateRepositories;
    private final boolean twoFactorRequirementEnabled;
    private final String membersAllowedRepositoryCreationType;
    private final boolean membersCanCreatePublicRepositories;
    private final boolean membersCanCreatePrivateRepositories;
    private final boolean membersCanCreateInternalRepositories;
    private final boolean membersCanCreatePages;
    private final boolean membersCanForkPrivateRepositories;
    private final boolean webCommitSignoffRequired;
    private final boolean membersCanCreatePublicPages;
    private final boolean membersCanCreatePrivatePages;
    private final boolean advancedSecurityEnabledForNewRepositories;
    private final boolean dependabotAlertsEnabledForNewRepositories;
    private final boolean dependabotSecurityUpdatesEnabledForNewRepositories;
    private final boolean dependencyGraphEnabledForNewRepositories;
    private final boolean secretScanningEnabledForNewRepositories;
    private final boolean secretScanningPushProtectionEnabledForNewRepositories;
    private final boolean secretScanningPushProtectionCustomLinkEnabled;
    private final String secretScanningPushProtectionCustomLink;
    private final boolean secretScanningValidityChecksEnabled;

    public GHOrganisation(Github github, JSONObject jsonObject, String name) {
        super(jsonObject);
        this.github = github;
        this.givenName = name;
        this.hooksUrl = getStringOrNull(jsonObject, "hooks_url");
        this.issuesUrl = getStringOrNull(jsonObject, "issues_url");
        this.membersUrl = getStringOrNull(jsonObject, "members_url");
        this.publicMembersUrl = getStringOrNull(jsonObject, "public_members_url");
        this.description = getStringOrNull(jsonObject, "description");
        this.name = getStringOrNull(jsonObject, "name");
        this.company = getStringOrNull(jsonObject, "company");
        this.blog = getStringOrNull(jsonObject, "blog");
        this.location = getStringOrNull(jsonObject, "location");
        this.email = getStringOrNull(jsonObject, "email");
        this.twitterUsername = getStringOrNull(jsonObject, "twitter_username");
        this.isVerified = getBooleanOrNull(jsonObject, "is_verified");
        this.hasOrganizationProjects = getBooleanOrNull(jsonObject, "has_organization_projects");
        this.hasRepositoryProjects = getBooleanOrNull(jsonObject, "has_repository_projects");
        this.publicRepos = getIntOrNull(jsonObject, "public_repos");
        this.publicGists = getIntOrNull(jsonObject, "public_gists");
        this.followers = getIntOrNull(jsonObject, "followers");
        this.following = getIntOrNull(jsonObject, "following");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.archivedAt = getLocalDateOrNull(jsonObject, "archived_at");
        this.totalPrivateRepos = getIntOrNull(jsonObject, "total_private_repos");
        this.ownedPrivateRepos = getIntOrNull(jsonObject, "owned_private_repos");
        this.privateGists = getIntOrNull(jsonObject, "private_gists");
        this.diskUsage = getIntOrNull(jsonObject, "disk_usage");
        this.collaborators = getIntOrNull(jsonObject, "collaborators");
        this.billingEmail = getStringOrNull(jsonObject, "billing_email");
        this.defaultRepositoryPermission = getStringOrNull(jsonObject, "default_repository_permission");
        this.membersCanCreateRepositories = getBooleanOrNull(jsonObject, "members_can_create_repositories");
        this.twoFactorRequirementEnabled = getBooleanOrNull(jsonObject, "two_factor_requirement_enabled");
        this.membersAllowedRepositoryCreationType = getStringOrNull(jsonObject, "members_allowed_repository_creation_type");
        this.membersCanCreatePublicRepositories = getBooleanOrNull(jsonObject, "members_can_create_public_repositories");
        this.membersCanCreatePrivateRepositories = getBooleanOrNull(jsonObject, "members_can_create_private_repositories");
        this.membersCanCreateInternalRepositories = getBooleanOrNull(jsonObject, "members_can_create_internal_repositories");
        this.membersCanCreatePages = getBooleanOrNull(jsonObject, "members_can_create_pages");
        this.membersCanForkPrivateRepositories = getBooleanOrNull(jsonObject, "members_can_fork_private_repositories");
        this.webCommitSignoffRequired = getBooleanOrNull(jsonObject, "web_commit_signoff_required");
        this.membersCanCreatePublicPages = getBooleanOrNull(jsonObject, "members_can_create_public_pages");
        this.membersCanCreatePrivatePages = getBooleanOrNull(jsonObject, "members_can_create_private_pages");
        this.plan = new GHPlan(jsonObject.getJSONObject("plan"));
        this.advancedSecurityEnabledForNewRepositories = getBooleanOrNull(jsonObject, "advanced_security_enabled_for_new_repositories");
        this.dependabotAlertsEnabledForNewRepositories = getBooleanOrNull(jsonObject, "dependabot_alerts_enabled_for_new_repositories");
        this.dependabotSecurityUpdatesEnabledForNewRepositories = getBooleanOrNull(jsonObject, "dependabot_security_updates_enabled_for_new_repositories");
        this.dependencyGraphEnabledForNewRepositories = getBooleanOrNull(jsonObject, "dependency_graph_enabled_for_new_repositories");
        this.secretScanningEnabledForNewRepositories = getBooleanOrNull(jsonObject, "secret_scanning_enabled_for_new_repositories");
        this.secretScanningPushProtectionEnabledForNewRepositories = getBooleanOrNull(jsonObject, "secret_scanning_push_protection_enabled_for_new_repositories");
        this.secretScanningPushProtectionCustomLinkEnabled = getBooleanOrNull(jsonObject, "secret_scanning_push_protection_custom_link_enabled");
        this.secretScanningPushProtectionCustomLink = getStringOrNull(jsonObject, "secret_scanning_push_protection_custom_link");
        this.secretScanningValidityChecksEnabled = getBooleanOrNull(jsonObject, "secret_scanning_validity_checks_enabled");
    }

    public static GHOrganisation getOrganisation(Github github, String name) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/orgs/" + name, github.getToken());
        assert response != null;
        return new GHOrganisation(github, new JSONObject(response), name);
    }

    public GHRepository findRepositoryByName(String name) {
        String url = getBaseUrl() + "/repos/" + this.givenName + "/" + name;
        return new GHRepository(new JSONObject(sendGetRequest(url, github.getToken())), github);
    }

    public List<GHRepository> getRepositories() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/repos", json -> new GHRepository(json, github), github.getToken());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Github getGithub() {
        return github;
    }

    public String getCompany() {
        return company;
    }

    public String getHooksUrl() {
        return hooksUrl;
    }

    public String getIssuesUrl() {
        return issuesUrl;
    }

    public String getMembersUrl() {
        return membersUrl;
    }

    public String getPublicMembersUrl() {
        return publicMembersUrl;
    }

    public int getFollowers() {
        return followers;
    }

    public String getBlog() {
        return blog;
    }

    public int getFollowing() {
        return following;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getArchivedAt() {
        return archivedAt;
    }

    public String getLocation() {
        return location;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isHasOrganizationProjects() {
        return hasOrganizationProjects;
    }

    public boolean isHasRepositoryProjects() {
        return hasRepositoryProjects;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public GHPlan getPlan() {
        return plan;
    }

    public int getCollaborators() {
        return collaborators;
    }

    public int getOwnedPrivateRepos() {
        return ownedPrivateRepos;
    }

    public int getPrivateGists() {
        return privateGists;
    }

    public int getTotalPrivateRepos() {
        return totalPrivateRepos;
    }

    public long getDiskUsage() {
        return diskUsage;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public String getDefaultRepositoryPermission() {
        return defaultRepositoryPermission;
    }

    public String getMembersAllowedRepositoryCreationType() {
        return membersAllowedRepositoryCreationType;
    }

    public String getSecretScanningPushProtectionCustomLink() {
        return secretScanningPushProtectionCustomLink;
    }

    public boolean isAdvancedSecurityEnabledForNewRepositories() {
        return advancedSecurityEnabledForNewRepositories;
    }

    public boolean isDependabotAlertsEnabledForNewRepositories() {
        return dependabotAlertsEnabledForNewRepositories;
    }

    public boolean isDependabotSecurityUpdatesEnabledForNewRepositories() {
        return dependabotSecurityUpdatesEnabledForNewRepositories;
    }

    public boolean isDependencyGraphEnabledForNewRepositories() {
        return dependencyGraphEnabledForNewRepositories;
    }

    public boolean isMembersCanCreateInternalRepositories() {
        return membersCanCreateInternalRepositories;
    }

    public boolean isMembersCanCreatePages() {
        return membersCanCreatePages;
    }

    public boolean isMembersCanCreatePrivatePages() {
        return membersCanCreatePrivatePages;
    }

    public boolean isMembersCanCreatePrivateRepositories() {
        return membersCanCreatePrivateRepositories;
    }

    public boolean isMembersCanCreatePublicPages() {
        return membersCanCreatePublicPages;
    }

    public boolean isMembersCanCreatePublicRepositories() {
        return membersCanCreatePublicRepositories;
    }

    public boolean isMembersCanCreateRepositories() {
        return membersCanCreateRepositories;
    }

    public boolean isMembersCanForkPrivateRepositories() {
        return membersCanForkPrivateRepositories;
    }

    public boolean isSecretScanningEnabledForNewRepositories() {
        return secretScanningEnabledForNewRepositories;
    }

    public boolean isSecretScanningPushProtectionCustomLinkEnabled() {
        return secretScanningPushProtectionCustomLinkEnabled;
    }

    public boolean isSecretScanningPushProtectionEnabledForNewRepositories() {
        return secretScanningPushProtectionEnabledForNewRepositories;
    }

    public boolean isSecretScanningValidityChecksEnabled() {
        return secretScanningValidityChecksEnabled;
    }

    public boolean isTwoFactorRequirementEnabled() {
        return twoFactorRequirementEnabled;
    }

    public boolean isWebCommitSignoffRequired() {
        return webCommitSignoffRequired;
    }


    public String getGivenName() {
        return givenName.replace(" ", "-");
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject().put("name", name).put("description", description).put("company", company).put("blog", blog).put("location", location).put("email", email).put("twitter_username", twitterUsername).put("is_verified", isVerified).put("has_organization_projects", hasOrganizationProjects).put("has_repository_projects", hasRepositoryProjects).put("public_repos", publicRepos).put("public_gists", publicGists).put("followers", followers).put("following", following).put("created_at", createdAt).put("updated_at", updatedAt).put("archived_at", archivedAt).put("total_private_repos", totalPrivateRepos).put("owned_private_repos", ownedPrivateRepos).put("private_gists", privateGists).put("disk_usage", diskUsage).put("collaborators", collaborators).put("billing_email", billingEmail).put("default_repository_permission", defaultRepositoryPermission).put("members_can_create_repositories", membersCanCreateRepositories).put("two_factor_requirement_enabled", twoFactorRequirementEnabled).put("members_allowed_repository_creation_type", membersAllowedRepositoryCreationType).put("members_can_create_public_repositories", membersCanCreatePublicRepositories).put("members_can_create_private_repositories", membersCanCreatePrivateRepositories).put("members_can_create_internal_repositories", membersCanCreateInternalRepositories).put("members_can_create_pages", membersCanCreatePages).put("members_can_fork_private_repositories", membersCanForkPrivateRepositories).put("web_commit_signoff_required", webCommitSignoffRequired).put("members_can_create_public_pages", membersCanCreatePublicPages).put("members_can_create_private_pages", membersCanCreatePrivatePages).put("advanced_security_enabled_for_new_repositories", advancedSecurityEnabledForNewRepositories).put("dependabot_alerts_enabled_for_new_repositories", dependabotAlertsEnabledForNewRepositories).put("dependabot_security_updates_enabled_for_new_repositories", dependabotSecurityUpdatesEnabledForNewRepositories).put("dependency_graph_enabled_for_new_repositories", dependencyGraphEnabledForNewRepositories).put("secret_scanning_enabled_for_new_repositories", secretScanningEnabledForNewRepositories).put("secret_scanning_push_protection_enabled_for_new_repositories", secretScanningPushProtectionEnabledForNewRepositories).put("secret_scanning_push_protection_custom_link_enabled", secretScanningPushProtectionCustomLinkEnabled).put("secret_scanning_push_protection_custom_link", secretScanningPushProtectionCustomLink).put("secret_scanning_validity_checks_enabled", secretScanningValidityChecksEnabled);
    }

    public List<GHTeam> getTeams() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/teams", GHTeam::new, github.getToken());
    }

    public List<GHTeam> listTeams() {
        return getTeams();
    }

    public void getTeam(Long id) {
        //TODO: Implement Function
    }

    public GHTeam findTeamByName(String name) {
        return GHTeam.getTeamByName(this.github, name, this);
    }

    public GHTeam findTeamBySlug(String slug) {
        return GHTeam.getTeamByName(this.github, slug, this);
    }

    public Boolean hasMember(GHUser user) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/members/" + user.getLogin();
        return sendGetRequestWithResponseCode(url, github.getToken(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    public void addMember(GHUser user) {
        //TODO: Implement Function
    }

    public void removeMember(GHUser user) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/members/" + user.getLogin();
        HttpRequestHelper.sendDeleteRequest(url, github.getToken());
    }

    public Boolean hasPublicMember(GHUser user) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/public_members/" + user.getLogin();
        return sendGetRequestWithResponseCode(url, github.getToken(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    public void publicize(GHUser user) {
        //TODO: Implement Function
    }

    public List<GHUser> listMembers() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/members", GHUser::new, github.getToken());
    }

    public List<GHUser> listPublicMembers() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/public_members", GHUser::new, github.getToken());
    }

    public List<GHUser> listOutsideCollaborators() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/outside_collaborators", GHUser::new, github.getToken());
    }

    public void listMembersWithRole(String role) {
        //TODO: Implement Function
    }

    public void isOrganisationProjectsEnabled() {
        //TODO: Implement Function
    }

    public void enableOrganisationProjects() {
        //TODO: Implement Function
    }

    public List<GHProject> listProjects() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/projects", GHProject::new, github.getToken());
    }

    public void createProject() {
        //TODO: Implement Function
    }

    public void createTeam(String name) {
        //TODO: Implement Function
    }

    public void getRepositorysWithOpenPullRequests() {
        //TODO: Implement Function
    }

    public void getPullRequests() {
        //TODO: Implement Function
    }

    public void listEvents() {
        //TODO: Implement Function
    }

    public void getHooks() {
        //TODO: Implement Function
    }

    public void getHook(Long id) {
        //TODO: Implement Function
    }

    public void createHook() {
        //TODO: Implement Function
    }

    public void deleteHook(Long id) {
        //TODO: Implement Function
    }

    public void createWebhook() {
        //TODO: Implement Function
    }
}
