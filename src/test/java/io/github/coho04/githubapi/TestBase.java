package io.github.coho04.githubapi;

import io.github.coho04.githubapi.entities.GHTeam;
import io.github.coho04.githubapi.entities.GHUser;
import io.github.coho04.githubapi.entities.GHVulnerability;
import io.github.coho04.githubapi.entities.repositories.GHFile;
import io.github.coho04.githubapi.entities.repositories.GHMilestone;
import io.github.coho04.githubapi.entities.repositories.GHRepository;
import io.github.coho04.githubapi.enums.GHState;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

public class TestBase {

    public final Random random = new Random();
    private final Github github;

    public TestBase() {
        github = Mockito.mock(Github.class);
    }

    protected GHUser setupUser() {
        return new GHUser(Mockito.mock(Github.class), new JSONObject()
                .put("contributions", String.valueOf(random.nextInt()))
                .put("gists_url", "https://example.com/gists_url")
                .put("site_admin", true)
                .put("gravatar_id", "A principal idea stands upon somebody else's legs.")
                .put("starred_url", "https://example.com/starred_url")
                .put("followers_url", "https://example.com/followers_url")
                .put("following_url", "https://example.com/following_url")
                .put("subscriptions_url", "https://example.com/subscriptions_url")
                .put("received_events_url", "https://example.com/received_events_url")
                .put("organizations_url", "https://example.com/organizations_url")
                .put("type", "An idea stands upon somebody else's legs.")
                .put("login", "Sex stands upon somebody else's legs.")
                .put("repos_url", "https://example.com/repos_url")
                .put("avatar_url", "https://example.com/avatar_url")
                .put("id", String.valueOf(random.nextInt()))
                .put("node_id", "A token of gratitude stands upon somebody else's legs.")
                .put("html_url", "https://example.com/html_url")
                .put("url", "https://example.com/url")
                .put("events_url", "https://example.com/events_url")
        );
    }

    protected GHTeam setupTeam() {
        return new GHTeam(new JSONObject()
                .put("name", "Test Team")
                .put("slug", "test-team")
                .put("description", "A test team")
                .put("privacy", "closed")
                .put("notification_setting", "all")
                .put("permission", "admin")
                .put("members_url", "https://api.github.com/teams/1/members")
                .put("repositories_url", "https://api.github.com/teams/1/repos")
                .put("created_at", OffsetDateTime.now().toString())
                .put("updated_at", OffsetDateTime.now().toString())
                .put("members_count", 10)
                .put("repos_count", 5)
                .put("parent", JSONObject.NULL)
        );
    }

    protected GHMilestone setupMileStone() {
        return new GHMilestone(Mockito.mock(Github.class), new JSONObject()
                .put("closed_at", "2011-04-10T20:09:31Z")
                .put("description", "Passion or serendipity stands upon somebody else's legs.")
                .put("created_at", "2011-04-10T20:09:31Z")
                .put("title", "A cranky old lady stands upon somebody else's legs.")
                .put("closed_issues", String.valueOf(random.nextInt()))
                .put("due_on", "fczvgubhijokpl")
                .put("creator", setupUser().toJSONObject())
                .put("state", GHState.CLOSED.toString())
                .put("labels_url", "https://example.com/labels_url")
                .put("number", String.valueOf(random.nextInt()))
                .put("updated_at", "2011-04-10T20:09:31Z")
                .put("open_issues", String.valueOf(random.nextInt()))
        );
    }

    protected GHVulnerability setupVulnerabilities() {
        return new GHVulnerability(Mockito.mock(Github.class), new JSONObject()
                .put("contributions", String.valueOf(random.nextInt()))
                .put("first_patched_version", "https://example.com/gists_url")
                .put("vulnerable_version_range", String.valueOf(random.nextBoolean()))
                .put("vulnerable_functions", new JSONArray())
        );
    }

    protected Github getMockedGithub() {
        return github;
    }


