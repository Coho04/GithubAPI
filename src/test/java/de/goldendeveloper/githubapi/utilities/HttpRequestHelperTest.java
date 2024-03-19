package de.goldendeveloper.githubapi.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpRequestHelperTest {

    @Test
    public void shouldExtractNextPageUrlSuccessfully() {
        String linkHeader = "<https://api.github.com/user/repos?page=3&per_page=100>; rel=\"next\", <https://api.github.com/user/repos?page=50&per_page=100>; rel=\"last\"";
        String expectedUrl = "https://api.github.com/user/repos?page=3&per_page=100";
        String actualUrl = HttpRequestHelper.extractNextPageUrl(linkHeader);
        assertEquals(expectedUrl, actualUrl, "The extracted URL should match the expected next page URL.");
    }
}
