package de.coho04.githubapi.utilities;

import de.coho04.githubapi.factories.HttpURLConnectionFactory;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;

/**
 * This class provides helper methods for sending HTTP requests.
 */
public class HttpRequestHelper {

    public static HttpURLConnectionFactory connectionFactory = new HttpURLConnectionFactory();

    /**
     * Sends a POST request to the specified URL with the provided GitHub token and JSON object.
     *
     * @param url         the URL to send the request to
     * @param githubToken the GitHub token
     * @param jsonObject  the JSON object to send
     */
    public static String sendPostRequest(String url, String githubToken, JSONObject jsonObject) {
        try {
            HttpURLConnection con = connectionFactory.createHttpURLConnection(url);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/vnd.github+json");
            con.addRequestProperty("Authorization", "Bearer " + githubToken);
            con.setDoOutput(true);
            con.getOutputStream().write(jsonObject.toString().getBytes());
            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_CREATED) {
                throw new UnsupportedOperationException("Not supported yet. Response Code" + responseCode);
            } else {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    return in.lines().collect(Collectors.joining());
                }
            }
        } catch (IOException exception) {
            System.out.println("Failed to send POST request to: " + url);
            System.out.println("ErrorMessage: " + exception.getMessage());
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * Sends a GET request to the specified URL with the provided GitHub token.
     *
     * @param url         the URL to send the request to
     * @param githubToken the GitHub token
     * @return the response body as a string
     */
    public static String sendGetRequest(String url, String githubToken) {
        try {
            HttpURLConnection con = connectionFactory.createHttpURLConnection(url);
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    return in.lines().collect(Collectors.joining());
                }
            } else if (responseCode != HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("Response Message: " + con.getResponseMessage());
                System.out.println("Response Code: " + responseCode);
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

    /**
     * Sends a GET request to the specified URL with the provided GitHub token and returns the response body and the Link header.
     *
     * @param url         the URL to send the request to
     * @param githubToken the GitHub token
     * @return an array containing the response body and the Link header
     */
    public static String[] sendGetRequestWithLinkHeader(String url, String githubToken) {
        try {
            HttpURLConnection con = connectionFactory.createHttpURLConnection(url);
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseBody;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    responseBody = in.lines().collect(Collectors.joining());
                }
                return new String[]{responseBody, con.getHeaderField("Link")};
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

    /**
     * Extracts the URL of the next page from the Link header.
     *
     * @param linkHeader the Link header
     * @return the URL of the next page
     */
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

    /**
     * Sends a DELETE request to the specified URL with the provided GitHub token.
     *
     * @param url         the URL to send the request to
     * @param githubToken the GitHub token
     */
    public static void sendDeleteRequest(String url, String githubToken) {
        try {
            HttpURLConnection con = connectionFactory.createHttpURLConnection(url);
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            int responseCode = con.getResponseCode();
            if (responseCode >= 300 || responseCode < 200) {
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

    /**
     * Sends a DELETE request to the specified URL with the provided GitHub token and checks if the response code matches the expected response code.
     *
     * @param url          the URL to send the request to
     * @param githubToken  the GitHub token
     * @param responseCode the expected response code
     * @return true if the response code matches the expected response code, false otherwise
     */
    public static boolean sendDeleteRequestWithResponseCode(String url, String githubToken, int responseCode) {
        try {
            HttpURLConnection con = connectionFactory.createHttpURLConnection(url);
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            return responseCode == con.getResponseCode();
        } catch (IOException exception) {
            if (!exception.getMessage().contains("404")) {
                System.out.println("Failed to send GET request to: " + url);
                System.out.println("ErrorMessage: " + exception.getMessage());
            }
        }
        return false;
    }

    /**
     * Sends a GET request to the specified URL with the provided GitHub token and checks if the response code matches the expected response code.
     *
     * @param url          the URL to send the request to
     * @param githubToken  the GitHub token
     * @param responseCode the expected response code
     * @return true if the response code matches the expected response code, false otherwise
     */
    public static boolean sendGetRequestWithResponseCode(String url, String githubToken, int responseCode) {
        try {
            HttpURLConnection con = connectionFactory.createHttpURLConnection(url);
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

    /**
     * Sends a PUT request to the specified URL with the provided GitHub token and JSON object.
     *
     * @param url         the URL to send the request to
     * @param githubToken the GitHub token
     * @param jsonObject  the JSON object to send
     */
    public static void sendPutRequest(String url, String githubToken, JSONObject jsonObject) {
        try {
            HttpURLConnection con = connectionFactory.createHttpURLConnection(url);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("Authorization", "Bearer " + githubToken);
            con.setDoOutput(true);
            if (jsonObject != null) {
                con.getOutputStream().write(jsonObject.toString().getBytes());
            }
            int responseCode = con.getResponseCode();
            if (responseCode >= 300 || responseCode < 200) {
                System.out.println("Failed to send PUT request to: " + url);
                System.out.println("Response Code: " + responseCode);
            }
        } catch (IOException exception) {
            System.out.println("Failed to send PUT request to: " + url);
            System.out.println("ErrorMessage: " + exception.getMessage());
        }
    }

    /**
     * Sends a PATCH request to the specified URL with the provided GitHub token and JSON object.
     *
     * @param url         the URL to send the request to
     * @param githubToken the GitHub token
     * @param jsonObject  the JSON object to send
     */
    public static void sendPatchRequest(String url, String githubToken, JSONObject jsonObject) {
        try {
            System.out.println("Sending PATCH request to: " + url);
            HttpURLConnection con = connectionFactory.createHttpURLConnection(url);

            // Using reflection to enable PATCH method
            con.setRequestMethod("POST");
            con.setRequestProperty("X-HTTP-Method-Override", "PATCH");

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + githubToken);
            con.setDoOutput(true);
            if (jsonObject != null) {
                con.getOutputStream().write(jsonObject.toString().getBytes());
            }
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if (responseCode >= 300 || responseCode < 200) {
                System.out.println("Failed to send PATCH request to: " + url);
                System.out.println("Response Code: " + responseCode);
            }
        } catch (IOException exception) {
            System.out.println("Failed to send PATCH request to: " + url);
            System.out.println("ErrorMessage: " + exception.getMessage());
        }
    }
}
