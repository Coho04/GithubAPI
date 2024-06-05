package de.coho04.githubapi;


import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.GHOrganisation;
import de.coho04.githubapi.entities.GHUser;
import org.json.JSONObject;

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
}
