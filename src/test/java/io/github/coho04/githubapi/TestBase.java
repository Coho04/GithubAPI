package io.github.coho04.githubapi;

import io.github.coho04.githubapi.entities.GHTeam;
import io.github.coho04.githubapi.entities.GHUser;
import io.github.coho04.githubapi.entities.GHVulnerability;
import io.github.coho04.githubapi.entities.repositories.GHMilestone;
import io.github.coho04.githubapi.enums.GHState;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.Random;

public class TestBase {

    public final Random random = new Random();

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
}
