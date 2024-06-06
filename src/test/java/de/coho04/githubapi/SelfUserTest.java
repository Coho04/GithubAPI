package de.coho04.githubapi;
import static org.mockito.Mockito.*;

import java.net.HttpURLConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SelfUserTest {

    @Mock
    private Github mockGithub;

    @Mock
    private HttpURLConnection mockConnection;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockGithub.getToken()).thenReturn("mocktoken");
    }

    @Test
    void testGetSelfUser() throws Exception {
//        // Simulierte Antwort
//        String mockResponse = "{\"login\":\"mockuser\",\"id\":123456}";
//        InputStream mockInputStream = new ByteArrayInputStream(mockResponse.getBytes());
//
//        // Mock für die HttpURLConnection
//        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
//        when(mockConnection.getInputStream()).thenReturn(mockInputStream);
//
//        // Mock für die HttpRequestHelper.sendGetRequest Methode
//        PowerMockito.when(HttpRequestHelper.sendGetRequest(anyString(), anyString())).thenCallRealMethod();
//        PowerMockito.whenNew(HttpURLConnection.class).withAnyArguments().thenReturn(mockConnection);
//
//        // Tatsächliches Ergebnis
//        SelfUser actualUser = SelfUser.getSelfUser(mockGithub);
//
//        // Überprüfen, ob die Rückgabe korrekt ist
//        assertNotNull(actualUser);
//        assertEquals("mockuser", actualUser.getLogin());
//        assertEquals(123456, actualUser.getId());
    }
}
