package controllers;

import models.Line;
import models.StationStop;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * API for use with public views. Supplies information about Stops and Routes.
 */
public class ApiController extends Controller {

    //Stop features
    //Returns JSON blob of Station names i.e. list of Stop names without duplicates
    public Result getJsonStations()  {
        List<StationStop> stops = StationStop.find.all();

        if (stops.isEmpty()) return notFound(Json.parse("[]"));

        Set<String> stopNames = new TreeSet<>();
        for (StationStop stop : stops) {
            stopNames.add(stop.name);
        }
        return ok(Json.toJson(stopNames));
    }

    //Returns full list of Available Stops
    public Result getJsonStops() {
        return ok(Json.toJson(StationStop.find.all()));
    }

    //Returns Json blob of all Stops with same name as param
    public Result getJsonAllStopsAtStation(String name) {
        return ok(Json.toJson(StationStop.find.where()
                  .eq("name", name)
                  .findList()));
    }

    //Returns Json blob of all Stops with same line as param
    public Result getJsonAllStopsOnLine(String line) {
        return ok(Json.toJson(StationStop.find.where()
                  .eq("line", line)
                  .findList()));
    }

    //Returns Json blob of all Lines
    public Result getJsonAllLines() {
        return ok(Json.toJson(Line.find.all()));
    }

    //Route features
    //Takes two Params, A and B, and returns route from A to B as JSON.
    public Result getJsonRoute(Long a, Long b){
        StationStop stopA = StationStop.find.byId(a);
        StationStop stopB = StationStop.find.byId(b);

        if (stopA == null || stopB == null) return badRequest(Json.parse("[]"));

        AppController.ROUTEFINDER.generateDistancesFrom(stopA);
        Collection<StationStop> route = AppController.ROUTEFINDER.getRouteTo(stopB);
        return ok(Json.toJson(route));
    }

}
