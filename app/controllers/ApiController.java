package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import models.Line;
import models.Route;
import models.StationStop;
import models.StopConnection;
import models.routefinder.Routefinder;
import org.apache.commons.lang3.text.WordUtils;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.*;


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

        if (stopA == null || stopB == null || stopA.equals(stopB)) return badRequest(Json.parse("[]"));

        List<StationStop> stops = StationStop.find.all();
        List<StopConnection> connections = StopConnection.find.all();

        Routefinder routefinder = new Routefinder(stops, connections);

        routefinder.generateTimesFrom(stopA);

        LinkedList<StationStop> stopsOnRoute = routefinder.getRouteTo(stopB);
        LinkedList<StopConnection> connectionsOnRoute = routefinder.getAllConnectionsOnRoute(stopsOnRoute);

        Route route = new Route(stopsOnRoute, connectionsOnRoute);

        return ok(Json.toJson(route));
    }

    public Result getJsonRouteByName(String start, String destination) {
        String formattedStart = WordUtils.capitalize(start.toLowerCase().trim());
        String formattedDestination = WordUtils.capitalize(destination.toLowerCase().trim());

        List<StationStop> startStops = StationStop.find
                .where()
                .eq("name", formattedStart)
                .findList();
        List<StationStop> destinationStops = StationStop.find
                .where()
                .eq("name", formattedDestination)
                .findList();

        if(startStops.isEmpty() || destinationStops.isEmpty()
            || formattedStart.equals(formattedDestination))
            return badRequest(Json.parse("{}"));

        Routefinder rf = new Routefinder(StationStop.find.all(),StopConnection.find.all());
        Route shortestRoute = Routefinder.getBestRoute(startStops, destinationStops, rf);
        return  ok(Json.toJson(shortestRoute));
    }




    public Result getJsonRouteAvoiding(Long a, Long b, Long avoid){
        Routefinder rfAvoid = new Routefinder();

        StationStop stopA = StationStop.find.byId(a);
        StationStop stopB = StationStop.find.byId(b);
        StationStop avoidStop = StationStop.find.byId(avoid);

        if(stopA == null || stopB == null || avoidStop == null) return badRequest(Json.parse("[]"));

        Long avoidId = avoidStop.id;

        List<StationStop> listOfStops = StationStop.find.where().ne("id", avoidId).findList();
        List<StopConnection> listOfConnections = StopConnection.find
                .where()
                .ne("stop_a_id", avoidId)
                .ne("stop_b_id", avoidId)
                .findList();

        rfAvoid.setConnections(listOfConnections);
        rfAvoid.setStops(listOfStops);
        rfAvoid.generateTimesFrom(stopA);
        Collection<StationStop> routeAvoiding = rfAvoid.getRouteTo(stopB);

        if(routeAvoiding == null) return badRequest(Json.parse("[]"));

            return ok(Json.toJson(routeAvoiding));

    }

    public Result getJsonRouteAvoidingStation(String a, String b, String avoid){
//        Routefinder rfAvoid = new Routefinder();
//
//        StationStop stopA = StationStop.find.byId(a);
//        StationStop stopB = StationStop.find.byId(b);
//        StationStop avoidStop = StationStop.find.byId(avoid);
//
//        if(stopA == null || stopB == null || avoidStop == null) return badRequest(Json.parse("[]"));
//
//        Long avoidId = avoidStop.id;
//
//        List<StationStop> listOfStops = StationStop.find.where().ne("id", avoidId).findList();
//        List<StopConnection> listOfConnections = StopConnection.find
//                .where()
//                .ne("stop_a_id", avoidId)
//                .ne("stop_b_id", avoidId)
//                .findList();
//
        return ok();
    }
}
