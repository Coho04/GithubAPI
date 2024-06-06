package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GHPublicKeyBuilderTest {

    @Mock
    private Github github;

    private GHPublicKeyBuilder builder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        builder = new GHPublicKeyBuilder(github);
    }

    @Test
    @DisplayName("Should return the same builder instance after setting title and key")
    public void returnsSameBuilderInstanceAfterSettingTitleAndKey() {
        GHPublicKeyBuilder resultTitle = builder.setTitle("testTitle");
        GHPublicKeyBuilder resultKey = builder.setKey("testKey");

        assertEquals(builder, resultTitle);
        assertEquals(builder, resultKey);
    }
}