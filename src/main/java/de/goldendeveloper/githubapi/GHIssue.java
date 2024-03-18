package de.goldendeveloper.githubapi;

public class GHIssue {

    private String title;

    public GHIssue(String title) {
        this.title = title;
    }

    public GHIssue assignee() {
        return this;
    }

    public void create() {
        System.out.println("Issue created");
    }
}
