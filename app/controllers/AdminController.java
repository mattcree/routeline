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

    //Main Admin Menu
    public Result options() {
        return ok(index.render(adminOptions.render()));
    }

    //Stops
    public Result adminStops() {
        List<StationStop> stops = StationStop.find.all();
        return ok(index.render(listStops.render(stops)));
    }

    public Result addStopForm() {
        List<Line> lines = Line.find.all();
        return ok(index.render(addStopForm.render(lines)));
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

    public Result deleteStop(Long id) {
        StationStop stop = StationStop.find.byId(id);
        if(stop != null) {
            stop.delete();
        }
        return redirect(routes.AdminController.adminStops());
    }

    //Connections
    public Result adminConnections() {
        List<StopConnection> connections = StopConnection.find.all();
        return ok(index.render(listConnections.render(connections)));
    }

    public Result addConnectionForm() {
        List<StationStop> stops = StationStop.find.all();
        return ok(index.render(addConnectionForm.render(stops)));
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

    public Result deleteConnection(Long id) {
        StopConnection connection = StopConnection.find.byId(id);
        if(connection != null) {
            connection.delete();
        }
        return redirect(routes.AdminController.adminConnections());
    }

    //Lines
    public Result adminLines() {
        List<Line> lines = Line.find.all();
        return ok(index.render(listLines.render(lines)));
    }

    public Result addLineForm() {
        return ok(index.render(addLineForm.render()));
    }

    public Result doAddLine() {
        Form form = formFactory.form().bindFromRequest();
        String lineName = form.data().get("name").toString();

        Line line = new Line();
        line.name = lineName;

        line.save();
        return redirect(routes.AdminController.adminLines());
    }

    public Result deleteLine(Long id) {
        Line line = Line.find.byId(id);
        if(line != null) {
            line.delete();
        }
        return redirect(routes.AdminController.adminLines());
    }

    //Users
    public Result adminUsers() {
        List<User> user = User.find.all();
        return ok(index.render(listUsers.render(user)));
    }

    public Result addUserForm() {
        return ok(index.render(addUserForm.render("")));
    }

    public Result doAddUser() {
        Form form = formFactory.form().bindFromRequest();

        String userName = form.data().get("username").toString();
        String email = form.data().get("email").toString();
        String password = form.data().get("password").toString();
        String passwordMatch = form.data().get("passwordmatch").toString();

        if (!password.equals(passwordMatch) ||
                User.findByEmailAddressAndPassword(email, password) != null)
            return badRequest(index.render(addUserForm.render("<b>error</b>")));

        User user = new User(email, password, userName);

        System.out.println(password);
        System.out.println("get " + user.getPassword());

        user.save();
        return redirect(routes.AdminController.adminUsers());
    }

}
