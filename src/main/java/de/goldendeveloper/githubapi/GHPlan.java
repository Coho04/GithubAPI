package de.goldendeveloper.githubapi;

import de.goldendeveloper.githubapi.bases.GHBase;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class GHPlan extends GHBase {

    private final String name;
    private final long space;
    private final int privateRepos;
    private final int filledSeats;
    private final int seats;

    public GHPlan(JSONObject jsonObject) {
        this.seats = getIntOrNull(jsonObject, "seats");
        this.space = getLongOrNull(jsonObject, "space");
        this.name = getStringOrNull(jsonObject, "name");
        this.filledSeats = getIntOrNull(jsonObject, "filled_seats");
        this.privateRepos = getIntOrNull(jsonObject, "private_repos");
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
