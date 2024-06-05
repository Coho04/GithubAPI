package de.coho04.githubapi;


import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Github extends GHBase {

    private final String username;
    private final String token;

    public Github(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public GHOrganisation findOrganisationByName(String name) {
        return GHOrganisation.getOrganisation(this, name);
    }

    public GHUser findUserByName(String name) {
        return GHUser.getUser(this, name);
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject();
    }


    public List<GHAdvisory> listGlobalAdvisories() {
        return fetchPaginatedData("/advisories", GHAdvisory::new, getToken());
    }

    public GHAdvisory getGlobalAdvisories(String id) {
        String response = sendGetRequest("/advisories/" + id, getToken());
        assert response != null;
        return new GHAdvisory(new JSONObject(response));
    }

    public List<GHGitignoreTemplate> listGitignoreTemplates() {
        String response = sendGetRequest(getBaseUrl() + "/gitignore/templates", getToken());
        assert response != null;
        List<GHGitignoreTemplate> templates = new ArrayList<>();
        JSONArray array = new JSONArray(response);
        array.toList().forEach(o -> {
                    JSONObject json = new JSONObject("{\"name\":\"" + o + "\"}");
                    templates.add(new GHGitignoreTemplate(json));
                });
        return templates;
    }

    public GHGitignoreTemplate getGitignoreTemplate(String name) {
        String response = sendGetRequest(getBaseUrl() + "/gitignore/templates/" + name, getToken());
        assert response != null;
        return new GHGitignoreTemplate(new JSONObject(response));
    }
}
