package de.coho04.githubapi;

import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.entities.*;
import de.coho04.githubapi.utilities.LogFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * This class represents the main entry point for interacting with the GitHub API.
 * It provides methods for fetching data about users, organizations, advisories, and gitignore templates.
 */
public class Github extends GHBase {

    private final String token;
    private static Logger logger;

    /**
     * Constructs a new GitHub instance with the provided username and token.
     *
     * @param token the GitHub token
     */
    public Github(String token) {
        this.token = token;
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new LogFormatter());
        logger.addHandler(handler);
    }

    /**
     * Finds an organization by its name.
     *
     * @param name the name of the organization
     * @return the organization
     */
    public GHOrganisation findOrganisationByName(String name) {
        return GHOrganisation.getOrganisation(this, name);
    }

    /**
     * Finds a user by their username.
     *
     * @param name the username of the user
     * @return the user
     */
    public GHUser findUserByName(String name) {
        return GHUser.getUser(this, name);
    }


    /**
     * Returns the GitHub token.
     *
     * @return the GitHub token
     */
    public String getToken() {
        return token;
    }


    /**
     * Lists all global advisories.
     *
     * @return a list of all global advisories
     */
    public List<GHAdvisory> listGlobalAdvisories() {
        return fetchPaginatedData("/advisories", json -> new GHAdvisory(this, json), getToken());
    }

    /**
     * Gets a global advisory by its ID.
     *
     * @param id the ID of the advisory
     * @return the advisory
     */
    public GHAdvisory getGlobalAdvisories(String id) {
        String response = sendGetRequest("/advisories/" + id, getToken());
        assert response != null;
        return new GHAdvisory(this, new JSONObject(response));
    }

    /**
     * Lists all gitignore templates.
     *
     * @return a list of all gitignore templates
     */
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

    /**
     * Gets a gitignore template by its name.
     *
     * @param name the name of the template
     * @return the gitignore template
     */
    public GHGitignoreTemplate getGitignoreTemplate(String name) {
        String response = sendGetRequest(getBaseUrl() + "/gitignore/templates/" + name, getToken());
        assert response != null;
        return new GHGitignoreTemplate(new JSONObject(response));
    }

    /**
     * Lists all user advisories.
     *
     * @return a list of all user advisories
     */
    public SelfUser getSelfUser() {
        return SelfUser.getSelfUser(this);
    }

    public static Logger getLogger() {
        return logger;
    }
}