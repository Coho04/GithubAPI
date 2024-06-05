package de.coho04.githubapi.repositories;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;


@SuppressWarnings("unused")
public class GHFile extends GHBase {

    private final String name;
    private final String path;
    private final String sha;
    private final int size;
    private final String url;
    private final String htmlUrl;
    private final String gitUrl;
    private final String downloadUrl;
    private final String type;

    public GHFile(JSONObject jsonObject) {
        this.name = getStringOrNull(jsonObject, "name");
        this.path = getStringOrNull(jsonObject, "path");
        this.sha = getStringOrNull(jsonObject, "sha");
        this.size = getIntOrNull(jsonObject, "size");
        this.url = getStringOrNull(jsonObject, "url");
        this.htmlUrl = getStringOrNull(jsonObject, "html_url");
        this.gitUrl = getStringOrNull(jsonObject, "git_url");
        this.downloadUrl = getStringOrNull(jsonObject, "download_url");
        this.type = getStringOrNull(jsonObject, "type");
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getPath() {
        return path;
    }

    public String getSha() {
        return sha;
    }

    public String getType() {
        return type;
    }
}
