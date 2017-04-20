package controllers;

import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by Cree on 10/04/2017.
 */

@Security.Authenticated(Secured.class)
public class AdminController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result options() {
        return ok(index.render(adminOptions.render()));
    }

    public Result stops() {
        return ok();
    }

    public Result adminStops() {
        List<StationStop> stops = StationStop.find.all();
        return ok(index.render(stopList.render(stops)));
    }

    public Result adminConnections() {
        List<StopConnection> connections = StopConnection.find.all();
        return ok(index.render(connectionsList.render(connections)));
    }

    public Result addConnectionForm() {
        List<StationStop> stops = StationStop.find.all();
        return ok(index.render(addConnectionForm.render(stops)));
    }

    public Result adminLines() {
        List<Line> lines = Line.find.all();
        return ok(index.render(lineList.render(lines)));
    }

    public Result doAddLine() {
        Form form = formFactory.form().bindFromRequest();
        String lineName = form.data().get("name").toString();

        Line line = new Line();
        line.name = lineName;

        line.save();
        return redirect(routes.AdminController.adminLines());
    }



    public Result addLineForm() {
        return ok(index.render(addLineForm.render()));
    }


    public Result addStopForm() {
        return ok(index.render(addStopForm.render()));
    }

    public Result doAddStop() {
        Form form = formFactory.form().bindFromRequest();
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

    public Result doAddConnection() {
        Form form = formFactory.form().bindFromRequest();

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
        connection.stopA = stopA;
        connection.stopB = stopB;
        connection.distance = distance;
        System.out.println(connection);
        connection.save();
        return redirect(routes.AdminController.adminConnections());
    }

    public Result deleteStop(Long id) {
        StationStop stop = StationStop.find.byId(id);
        if(stop == null) {
            return redirect(routes.AdminController.adminStops());
        } else {
            stop.delete();
            return redirect(routes.AdminController.adminStops());
        }
    }

    public Result deleteConnection(Long id) {
        StopConnection connection = StopConnection.find.byId(id);
        if(connection == null) {
            return redirect(routes.AdminController.adminConnections());
        } else {
            connection.delete();
            return redirect(routes.AdminController.adminConnections());
        }
    }

}
