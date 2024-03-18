package de.goldendeveloper.githubapi;

import de.goldendeveloper.githubapi.interfaces.HttpRequestInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class GHOrganisation implements HttpRequestInterface {

    private Github github;
    private String givenName;

    private String login;
    private int id;
    private String nodeId;
    private String url;
    private String reposUrl;
    private String eventsUrl;
    private String hooksUrl;
    private String issuesUrl;
    private String membersUrl;
    private String publicMembersUrl;
    private String avatarUrl;
    private String description;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String twitterUsername;
    private boolean isVerified;
    private boolean hasOrganizationProjects;
    private boolean hasRepositoryProjects;
    private int publicRepos;
    private int publicGists;
    private int followers;
    private int following;
    private String htmlUrl;
    private String createdAt;
    private String updatedAt;
    private String archivedAt;
    private String type;

    public int totalPrivateRepos;
    public int ownedPrivateRepos;
    public int privateGists;
    public long diskUsage;
    public int collaborators;
    public String billingEmail;
    public String defaultRepositoryPermission;
    public boolean membersCanCreateRepositories;
    public boolean twoFactorRequirementEnabled;
    public String membersAllowedRepositoryCreationType;
    public boolean membersCanCreatePublicRepositories;
    public boolean membersCanCreatePrivateRepositories;
    public boolean membersCanCreateInternalRepositories;
    public boolean membersCanCreatePages;
    public boolean membersCanForkPrivateRepositories;
    public boolean webCommitSignoffRequired;
    public boolean membersCanCreatePublicPages;
    public boolean membersCanCreatePrivatePages;

    private GHPlan plan;

    private boolean advancedSecurityEnabledForNewRepositories;
    private boolean dependabotAlertsEnabledForNewRepositories;
    private boolean dependabotSecurityUpdatesEnabledForNewRepositories;
    private boolean dependencyGraphEnabledForNewRepositories;
    private boolean secretScanningEnabledForNewRepositories;
    private boolean secretScanningPushProtectionEnabledForNewRepositories;
    private boolean secretScanningPushProtectionCustomLinkEnabled;
    private String secretScanningPushProtectionCustomLink;
    private boolean secretScanningValidityChecksEnabled;

    public GHOrganisation(Github github, String name) {
        this.github = github;
        this.givenName = name;
        String response = sendGetRequest("https://api.github.com/orgs/" + name,  github.getToken());
        JSONObject json = new JSONObject(response);
        convertJsonToOrganisation(json);
    }

    private void convertJsonToOrganisation(JSONObject json) {
//        System.out.println(json.get("company"));

        this.login = json.getString("login");
        this.id = json.getInt("id");
        this.nodeId = json.getString("node_id");
        this.url = json.getString("url");
        this.reposUrl = json.getString("repos_url");
        this.eventsUrl = json.getString("events_url");
        this.hooksUrl = json.getString("hooks_url");
        this.issuesUrl = json.getString("issues_url");
        this.membersUrl = json.getString("members_url");
        this.publicMembersUrl = json.getString("public_members_url");
        this.avatarUrl = json.getString("avatar_url");
        this.description = json.getString("description");
        this.name = json.getString("name");
//        this.company = json.getString("company");
        this.blog = json.getString("blog");
        this.location = json.getString("location");
        this.email = json.getString("email");
//       this.twitterUsername = json.getString("twitter_username");
        this.isVerified = json.getBoolean("is_verified");
        this.hasOrganizationProjects = json.getBoolean("has_organization_projects");
        this.hasRepositoryProjects = json.getBoolean("has_repository_projects");
        this.publicRepos = json.getInt("public_repos");
        this.publicGists = json.getInt("public_gists");
        this.followers = json.getInt("followers");
        this.following = json.getInt("following");
        this.htmlUrl = json.getString("html_url");
        this.createdAt = json.getString("created_at");
        this.updatedAt = json.getString("updated_at");
//        this.archivedAt = json.getString("archived_at");
        this.type = json.getString("type");
        this.totalPrivateRepos = json.getInt("total_private_repos");
        this.ownedPrivateRepos = json.getInt("owned_private_repos");
        this.privateGists = json.getInt("private_gists");
        this.diskUsage = json.getInt("disk_usage");
        this.collaborators = json.getInt("collaborators");
        this.billingEmail = json.getString("billing_email");
        this.defaultRepositoryPermission = json.getString("default_repository_permission");
        this.membersCanCreateRepositories = json.getBoolean("members_can_create_repositories");
        this.twoFactorRequirementEnabled = json.getBoolean("two_factor_requirement_enabled");
        this.membersAllowedRepositoryCreationType = json.getString("members_allowed_repository_creation_type");
        this.membersCanCreatePublicRepositories = json.getBoolean("members_can_create_public_repositories");
        this.membersCanCreatePrivateRepositories = json.getBoolean("members_can_create_private_repositories");
        this.membersCanCreateInternalRepositories = json.getBoolean("members_can_create_internal_repositories");
        this.membersCanCreatePages = json.getBoolean("members_can_create_pages");
        this.membersCanForkPrivateRepositories = json.getBoolean("members_can_fork_private_repositories");
        this.webCommitSignoffRequired = json.getBoolean("web_commit_signoff_required");
        this.membersCanCreatePublicPages = json.getBoolean("members_can_create_public_pages");
        this.membersCanCreatePrivatePages= json.getBoolean("members_can_create_private_pages");
        this.plan = new GHPlan(json.getJSONObject("plan"));
        this.advancedSecurityEnabledForNewRepositories = json.getBoolean("advanced_security_enabled_for_new_repositories");
        this.dependabotAlertsEnabledForNewRepositories = json.getBoolean("dependabot_alerts_enabled_for_new_repositories");
        this.dependabotSecurityUpdatesEnabledForNewRepositories = json.getBoolean("dependabot_security_updates_enabled_for_new_repositories");
        this.dependencyGraphEnabledForNewRepositories = json.getBoolean("dependency_graph_enabled_for_new_repositories");
        this.secretScanningEnabledForNewRepositories = json.getBoolean("secret_scanning_enabled_for_new_repositories");
        this.secretScanningPushProtectionEnabledForNewRepositories = json.getBoolean("secret_scanning_push_protection_enabled_for_new_repositories");
        this.secretScanningPushProtectionCustomLinkEnabled = json.getBoolean("secret_scanning_push_protection_custom_link_enabled");
//        this.secretScanningPushProtectionCustomLink = json.getString("secret_scanning_push_protection_custom_link");
        this.secretScanningValidityChecksEnabled = json.getBoolean("secret_scanning_validity_checks_enabled");
    }

    public List<GHRepository> getRepositories() {
        String response = sendGetRequest("https://api.github.com/orgs/" + this.givenName + "/repos",  github.getToken());
        JSONArray json = new JSONArray(response);
        List<GHRepository> repositories = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            JSONObject repoJson = json.getJSONObject(i);
            repositories.add(new GHRepository(repoJson, github));
        }
        return repositories;
    }


    public String getNodeId() {
        return nodeId;
    }

    public String getName() {
        return name;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
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

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getType() {
        return type;
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

    public String getArchivedAt() {
        return archivedAt;
    }

    public String getLocation() {
        return location;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUrl() {
        return url;
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
}
