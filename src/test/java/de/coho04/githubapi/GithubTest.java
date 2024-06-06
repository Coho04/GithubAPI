package de.coho04.githubapi;

import de.coho04.githubapi.entities.GHOrganisation;
import de.coho04.githubapi.entities.GHUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GithubTest {

    @Test
    void shouldFindOrganisationByName() {
        Github github = Mockito.mock(Github.class);
        GHOrganisation mockOrganisation = Mockito.mock(GHOrganisation.class);
        when(github.findOrganisationByName("orgName")).thenReturn(mockOrganisation);
        GHOrganisation result = github.findOrganisationByName("orgName");
        assertEquals(mockOrganisation, result);
    }

    @Test
    void shouldFindUserByName() {
        Github github = Mockito.mock(Github.class);
        GHUser mockUser = Mockito.mock(GHUser.class);
        when(github.findUserByName("userName")).thenReturn(mockUser);
        GHUser result = github.findUserByName("userName");
        assertEquals(mockUser, result);
    }

    @Test
    void shouldReturnToken() {
        Github github = new Github( "token");
        String result = github.getToken();
        assertEquals("token", result);
    }
}