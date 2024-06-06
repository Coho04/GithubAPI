package de.coho04.githubapi;

import de.coho04.githubapi.entities.GHUser;
import de.coho04.githubapi.repositories.GHIssue;
import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

import java.util.List;

@SuppressWarnings("unused")
public class SelfUser extends GHUser {

    /**
     * Constructs a new GHUser instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the user data
     */
    public SelfUser(Github github, JSONObject jsonObject) {
        super(github, jsonObject);
    }

    public static SelfUser getSelfUser(Github github) {
        String response = HttpRequestHelper.sendGetRequest(getBaseUrl() + "/user", github.getToken());
        assert response != null;
        return new SelfUser(github, new JSONObject(response));
    }

    public List<GHIssue> getIssues() {
        return  fetchPaginatedData( "/issues", jsonObject -> new GHIssue(github, jsonObject), github.getToken());
    }
}
