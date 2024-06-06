package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.repositories.GHIssue;
import de.coho04.githubapi.repositories.GHRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GHIssueBuilderTest {

    private Github github;

    @BeforeEach
    void setUp() {
        github = Mockito.mock(Github.class);
    }


}