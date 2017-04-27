package controllers;

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
 * Created by Cree on 14/04/2017.
 */
public class ApiController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result getJsonStations()  {
        List<StationStop> stops = StationStop.find.all();

        if (stops.isEmpty()) return notFound(Json.parse("[]"));

        Set<String> stopNames = new TreeSet<>();
        for (StationStop stop : stops) {
            stopNames.add(stop.name);
        }
        return ok(Json.toJson(stopNames));
    }

    public Result getJsonStops() {
        List<StationStop> stops = StationStop.find.all();
        System.out.println(stops);
        return ok(Json.toJson(stops));
    }

    public Result getJsonRoute(Long a, Long b){
        StationStop stopA = StationStop.find.byId(a);
        StationStop stopB = StationStop.find.byId(b);

        System.out.println(stopA);
        System.out.println(stopB);


        AppController.ROUTEFINDER.generateDistancesFrom(stopA);

        Collection<StationStop> route = AppController.ROUTEFINDER.getRouteTo(stopB);

        return ok(Json.toJson(route));
    }

}
