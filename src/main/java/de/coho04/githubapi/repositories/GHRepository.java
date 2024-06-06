package de.coho04.githubapi.repositories;

import de.coho04.githubapi.entities.*;
import de.coho04.githubapi.Github;
import de.coho04.githubapi.bases.ClassBase;
import de.coho04.githubapi.builders.GHFileBuilder;
import de.coho04.githubapi.builders.GHIssueBuilder;
import de.coho04.githubapi.entities.GHPermission;
import de.coho04.githubapi.enums.GHState;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents a GitHub repository.
 * It provides methods for fetching and manipulating data about the repository such as its size, forks, license, name, owner, watchers, and more.
 */
@SuppressWarnings("unused")
public class GHRepository extends ClassBase {

    private final int size;
    private final int forks;

    private GHLicense license;
    private final String name;
    private final boolean fork;
    private final int watchers;
    private GHUser owner;
    private final String sshUrl;
    private final String svnUrl;
    private final Github github;
    private final String keysUrl;
    private final String tagsUrl;
    private final String cloneUrl;
    private final OffsetDateTime pushedAt;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final String language;
    private final String fullName;
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
    private final GHPermission permissions;

    private final boolean webCommitSignoffRequired;

    /**
     * Constructs a new GHRepository instance with the provided JSON object and GitHub instance.
     *
     * @param jsonObject the JSON object containing the repository data
     * @param github     the GitHub instance associated with this repository
     */
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
        this.fullName = getStringOrNull(jsonObject, "full_name");
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
        if (jsonObject.has("owner") && !jsonObject.isNull("owner")) {
            this.owner = new GHUser(github, getJSONObjectOrNull(jsonObject, "owner"));
        }
        if (jsonObject.has("license") && !jsonObject.isNull("license")) {
            this.license = new GHLicense(getJSONObjectOrNull(jsonObject, "license"));
        }
        this.permissions = new GHPermission(getJSONObjectOrNull(jsonObject, "permissions"));
        this.topics = jsonObject.getJSONArray("topics").toList().stream().map(Object::toString).collect(Collectors.toList());
    }

    /**
     * Fetches and returns a GitHub repository based on the provided owner and name.
     *
     * @param github the GitHub instance associated with this repository
     * @param owner the username of the owner of the repository
     * @param name the name of the repository
     * @return a GHRepository instance representing the fetched repository
     */
    public static GHRepository getRepository(Github github, String owner, String name) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/repos/" + owner + "/" + name, github.getToken());
        assert response != null;
        return new GHRepository(new JSONObject(response), github);
    }

    /**
     * Creates a new issue in this repository with the given title.
     *
     * @param title the title of the new issue
     * @return a GHIssueBuilder instance for building the new issue
     */
    public GHIssueBuilder createIssue(String title) {
        return new GHIssueBuilder(github,this, title);
    }

    /**
     * Fetches the branches of this repository.
     *
     * @return a HashMap mapping branch names to GHBranch instances
     */
    public Map<String, GHBranch> getBranches() {
        Map<String, GHBranch> branches = new HashMap<>();
        String response = sendGetRequest(getUrl() + "/branches", github.getToken());
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = getStringOrNull(jsonObject, "name");
            branches.put(name, new GHBranch(jsonObject));
        }
        return branches;
    }

    /**
     * Fetches the contributors of this repository.
     *
     * @return a List of GHUser instances representing the contributors of this repository
     */
    public List<GHUser> getContributors() {
        String response = sendGetRequest(getUrl() + "/contributors", github.getToken());
        List<GHUser> contributors = new ArrayList<>();
        if (response != null) {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                contributors.add(new GHUser(github, jsonArray.getJSONObject(i)));
            }
        }
        return contributors;
    }

    /**
     * Fetches the issues of this repository with the given state.
     *
     * @param state the state of the issues to fetch
     * @return a List of GHIssue instances representing the issues of this repository with the given state
     */
    public List<GHIssue> getIssues(GHState state) {
        String response = sendGetRequest(getUrl() + "/issues", github.getToken());
        List<GHIssue> issues = new ArrayList<>();
        if (response != null) {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                issues.add(new GHIssue(github, jsonArray.getJSONObject(i)));
            }
        }
        return issues;
    }

    /**
     * Fetches all issues of this repository.
     *
     * @return a List of GHIssue instances representing all issues of this repository
     */
    public List<GHIssue> getIssues() {
        return getIssues(GHState.ALL);
    }

    /**
     * Starts the process of adding a new file to this repository.
     *
     * @return a GHFileBuilder instance for building the new file
     */
    public GHFileBuilder addFile() {
        return new GHFileBuilder(this, github);
    }

    /**
     * Starts the process of adding a new file to the given branch of this repository.
     *
     * @param branch the branch to add the new file to
     * @return a GHFileBuilder instance for building the new file
     */
    public GHFileBuilder addFile(GHBranch branch) {
        return new GHFileBuilder(this, branch, github);
    }

    /**
     * Starts the process of adding a new file with the given path, content, and commit message to the given branch of this repository.
     *
     * @param branch  the branch to add the new file to
     * @param path    the path of the new file
     * @param content the content of the new file
     * @param message the commit message for the new file
     * @return a GHFileBuilder instance for building the new file
     */
    public GHFileBuilder addFile(GHBranch branch, String path, String content, String message) {
        return new GHFileBuilder(this, branch, path, content, message, github);
    }

    /**
     * Fetches all filenames in this repository.
     *
     * @return a List of Strings representing all filenames in this repository
     */
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

    /**
     * Fetches the content of the directory at the given path in this repository.
     *
     * @param path the path of the directory
     * @return a List of GHFile instances representing the content of the directory
     */
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
            return new ArrayList<>();
        }
        return files;
    }

    /**
     * Returns the number of forks of this repository.
     *
     * @return the number of forks
     */
    public int getForks() {
        return forks;
    }

    /**
     * Returns the number of open issues in this repository.
     *
     * @return the number of open issues
     */
    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    /**
     * Returns the size of this repository.
     *
     * @return the size of the repository
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the number of stargazers for this repository.
     *
     * @return the number of stargazers
     */
    public int getStargazersCount() {
        return stargazersCount;
    }

    /**
     * Returns the permissions associated with this repository.
     *
     * @return the permissions
     */
    public Object getPermissions() {
        return permissions;
    }

    /**
     * Returns the date and time when this repository was last pushed to.
     *
     * @return the date and time of the last push
     */
    public OffsetDateTime getPushedAt() {
        return pushedAt;
    }

    /**
     * Returns the URL for fetching the branches of this repository.
     *
     * @return the branches URL
     */
    public String getBranchesUrl() {
        return branchesUrl;
    }

    /**
     * Returns the URL for fetching issue comments in this repository.
     *
     * @return the issue comment URL
     */
    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    /**
     * Returns the URL for fetching the archive of this repository.
     *
     * @return the archive URL
     */
    public String getArchiveUrl() {
        return archiveUrl;
    }

    /**
     * Returns the URL for fetching labels in this repository.
     *
     * @return the labels URL
     */
    public String getLabelsUrl() {
        return labelsUrl;
    }

    /**
     * Returns the primary programming language used in this repository.
     *
     * @return the programming language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the URL for fetching releases in this repository.
     *
     * @return the releases URL
     */
    public String getReleasesUrl() {
        return releasesUrl;
    }

    /**
     * Returns the URL for fetching subscribers of this repository.
     *
     * @return the subscribers URL
     */
    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    /**
     * Returns the URL for subscribing to this repository.
     *
     * @return the subscription URL
     */
    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    /**
     * Returns the URL for fetching forks of this repository.
     *
     * @return the forks URL
     */
    public String getForksUrl() {
        return forksUrl;
    }

    /**
     * Returns the URL for fetching git references in this repository.
     *
     * @return the git references URL
     */
    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    /**
     * Returns the number of watchers of this repository.
     *
     * @return the number of watchers
     */
    public int getWatchers() {
        return watchers;
    }

    /**
     * Returns the SVN URL of this repository.
     *
     * @return the SVN URL
     */
    public String getSvnUrl() {
        return svnUrl;
    }

    /**
     * Returns the date and time when this repository was created.
     *
     * @return the creation date and time
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the date and time when this repository was last updated.
     *
     * @return the update date and time
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the URL for fetching blobs in this repository.
     *
     * @return the blobs URL
     */
    public String getBlobsUrl() {
        return blobsUrl;
    }

    /**
     * Returns the clone URL of this repository.
     *
     * @return the clone URL
     */
    public String getCloneUrl() {
        return cloneUrl;
    }

    /**
     * Returns the URL for fetching collaborators of this repository.
     *
     * @return the collaborators URL
     */
    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    /**
     * Returns the URL for fetching comments in this repository.
     *
     * @return the comments URL
     */
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * Returns the URL for fetching commits in this repository.
     *
     * @return the commits URL
     */
    public String getCommitsUrl() {
        return commitsUrl;
    }

    /**
     * Returns the visibility of this repository.
     *
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Returns the URL for comparing commits in this repository.
     *
     * @return the compare URL
     */
    public String getCompareUrl() {
        return compareUrl;
    }

    /**
     * Returns the URL for fetching contents of this repository.
     *
     * @return the contents URL
     */
    public String getContentsUrl() {
        return contentsUrl;
    }

    /**
     * Returns the URL for fetching contributors to this repository.
     *
     * @return the contributors URL
     */
    public String getContributorsUrl() {
        return contributorsUrl;
    }

    /**
     * Returns the default branch of this repository.
     *
     * @return the default branch
     */
    public String getDefaultBranch() {
        return defaultBranch;
    }

    /**
     * Returns the URL for fetching deployments in this repository.
     *
     * @return the deployments URL
     */
    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    /**
     * Returns the description of this repository.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the URL for fetching downloads in this repository.
     *
     * @return the downloads URL
     */
    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    /**
     * Returns the URL for fetching statuses in this repository.
     *
     * @return the statuses URL
     */
    public String getStatusesUrl() {
        return statusesUrl;
    }

    /**
     * Returns the full name of this repository.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Returns the URL for fetching git commits in this repository.
     *
     * @return the git commits URL
     */
    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    /**
     * Returns the URL for fetching languages used in this repository.
     *
     * @return the languages URL
     */
    public String getLanguagesUrl() {
        return languagesUrl;
    }

    /**
     * Returns the URL for fetching git tags in this repository.
     *
     * @return the git tags URL
     */
    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    /**
     * Returns the git URL of this repository.
     *
     * @return the git URL
     */
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     * Returns the SSH URL of this repository.
     *
     * @return the SSH URL
     */
    public String getSshUrl() {
        return sshUrl;
    }

    /**
     * Returns the URL for fetching assignees in this repository.
     *
     * @return the assignees URL
     */
    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    /**
     * Returns the number of open issues in this repository.
     *
     * @return the number of open issues
     */
    public int getOpenIssues() {
        return openIssues;
    }

    /**
     * Returns the number of watchers in this repository.
     *
     * @return the watchers count
     */
    public int getWatchersCount() {
        return watchersCount;
    }

    /**
     * Returns the URL for fetching hooks in this repository.
     *
     * @return the hooks URL
     */
    public String getHooksUrl() {
        return hooksUrl;
    }

    /**
     * Returns the URL for fetching issue events in this repository.
     *
     * @return the issue events URL
     */
    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    /**
     * Returns the homepage URL of this repository.
     *
     * @return the homepage URL
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * Returns the URL for fetching issues in this repository.
     *
     * @return the issues URL
     */
    public String getIssuesUrl() {
        return issuesUrl;
    }

    /**
     * Returns the number of forks of this repository.
     *
     * @return the forks count
     */
    public int getForksCount() {
        return forksCount;
    }

    /**
     * Returns the URL for fetching keys in this repository.
     *
     * @return the keys URL
     */
    public String getKeysUrl() {
        return keysUrl;
    }

    /**
     * Returns the URL for fetching merges in this repository.
     *
     * @return the merges URL
     */
    public String getMergesUrl() {
        return mergesUrl;
    }

    /**
     * Returns the URL for fetching milestones in this repository.
     *
     * @return the milestones URL
     */
    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    /**
     * Returns the mirror URL of this repository.
     *
     * @return the mirror URL
     */
    public String getMirrorUrl() {
        return mirrorUrl;
    }

    /**
     * Returns the name of this repository.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the URL for fetching notifications in this repository.
     *
     * @return the notifications URL
     */
    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    /**
     * Returns the URL for fetching pull requests in this repository.
     *
     * @return the pulls URL
     */
    public String getPullsUrl() {
        return pullsUrl;
    }

    /**
     * Returns the URL for fetching stargazers of this repository.
     *
     * @return the stargazers URL
     */
    public String getStargazersUrl() {
        return stargazersUrl;
    }

    /**
     * Returns the URL for fetching tags in this repository.
     *
     * @return the tags URL
     */
    public String getTagsUrl() {
        return tagsUrl;
    }

    /**
     * Returns the URL for fetching teams associated with this repository.
     *
     * @return the teams URL
     */
    public String getTeamsUrl() {
        return teamsUrl;
    }

    /**
     * Returns the URL for fetching trees in this repository.
     *
     * @return the trees URL
     */
    public String getTreesUrl() {
        return treesUrl;
    }

    /**
     * Returns whether web commit signoff is required for this repository.
     *
     * @return true if web commit signoff is required, false otherwise
     */
    public boolean isWebCommitSignoffRequired() {
        return webCommitSignoffRequired;
    }

    /**
     * Returns whether this repository has discussions enabled.
     *
     * @return true if discussions are enabled, false otherwise
     */
    public boolean isHasDiscussions() {
        return hasDiscussions;
    }

    /**
     * Returns whether forking is allowed for this repository.
     *
     * @return true if forking is allowed, false otherwise
     */
    public boolean isAllowForking() {
        return allowForking;
    }

    /**
     * Returns whether this repository is a template repository.
     *
     * @return true if this is a template repository, false otherwise
     */
    public boolean isTemplate() {
        return isTemplate;
    }

    /**
     * Returns whether this repository is archived.
     *
     * @return true if this repository is archived, false otherwise
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     * Returns whether this repository is disabled.
     *
     * @return true if this repository is disabled, false otherwise
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Returns whether this repository is a fork.
     *
     * @return true if this repository is a fork, false otherwise
     */
    public boolean isFork() {
        return fork;
    }

    /**
     * Returns whether this repository has GitHub Pages enabled.
     *
     * @return true if GitHub Pages is enabled, false otherwise
     */
    public boolean isHasPages() {
        return hasPages;
    }

    /**
     * Returns whether this repository has downloads enabled.
     *
     * @return true if downloads are enabled, false otherwise
     */
    public boolean isHasDownloads() {
        return hasDownloads;
    }

    /**
     * Returns whether this repository has projects enabled.
     *
     * @return true if projects are enabled, false otherwise
     */
    public boolean isHasProjects() {
        return hasProjects;
    }

    /**
     * Returns whether this repository has a wiki enabled.
     *
     * @return true if the wiki is enabled, false otherwise
     */
    public boolean isHasWiki() {
        return hasWiki;
    }

    /**
     * Returns whether this repository is private.
     *
     * @return true if this repository is private, false otherwise
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Returns whether this repository has issues enabled.
     *
     * @return true if issues are enabled, false otherwise
     */
    public boolean isHasIssues() {
        return hasIssues;
    }

    /**
     * Returns the list of topics associated with this repository.
     *
     * @return the list of topics
     */
    public List<String> getTopics() {
        return topics;
    }

    /**
     * Returns the license associated with this repository.
     *
     * @return the license
     */
    public GHLicense getLicense() {
        return license;
    }

    /**
     * Returns the owner of this repository.
     *
     * @return the owner
     */
    public GHUser getOwner() {
        return owner;
    }

    /**
     * Fetches the list of pull requests in this repository.
     *
     * @return a List of GHPullRequest instances representing the pull requests in this repository
     */
    public List<GHPullRequest> listPullRequests() {
        String url = "/repos/" + this.getOwner().getLogin() + "/" + this.getName() + "/pulls";
        return fetchPaginatedData(url, json -> new GHPullRequest(github, json), github.getToken(), "&state=all");
    }

    /**
     * Checks if this repository has any pull requests with the given state.
     *
     * @param state the state of the pull requests to check for
     * @return true if this repository has any pull requests with the given state, false otherwise
     */
    public boolean hasPullRequestsWithState(GHState state) {
        return listPullRequests().stream().anyMatch(pr -> pr.getState().equals(state));
    }

    /**
     * Updates the topics of this repository.
     *
     * @param topics the new topics
     */
    public void updateTopics(List<String> topics) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("names", topics);
        //TODO: Implement
