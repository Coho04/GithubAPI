package de.goldendeveloper.githubapi.repositories;

import de.goldendeveloper.githubapi.GHPermissions;
import de.goldendeveloper.githubapi.Github;
import de.goldendeveloper.githubapi.bases.ClassBase;
import de.goldendeveloper.githubapi.builders.GHFileBuilder;
import de.goldendeveloper.githubapi.builders.GHIssueBuilder;
import de.goldendeveloper.githubapi.entities.GHUser;
import de.goldendeveloper.githubapi.enums.GHState;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class GHRepository extends ClassBase {

    private final int size;
    private final int forks;

    private GHLicense license;
    private final String name;
    private final boolean fork;
    private final int watchers;
    private final GHUser owner;
    private final String sshUrl;
    private final String svnUrl;
    private final Github github;
    private final String keysUrl;
    private final String tagsUrl;
    private final String cloneUrl;
    private final LocalDateTime pushedAt;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String language;
    private final String fullname;
    private final String forksUrl;
    private final String treesUrl;
    private final String pullsUrl;
    private final String hooksUrl;
    private final String labelsUrl;
    private final boolean archived;
    private final boolean isPrivate;
    private final String gitRefsUrl;
    private final String visibility;
    private final String archiveUrl;
    private final String statusesUrl;
    private final boolean isTemplate;
    private final String description;
    private final String branchesUrl;
    private final String releasesUrl;
    private List<String> topics;
    private final String languagesUrl;
    private final int stargazersCount;
    private final int openIssuesCount;
    private final String defaultBranch;
    private final boolean hasDownloads;
    private final boolean allowForking;
    private final String subscribersUrl;
    private final String subscriptionUrl;
    private final String issueCommentUrl;
    private final boolean hasDiscussions;
    private final String contributorsUrl;
    private final String collaboratorsUrl;
    private final boolean hasProjects;
    private final String deploymentsUrl;
    private final boolean hasWiki;
    private final String commentsUrl;
    private final String stargazersUrl;
    private final boolean disabled;
    private final String gitUrl;
    private final boolean hasPages;
    private final String commitsUrl;
    private final String compareUrl;
    private final String gitCommitsUrl;
    private final String blobsUrl;
    private final String gitTagsUrl;
    private final String mergesUrl;
    private final String downloadsUrl;
    private final boolean hasIssues;
    private final String notificationsUrl;
    private final String contentsUrl;
    private final String mirrorUrl;
    private final String milestonesUrl;
    private final String teamsUrl;
    private final String issuesUrl;

    private final String issueEventsUrl;
    private final String assigneesUrl;
    private final int openIssues;
    private final int watchersCount;
    private final String homepage;
    private final int forksCount;
    private final GHPermissions permissions;

    private final boolean webCommitSignoffRequired;

/*            "security_and_analysis": {
        "secret_scanning_push_protection": {
            "status": "disabled"
        },
        "secret_scanning_validity_checks": {
            "status": "disabled"
        },
        "secret_scanning": {
            "status": "disabled"
        },
        "dependabot_security_updates": {
            "status": "enabled"
        }

        getReleases
getTags
getIssues
getPullRequests
getProjects
getActions
getWorkflows
getSecurity
getDependabot
getDependabotAlerts
getDependabotSecurity
getDependabotSecurityAlerts
getDependabotSecurityFixes
getDependabotSecurityFixesAlerts
getDependabotSecurityFixesPullRequests
getDependabotSecurityFixesPullRequestsAlerts
getDependabotSecurityFixesPullRequestsMerged
getDependabotSecurityFixesPullRequestsMergedAlerts
getDependabotSecurityFixesPullRequestsClosed
getDependabotSecurityFixesPullRequestsClosedAlerts
getDependabotSecurityFixesPullRequestsOpen
getDependabotSecurityFixesPullRequestsOpenAlerts
getDependabotSecurityFixesPullRequestsDraft
getDependabotSecurityFixesPullRequestsDraftAlerts
getDependabotSecurityFixesPullRequestsReady
getDependabotSecurityFixesPullRequestsReadyAlerts
getDependabotSecurityFixesPullRequestsLocked
getDependabotSecurityFixesPullRequestsLockedAlerts

//            System.out.println(ghRepository.getReadme());
//            System.out.println(ghRepository.getContributors());
//            System.out.println(ghRepository.getCommits());

getStars
getSubscribers

    },*/

    public GHRepository(JSONObject jsonObject, Github github) {
        super(jsonObject);
        this.github = github;
        this.allowForking = getBooleanOrNull(jsonObject, "allow_forking");
        this.stargazersCount = getIntOrNull(jsonObject, "stargazers_count");
        this.isTemplate = getBooleanOrNull(jsonObject, "is_template");
        this.pushedAt = getLocalDateOrNull(jsonObject, "pushed_at");
        this.subscriptionUrl = getStringOrNull(jsonObject, "subscription_url");
        this.language = getStringOrNull(jsonObject, "language");
        this.branchesUrl = getStringOrNull(jsonObject, "branches_url");
        this.issueCommentUrl = getStringOrNull(jsonObject, "issue_comment_url");
        this.labelsUrl = getStringOrNull(jsonObject, "labels_url");
        this.subscribersUrl = getStringOrNull(jsonObject, "subscribers_url");
        this.releasesUrl = getStringOrNull(jsonObject, "releases_url");
        this.svnUrl = getStringOrNull(jsonObject, "svn_url");
        this.hasDiscussions = getBooleanOrNull(jsonObject, "has_discussions");
        this.forks = getIntOrNull(jsonObject, "forks");
        this.archiveUrl = getStringOrNull(jsonObject, "archive_url");
        this.gitRefsUrl = getStringOrNull(jsonObject, "git_refs_url");
        this.forksUrl = getStringOrNull(jsonObject, "forks_url");
        this.visibility = getStringOrNull(jsonObject, "visibility");
        this.statusesUrl = getStringOrNull(jsonObject, "statuses_url");
        this.sshUrl = getStringOrNull(jsonObject, "ssh_url");
        this.fullname = getStringOrNull(jsonObject, "full_name");
        this.size = getIntOrNull(jsonObject, "size");
        this.languagesUrl = getStringOrNull(jsonObject, "languages_url");
        this.collaboratorsUrl = getStringOrNull(jsonObject, "collaborators_url");
        this.cloneUrl = getStringOrNull(jsonObject, "clone_url");
        this.name = getStringOrNull(jsonObject, "name");
        this.pullsUrl = getStringOrNull(jsonObject, "pulls_url");
        this.defaultBranch = getStringOrNull(jsonObject, "default_branch");
        this.hooksUrl = getStringOrNull(jsonObject, "hooks_url");
        this.treesUrl = getStringOrNull(jsonObject, "trees_url");
        this.tagsUrl = getStringOrNull(jsonObject, "tags_url");
        this.gitUrl = getStringOrNull(jsonObject, "git_url");
        this.isPrivate = getBooleanOrNull(jsonObject, "private");
        this.contributorsUrl = getStringOrNull(jsonObject, "contributors_url");
        this.hasDownloads = getBooleanOrNull(jsonObject, "has_downloads");
        this.notificationsUrl = getStringOrNull(jsonObject, "notifications_url");
        this.openIssuesCount = getIntOrNull(jsonObject, "open_issues_count");
        this.description = getStringOrNull(jsonObject, "description");
        this.createdAt = getLocalDateOrNull(jsonObject, "created_at");
        this.watchers = getIntOrNull(jsonObject, "watchers");
        this.keysUrl = getStringOrNull(jsonObject, "keys_url");
        this.deploymentsUrl = getStringOrNull(jsonObject, "deployments_url");
        this.hasProjects = getBooleanOrNull(jsonObject, "has_projects");
        this.archived = getBooleanOrNull(jsonObject, "archived");
        this.hasWiki = getBooleanOrNull(jsonObject, "has_wiki");
        this.updatedAt = getLocalDateOrNull(jsonObject, "updated_at");
        this.commentsUrl = getStringOrNull(jsonObject, "comments_url");
        this.disabled = getBooleanOrNull(jsonObject, "disabled");
        this.hasPages = getBooleanOrNull(jsonObject, "has_pages");
        this.stargazersUrl = getStringOrNull(jsonObject, "stargazers_url");
        this.commitsUrl = getStringOrNull(jsonObject, "commits_url");
        this.compareUrl = getStringOrNull(jsonObject, "compare_url");
        this.gitCommitsUrl = getStringOrNull(jsonObject, "git_commits_url");
        this.blobsUrl = getStringOrNull(jsonObject, "blobs_url");
        this.gitTagsUrl = getStringOrNull(jsonObject, "git_tags_url");
        this.mergesUrl = getStringOrNull(jsonObject, "merges_url");
        this.downloadsUrl = getStringOrNull(jsonObject, "downloads_url");
        this.hasIssues = getBooleanOrNull(jsonObject, "has_issues");
        this.webCommitSignoffRequired = getBooleanOrNull(jsonObject, "web_commit_signoff_required");
        this.contentsUrl = getStringOrNull(jsonObject, "contents_url");
        this.mirrorUrl = getStringOrNull(jsonObject, "mirror_url");
        this.milestonesUrl = getStringOrNull(jsonObject, "milestones_url");
        this.teamsUrl = getStringOrNull(jsonObject, "teams_url");
        this.fork = getBooleanOrNull(jsonObject, "fork");
        this.issuesUrl = getStringOrNull(jsonObject, "issues_url");
        this.issueEventsUrl = getStringOrNull(jsonObject, "issue_events_url");
        this.assigneesUrl = getStringOrNull(jsonObject, "assignees_url");
        this.openIssues = getIntOrNull(jsonObject, "open_issues");
        this.watchersCount = getIntOrNull(jsonObject, "watchers_count");
        this.homepage = getStringOrNull(jsonObject, "homepage");
        this.forksCount = getIntOrNull(jsonObject, "forks_count");
        this.owner = new GHUser(getJSONObjectOrNull(jsonObject, "owner"));
        if (jsonObject.has("license") && !jsonObject.isNull("license")) {
            this.license = new GHLicense(getJSONObjectOrNull(jsonObject, "license"));
        }
        this.permissions = new GHPermissions(getJSONObjectOrNull(jsonObject, "permissions"));
        this.topics = jsonObject.getJSONArray("topics").toList().stream().map(Object::toString).collect(Collectors.toList());
    }

    public GHIssueBuilder createIssue(String title) {
        return new GHIssueBuilder(title);
    }

    public HashMap<String, GHBranch> getBranches() {
        HashMap<String, GHBranch> branches = new HashMap<>();
        String response = sendGetRequest(getUrl() + "/branches", github.getToken());
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = getStringOrNull(jsonObject, "name");
            branches.put(name, new GHBranch(jsonObject));
        }
        return branches;
    }

    public List<GHUser> getContributors() {
        String response = sendGetRequest(getUrl() + "/contributors", github.getToken());
        List<GHUser> contributors = new ArrayList<>();
        if (response != null) {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                contributors.add(new GHUser(jsonArray.getJSONObject(i)));
            }
        }
        return contributors;
    }

    public List<GHIssue> getIssues(GHState state) {
        String response = sendGetRequest(getUrl() + "/issues", github.getToken());
        List<GHIssue> issues = new ArrayList<>();
        if (response != null) {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                issues.add(new GHIssue(jsonArray.getJSONObject(i)));
            }
        }
        return issues;
    }

    public List<GHIssue> getIssues() {
        return getIssues(GHState.ALL);
    }

    public GHFileBuilder addFile() {
        return new GHFileBuilder(this);
    }

    public GHFileBuilder addFile(GHBranch branch) {
        return new GHFileBuilder(this, branch);
    }

    public GHFileBuilder addFile(GHBranch branch, String path, String content, String message) {
        return new GHFileBuilder(this, branch, path, content, message);
    }

    public List<String> getAllFilenames() {
        List<String> filenames = new ArrayList<>();
        String url = getBaseUrl() + "/repos/" + this.owner.getLogin() + "/" + this.name + "/contents/?per_page=100";
        while (url != null) {
            String[] responseAndLink = sendGetRequestWithLinkHeader(url, github.getToken());
            String response = responseAndLink[0];
            JSONArray json = new JSONArray(response);
            for (int i = 0; i < json.length(); i++) {
                JSONObject fileJson = json.getJSONObject(i);
                if ("file".equals(fileJson.getString("type"))) {
                    filenames.add(fileJson.getString("name"));
                }
            }
            url = extractNextPageUrl(responseAndLink[1]);
        }
        return filenames;
    }


    public List<GHFile> getDirectoryContent(String path) {
        List<GHFile> files = new ArrayList<>();
        try {
            String response = sendGetRequest(getUrl() + "/contents/" + path, github.getToken());
            if (response != null) {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    files.add(new GHFile(jsonArray.getJSONObject(i)));
                }
            }
        } catch (Exception e) {
            return null;
        }
        return files;
    }

    public int getForks() {
        return forks;
    }

    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    public int getSize() {
        return size;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public Object getPermissions() {
        return permissions;
    }

    public LocalDateTime getPushedAt() {
        return pushedAt;
    }

    public String getBranchesUrl() {
        return branchesUrl;
    }

    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    public String getArchiveUrl() {
        return archiveUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public String getLanguage() {
        return language;
    }

    public String getReleasesUrl() {
        return releasesUrl;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    public int getWatchers() {
        return watchers;
    }

    public String getSvnUrl() {
        return svnUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getBlobsUrl() {
        return blobsUrl;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getCompareUrl() {
        return compareUrl;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public String getFullname() {
        return fullname;
    }

    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    public String getLanguagesUrl() {
        return languagesUrl;
    }

    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getSshUrl() {
        return sshUrl;
    }

    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    public int getOpenIssues() {
        return openIssues;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public String getHooksUrl() {
        return hooksUrl;
    }

    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getIssuesUrl() {
        return issuesUrl;
    }

    public int getForksCount() {
        return forksCount;
    }

    public String getKeysUrl() {
        return keysUrl;
    }

    public String getMergesUrl() {
        return mergesUrl;
    }

    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    public String getMirrorUrl() {
        return mirrorUrl;
    }

    public String getName() {
        return name;
    }

    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public String getStargazersUrl() {
        return stargazersUrl;
    }

    public String getTagsUrl() {
        return tagsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public String getTreesUrl() {
        return treesUrl;
    }

    public boolean isWebCommitSignoffRequired() {
        return webCommitSignoffRequired;
    }

    public boolean isHasDiscussions() {
        return hasDiscussions;
    }

    public boolean isAllowForking() {
        return allowForking;
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public boolean isArchived() {
        return archived;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public boolean isFork() {
        return fork;
    }

    public boolean isHasPages() {
        return hasPages;
    }

    public boolean isHasDownloads() {
        return hasDownloads;
    }

    public boolean isHasProjects() {
        return hasProjects;
    }

    public boolean isHasWiki() {
        return hasWiki;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public boolean isHasIssues() {
        return hasIssues;
    }

    public List<String> getTopics() {
        return topics;
    }

    public GHLicense getLicense() {
        return license;
    }

    public GHUser getOwner() {
        return owner;
    }

    public void updateTopics(List<String> topics) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("names", topics);
        //TODO: Implement
//        sendPatchRequest(this.url + "/topics", jsonObject.toString(), github.getToken());
        this.topics = topics;
    }

    public void updateHomePage(String url) {
        //TODO: Implement
    }
}
