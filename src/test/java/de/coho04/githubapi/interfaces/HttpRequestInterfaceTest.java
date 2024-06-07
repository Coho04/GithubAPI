package de.coho04.githubapi.interfaces;

import de.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class HttpRequestInterfaceTest implements HttpRequestInterface {

    @Test
    void shouldSendPostRequest() {
        HttpRequestInterface httpRequestInterface = Mockito.mock(HttpRequestInterface.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        httpRequestInterface.sendPostRequest("https://api.github.com", "githubToken", jsonObject);

        verify(httpRequestInterface, times(1)).sendPostRequest("https://api.github.com", "githubToken", jsonObject);
    }

    @Test
    void shouldSendGetRequest() {
        HttpRequestInterface httpRequestInterface = Mockito.mock(HttpRequestInterface.class);

        httpRequestInterface.sendGetRequest("https://api.github.com", "githubToken");

        verify(httpRequestInterface, times(1)).sendGetRequest("https://api.github.com", "githubToken");
    }

    @Test
    void shouldSendGetRequestWithLinkHeader() {
        HttpRequestInterface httpRequestInterface = Mockito.mock(HttpRequestInterface.class);

        httpRequestInterface.sendGetRequestWithLinkHeader("https://api.github.com", "githubToken");

        verify(httpRequestInterface, times(1)).sendGetRequestWithLinkHeader("https://api.github.com", "githubToken");
    }

    @Test
    void shouldExtractNextPageUrl() {
        HttpRequestInterface httpRequestInterface = Mockito.mock(HttpRequestInterface.class);
        String linkHeader = "<https://api.github.com/user/repos?page=3&per_page=100>; rel=\"next\"," +
                " <https://api.github.com/user/repos?page=50&per_page=100>; rel=\"last\"";

        httpRequestInterface.extractNextPageUrl(linkHeader);

        verify(httpRequestInterface, times(1)).extractNextPageUrl(linkHeader);
    }

    @Test
    void testSendPostRequest() {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");
        String expectedResponse = "{\"message\":\"success\"}";

        try (MockedStatic<HttpRequestHelper> mockedHttpRequestHelper = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedHttpRequestHelper.when(() -> HttpRequestHelper.sendPostRequest(url, githubToken, jsonObject))
                    .thenReturn(expectedResponse);

            String response = sendPostRequest(url, githubToken, jsonObject);
            assertEquals(expectedResponse, response);

            mockedHttpRequestHelper.verify(() -> HttpRequestHelper.sendPostRequest(url, githubToken, jsonObject));
        }
    }

    @Test
    void testSendGetRequest() {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        String expectedResponse = "{\"message\":\"success\"}";

        try (MockedStatic<HttpRequestHelper> mockedHttpRequestHelper = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedHttpRequestHelper.when(() -> HttpRequestHelper.sendGetRequest(url, githubToken))
                    .thenReturn(expectedResponse);

            String response = sendGetRequest(url, githubToken);
            assertEquals(expectedResponse, response);

            mockedHttpRequestHelper.verify(() -> HttpRequestHelper.sendGetRequest(url, githubToken));
        }
    }

    @Test
    void testSendDeleteRequest() {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        try (MockedStatic<HttpRequestHelper> mockedHttpRequestHelper = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedHttpRequestHelper.when(() -> HttpRequestHelper.sendDeleteRequest(url, githubToken)).thenAnswer(invocation -> null);

            sendDeleteRequest(url, githubToken);

            mockedHttpRequestHelper.verify(() -> HttpRequestHelper.sendDeleteRequest(url, githubToken));
        }
    }

    @Test
    void testSendDeleteRequestWithResponseCode() {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        int responseCode = 204;

        try (MockedStatic<HttpRequestHelper> mockedHttpRequestHelper = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedHttpRequestHelper.when(() -> HttpRequestHelper.sendDeleteRequestWithResponseCode(url, githubToken, responseCode))
                    .thenReturn(true);

            boolean result = sendDeleteRequestWithResponseCode(url, githubToken, responseCode);
            assertTrue(result);

            mockedHttpRequestHelper.verify(() -> HttpRequestHelper.sendDeleteRequestWithResponseCode(url, githubToken, responseCode));
        }
    }

    @Test
    void testSendGetRequestWithLinkHeader() {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        String[] expectedResponse = {"{\"message\":\"success\"}", "<https://api.github.com/some/next>; rel=\"next\""};

        try (MockedStatic<HttpRequestHelper> mockedHttpRequestHelper = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedHttpRequestHelper.when(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(url, githubToken))
                    .thenReturn(expectedResponse);

            String[] response = sendGetRequestWithLinkHeader(url, githubToken);
            assertEquals(expectedResponse, response);

            mockedHttpRequestHelper.verify(() -> HttpRequestHelper.sendGetRequestWithLinkHeader(url, githubToken));
        }
    }

    @Test
    void testSendGetRequestWithResponseCode() {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        int responseCode = 200;

        try (MockedStatic<HttpRequestHelper> mockedHttpRequestHelper = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedHttpRequestHelper.when(() -> HttpRequestHelper.sendGetRequestWithResponseCode(url, githubToken, responseCode))
                    .thenReturn(true);

            boolean result = sendGetRequestWithResponseCode(url, githubToken, responseCode);
            assertTrue(result);

            mockedHttpRequestHelper.verify(() -> HttpRequestHelper.sendGetRequestWithResponseCode(url, githubToken, responseCode));
        }
    }

    @Test
    void testExtractNextPageUrl() {
        String linkHeader = "<https://api.github.com/some/next>; rel=\"next\", <https://api.github.com/some/prev>; rel=\"prev\"";
        String expectedNextPageUrl = "https://api.github.com/some/next";

        try (MockedStatic<HttpRequestHelper> mockedHttpRequestHelper = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedHttpRequestHelper.when(() -> HttpRequestHelper.extractNextPageUrl(linkHeader))
                    .thenReturn(expectedNextPageUrl);

            String nextPageUrl = extractNextPageUrl(linkHeader);
            assertEquals(expectedNextPageUrl, nextPageUrl);

            mockedHttpRequestHelper.verify(() -> HttpRequestHelper.extractNextPageUrl(linkHeader));
        }
    }

    @Test
    void testSendPutRequest() {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        try (MockedStatic<HttpRequestHelper> mockedHttpRequestHelper = Mockito.mockStatic(HttpRequestHelper.class)) {
            mockedHttpRequestHelper.when(() -> HttpRequestHelper.sendPutRequest(url, githubToken, jsonObject)).thenAnswer(invocation -> null);

            sendPutRequest(url, githubToken, jsonObject);

            mockedHttpRequestHelper.verify(() -> HttpRequestHelper.sendPutRequest(url, githubToken, jsonObject));
        }
    }
}
