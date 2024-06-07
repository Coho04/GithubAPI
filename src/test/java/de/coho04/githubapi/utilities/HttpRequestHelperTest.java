package de.coho04.githubapi.utilities;

import de.coho04.githubapi.factories.HttpURLConnectionFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HttpRequestHelperTest {

    @Mock
    private HttpURLConnection mockConnection;

    @Mock
    private HttpURLConnectionFactory mockFactory;

    @Mock
    private OutputStream mockOutputStream;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        HttpRequestHelper.connectionFactory = mockFactory;
    }

    @Test
    public void testSendPostRequest_Success() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        String mockResponse = "{\"message\":\"success\"}";
        InputStream mockInputStream = new ByteArrayInputStream(mockResponse.getBytes());
        ByteArrayOutputStream mockOutputStream = new ByteArrayOutputStream();

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_CREATED);
        when(mockConnection.getInputStream()).thenReturn(mockInputStream);
        when(mockConnection.getOutputStream()).thenReturn(mockOutputStream);

        String result = HttpRequestHelper.sendPostRequest(url, githubToken, jsonObject);
        assertNotNull(result);
        assertEquals(mockResponse, result);

        verify(mockConnection).setRequestMethod("POST");
        verify(mockConnection).setRequestProperty("Accept", "application/vnd.github+json");
        verify(mockConnection).addRequestProperty("Authorization", "Bearer " + githubToken);
        verify(mockConnection).setDoOutput(true);
        verify(mockConnection).getOutputStream();
    }

    @Test
    public void testSendPostRequest_Failure() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);
        when(mockConnection.getOutputStream()).thenReturn(mockOutputStream);

        assertThrows(UnsupportedOperationException.class, () -> HttpRequestHelper.sendPostRequest(url, githubToken, jsonObject));

        verify(mockConnection).setRequestMethod("POST");
        verify(mockConnection).setRequestProperty("Accept", "application/vnd.github+json");
        verify(mockConnection).addRequestProperty("Authorization", "Bearer " + githubToken);
        verify(mockConnection).setDoOutput(true);
        verify(mockConnection).getOutputStream();
    }

    @Test
    public void testSendPostRequest_IOException() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        ByteArrayOutputStream mockOutputStream = new ByteArrayOutputStream();

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getOutputStream()).thenReturn(mockOutputStream);
        when(mockConnection.getResponseCode()).thenThrow(new IOException("Mocked IO Exception"));

        assertThrows(UnsupportedOperationException.class, () -> {
            HttpRequestHelper.sendPostRequest(url, githubToken, jsonObject);
        });

        verify(mockConnection).setRequestMethod("POST");
        verify(mockConnection).setRequestProperty("Accept", "application/vnd.github+json");
        verify(mockConnection).addRequestProperty("Authorization", "Bearer " + githubToken);
        verify(mockConnection).setDoOutput(true);
        verify(mockConnection).getOutputStream();
    }

    @Test
    public void testSendGetRequest_Success() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        String mockResponse = "{\"message\":\"success\"}";
        InputStream mockInputStream = new ByteArrayInputStream(mockResponse.getBytes());

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(mockConnection.getInputStream()).thenReturn(mockInputStream);

        String result = HttpRequestHelper.sendGetRequest(url, githubToken);
        assertNotNull(result);
        assertEquals(mockResponse, result);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendGetRequest_Failure() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);

        String result = HttpRequestHelper.sendGetRequest(url, githubToken);
        assertNull(result);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendGetRequest_IOException() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenThrow(new IOException("Mocked IO Exception"));

        String result = HttpRequestHelper.sendGetRequest(url, githubToken);
        assertNull(result);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendGetRequestWithLinkHeader_Success() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        String mockResponse = "{\"message\":\"success\"}";
        String mockLinkHeader = "<https://api.github.com/some/next>; rel=\"next\"";
        InputStream mockInputStream = new ByteArrayInputStream(mockResponse.getBytes());

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(mockConnection.getInputStream()).thenReturn(mockInputStream);
        when(mockConnection.getHeaderField("Link")).thenReturn(mockLinkHeader);

        String[] result = HttpRequestHelper.sendGetRequestWithLinkHeader(url, githubToken);
        assertNotNull(result);
        assertEquals(mockResponse, result[0]);
        assertEquals(mockLinkHeader, result[1]);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendGetRequestWithLinkHeader_Failure() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);

        String[] result = HttpRequestHelper.sendGetRequestWithLinkHeader(url, githubToken);
        assertNotNull(result);
        assertNull(result[0]);
        assertNull(result[1]);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendGetRequestWithLinkHeader_IOException() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenThrow(new IOException("Mocked IO Exception"));

        String[] result = HttpRequestHelper.sendGetRequestWithLinkHeader(url, githubToken);
        assertNotNull(result);
        assertNull(result[0]);
        assertNull(result[1]);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testExtractNextPageUrl() {
        String linkHeader = "<https://api.github.com/some/next>; rel=\"next\", <https://api.github.com/some/prev>; rel=\"prev\"";
        String expectedNextPageUrl = "https://api.github.com/some/next";

        String nextPageUrl = HttpRequestHelper.extractNextPageUrl(linkHeader);
        assertEquals(expectedNextPageUrl, nextPageUrl);

        linkHeader = "<https://api.github.com/some/prev>; rel=\"prev\"";
        nextPageUrl = HttpRequestHelper.extractNextPageUrl(linkHeader);
        assertNull(nextPageUrl);
    }

    @Test
    public void testSendDeleteRequest_Success() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NO_CONTENT);

        HttpRequestHelper.sendDeleteRequest(url, githubToken);

        verify(mockConnection).setRequestMethod("DELETE");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendDeleteRequest_Failure() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);

        HttpRequestHelper.sendDeleteRequest(url, githubToken);

        verify(mockConnection).setRequestMethod("DELETE");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendDeleteRequest_IOException() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenThrow(new IOException("Mocked IO Exception"));

        HttpRequestHelper.sendDeleteRequest(url, githubToken);

        verify(mockConnection).setRequestMethod("DELETE");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendDeleteRequestWithResponseCode_Success() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        int expectedResponseCode = HttpURLConnection.HTTP_NO_CONTENT;

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(expectedResponseCode);

        boolean result = HttpRequestHelper.sendDeleteRequestWithResponseCode(url, githubToken, expectedResponseCode);
        assertTrue(result);

        verify(mockConnection).setRequestMethod("DELETE");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendDeleteRequestWithResponseCode_Failure() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        int expectedResponseCode = HttpURLConnection.HTTP_NO_CONTENT;

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);

        boolean result = HttpRequestHelper.sendDeleteRequestWithResponseCode(url, githubToken, expectedResponseCode);
        assertFalse(result);

        verify(mockConnection).setRequestMethod("DELETE");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendDeleteRequestWithResponseCode_IOException() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        int expectedResponseCode = HttpURLConnection.HTTP_NO_CONTENT;

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenThrow(new IOException("Mocked IO Exception"));

        boolean result = HttpRequestHelper.sendDeleteRequestWithResponseCode(url, githubToken, expectedResponseCode);
        assertFalse(result);

        verify(mockConnection).setRequestMethod("DELETE");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendGetRequestWithResponseCode_Success() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        int expectedResponseCode = HttpURLConnection.HTTP_OK;

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(expectedResponseCode);

        boolean result = HttpRequestHelper.sendGetRequestWithResponseCode(url, githubToken, expectedResponseCode);
        assertTrue(result);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendGetRequestWithResponseCode_Failure() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        int expectedResponseCode = HttpURLConnection.HTTP_OK;

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);

        boolean result = HttpRequestHelper.sendGetRequestWithResponseCode(url, githubToken, expectedResponseCode);
        assertFalse(result);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendGetRequestWithResponseCode_IOException() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        int expectedResponseCode = HttpURLConnection.HTTP_OK;

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenThrow(new IOException("Mocked IO Exception"));

        boolean result = HttpRequestHelper.sendGetRequestWithResponseCode(url, githubToken, expectedResponseCode);
        assertFalse(result);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "Bearer " + githubToken);
    }

    @Test
    public void testSendPutRequest_Success() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(mockConnection.getOutputStream()).thenReturn(mockOutputStream);

        HttpRequestHelper.sendPutRequest(url, githubToken, jsonObject);

        verify(mockConnection).setRequestMethod("PUT");
        verify(mockConnection).setRequestProperty("Content-Type", "application/json");
        verify(mockConnection).addRequestProperty("Authorization", "Bearer " + githubToken);
        verify(mockConnection).setDoOutput(true);
        verify(mockConnection).getOutputStream();
    }

    @Test
    public void testSendPutRequest_Failure() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);
        when(mockConnection.getOutputStream()).thenReturn(mockOutputStream);

        HttpRequestHelper.sendPutRequest(url, githubToken, jsonObject);

        verify(mockConnection).setRequestMethod("PUT");
        verify(mockConnection).setRequestProperty("Content-Type", "application/json");
        verify(mockConnection).addRequestProperty("Authorization", "Bearer " + githubToken);
        verify(mockConnection).setDoOutput(true);
        verify(mockConnection).getOutputStream();
    }

    @Test
    public void testSendPutRequest_IOException() throws IOException {
        String url = "https://api.github.com/some/endpoint";
        String githubToken = "test_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        when(mockFactory.createHttpURLConnection(url)).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenThrow(new IOException("Mocked IO Exception"));
        when(mockConnection.getOutputStream()).thenReturn(mockOutputStream);

        HttpRequestHelper.sendPutRequest(url, githubToken, jsonObject);

        verify(mockConnection).setRequestMethod("PUT");
        verify(mockConnection).setRequestProperty("Content-Type", "application/json");
        verify(mockConnection).addRequestProperty("Authorization", "Bearer " + githubToken);
        verify(mockConnection).setDoOutput(true);
        verify(mockConnection).getOutputStream();
    }

    @Test
    public void testExtractNextPageUrlWithNull() {
        String nextPageUrl = HttpRequestHelper.extractNextPageUrl(null);
        assertNull(nextPageUrl);
    }
}