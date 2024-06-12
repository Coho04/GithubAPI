package io.github.coho04.githubapi.entities.repositories;

import io.github.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub file.
 * It provides methods for fetching data about the file such as its name, path, SHA, size, URLs, and type.
 */
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

    /**
     * Constructs a new GHFile instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the file data
     */
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

    /**
     * Returns the HTML URL of the file.
     *
     * @return the HTML URL of the file
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Returns the URL of the file.
     *
     * @return the URL of the file
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the name of the file.
     *
     * @return the name of the file
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the size of the file.
     *
     * @return the size of the file
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the download URL of the file.
     *
     * @return the download URL of the file
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Returns the Git URL of the file.
     *
     * @return the Git URL of the file
     */
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     * Returns the path of the file.
     *
     * @return the path of the file
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the SHA of the file.
     *
     * @return the SHA of the file
     */
    public String getSha() {
        return sha;
    }

    /**
     * Returns the type of the file.
     *
     * @return the type of the file
     */
    public String getType() {
        return type;
    }
}