package de.goldendeveloper.githubapi.builders;

import de.goldendeveloper.githubapi.repositories.GHBranch;
import de.goldendeveloper.githubapi.repositories.GHRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GHFileBuilderTest {

//    @Test
//    void shouldSetBranchWithGHBranch() {
//        GHRepository repository = Mockito.mock(GHRepository.class);
//        GHBranch branch = Mockito.mock(GHBranch.class);
//        GHFileBuilder builder = new GHFileBuilder(repository);
//        builder.setBranch(branch);
//        assertEquals(branch, builder.getBranch());
//    }
//
//    @Test
//    void shouldSetBranchWithString() {
//        GHRepository repository = Mockito.mock(GHRepository.class);
//        GHBranch branch = Mockito.mock(GHBranch.class);
//        when(repository.getBranches()).thenReturn(Collections.singletonMap("branch", branch));
//        GHFileBuilder builder = new GHFileBuilder(repository);
//        builder.setBranch("branch");
//        assertEquals(branch, builder.getBranch());
//    }
//
//    @Test
//    void shouldSetContent() {
//        GHRepository repository = Mockito.mock(GHRepository.class);
//        GHFileBuilder builder = new GHFileBuilder(repository);
//        builder.setContent("content");
//        assertEquals("content", builder.getContent());
//    }
//
//    @Test
//    void shouldSetMessage() {
//        GHRepository repository = Mockito.mock(GHRepository.class);
//        GHFileBuilder builder = new GHFileBuilder(repository);
//        builder.setMessage("message");
//        assertEquals("message", builder.getMessage());
//    }
//
//    @Test
//    void shouldSetPath() {
//        GHRepository repository = Mockito.mock(GHRepository.class);
//        GHFileBuilder builder = new GHFileBuilder(repository);
//        builder.setPath("path");
//        assertEquals("path", builder.getPath());
//    }
}