package de.coho04.githubapi.interfaces;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class HttpRequestInterfaceTest {

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
}