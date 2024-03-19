package de.goldendeveloper.githubapi.interfaces;

import de.goldendeveloper.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

public interface HttpRequestInterface {

    default void sendPostRequest(String url, String githubToken, JSONObject jsonObject) {
        HttpRequestHelper.sendPostRequest(url, githubToken, jsonObject);
    }

    default String sendGetRequest(String url, String githubToken) {
        return HttpRequestHelper.sendGetRequest(url, githubToken);
    }

    default String[] sendGetRequestWithLinkHeader(String url, String githubToken) {
        return HttpRequestHelper.sendGetRequestWithLinkHeader(url, githubToken);
    }

    default String extractNextPageUrl(String linkHeader) {
        return HttpRequestHelper.extractNextPageUrl(linkHeader);
    }
}
