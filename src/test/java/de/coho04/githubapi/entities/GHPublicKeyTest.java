package de.coho04.githubapi.entities;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GHPublicKeyTest {

    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        jsonObject = new JSONObject();
        jsonObject.put("key_id", "1");
        jsonObject.put("key", "ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...");
    }

    @Test
    void shouldReturnCorrectKeyId() {
        assertEquals("1", new GHPublicKey(jsonObject).getKeyId());
    }

    @Test
    void shouldReturnNullWhenKeyIdIsNotPresent() {
        jsonObject.remove("key_id");
        assertNull(new GHPublicKey(jsonObject).getKeyId());
    }

    @Test
    void shouldReturnCorrectKey() {
        assertEquals("ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAr6...", new GHPublicKey(jsonObject).getKey());
    }

    @Test
    void shouldReturnNullWhenKeyIsNotPresent() {
        jsonObject.remove("key");
        assertNull(new GHPublicKey(jsonObject).getKey());
    }
}