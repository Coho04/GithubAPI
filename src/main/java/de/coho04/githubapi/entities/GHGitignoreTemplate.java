package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub Gitignore Template.
 * It provides methods for fetching data about the gitignore template such as its name and source.
 */
public class GHGitignoreTemplate extends GHBase {

    private final String name;
    private final String source;

    /**
     * Constructs a new GHGitignoreTemplate instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the gitignore template data
     */
    public GHGitignoreTemplate(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.source = getStringOrNull(jsonObject, "source");
    }

    /**
     * Returns the name of the gitignore template.
     *
     * @return the name of the gitignore template
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the source of the gitignore template.
     *
     * @return the source of the gitignore template
     */
    public String getSource() {
        return source;
    }
}