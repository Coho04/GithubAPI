package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.EntityBase;
import de.coho04.githubapi.enums.GHPackageType;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.entities.repositories.GHRepository;
import de.coho04.githubapi.Github;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a GitHub organization.
 * This class provides methods and properties to access information about an organization on GitHub.
 */
public class GHOrganisation extends EntityBase {

    private final Github github;
    private GHPlan plan;
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

    /**
     * Constructs a new GHOrganisation instance with the provided GitHub instance, JSON object, and name.
     *
     * @param github     the GitHub instance
     * @param jsonObject the JSON object containing the organization data
     * @param name       the name of the organization
     */
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
        if (jsonObject.has("plan") && !jsonObject.isNull("plan")) {
            this.plan = new GHPlan(jsonObject.getJSONObject("plan"));
        }
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

    /**
     * Fetches a GitHub organization by its name.
     *
     * @param github the GitHub instance
     * @param name   the name of the organization to fetch
     * @return a GHOrganisation instance representing the fetched organization
     */
    public static GHOrganisation getOrganisation(Github github, String name) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/orgs/" + name, github.getToken());
        assert response != null;
        return new GHOrganisation(github, new JSONObject(response), name);
    }

    /**
     * Finds a repository by its name within the organization.
     *
     * @param name the name of the repository to find
     * @return a GHRepository instance representing the found repository
     */
    public GHRepository findRepositoryByName(String name) {
        String url = getBaseUrl() + "/repos/" + this.givenName + "/" + name;
        return new GHRepository(new JSONObject(sendGetRequest(url, github.getToken())), github);
    }

    /**
     * Returns a list of repositories within the organization.
     *
     * @return a list of GHRepository instances
     */
    public List<GHRepository> getRepositories() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/repos", json -> new GHRepository(json, github), github.getToken());
    }

    /**
     * Returns the name of the organization.
     *
     * @return the name of the organization
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the organization.
     *
     * @return the description of the organization
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the GitHub instance associated with this organization.
     *
     * @return the GitHub instance
     */
    public Github getGithub() {
        return github;
    }

    /**
     * Returns the company associated with the organization.
     *
     * @return the company associated with the organization
     */
    public String getCompany() {
        return company;
    }

    /**
     * Returns the URL for fetching hooks of the organization.
     *
     * @return the hooks URL
     */
    public String getHooksUrl() {
        return hooksUrl;
    }

    /**
     * Returns the URL for fetching issues of the organization.
     *
     * @return the issues URL
     */
    public String getIssuesUrl() {
        return issuesUrl;
    }

    /**
     * Returns the URL for fetching members of the organization.
     *
     * @return the members URL
     */
    public String getMembersUrl() {
        return membersUrl;
    }

    /**
     * Returns the URL for fetching public members of the organization.
     *
     * @return the public members URL
     */
    public String getPublicMembersUrl() {
        return publicMembersUrl;
    }

    /**
     * Returns the number of followers of the organization.
     *
     * @return the number of followers
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * Returns the blog URL of the organization.
     *
     * @return the blog URL
     */
    public String getBlog() {
        return blog;
    }

    /**
     * Returns the number of following of the organization.
     *
     * @return the number of following
     */
    public int getFollowing() {
        return following;
    }

    /**
     * Returns the number of public gists of the organization.
     *
     * @return the number of public gists
     */
    public int getPublicGists() {
        return publicGists;
    }

    /**
     * Returns the number of public repositories of the organization.
     *
     * @return the number of public repositories
     */
    public int getPublicRepos() {
        return publicRepos;
    }

    /**
     * Returns the email of the organization.
     *
     * @return the email of the organization
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the date and time when the organization was archived.
     *
     * @return the archived date and time
     */
    public OffsetDateTime getArchivedAt() {
        return archivedAt;
    }

    /**
     * Returns the location of the organization.
     *
     * @return the location of the organization
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the creation date and time of the organization.
     *
     * @return the creation date and time
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the Twitter username of the organization.
     *
     * @return the Twitter username
     */
    public String getTwitterUsername() {
        return twitterUsername;
    }

    /**
     * Returns the last updated date and time of the organization.
     *
     * @return the last updated date and time
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns whether the organization has organization projects enabled.
     *
     * @return true if organization projects are enabled, false otherwise
     */
    public boolean isHasOrganizationProjects() {
        return hasOrganizationProjects;
    }

    /**
     * Returns whether the organization has repository projects enabled.
     *
     * @return true if repository projects are enabled, false otherwise
     */
    public boolean isHasRepositoryProjects() {
        return hasRepositoryProjects;
    }

    /**
     * Returns whether the organization is verified.
     *
     * @return true if the organization is verified, false otherwise
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * Returns the plan associated with the organization.
     *
     * @return the plan associated with the organization
     */
    public GHPlan getPlan() {
        return plan;
    }

    /**
     * Returns the number of collaborators in the organization.
     *
     * @return the number of collaborators
     */
    public int getCollaborators() {
        return collaborators;
    }

    /**
     * Returns the number of owned private repositories in the organization.
     *
     * @return the number of owned private repositories
     */
    public int getOwnedPrivateRepos() {
        return ownedPrivateRepos;
    }

    /**
     * Returns the number of private gists in the organization.
     *
     * @return the number of private gists
     */
    public int getPrivateGists() {
        return privateGists;
    }

    /**
     * Returns the total number of private repositories in the organization.
     *
     * @return the total number of private repositories
     */
    public int getTotalPrivateRepos() {
        return totalPrivateRepos;
    }

    /**
     * Returns the disk usage of the organization.
     *
     * @return the disk usage
     */
    public long getDiskUsage() {
        return diskUsage;
    }

    /**
     * Returns the billing email of the organization.
     *
     * @return the billing email
     */
    public String getBillingEmail() {
        return billingEmail;
    }

    /**
     * Returns the default repository permission for the organization.
     *
     * @return the default repository permission
     */
    public String getDefaultRepositoryPermission() {
        return defaultRepositoryPermission;
    }

    /**
     * Returns the allowed repository creation type for members of the organization.
     *
     * @return the allowed repository creation type for members
     */
    public String getMembersAllowedRepositoryCreationType() {
        return membersAllowedRepositoryCreationType;
    }

    /**
     * Returns the custom link for secret scanning push protection.
     *
     * @return the custom link for secret scanning push protection
     */
    public String getSecretScanningPushProtectionCustomLink() {
        return secretScanningPushProtectionCustomLink;
    }

    /**
     * Returns whether advanced security is enabled for new repositories in the organization.
     *
     * @return true if advanced security is enabled for new repositories, false otherwise
     */
    public boolean isAdvancedSecurityEnabledForNewRepositories() {
        return advancedSecurityEnabledForNewRepositories;
    }

    /**
     * Returns whether Dependabot alerts are enabled for new repositories in the organization.
     *
     * @return true if Dependabot alerts are enabled for new repositories, false otherwise
     */
    public boolean isDependabotAlertsEnabledForNewRepositories() {
        return dependabotAlertsEnabledForNewRepositories;
    }

    /**
     * Returns whether Dependabot security updates are enabled for new repositories in the organization.
     *
     * @return true if Dependabot security updates are enabled for new repositories, false otherwise
     */
    public boolean isDependabotSecurityUpdatesEnabledForNewRepositories() {
        return dependabotSecurityUpdatesEnabledForNewRepositories;
    }

    /**
     * Returns whether the dependency graph is enabled for new repositories in the organization.
     *
     * @return true if the dependency graph is enabled for new repositories, false otherwise
     */
    public boolean isDependencyGraphEnabledForNewRepositories() {
        return dependencyGraphEnabledForNewRepositories;
    }

    /**
     * Returns whether members can create internal repositories in the organization.
     *
     * @return true if members can create internal repositories, false otherwise
     */
    public boolean isMembersCanCreateInternalRepositories() {
        return membersCanCreateInternalRepositories;
    }

    /**
     * Returns whether members can create pages in the organization.
     *
     * @return true if members can create pages, false otherwise
     */
    public boolean isMembersCanCreatePages() {
        return membersCanCreatePages;
    }

    /**
     * Returns whether members can create private pages in the organization.
     *
     * @return true if members can create private pages, false otherwise
     */
    public boolean isMembersCanCreatePrivatePages() {
        return membersCanCreatePrivatePages;
    }

    /**
     * Returns whether members can create private repositories in the organization.
     *
     * @return true if members can create private repositories, false otherwise
     */
    public boolean isMembersCanCreatePrivateRepositories() {
        return membersCanCreatePrivateRepositories;
    }

    /**
     * Returns whether members can create public pages in the organization.
     *
     * @return true if members can create public pages, false otherwise
     */
    public boolean isMembersCanCreatePublicPages() {
        return membersCanCreatePublicPages;
    }

    /**
     * Returns whether members can create public repositories in the organization.
     *
     * @return true if members can create public repositories, false otherwise
     */
    public boolean isMembersCanCreatePublicRepositories() {
        return membersCanCreatePublicRepositories;
    }

    /**
     * Returns whether members can create repositories in the organization.
     *
     * @return true if members can create repositories, false otherwise
     */
    public boolean isMembersCanCreateRepositories() {
        return membersCanCreateRepositories;
    }

    /**
     * Returns whether members can fork private repositories in the organization.
     *
     * @return true if members can fork private repositories, false otherwise
     */
    public boolean isMembersCanForkPrivateRepositories() {
        return membersCanForkPrivateRepositories;
    }

    /**
     * Returns whether secret scanning is enabled for new repositories in the organization.
     *
     * @return true if secret scanning is enabled for new repositories, false otherwise
     */
    public boolean isSecretScanningEnabledForNewRepositories() {
        return secretScanningEnabledForNewRepositories;
    }

    /**
     * Returns whether the custom link for secret scanning push protection is enabled.
     *
     * @return true if the custom link for secret scanning push protection is enabled, false otherwise
     */
    public boolean isSecretScanningPushProtectionCustomLinkEnabled() {
        return secretScanningPushProtectionCustomLinkEnabled;
    }

    /**
     * Returns whether secret scanning push protection is enabled for new repositories in the organization.
     *
     * @return true if secret scanning push protection is enabled for new repositories, false otherwise
     */
    public boolean isSecretScanningPushProtectionEnabledForNewRepositories() {
        return secretScanningPushProtectionEnabledForNewRepositories;
    }

    /**
     * Returns whether secret scanning validity checks are enabled.
     *
     * @return true if secret scanning validity checks are enabled, false otherwise
     */
    public boolean isSecretScanningValidityChecksEnabled() {
        return secretScanningValidityChecksEnabled;
    }

    /**
     * Returns whether two-factor authentication is required for members of the organization.
     *
     * @return true if two-factor authentication is required, false otherwise
     */
    public boolean isTwoFactorRequirementEnabled() {
        return twoFactorRequirementEnabled;
    }

    /**
     * Returns whether web commit signoff is required for the organization.
     *
     * @return true if web commit signoff is required, false otherwise
     */
    public boolean isWebCommitSignoffRequired() {
        return webCommitSignoffRequired;
    }

    /**
     * Returns the given name of the organization.
     *
     * @return the given name of the organization
     */
    public String getGivenName() {
        return givenName.replace(" ", "-");
    }

    /**
     * Converts this organization instance to a JSONObject.
     *
     * @return a JSONObject representation of this organization
     */
    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("plan", plan.toJSONObject())
                .put("name", name)
                .put("description", description)
                .put("company", company)
                .put("blog", blog)
                .put("location", location)
                .put("email", email)
                .put("hooks_url", hooksUrl)
                .put("members_url", membersUrl)
                .put("issues_url", issuesUrl)
                .put("public_members_url", publicMembersUrl)
                .put("twitter_username", twitterUsername)
                .put("is_verified", isVerified)
                .put("has_organization_projects", hasOrganizationProjects)
                .put("has_repository_projects", hasRepositoryProjects)
                .put("public_repos", publicRepos)
                .put("public_gists", publicGists)
                .put("followers", followers)
                .put("following", following)
                .put("created_at", createdAt)
                .put("updated_at", updatedAt)
                .put("archived_at", archivedAt)
                .put("total_private_repos", totalPrivateRepos)
                .put("owned_private_repos", ownedPrivateRepos)
                .put("private_gists", privateGists)
                .put("disk_usage", diskUsage)
                .put("collaborators", collaborators)
                .put("billing_email", billingEmail)
                .put("default_repository_permission", defaultRepositoryPermission)
                .put("members_can_create_repositories", membersCanCreateRepositories)
                .put("two_factor_requirement_enabled", twoFactorRequirementEnabled)
                .put("members_allowed_repository_creation_type", membersAllowedRepositoryCreationType)
                .put("members_can_create_public_repositories", membersCanCreatePublicRepositories)
                .put("members_can_create_private_repositories", membersCanCreatePrivateRepositories)
                .put("members_can_create_internal_repositories", membersCanCreateInternalRepositories)
                .put("members_can_create_pages", membersCanCreatePages)
                .put("members_can_fork_private_repositories", membersCanForkPrivateRepositories)
                .put("web_commit_signoff_required", webCommitSignoffRequired)
                .put("members_can_create_public_pages", membersCanCreatePublicPages)
                .put("members_can_create_private_pages", membersCanCreatePrivatePages)
                .put("advanced_security_enabled_for_new_repositories", advancedSecurityEnabledForNewRepositories)
                .put("dependabot_alerts_enabled_for_new_repositories", dependabotAlertsEnabledForNewRepositories)
                .put("dependabot_security_updates_enabled_for_new_repositories", dependabotSecurityUpdatesEnabledForNewRepositories)
                .put("dependency_graph_enabled_for_new_repositories", dependencyGraphEnabledForNewRepositories)
                .put("secret_scanning_enabled_for_new_repositories", secretScanningEnabledForNewRepositories)
                .put("secret_scanning_push_protection_enabled_for_new_repositories", secretScanningPushProtectionEnabledForNewRepositories)
                .put("secret_scanning_push_protection_custom_link_enabled", secretScanningPushProtectionCustomLinkEnabled)
                .put("secret_scanning_push_protection_custom_link", secretScanningPushProtectionCustomLink)
                .put("secret_scanning_validity_checks_enabled", secretScanningValidityChecksEnabled);
    }

    /**
     * Returns a list of teams within the organization.
     *
     * @return a list of GHTeam instances
     */
    public List<GHTeam> getTeams() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/teams", GHTeam::new, github.getToken());
    }

    /**
     * Returns a list of teams within the organization.
     *
     * @return a list of GHTeam instances
     */
    public List<GHTeam> listTeams() {
        return getTeams();
    }

    /**
     * Fetches a team within the organization by its ID.
     *
     * @param id the ID of the team to fetch
     */
    public void getTeam(Long id) {
        //TODO: Implement Function
    }

    /**
     * Finds a team by its name within the organization.
     *
     * @param name the name of the team to find
     * @return a GHTeam instance representing the found team
     */
    public GHTeam findTeamByName(String name) {
        return GHTeam.getTeamByName(this.github, name, this);
    }

    /**
     * Finds a team by its slug within the organization.
     *
     * @param slug the slug of the team to find
     * @return a GHTeam instance representing the found team
     */
    public GHTeam findTeamBySlug(String slug) {
        return GHTeam.getTeamByName(this.github, slug, this);
    }

    /**
     * Checks if a user is a member of the organization.
     *
     * @param user the user to check
     * @return true if the user is a member, false otherwise
     */
    public Boolean hasMember(GHUser user) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/members/" + user.getLogin();
        return sendGetRequestWithResponseCode(url, github.getToken(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    /**
     * Adds a member to the organization.
     *
     * @param user the user to add
     */
    public void addMember(GHUser user) {
        //TODO: Implement Function
    }

    /**
     * Removes a member from the organization.
     *
     * @param user the user to remove
     */
    public void removeMember(GHUser user) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/members/" + user.getLogin();
        HttpRequestHelper.sendDeleteRequest(url, github.getToken());
    }

    /**
     * Checks if a user is a public member of the organization.
     *
     * @param user the user to check
     * @return true if the user is a public member, false otherwise
     */
    public Boolean hasPublicMember(GHUser user) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/public_members/" + user.getLogin();
        return sendGetRequestWithResponseCode(url, github.getToken(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    /**
     * Publicizes a user's membership in the organization.
     *
     * @param user the user to publicize
     */
    public void publicize(GHUser user) {
        //TODO: Implement Function
    }

    /**
     * Returns a list of members of the organization.
     *
     * @return a list of GHUser instances
     */
    public List<GHUser> listMembers() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/members", jsonObject -> new GHUser(github, jsonObject), github.getToken());
    }

    /**
     * Returns a list of public members of the organization.
     *
     * @return a list of GHUser instances
     */
    public List<GHUser> listPublicMembers() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/public_members", jsonObject -> new GHUser(github, jsonObject), github.getToken());
    }

    /**
     * Returns a list of outside collaborators of the organization.
     *
     * @return a list of GHUser instances
     */
    public List<GHUser> listOutsideCollaborators() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/outside_collaborators", jsonObject -> new GHUser(github, jsonObject), github.getToken());
    }

    /**
     * Lists members of the organization with a specific role.
     *
     * @param role the role to filter members by
     */
    public void listMembersWithRole(String role) {
        //TODO: Implement Function
    }

    /**
     * Checks if organization projects are enabled.
     */
    public void isOrganisationProjectsEnabled() {
        //TODO: Implement Function
    }

    /**
     * Enables organization projects.
     */
    public void enableOrganisationProjects() {
        //TODO: Implement Function
    }

    /**
     * Returns a list of projects in the organization.
     *
     * @return a list of GHProject instances
     */
    public List<GHProject> listProjects() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/projects", json -> new GHProject(github, json), github.getToken());
    }

    /**
     * Creates a project in the organization.
     */
    public void createProject() {
        //TODO: Implement Function
    }

    /**
     * Creates a team in the organization.
     *
     * @param name the name of the team to create
     */
    public void createTeam(String name) {
        //TODO: Implement Function
    }

    /**
     * Returns a list of repositories with open pull requests in the organization.
     *
     * @return a list of GHRepository instances
     */
    public List<GHRepository> getRepositorysWithOpenPullRequests() {
        return getRepositories().stream().filter(repo -> repo.hasPullRequestsWithState(GHState.OPEN)).toList();
    }

    /**
     * Lists pull requests in the organization.
     */
    public void getPullRequests() {
        //TODO: Implement Function
    }

    /**
     * Returns a list of events in the organization.
     *
     * @return a list of GHEvent instances
     */
    public List<GHEvent> listEvents() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/events", GHEvent::new, github.getToken());
    }

    /**
     * Returns a list of hooks in the organization.
     *
     * @return a list of GHHook instances
     */
    public List<GHHook> getHooks() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/hooks", GHHook::new, github.getToken());
    }

    /**
     * Returns a specific hook in the organization by its ID.
     *
     * @param id the ID of the hook to fetch
     * @return a GHHook instance representing the fetched hook
     */
    public GHHook getHook(Long id) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/hooks/" + id;
        String response = sendGetRequest(url, github.getToken());
        assert response != null;
        return new GHHook(new JSONObject(response));
    }

    /**
     * Returns a specific hook in the organization by its ID.
     *
     * @param id the ID of the hook to fetch
     * @return a GHHook instance representing the fetched hook
     */
    public GHHook getHook(int id) {
        return getHook(Integer.toUnsignedLong(id));
    }

    /**
     * Creates a hook in the organization.
     */
    public void createHook() {
        //TODO: Implement Function
    }

    /**
     * Deletes a hook in the organization by its ID.
     *
     * @param id the ID of the hook to delete
     */
    public void deleteHook(Long id) {
        //TODO: Implement Function
    }

    /**
     * Returns a list of Docker conflict packages in the organization.
     *
     * @return a list of GHPackage instances
     */
    public List<GHPackage> listDockerConflictsPackages() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/docker/conflicts", json -> new GHPackage(github, json), github.getToken());
    }

    /**
     * Returns a list of packages in the organization filtered by package type.
     *
     * @param packageType the type of packages to list
     * @return a list of GHPackage instances
     */
    public List<GHPackage> listPackages(GHPackageType packageType) {
        return fetchPaginatedData("/orgs/" + this.givenName + "/packages?package_type=" + packageType.toURL(), json -> new GHPackage(github, json), github.getToken());
    }

    /**
     * Returns a specific package in the organization by its name and package type.
     *
     * @param name        the name of the package
     * @param packageType the type of the package
     * @return a GHPackage instance representing the fetched package
     */
    public GHPackage getPackage(String name, GHPackageType packageType) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/packages/" + packageType.toURL() + name;
        String response = sendGetRequest(url, github.getToken());
        assert response != null;
        return new GHPackage(github, new JSONObject(response));
    }

    /**
     * Returns a list of secret scanning alerts in the organization.
     *
     * @return a list of GHAlert instances
     */
    public List<GHAlert> listSecretScanningAlerts() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/secret-scanning/alerts", json -> new GHAlert(json, github), github.getToken());
    }

    /**
     * Returns the total active cache usage count for the organization.
     *
     * @return the total active cache usage count
     */
    public int getCacheUsageActiveCount() {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/actions/cache/usage";
        String response = sendGetRequest(url, github.getToken());
        assert response != null;
        return new JSONObject(response).getInt("total_active_caches_count");
    }

    /**
     * Returns the total active cache usage size in bytes for the organization.
     *
     * @return the total active cache usage size in bytes
     */
    public int getCacheUsageSizeInBytes() {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/actions/cache/usage";
        String response = sendGetRequest(url, github.getToken());
        assert response != null;
        return new JSONObject(response).getInt("total_active_caches_size_in_bytes");
    }

    /**
     * Returns a list of repository caches in the organization.
     *
     * @return a list of GHRepositoryCache instances
     */
    public List<GHRepositoryCache> listRepositoryCaches() {
        return fetchPaginatedData("/orgs/" + this.givenName + "/actions/actions/cache/usage-by-repository", GHRepositoryCache::new, github.getToken());
    }

    /**
     * Returns a list of secrets in the organization.
     *
     * @return a list of GHSecret instances
     */
    public List<GHSecret> listSecrets() {
        return fetchArrayData("/orgs/" + this.givenName + "/actions/secrets", GHSecret::new, github.getToken(), "secrets");
    }

    /**
     * Returns the public key for secrets in the organization.
     *
     * @return a GHPublicKey instance representing the public key
     */
    public GHPublicKey getPublicKey() {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/actions/secrets/public-key";
        String response = sendGetRequest(url, github.getToken());
        assert response != null;
        return new GHPublicKey(new JSONObject(response));
    }

    /**
     * Returns a specific secret in the organization by its name.
     *
     * @param name the name of the secret to fetch
     * @return a GHSecret instance representing the fetched secret
     */
    public GHSecret getSecret(String name) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/actions/secrets/" + name;
        String response = sendGetRequest(url, github.getToken());
        assert response != null;
        return new GHSecret(new JSONObject(response));
    }

    /**
     * Deletes a specific secret in the organization by its name.
     *
     * @param name the name of the secret to delete
     * @return true if the secret was deleted successfully, false otherwise
     */
    public boolean deleteSecret(String name) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/actions/secrets/" + name;
        return sendDeleteRequestWithResponseCode(url, github.getToken(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    /**
     * Returns a list of repositories associated with a specific secret in the organization.
     *
     * @return a list of GHRepository instances
     */
    public List<GHRepository> listSecretRepositorys() {
        return fetchArrayData("/orgs/" + this.givenName + "/actions/secrets", jsonObject -> new GHRepository(jsonObject, github), github.getToken(), "repositories");
    }

    /**
     * Deletes a repository from a specific secret in the organization by secret name and repository ID.
     *
     * @param secretName the name of the secret
     * @param repoId     the ID of the repository
     * @return true if the repository was deleted successfully, false otherwise
     */
    public boolean deleteRepositoryFromSecret(String secretName, String repoId) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/actions/secrets/" + secretName + "/repositories/" + repoId;
        return sendDeleteRequestWithResponseCode(url, github.getToken(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    /**
     * Returns a list of variables in the organization.
     *
     * @return a list of GHVariable instances
     */
    public List<GHVariable> listVariables() {
        return fetchArrayData("/orgs/" + this.givenName + "/actions/variables", GHVariable::new, github.getToken(), "variables");
    }

    /**
     * Returns a specific variable in the organization by its name.
     *
     * @param name the name of the variable to fetch
     * @return a GHVariable instance representing the fetched variable
     */
    public GHVariable getVariable(String name) {
        String url = getBaseUrl() + "/orgs/" + this.givenName + "/actions/variables/" + name;
        String response = sendGetRequest(url, github.getToken());
        assert response != null;
        return new GHVariable(new JSONObject(response));
    }

    /**
     * Returns a list of repositories associated with a specific variable in the organization.
     *
     * @param name the name of the variable
     * @return a list of GHRepository instances
     */
    public List<GHRepository> listVariableRepositorys(String name) {
        return fetchArrayData("/orgs/" + this.givenName + "/actions/variables/" + name, jsonObject -> new GHRepository(jsonObject, github), github.getToken(), "repositories");
    }
}
