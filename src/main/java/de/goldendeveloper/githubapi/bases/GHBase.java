package de.goldendeveloper.githubapi.bases;

import de.goldendeveloper.githubapi.interfaces.HttpRequestInterface;
import de.goldendeveloper.githubapi.interfaces.JSONHelper;
import org.json.JSONObject;

public class GHBase implements JSONHelper, HttpRequestInterface {

    public JSONObject toJSONObject() {
        return new JSONObject();
    }

    public static String getBaseUrl() {
        return "https://api.github.com";
    }
}
