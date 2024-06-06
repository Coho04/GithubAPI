package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHWorkflowRun extends GHBase {

    public int id;
    public int repositoryId;
    public int headRepositoryId;
    public String headBranch;
    public String headSha;


    public GHWorkflowRun(JSONObject jsonObject) {
        this.id = getIntOrNull(jsonObject, "id");
        this.repositoryId = getIntOrNull(jsonObject, "repository_id");
        this.headRepositoryId = getIntOrNull(jsonObject, "head_repository_id");
        this.headBranch = getStringOrNull(jsonObject, "head_branch");
        this.headSha = getStringOrNull(jsonObject, "head_sha");
    }

    public int getId() {
        return id;
    }

    public int getHeadRepositoryId() {
        return headRepositoryId;
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public String getHeadBranch() {
        return headBranch;
    }

    public String getHeadSha() {
        return headSha;
    }
}
