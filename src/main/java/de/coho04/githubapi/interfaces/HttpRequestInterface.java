package de.coho04.githubapi.interfaces;

import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;

/**
 * This interface provides methods for sending HTTP requests.
 * It uses the HttpRequestHelper class to send the requests.
 */
public interface HttpRequestInterface {

    /**
     * Sends a POST request to the specified URL with the provided GitHub token and JSON object.
     *
     * @param url the URL
     * @param githubToken the GitHub token
     * @param jsonObject the JSON object
     */
    default void sendPostRequest(String url, String githubToken, JSONObject jsonObject) {
        HttpRequestHelper.sendPostRequest(url, githubToken, jsonObject);
    }

    /**
     * Sends a GET request to the specified URL with the provided GitHub token.
     *
     * @param url the URL
     * @param githubToken the GitHub token
     * @return the response body as a string
     */
    default String sendGetRequest(String url, String githubToken) {
        return HttpRequestHelper.sendGetRequest(url, githubToken);
    }

    /**
     * Sends a DELETE request to the specified URL with the provided GitHub token.
     *
     * @param url the URL
     * @param githubToken the GitHub token
     */
    default void sendDeleteRequest(String url, String githubToken) {
        HttpRequestHelper.sendDeleteRequest(url, githubToken);
    }

    /**
     * Sends a DELETE request to the specified URL with the provided GitHub token and expects a specific response code.
     *
     * @param url the URL
     * @param githubToken the GitHub token
     * @param responseCode the expected response code
     * @return true if the response code matches the expected response code, false otherwise
     */
    default Boolean sendDeleteRequestWithResponseCode(String url, String githubToken, int responseCode) {
        return HttpRequestHelper.sendDeleteRequestWithResponseCode(url, githubToken, responseCode);
    }

    /**
     * Sends a GET request to the specified URL with the provided GitHub token and returns the response body and the Link header.
     *
     * @param url the URL
     * @param githubToken the GitHub token
     * @return an array containing the response body and the Link header
     */
    default String[] sendGetRequestWithLinkHeader(String url, String githubToken) {
        return HttpRequestHelper.sendGetRequestWithLinkHeader(url, githubToken);
    }

    /**
     * Sends a GET request to the specified URL with the provided GitHub token and expects a specific response code.
     *
     * @param url the URL
     * @param githubToken the GitHub token
     * @param responseCode the expected response code
     * @return true if the response code matches the expected response code, false otherwise
     */
    default Boolean sendGetRequestWithResponseCode(String url, String githubToken, int responseCode) {
        return HttpRequestHelper.sendGetRequestWithResponseCode(url, githubToken, responseCode);
    }

    /**
     * Extracts the URL of the next page from the Link header.
     *
     * @param linkHeader the Link header
     * @return the URL of the next page
     */
    default String extractNextPageUrl(String linkHeader) {
        return HttpRequestHelper.extractNextPageUrl(linkHeader);
    }
}