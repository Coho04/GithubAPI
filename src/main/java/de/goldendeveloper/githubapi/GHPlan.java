package de.goldendeveloper.githubapi;

import org.json.JSONObject;

public class GHPlan {

    private final String name;
    private final long space;
    private final int privateRepos;
    private final int filledSeats;
    private final int seats;

    public GHPlan(JSONObject json) {
        this.name = json.getString("name");
        this.space = json.getLong("space");
        this.privateRepos = json.getInt("private_repos");
        this.filledSeats = json.getInt("filled_seats");
        this.seats = json.getInt("seats");
    }

    public String getName() {
        return name;
    }

    public int getFilledSeats() {
        return filledSeats;
    }

    public int getPrivateRepos() {
        return privateRepos;
    }

    public int getSeats() {
        return seats;
    }

    public long getSpace() {
        return space;
    }
}
