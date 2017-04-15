package controllers;

import models.StationStop;
import play.libs.Json;
import play.mvc.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static play.libs.Json.toJson;

/**
 * Created by Cree on 14/04/2017.
 */
public class ApiController extends Controller {

    public static Result getJsonStations()  {
        List<StationStop> stops = StationStop.find.all();

        if (stops.isEmpty()) return notFound(Json.parse("[]"));

        Set<String> stopNames = new TreeSet<>();
        for (StationStop stop : stops) {
            stopNames.add(stop.name);
        }
        return ok(Json.toJson(stopNames));
    }

    public static Result getJsonStops() {
        List<StationStop> stops = StationStop.find.all();
        System.out.println(stops);
        return ok(toJson(stops));
    }

}
