package de.coho04.githubapi.builders;

import de.coho04.githubapi.bases.GHBase;
import de.coho04.githubapi.repositories.GHBranch;
import de.coho04.githubapi.repositories.GHRepository;

@SuppressWarnings("unused")
public class GHFileBuilder extends GHBase {

    private GHBranch branch;
    private String path;
    private String content;
    private String message;
    private GHRepository repository;

    public GHFileBuilder(GHRepository repository) {
        this.repository = repository;
    }

    public GHFileBuilder(GHRepository repository, GHBranch branch) {
        this.branch = branch;
    }

    public GHFileBuilder(GHRepository repository, GHBranch branch, String path, String content, String message) {
        this.branch = branch;
        this.path = path;
        this.content = content;
        this.message = message;
    }

    public GHFileBuilder setBranch(GHBranch branch) {
        this.branch = branch;
        return this;
    }


    public GHFileBuilder setBranch(String branch) {
        this.branch = repository.getBranches().get(branch);
        return this;
    }


    public GHFileBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public GHFileBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public GHFileBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public void commit() {
        //TODO: Implement
    }
}