//        sendPatchRequest(this.url + "/topics", jsonObject.toString(), github.getToken());
        this.topics = topics;
    }

    /**
     * Updates the homepage of this repository.
     *
     * @param url the new homepage URL
     */
    public void updateHomePage(String url) {
        //TODO: Implement
    }

    /**
     * Fetches the list of events in this repository.
     *
     * @return a List of GHEvent instances representing the events in this repository
     */
    public List<GHEvent> listEvents() {
        return fetchPaginatedData(getUrl(), "/events", GHEvent::new, github.getToken());
    }

    /**
     * Fetches the list of artifacts in this repository.
     *
     * @return a List of GHArtifact instances representing the artifacts in this repository
     */
    public List<GHArtifact> listArtifacts() {
        return fetchPaginatedData(getUrl(), "/actions/artifacts", GHArtifact::new, github.getToken());
    }

    /**
     * Fetches the artifact with the given ID in this repository.
     *
     * @param id the ID of the artifact
     * @return a GHArtifact instance representing the artifact with the given ID
     */
    public GHArtifact getArtifact(int id) {
        String response = sendGetRequest(getUrl() + "/actions/artifacts/" + id, github.getToken());
        return new GHArtifact(new JSONObject(response));
    }

    /**
     * Deletes the artifact with the given ID in this repository.
     *
     * @param id the ID of the artifact
     * @return true if the artifact was successfully deleted, false otherwise
     */
    public Boolean deleteArtifact(int id) {
        return sendDeleteRequestWithResponseCode(getUrl() + "/actions/artifacts/" + id, github.getToken(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    /**
     * Fetches the cache of this repository.
     *
     * @return a GHRepositoryCache instance representing the cache of this repository
     */
    public GHRepositoryCache getCache() {
        String response = sendGetRequest(getUrl() + "/actions/cache/usage", github.getToken());
        assert response != null;
        return new GHRepositoryCache(new JSONObject(response));
    }

    /**
     * Fetches the list of actions caches in this repository.
     *
     * @return a List of GHActionsCache instances representing the actions caches in this repository
     */
    public List<GHActionsCache> listActionsCaches() {
        return fetchPaginatedData(getUrl(), "/actions/artifacts", GHActionsCache::new, github.getToken());
    }

    /**
     * Deletes the actions cache with the given key in this repository.
     *
     * @param key the key of the actions cache
     */
    public void deleteActionsCache(String key) {
        sendDeleteRequest(getUrl() + "/actions/caches?key=" + key, github.getToken());
    }

    /**
     * Deletes the actions cache with the given ID in this repository.
     *
     * @param id the ID of the actions cache
     * @return true if the actions cache was successfully deleted, false otherwise
     */
    public boolean deleteActionsCache(int id) {
        return sendDeleteRequestWithResponseCode(getUrl() + "/actions/caches/" + id, github.getToken(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    /**
     * Fetches the list of organisation secrets in this repository.
     *
     * @return a List of GHSecret instances representing the organisation secrets in this repository
     */
    public List<GHSecret> listOrganisationSecrets() {
        return fetchArrayData(getUrl(), "/actions/organization-secrets", GHSecret::new, github.getToken(), "secrets");
    }

    /**
     * Fetches the list of repository secrets in this repository.
     *
     * @return a List of GHSecret instances representing the repository secrets in this repository
     */
    public List<GHSecret> listRepositorySecrets() {
        return fetchArrayData(getUrl(), "/actions/secrets", GHSecret::new, github.getToken(), "secrets");
    }

    /**
     * Fetches the public key of this repository.
     *
     * @return a GHPublicKey instance representing the public key of this repository
     */
    public GHPublicKey getPublicKey() {
        String response = sendGetRequest(getUrl() + "/actions/secrets/public-key", github.getToken());
        return new GHPublicKey(new JSONObject(response));
    }

    /**
     * Fetches the secret with the given name in this repository.
     *
     * @param name the name of the secret
     * @return a GHSecret instance representing the secret with the given name
     */
    public GHSecret getSecret(String name) {
        String response = sendGetRequest(getUrl() + "/actions/secrets/" + name, github.getToken());
        return new GHSecret(new JSONObject(response));
    }

    /**
     * Fetches the list of environment secrets in this repository.
     *
     * @param environment the name of the environment
     * @return a List of GHSecret instances representing the environment secrets in this repository
     */
    public List<GHSecret> listEnvironmentSecrets(String environment) {
        return fetchArrayData(getUrl(), "/actions/environments/" + environment + "/secrets", GHSecret::new, github.getToken(), "secrets");
    }

    /**
     * Fetches the public key of the given environment in this repository.
     *
     * @param environment the name of the environment
     * @return a GHPublicKey instance representing the public key of the environment
     */
    public GHPublicKey getEnvironmentPublicKey(String environment) {
        String response = sendGetRequest(getUrl() + "/actions/environments/" + environment + "/secrets/public-key", github.getToken());
        return new GHPublicKey(new JSONObject(response));
    }

    /**
     * Fetches the secret with the given name in the specified environment.
     *
     * @param environment the name of the environment
     * @param name        the name of the secret
     * @return a GHSecret instance representing the secret with the given name
     */
    public GHSecret getEnvironmentSecret(String environment, String name) {
        String response = sendGetRequest(getUrl() + "/actions/environments/" + environment + "/secrets/" + name, github.getToken());
        return new GHSecret(new JSONObject(response));
    }

    /**
     * Fetches the list of organisation variables in this repository.
     *
     * @return a List of GHVariable instances representing the organisation variables in this repository
     */
    public List<GHVariable> listOrganisationVariables() {
        return fetchArrayData(getUrl(), "/actions/organization-variables", GHVariable::new, github.getToken(), "secrets");
    }

    /**
     * Fetches the list of repository variables in this repository.
     *
     * @return a List of GHVariable instances representing the repository variables in this repository
     */
    public List<GHVariable> listRepositoryVariables() {
        return fetchArrayData(getUrl(), "/actions/variables", GHVariable::new, github.getToken(), "variables");
    }

    /**
     * Fetches the variable with the given name in this repository.
     *
     * @param name the name of the variable
     * @return a GHVariable instance representing the variable with the given name
     */
    public GHVariable getVariable(String name) {
        String response = sendGetRequest(getUrl() + "/actions/variables/" + name, github.getToken());
        return new GHVariable(new JSONObject(response));
    }

    /**
     * Fetches the list of environment variables in the specified environment.
     *
     * @param environment the name of the environment
     * @return a List of GHVariable instances representing the environment variables
     */
    public List<GHVariable> listEnvironmentVariables(String environment) {
        return fetchArrayData(getUrl(), "/environments/" + environment + "/variables", GHVariable::new, github.getToken(), "variables");
    }

    /**
     * Fetches the variable with the given name in the specified environment.
     *
     * @param environment the name of the environment
     * @param name        the name of the variable
     * @return a GHVariable instance representing the variable with the given name
     */
    public GHVariable getEnvironmentVariable(String environment, String name) {
        String response = sendGetRequest(getUrl() + "/environments/" + environment + "/variables/" + name, github.getToken());
        return new GHVariable(new JSONObject(response));
    }

    /**
     * Fetches the workflow job with the given ID in this repository.
     *
     * @param id the ID of the workflow job
     * @return a GHWorkflowJob instance representing the workflow job with the given ID
     */
    public GHWorkflowJob getJobFromWorkflowRun(int id) {
        String response = sendGetRequest(getUrl() + "/actions/jobs/" + id, github.getToken());
        return new GHWorkflowJob(new JSONObject(response));
    }

    /**
     * Fetches the list of jobs in the specified workflow run.
     *
     * @param id the ID of the workflow run
     * @return a List of GHWorkflowJob instances representing the jobs in the workflow run
     */
    public List<GHWorkflowJob> listJobsFromWorkflowRun(int id) {
        return fetchArrayData(getUrl(), "/actions/runs/" + id + "/jobs", GHWorkflowJob::new, github.getToken(), "jobs");
    }

    /**
     * Fetches the list of jobs for the specified workflow attempt.
     *
     * @param id      the ID of the workflow run
     * @param attempt the attempt number
     * @return a List of GHWorkflowJob instances representing the jobs for the workflow attempt
     */
    public List<GHWorkflowJob> listJobsForWorkflowAttempt(int id, int attempt) {
        return fetchArrayData(getUrl(), "/actions/runs/" + id + "/attempts/" + attempt + "/jobs", GHWorkflowJob::new, github.getToken(), "jobs");
    }

    /**
     * Fetches the workflow run with the given ID in this repository.
     *
     * @param id the ID of the workflow run
     * @return a GHWorkflowRun instance representing the workflow run with the given ID
     */
    public GHWorkflowRun getWorkflowRun(int id) {
        String response = sendGetRequest(getUrl() + "/actions/runs/" + id, github.getToken());
        return new GHWorkflowRun(new JSONObject(response));
    }

}
