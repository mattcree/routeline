package controllers;

import com.avaje.ebean.Ebean;
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

    //Functions for Dev purposes. Seeding DB with first User and
    //populating Routefinder with current state of database.
    public Result createFirstUsers() {
        Ebean.beginTransaction();
        try {
            User admin = new User("admin@routeline.com", "admin", "Route Line");
            admin.save();
            User olebob = new User("bob@bob.com", "bob", "Ole Bob");
            olebob.save();
            Ebean.commitTransaction();
        } finally {
            Ebean.endTransaction();
        }
        return ok(Json.toJson(User.find.all()));
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
