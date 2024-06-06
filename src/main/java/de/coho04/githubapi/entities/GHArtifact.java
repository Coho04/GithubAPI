package de.coho04.githubapi.entities;

import de.coho04.githubapi.bases.ClassBase;
import org.json.JSONObject;

public class GHArtifact extends ClassBase {

    public String name;
    public int size_in_bytes;
    public String url;
    public String archive_download_url;
    public boolean expired;
    public String created_at;
    public String expires_at;
    public String updated_at;
    public GHWorkflowRun workflow_run;

    public GHArtifact(JSONObject jsonObject) {
        super(jsonObject);
        this.name = getStringOrNull(jsonObject, "name");
        this.size_in_bytes = getIntOrNull(jsonObject, "size_in_bytes");
        this.url = getStringOrNull(jsonObject, "url");
        this.archive_download_url = getStringOrNull(jsonObject, "archive_download_url");
        this.expired = getBooleanOrNull(jsonObject, "expired");
        this.created_at = getStringOrNull(jsonObject, "created_at");
        this.expires_at = getStringOrNull(jsonObject, "expires_at");
        this.updated_at = getStringOrNull(jsonObject, "updated_at");
        this.workflow_run = new GHWorkflowRun(getJSONObjectOrNull(jsonObject, "workflow_run"));
    }
}
