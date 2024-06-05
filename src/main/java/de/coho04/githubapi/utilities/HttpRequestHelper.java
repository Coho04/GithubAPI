package de.coho04.githubapi.utilities;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class HttpRequestHelper {

    public static void sendPostRequest(String url, String githubToken, JSONObject jsonObject) {
        try {
            HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("Authorization", "Bearer " + githubToken);
            con.setDoOutput(true);
            con.getOutputStream().write(jsonObject.toString().getBytes());
            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_CREATED) {
                System.out.println("Failed to send POST request to: " + url);
                System.out.println("Response Message: " + con.getResponseMessage());
                System.out.println("Response Code: " + responseCode);
            }
        } catch (IOException exception) {
            System.out.println("Failed to send POST request to: " + url);
            System.out.println("ErrorMessage: " + exception.getMessage());
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static String sendGetRequest(String url, String githubToken) {
        try {
            HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    return in.lines().collect(Collectors.joining());
                }
            } else if (responseCode != HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Token: " + githubToken);
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("Response Message: " + con.getResponseMessage());
                System.out.println("Response Code: " + responseCode);
                return null;
            } else {
                return null;
            }
        } catch (IOException exception) {
            if (!exception.getMessage().contains("404")) {
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("ErrorMessage: " + exception.getMessage());
            }
        }
        return null;
    }

    public static String[] sendGetRequestWithLinkHeader(String url, String githubToken) {
        try {
            HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseBody;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    responseBody = in.lines().collect(Collectors.joining());
                }
                String linkHeader = con.getHeaderField("Link");
                return new String[]{responseBody, linkHeader};
            } else {
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("Response Code: " + responseCode);
                return new String[]{null, null};
            }
        } catch (IOException exception) {
            System.out.println("Failed to send GET request to: " + url);
            System.out.println("ErrorMessage: " + exception.getMessage());
            return new String[]{null, null};
        }
    }

    public static String extractNextPageUrl(String linkHeader) {
        if (linkHeader == null) {
            return null;
        }
        String[] links = linkHeader.split(", ");
        for (String link : links) {
            if (link.contains("rel=\"next\"")) {
                int start = link.indexOf('<') + 1;
                int end = link.indexOf('>');
                if (start != -1 && end != -1) {
                    return link.substring(start, end);
                }
            }
        }
        return null;
    }

    public static void sendDeleteRequest(String url, String githubToken) {
        try {
            HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Successfully sent DELETE request to: " + url);
            } else if (responseCode != HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Token: " + githubToken);
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("Response Message: " + con.getResponseMessage());
                System.out.println("Response Code: " + responseCode);
            } else {
                System.out.println("Failed to send DELETE request to: " + url);
                System.out.println("Response Code: " + responseCode);
            }
        } catch (IOException exception) {
            if (!exception.getMessage().contains("404")) {
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("ErrorMessage: " + exception.getMessage());
            }
        }
    }

    public static Boolean sendGetRequestWithResponseCode(String url , String githubToken, int responseCode) {
        try {
            HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            return con.getResponseCode() == responseCode;
        } catch (IOException exception) {
            if (!exception.getMessage().contains("404")) {
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("ErrorMessage: " + exception.getMessage());
            }
        }
        return false;
    }
}
