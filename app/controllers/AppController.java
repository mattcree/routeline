package controllers;

import models.StationStop;
import models.StopConnection;
import models.User;
import models.routefinder.Routefinder;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.index;
import views.html.components.login;
import views.html.components.stationPicker;

import java.util.Collection;

/**
 * AppController for public functions. Viewing Index
 */
public class AppController extends Controller {

    protected static Routefinder ROUTEFINDER = new Routefinder();

    //Functions for Dev purposes. Seeding DB with first User and
    //populating Routefinder with current state of database.
    public Result createFirstUser() {
        User user = new User("bob@bob.com", "bob", "Ole Bob");
        user.save();
        System.out.println(User.find.all());
        return ok(Json.toJson(user));
    }

    public Result populateRoutefinder() {
        Collection<StationStop> stops = StationStop.find.all();
        Collection<StopConnection> connections = StopConnection.find.all();

        if (stops == null || connections == null) return noContent();

        ROUTEFINDER.setStops(stops);
        ROUTEFINDER.setConnections(connections);
        return ok(Json.toJson(ROUTEFINDER));
    }

    //For Handling publicly available view options
    public Result index()  {
        return ok(index.render(stationPicker.render()));
    }

    //Displays admin login page for all users
    public Result getLogin() {
        return ok(index.render(login.render()));
    }

}
