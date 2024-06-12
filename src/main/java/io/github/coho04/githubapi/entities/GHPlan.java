package io.github.coho04.githubapi.entities;

import io.github.coho04.githubapi.bases.GHBase;
import org.json.JSONObject;

/**
 * This class represents a GitHub Plan.
 * It provides methods for fetching data about the plan such as its name, space, private repositories, filled seats, and seats.
 */
public class GHPlan extends GHBase {

    private final String name;
    private final long space;
    private final int privateRepos;
    private final int filledSeats;
    private final int seats;

    /**
     * Constructs a new GHPlan instance with the provided JSON object.
     *
     * @param jsonObject the JSON object containing the plan data
     */
    public GHPlan(JSONObject jsonObject) {
        this.seats = getIntOrNull(jsonObject, "seats");
        this.space = getLongOrNull(jsonObject, "space");
        this.name = getStringOrNull(jsonObject, "name");
        this.filledSeats = getIntOrNull(jsonObject, "filled_seats");
        this.privateRepos = getIntOrNull(jsonObject, "private_repos");
    }

    /**
     * Returns the name of the plan.
     *
     * @return the name of the plan
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of filled seats of the plan.
     *
     * @return the number of filled seats of the plan
     */
    public int getFilledSeats() {
        return filledSeats;
    }

    /**
     * Returns the number of private repositories of the plan.
     *
     * @return the number of private repositories of the plan
     */
    public int getPrivateRepos() {
        return privateRepos;
    }

    /**
     * Returns the number of seats of the plan.
     *
     * @return the number of seats of the plan
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Returns the space of the plan.
     *
     * @return the space of the plan
     */
    public long getSpace() {
        return space;
    }

    /**
     * Returns a JSON object representation of the GHPlan instance.
     *
     * @return a JSON object representation of the GHPlan instance
     */
    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject()
                .put("name", name)
                .put("space", space)
                .put("seats", seats)
                .put("filled_seats", filledSeats)
                .put("private_repos", privateRepos);
    }
}