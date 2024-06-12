package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub Workflow Run.
 * It provides methods for fetching data about the workflow run such as its id, repository id, head repository id, head branch, and head SHA.
 */
public class GHWorkflowRun extends GHBase {

    private final int id;
    private final int repositoryId;
    private final int headRepositoryId;
    private final String headBranch;
    private final String headSha;

    /**
     * Constructs a new GHWorkflowRun instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the workflow run data
     */
    public GHWorkflowRun(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.repositoryId = getIntOrNull(jsonObject, "repository_id");
        this.headRepositoryId = getIntOrNull(jsonObject, "head_repository_id");
        this.headBranch = getStringOrNull(jsonObject, "head_branch");
        this.headSha = getStringOrNull(jsonObject, "head_sha");
    }

    /**
     * Returns the id of the workflow run.
     *
     * @return the id of the workflow run
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the id of the head repository of the workflow run.
     *
     * @return the id of the head repository
     */
    public int getHeadRepositoryId() {
        return headRepositoryId;
    }

    /**
     * Returns the id of the repository of the workflow run.
     *
     * @return the id of the repository
     */
    public int getRepositoryId() {
        return repositoryId;
    }

    /**
     * Returns the name of the head branch of the workflow run.
     *
     * @return the name of the head branch
     */
    public String getHeadBranch() {
        return headBranch;
    }

    /**
     * Returns the SHA of the head commit of the workflow run.
     *
     * @return the SHA of the head commit
     */
    public String getHeadSha() {
        return headSha;
    }
}