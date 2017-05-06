package controllers;

import com.avaje.ebean.Ebean;
import controllers.security.Secured;
import models.StationStop;
import models.StopConnection;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
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

    //Shows list of Connections
    public Result list() {
        List<StopConnection> connections = StopConnection.find.all();
        return ok(index.render(list.render(connections)));
    }

    public Result add() {
        List<StationStop> stops = StationStop.find.all();
        return ok(index.render(add.render(stops)));
    }

    public Result doAddConnection() {
        Form<ConnectionForm> connectionForm = formFactory.form(ConnectionForm.class).bindFromRequest();

        if (connectionForm.hasErrors()) {
            return badRequest(connectionForm.errorsAsJson());
        }

        ConnectionForm form = connectionForm.get();


        StationStop stopA = StationStop.find.byId(form.stopA);
        StationStop stopB = StationStop.find.byId(form.stopB);

        if (stopA == null || stopB == null || stopA.equals(stopB)) return redirect(routes.ConnectionController.add());

        if (form.checkbox)
        {
            Ebean.beginTransaction();
            try {
                StopConnection connection = new StopConnection(stopA, stopB, form.time);
                connection.save();
                Ebean.commitTransaction();
            } finally {
                Ebean.endTransaction();
            }
            return redirect(routes.ConnectionController.list());
        }

        Ebean.beginTransaction();
        try {
            StopConnection connection = new StopConnection(stopA, stopB, form.time);
            StopConnection connection2 = new StopConnection(stopB, stopA, form.time);
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

    public static class ConnectionForm {
        @Constraints.Required
        public Long stopA;
        @Constraints.Required
        public Long stopB;
        @Constraints.Required
        public int time;
        @Constraints.Required
        public boolean checkbox = false;
    }

}
