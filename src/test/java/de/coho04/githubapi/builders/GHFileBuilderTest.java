package de.coho04.githubapi.builders;

import de.coho04.githubapi.Github;
import de.coho04.githubapi.repositories.GHBranch;
import de.coho04.githubapi.repositories.GHRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class GHFileBuilderTest {

    @Test
    void shouldSetBranchWithGHBranch() {
        GHRepository repository = Mockito.mock(GHRepository.class);
        GHBranch branch = Mockito.mock(GHBranch.class);
        Github github = Mockito.mock(Github.class);
        GHFileBuilder builder = new GHFileBuilder(repository, github);
        builder.setBranch(branch);
        assertEquals(branch, builder.getBranch());
    }

    @Test
    void shouldSetBranchWithString() {
        GHRepository repository = Mockito.mock(GHRepository.class);
        GHBranch branch = Mockito.mock(GHBranch.class);
        when(repository.getBranches()).thenReturn(Collections.singletonMap("branch", branch));
        Github github = Mockito.mock(Github.class);
        GHFileBuilder builder = new GHFileBuilder(repository, github);
        builder.setBranch("branch");
        assertEquals(branch, builder.getBranch());
    }

    @Test
    void shouldSetContent() {
        GHRepository repository = Mockito.mock(GHRepository.class);
        Github github = Mockito.mock(Github.class);
        GHFileBuilder builder = new GHFileBuilder(repository, github);
        builder.setContent("content");
        assertEquals("content", builder.getContent());
    }

    @Test
    void shouldSetMessage() {
        GHRepository repository = Mockito.mock(GHRepository.class);
        Github github = Mockito.mock(Github.class);
        GHFileBuilder builder = new GHFileBuilder(repository, github);
        builder.setMessage("message");
        assertEquals("message", builder.getMessage());
    }

    @Test
    void shouldSetPath() {
        GHRepository repository = Mockito.mock(GHRepository.class);
        Github github = Mockito.mock(Github.class);
        GHFileBuilder builder = new GHFileBuilder(repository, github);
        builder.setPath("path");
        assertEquals("path", builder.getPath());
    }
}