    protected GHRepository setupRepository() {
        OffsetDateTime now = OffsetDateTime.now();
        return new GHRepository(new JSONObject()
                .put("owner", new JSONObject().put("login", "octocat"))
                .put("permissions", new JSONObject().put("login", "octocat")
                        .put("pull", random.nextBoolean())
                        .put("maintain", random.nextBoolean())
                        .put("admin", random.nextBoolean())
                        .put("triage", random.nextBoolean())
                        .put("push", random.nextBoolean()))
                .put("name", "test-repo")
                .put("full_name", "test-owner/test-repo")
                .put("forks", random.nextInt())
                .put("open_issues_count", random.nextInt())
                .put("stargazers_count", random.nextInt())
                .put("size", random.nextInt())
                .put("branches_url", "expected_branches_url")
                .put("language", "Java")
                .put("default_branch", "main")
                .put("url", "https://api.github.com/repos/test/repo")
                .put("topics", new JSONArray(List.of("topic1", "topic2")))
                .put("issue_comment_url", "expected_issue_comment_url")
                .put("archive_url", "expected_archive_url")
                .put("labels_url", "expected_labels_url")
                .put("language", "expected_language")
                .put("releases_url", "expected_releases_url")
                .put("subscribers_url", "expected_subscribers_url")
                .put("subscription_url", "expected_subscription_url")
                .put("forks_url", "expected_forks_url")
                .put("git_refs_url", "expected_git_refs_url")
                .put("watchers", random.nextInt())
                .put("svn_url", "expected_svn_url")
                .put("created_at", now.toString())
                .put("updated_at", now.toString())
                .put("blobs_url", "expected_blobs_url")
                .put("clone_url", "expected_clone_url")
                .put("collaborators_url", "expected_collaborators_url")
                .put("comments_url", "expected_comments_url")
                .put("commits_url", "expected_commits_url")
                .put("visibility", "expected_visibility")
                .put("compare_url", "expected_compare_url")
                .put("contents_url", "expected_contents_url")
                .put("contributors_url", "expected_contributors_url")
                .put("default_branch", "expected_default_branch")
                .put("deployments_url", "expected_deployments_url")
                .put("description", "expected_description")
                .put("downloads_url", "expected_downloads_url")
                .put("statuses_url", "expected_statuses_url")
                .put("full_name", "expected_full_name")
                .put("git_commits_url", "expected_git_commits_url")
                .put("languages_url", "expected_languages_url")
                .put("git_tags_url", "expected_git_tags_url")
                .put("git_url", "expected_git_url")
                .put("ssh_url", "expected_ssh_url")
                .put("assignees_url", "expected_assignees_url")
                .put("open_issues", random.nextInt())
                .put("watchers_count", random.nextInt())
                .put("hooks_url", "expected_hooks_url")
                .put("issue_events_url", "expected_issue_events_url")
                .put("homepage", "expected_homepage")
                .put("issues_url", "expected_issues_url")
                .put("forks_count", random.nextInt())
                .put("keys_url", "expected_keys_url")
                .put("web_commit_signoff_required", random.nextBoolean())
                .put("has_projects", random.nextBoolean())
                .put("has_downloads", random.nextBoolean())
                .put("archived", random.nextBoolean())
                .put("disabled", random.nextBoolean())
                .put("has_pages", random.nextBoolean())
                .put("has_discussions", random.nextBoolean())
                .put("has_wiki", random.nextBoolean())
                .put("allow_forking", random.nextBoolean())
                .put("has_issues", random.nextBoolean())
                .put("fork", random.nextBoolean())
                .put("private", random.nextBoolean())
                .put("is_template", random.nextBoolean())
                .put("notifications_url", "https://example.com/notifications_url")
                .put("pulls_url", "https://example.com/pulls_url")
                .put("stargazers_url", "https://example.com/stargazers_url")
                .put("tags_url", "https://example.com/tags_url")
                .put("teams_url", "https://example.com/teams_url")
                .put("trees_url", "https://example.com/trees_url")
                .put("merges_url", "https://example.com/merges_url")
                .put("milestones_url", "https://example.com/milestones_url")
                .put("mirror_url", "https://example.com/mirror_url")
                .put("pushed_at", now.toString()), getMockedGithub());
    }

    protected boolean jsonSimilar(JSONObject actual, JSONObject expected) {
        if (actual == null || expected == null) return false;
        if (actual.length() != expected.length()) return false;
        for (String key : expected.keySet()) {
            if (!actual.has(key)) return false;
            if (actual.isNull(key) && expected.isNull(key)) continue;
            if (actual.isNull(key) || expected.isNull(key)) return false;
            if (!actual.get(key).toString().equals(expected.get(key).toString())) return false;
        }
        return true;
    }

    protected GHFile setupGHFile() {
        return new GHFile(getMockedGithub(), new JSONObject()
                .put("name", "Test File"),
                setupRepository()
        );
    }
}
