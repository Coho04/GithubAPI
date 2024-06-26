package io.github.coho04.githubapi.bases;

import io.github.coho04.githubapi.interfaces.HttpRequestInterface;
import io.github.coho04.githubapi.interfaces.JSONHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * GHBase is a class that implements JSONHelper and HttpRequestInterface. It represents a base class for GitHub objects.
 * It contains several methods such as toJSONObject, getBaseUrl, and fetchPaginatedData.
 * This class serves as a foundation for other GitHub-related classes, providing common functionality for HTTP requests and JSON handling.
 *
 * @author Coho04
 * @version 1.0
 * @since 2024-1.2
 */
public class GHBase implements JSONHelper, HttpRequestInterface {

    /**
     * Returns a new JSONObject.
     *
     * @return a new JSONObject
     */
    public JSONObject toJSONObject() {
        return new JSONObject();
    }

    /**
     * Returns the base URL for the GitHub API.
     *
     * @return the base URL for the GitHub API
     */
    public static String getBaseUrl() {
        return "https://api.github.com";
    }

    /**
     * Fetches paginated data from a given endpoint and maps the data to a list of objects of type T.
     *
     * @param <T>      the type of objects in the returned list
     * @param endpoint the endpoint to fetch data from
     * @param mapper   a function that maps a JSONObject to an object of type T
     * @param token    the authentication token to use for the request
     * @param params   additional parameters to include in the request URL
     * @return a list of objects of type T representing the fetched data
     */
    protected <T> List<T> fetchPaginatedData(String endpoint, Function<JSONObject, T> mapper, String token, String params) {
        return fetchPaginatedData(getBaseUrl(), endpoint + "?per_page=100" + params, mapper, token);
    }

    /**
     * Fetches paginated data from a given endpoint and maps the data to a list of objects of type T.
     *
     * @param <T>      the type of objects in the returned list
     * @param endpoint the endpoint to fetch data from
     * @param mapper   a function that maps a JSONObject to an object of type T
     * @param token    the authentication token to use for the request
     * @return a list of objects of type T representing the fetched data
     */
    protected <T> List<T> fetchPaginatedData(String endpoint, Function<JSONObject, T> mapper, String token) {
        return fetchPaginatedData(getBaseUrl(), endpoint, mapper, token);
    }

    /**
     * Fetches paginated data from a given URL and maps the data to a list of objects of type T.
     *
     * @param <T>      the type of objects in the returned list
     * @param url      the URL to fetch data from
     * @param endpoint the endpoint to fetch data from if the URL is null
     * @param mapper   a function that maps a JSONObject to an object of type T
     * @param token    the authentication token to use for the request
     * @return a list of objects of type T representing the fetched data
     */
    protected <T> List<T> fetchPaginatedData(String url, String endpoint, Function<JSONObject, T> mapper, String token) {
        List<T> result = new ArrayList<>();
        url = Objects.requireNonNullElseGet(url, GHBase::getBaseUrl) + endpoint + "?per_page=100";
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

    /**
     * Fetches array data from a given endpoint and maps the data to a list of objects of type T.
     *
     * @param <T>        the type of objects in the returned list
     * @param endpoint   the endpoint to fetch data from
     * @param mapper     a function that maps a JSONObject to an object of type T
     * @param token      the authentication token to use for the request
     * @param entryPoint the entry point in the JSON response to extract the array data from
     * @return a list of objects of type T representing the fetched data
     */
    protected <T> List<T> fetchArrayData(String endpoint, Function<JSONObject, T> mapper, String token, String entryPoint) {
        return fetchArrayData(getBaseUrl(), endpoint, mapper, token, entryPoint);
    }

    /**
     * Fetches array data from a given URL and maps the data to a list of objects of type T.
     *
     * @param <T>        the type of objects in the returned list
     * @param url        the URL to fetch data from
     * @param endpoint   the endpoint to fetch data from if the URL is null
     * @param mapper     a function that maps a JSONObject to an object of type T
     * @param token      the authentication token to use for the request
     * @param entryPoint the entry point in the JSON response to extract the array data from
     * @return a list of objects of type T representing the fetched data
     */
    protected <T> List<T> fetchArrayData(String url, String endpoint, Function<JSONObject, T> mapper, String token, String entryPoint) {
        List<T> result = new ArrayList<>();
        url = Objects.requireNonNullElseGet(url, GHBase::getBaseUrl) + endpoint;
        String response = sendGetRequest(url, token);
        if (response != null) {
            JSONObject json = new JSONObject(response);
            JSONArray jsonArray = json.getJSONArray(entryPoint);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                result.add(mapper.apply(jsonObject));
            }
        }
        return result;
    }
}
