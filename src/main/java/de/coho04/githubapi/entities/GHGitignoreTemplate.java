package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

public class GHGitignoreTemplate extends GHBase {

    public String name;
    public String source;

    public GHGitignoreTemplate(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.source = getStringOrNull(jsonObject, "source");
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }
}
