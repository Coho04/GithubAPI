package io.github.coho04.githubapi.builders;

import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.entities.GHPublicKey;
import io.github.coho04.githubapi.utilities.HttpRequestHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GHPublicKeyBuilderTest {

    private Github github;
    private GHPublicKeyBuilder publicKeyBuilder;

    @BeforeEach
    void setUp() {
        github = mock(Github.class);
        when(github.getToken()).thenReturn("test_token");

        publicKeyBuilder = new GHPublicKeyBuilder(github);
    }

    @Test
    void testSetTitle() {
        String newTitle = "New Public Key";
        publicKeyBuilder.setTitle(newTitle);
        assertEquals(newTitle, publicKeyBuilder.getTitle());
    }

    @Test
    void testSetKey() {
        String newKey = "ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...";
        publicKeyBuilder.setKey(newKey);
        assertEquals(newKey, publicKeyBuilder.getKey());
    }

    @Test
    void testGithub() {
        assertEquals(publicKeyBuilder.getGithub(), github);
    }

    @Test
    void testConstructor() {
        GHPublicKeyBuilder newPublicKeyBuilder = new GHPublicKeyBuilder("Test Public Key", "ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...", github);
        assertEquals("Test Public Key", newPublicKeyBuilder.getTitle());
        assertEquals("ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...", newPublicKeyBuilder.getKey());
        assertEquals(github, newPublicKeyBuilder.getGithub());
    }

    @Test
    void testBuildPublicKey() {
        try (MockedStatic<HttpRequestHelper> mockedStatic = Mockito.mockStatic(HttpRequestHelper.class)) {
            JSONObject expectedJson = new JSONObject()
                    .put("title", "Test Public Key")
                    .put("key", "ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...");

            mockedStatic.when(() -> HttpRequestHelper.sendPostRequest(anyString(), anyString(), any(JSONObject.class)))
                    .thenReturn("{\"id\": 1, \"title\": \"Test Public Key\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...\"}");

            publicKeyBuilder.setTitle("Test Public Key");
            publicKeyBuilder.setKey("ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...");
            GHPublicKey publicKey = publicKeyBuilder.build();
            assertNotNull(publicKey);
            assertEquals("ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...", publicKey.getKey());

            mockedStatic.verify(() -> HttpRequestHelper.sendPostRequest(
                    eq("https://api.github.com/user/keys"),
                    eq("test_token"),
                    argThat(json -> jsonSimilar(new JSONObject(json.toString()), expectedJson))
            ));
        }
    }

    // Helper method to compare JSON objects
    private boolean jsonSimilar(JSONObject actual, JSONObject expected) {
        if (actual == null || expected == null) return false;
        return actual.toString().equals(expected.toString());
    }
}
