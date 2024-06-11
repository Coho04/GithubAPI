package de.coho04.githubapi.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class HttpURLConnectionFactoryTest {

    private HttpURLConnectionFactory factory;
    private HttpURLConnection mockConnection;
    private URL mockUrl;

    @BeforeEach
    public void setUp() throws IOException {
        factory = new HttpURLConnectionFactory();
        mockConnection = mock(HttpURLConnection.class);
        mockUrl = mock(URL.class);

        // Mocking the URL to return the mockConnection when openConnection is called
        when(mockUrl.openConnection()).thenReturn(mockConnection);
    }

    @Test
    void testCreateHttpURLConnection() throws IOException {
        String urlString = "https://api.github.com/some/endpoint";

        // Using PowerMockito to mock the static method URI.create(url).toURL()
        mockStatic(URI.class);
        URI mockUri = mock(URI.class);
        when(URI.create(urlString)).thenReturn(mockUri);
        when(mockUri.toURL()).thenReturn(mockUrl);

        HttpURLConnection connection = factory.createHttpURLConnection(urlString);
        assertNotNull(connection);
        assertEquals(mockConnection, connection);
    }
}
