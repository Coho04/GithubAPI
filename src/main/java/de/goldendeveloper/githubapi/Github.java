package de.goldendeveloper.githubapi;


import de.goldendeveloper.githubapi.interfaces.HttpRequestInterface;

@SuppressWarnings("unused")
public class Github implements HttpRequestInterface {

    private String username;
    private String token;

    public Github(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public GHOrganisation findOrganisationByName(String name) {
        return new GHOrganisation(this, name);
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
