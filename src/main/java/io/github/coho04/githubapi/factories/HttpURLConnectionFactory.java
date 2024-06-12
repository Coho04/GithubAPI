package io.github.coho04.githubapi.factories;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

public class HttpURLConnectionFactory {
    public HttpURLConnection createHttpURLConnection(String url) throws IOException {
        return (HttpURLConnection) URI.create(url).toURL().openConnection();
    }
}