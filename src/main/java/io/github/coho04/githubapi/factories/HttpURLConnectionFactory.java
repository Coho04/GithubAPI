package io.github.coho04.githubapi.factories;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

public class HttpURLConnectionFactory {

    /**
     * Creates a new HttpURLConnection instance for the given URL.
     *
     * @param url The URL for which the connection is to be established.
     * @return The created HttpURLConnection instance.
     * @throws IOException If an I/O exception occurs while opening the connection.
     */
    public HttpURLConnection createHttpURLConnection(String url) throws IOException {
        return (HttpURLConnection) URI.create(url).toURL().openConnection();
    }
}