package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.util.List;
import java.util.Map;

import static play.libs.Json.toJson;


/**
 * Created by Cree on 10/04/2017.
 */
public class AdminController extends Controller {

    public static Result getLogin() {
        return ok(admin.render(signin.render()));
    }

    public static Result login() {
        return ok(admin.render(adminOptions.render()));
    }

    public static Result stops() {
        return ok();
    }

    public static Result adminStops() {
        List<Stop> stops = Stop.find.all();
        return ok(admin.render(stopList.render(stops)));
    }

    public static Result connections() {
        return ok();
    }


    public static Result addStopForm() {
        return ok(admin.render(addStopForm.render()));
    }

    public static Result doAddStop() {
        Form form = Form.form().bindFromRequest();
        System.out.println(form.data());

        System.out.println(form.data().get("name"));
        String name = form.data().get("name").toString();
        String line = form.data().get("line").toString();
        Stop stop = new Stop(name, line);
        stop.save();

        System.out.println(stop);
        return redirect(routes.AdminController.adminStops());
    }

    public static Result getJsonStops() {
        List<Stop> stops = Stop.find.all();
        System.out.println(stops);
        return ok(toJson(stops));
    }

    public static Result deleteStop(Long id) {
        Stop stop = Stop.find.byId(id);
        if(stop == null) {
            return redirect(routes.AdminController.adminStops());
        } else {
            stop.delete();
            return redirect(routes.AdminController.adminStops());
        }
    }

}
