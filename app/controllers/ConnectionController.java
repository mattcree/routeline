package controllers;

import com.avaje.ebean.Ebean;
import controllers.security.Secured;
import models.StationStop;
import models.StopConnection;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.connection.add;
import views.html.connection.list;
import views.html.index;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Cree on 22/04/2017.
 */

@Security.Authenticated(Secured.class)
public class ConnectionController extends Controller {

    @Inject
    FormFactory formFactory;

    //Connections
    public Result list() {
        List<StopConnection> connections = StopConnection.find.all();
        return ok(index.render(list.render(connections)));
    }

    public Result add() {
        List<StationStop> stops = StationStop.find.all();
        return ok(index.render(add.render(stops)));
    }

    public Result doAddConnection() {
        Form form = formFactory.form().bindFromRequest();

        Long stopAId = null;
        Long stopBId = null;
        Integer time = null;

        try {
            stopAId = (Long) form.data().get("stopA");
            stopBId = (Long) form.data().get("stopB");
            time = (Integer) form.data().get("time");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        if (time == null || stopAId == null || stopBId == null) return redirect(routes.ConnectionController.add());

        StationStop stopA = StationStop.find.byId(stopAId);
        StationStop stopB = StationStop.find.byId(stopBId);

        if (stopA == null || stopB == null || stopA.equals(stopB)) return redirect(routes.ConnectionController.add());

        Ebean.beginTransaction();
        try {
            StopConnection connection = new StopConnection();
            StopConnection connection2 = new StopConnection();

            connection.stopA = stopA;
            connection.stopB = stopB;
            connection.time = time;

            connection2.stopA = stopB;
            connection2.stopB = stopA;
            connection2.time = time;

            connection.save();
            connection2.save();
            Ebean.commitTransaction();
        } finally {
            Ebean.endTransaction();
        }
        return redirect(routes.ConnectionController.list());
    }

    public Result deleteConnection(Long id) {
        StopConnection connection = StopConnection.find.byId(id);
        if(connection != null) {
            connection.delete();
        }
        return redirect(routes.ConnectionController.list());
    }
}
