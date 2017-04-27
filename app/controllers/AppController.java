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
import views.html.stationPicker;

import java.util.Collection;


public class AppController extends Controller {

    protected static Routefinder ROUTEFINDER = new Routefinder();

    public Result createFirstUser() {
        User user = new User("bob@bob.com", "bob", "bob bob");
        user.save();
        System.out.println(User.find.all());
        return ok(Json.toJson(user));
    }

    public Result createTestUser(String email, String password, String name) {
        User user = new User(email, password, name);
        user.save();
        System.out.println(User.find.all());
        return ok(Json.toJson(user));
    }


    public Result index()  {
        return ok(index.render(stationPicker.render()));
    }

    public Result getLogin() {
        return ok(index.render(login.render()));
    }

    public Result logout() {
        return redirect(routes.AppController.index());
    }

    //Public API Functions
    public Result getStops() {
        return ok(Json.toJson(StationStop.find.all()));
    }

    public Result populateRoutefinder() {
        Collection<StationStop> stops = StationStop.find.all();
        Collection<StopConnection> connections = StopConnection.find.all();

        if (stops == null || connections == null) return noContent();

        ROUTEFINDER.setStops(stops);
        ROUTEFINDER.setConnections(connections);
        return ok(Json.toJson(ROUTEFINDER));
    }


}
