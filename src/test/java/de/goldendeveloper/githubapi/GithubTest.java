package de.goldendeveloper.githubapi;

import de.goldendeveloper.githubapi.entities.GHOrganisation;
import de.goldendeveloper.githubapi.entities.GHUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GithubTest {

    @Test
    public void shouldFindOrganisationByName() {
        Github github = Mockito.mock(Github.class);
        GHOrganisation mockOrganisation = Mockito.mock(GHOrganisation.class);
        when(github.findOrganisationByName("orgName")).thenReturn(mockOrganisation);
        GHOrganisation result = github.findOrganisationByName("orgName");
        assertEquals(mockOrganisation, result);
    }

    @Test
    public void shouldFindUserByName() {
        Github github = Mockito.mock(Github.class);
        GHUser mockUser = Mockito.mock(GHUser.class);
        when(github.findUserByName("userName")).thenReturn(mockUser);
        GHUser result = github.findUserByName("userName");
        assertEquals(mockUser, result);
    }

    @Test
    public void shouldReturnUsername() {
        Github github = new Github("username", "token");
        String result = github.getUsername();
        assertEquals("username", result);
    }

    @Test
    public void shouldReturnToken() {
        Github github = new Github("username", "token");
        String result = github.getToken();
        assertEquals("token", result);
    }
}