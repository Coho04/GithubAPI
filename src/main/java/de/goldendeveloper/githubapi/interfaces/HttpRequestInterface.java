package de.goldendeveloper.githubapi.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public interface HttpRequestInterface {

    default void sendPostRequest(String url, String body) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    default String sendGetRequest(String url, String githubToken) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    return in.lines().collect(Collectors.joining());
                }
            } else {
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("Response Code: " + responseCode);
                return null;
            }
        } catch (IOException exception) {
            System.out.println("Failed to send GET request to: " + url);
            System.out.println("ErrorMessage: " + exception.getMessage());
        }
        return null;
    }
}
