package io.github.coho04.githubapi.entities.repositories;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.bases.GHBase;
import io.github.coho04.githubapi.builders.GHFileBuilder;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * This class represents a GitHub file.
 * It provides methods for fetching data about the file such as its name, path, SHA, size, URLs, and type.
 */
public class GHFile extends GHBase {

    private final Github github;
    private final GHRepository repository;
    private final String name;
    private final String path;
    private final String sha;
    private final int size;
    private final String url;
    private final String htmlUrl;
    private final String gitUrl;
    private final String downloadUrl;
    private final String type;
    private String content;

    /**
     * Constructs a new GHFile instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the file data
     */
    public GHFile(Github github, JSONObject jsonObject, GHRepository repository) {
        this.github = github;
        this.repository = repository;
        this.name = getStringOrNull(jsonObject, "name");
        this.path = getStringOrNull(jsonObject, "path");
        this.sha = getStringOrNull(jsonObject, "sha");
        this.size = getIntOrNull(jsonObject, "size");
        this.url = getStringOrNull(jsonObject, "url");
        this.htmlUrl = getStringOrNull(jsonObject, "html_url");
        this.gitUrl = getStringOrNull(jsonObject, "git_url");
        this.downloadUrl = getStringOrNull(jsonObject, "download_url");
        this.type = getStringOrNull(jsonObject, "type");
        if (jsonObject.has("content")) {
            String encodedContent = getStringOrNull(jsonObject, "content");
            if (encodedContent != null) {
                encodedContent = encodedContent.replaceAll("\\s+", "");
                byte[] decodedBytes = Base64.getDecoder().decode(encodedContent);
                this.content = new String(decodedBytes, StandardCharsets.UTF_8);
            }
        }
    }

    /**
     * Creates a new GHFileBuilder instance for updating the file.
     * The GHFileBuilder is initialized with the repository, GitHub instance, and SHA of the file.
     *
     * @return a new GHFileBuilder instance
     */
    public GHFileBuilder updateFile() {
        return new GHFileBuilder(repository, github, this.sha);
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


    /**
     * Returns the content of the file.
     * The content is encoded in Base64 format.
     *
     * @return the content of the file
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the GitHub instance associated with this file.
     * The GitHub instance is used to perform operations related to GitHub such as fetching and updating data.
     *
     * @return the GitHub instance associated with this file
     */
    public Github getGithub() {
        return github;
    }

    /**
     * Returns the GHRepository instance associated with this file.
     * The GHRepository instance represents the repository that this file belongs to.
     *
     * @return the GHRepository instance associated with this file
     */
    public GHRepository getRepository() {
        return repository;
    }
}