package de.coho04.githubapi.bases;

import de.coho04.githubapi.interfaces.HttpRequestInterface;
import de.coho04.githubapi.interfaces.JSONHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GHBase implements JSONHelper, HttpRequestInterface {

    public JSONObject toJSONObject() {
        return new JSONObject();
    }

    public static String getBaseUrl() {
        return "https://api.github.com";
    }

    protected <T> List<T> fetchPaginatedData(String endpoint, Function<JSONObject, T> mapper, String token) {
        List<T> result = new ArrayList<>();
        String url = getBaseUrl() + endpoint + "?per_page=100";
        while (url != null) {
            String[] responseAndLink = sendGetRequestWithLinkHeader(url, token);
            String response = responseAndLink[0];
            JSONArray json = new JSONArray(response);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = json.getJSONObject(i);
                result.add(mapper.apply(jsonObject));
            }
            String linkHeader = responseAndLink[1];
            url = extractNextPageUrl(linkHeader);
        }
        return result;
    }
}
