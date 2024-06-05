package de.coho04.githubapi.interfaces;

import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

public interface HttpRequestInterface {

    default void sendPostRequest(String url, String githubToken, JSONObject jsonObject) {
        HttpRequestHelper.sendPostRequest(url, githubToken, jsonObject);
    }

    default String sendGetRequest(String url, String githubToken) {
        return HttpRequestHelper.sendGetRequest(url, githubToken);
    }

    default void sendDeleteRequest(String url, String githubToken) {
        HttpRequestHelper.sendDeleteRequest(url, githubToken);
    }


    default String[] sendGetRequestWithLinkHeader(String url, String githubToken) {
        return HttpRequestHelper.sendGetRequestWithLinkHeader(url, githubToken);
    }

    default Boolean sendGetRequestWithResponseCode(String url, String githubToken, int responseCode) {
        return HttpRequestHelper.sendGetRequestWithResponseCode(url, githubToken, responseCode);
    }

    default String extractNextPageUrl(String linkHeader) {
        return HttpRequestHelper.extractNextPageUrl(linkHeader);
    }
}
