package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Cree on 10/04/2017.
 */
public class AdminController extends Controller {

    public static Result getLogin() {
        return ok(index.render(signin.render()));
    }

    public static Result login() {
        return ok(index.render(adminOptions.render()));
    }

    public static Result options() {
        return ok(index.render(adminOptions.render()));
    }

    public static Result stops() {
        return ok();
    }

    public static Result adminStops() {
        List<StationStop> stops = StationStop.find.all();
        return ok(index.render(stopList.render(stops)));
    }

    public static Result adminConnections() {
        List<StopConnection> connections = StopConnection.find.all();
        return ok(index.render(connectionsList.render(connections)));
    }

    public static Result addConnectionForm() {
        List<StationStop> stops = StationStop.find.all();

        return ok(index.render(addConnectionForm.render(stops)));
    }

    public static Result addStopForm() {
        return ok(index.render(addStopForm.render()));
    }

    public static Result doAddStop() {
        Form form = Form.form().bindFromRequest();
        System.out.println(form.data());

        System.out.println(form.data().get("name"));
        String name = form.data().get("name").toString();
        String line = form.data().get("line").toString();
        StationStop stop = new StationStop();
        stop.name = name;
        stop.line = line;
        stop.save();

        System.out.println(stop);
        return redirect(routes.AdminController.adminStops());
    }

    public static Result doAddConnection() {
        Form form = Form.form().bindFromRequest();

        Long stopAId = null;
        Long stopBId = null;
        Long distance = null;

        try {
            stopAId = Long.parseLong(form.data().get("stopA").toString());
            stopBId = Long.parseLong(form.data().get("stopB").toString());
            distance = Long.parseLong(form.data().get("time").toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        if (distance == null || stopAId == null || stopBId == null) return redirect(routes.AdminController.addConnectionForm());

        StationStop stopA = StationStop.find.byId(stopAId);
        StationStop stopB = StationStop.find.byId(stopBId);

        if (stopA == null || stopB == null || stopA.equals(stopB)) return redirect(routes.AdminController.addConnectionForm());
        StopConnection connection = new StopConnection();
        connection.stopA = stopAId;
        connection.stopB = stopBId;
        connection.distance = distance;
        System.out.println(connection);
        connection.save();
        return redirect(routes.AdminController.adminConnections());
    }

    public static Result deleteStop(Long id) {
        StationStop stop = StationStop.find.byId(id);
        if(stop == null) {
            return redirect(routes.AdminController.adminStops());
        } else {
            stop.delete();
            return redirect(routes.AdminController.adminStops());
        }
    }

    public static Result deleteConnection(Long id) {
        StopConnection connection = StopConnection.find.byId(id);
        if(connection == null) {
            return redirect(routes.AdminController.adminConnections());
        } else {
            connection.delete();
            return redirect(routes.AdminController.adminConnections());
        }
    }

}
