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
import play.twirl.api.Html;
import views.html.components.failure;
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

    //Shows the Add Connection screen.
    public Result add() {
        List<StationStop> stops = StationStop.find.orderBy("name asc").findList();
        return ok(index.render(add.render(stops, Html.apply(""))));
    }

    public Result doAddConnection() {
        Form<ConnectionForm> connectionForm = formFactory.form(ConnectionForm.class).bindFromRequest();

        if (connectionForm.hasErrors()) {
            return badRequest(connectionForm.errorsAsJson());
        }

        ConnectionForm form = connectionForm.get();
        StationStop stopA = StationStop.find.byId(form.stopA);
        StationStop stopB = StationStop.find.byId(form.stopB);

        if (stopA == null || stopB == null || stopA.equals(stopB))  {
            List<StationStop> stops = StationStop.find.orderBy("name asc").findList();
            return badRequest(index.render(add.render(stops, failure.render("Can't connect Stop to itself."))));
        }

        List<StopConnection> connectionsAtoB = StopConnection.find
                .where()
                .eq("stop_a_id", stopA.id)
                .eq("stop_b_id", stopB.id)
                .findList();
        System.out.println(connectionsAtoB);
        List<StopConnection> connectionsBtoA = StopConnection.find
                .where()
                .eq("stop_a_id", stopA.id)
                .eq("stop_b_id", stopB.id)
                .findList();


        String a = stopA.name;
        String b = stopB.name;
        String aLine = stopA.line;
        String bLine = stopB.line;

        //If the forms checkbox has been checked (selects between uni-directional
        // and bi-directional for line administration purposes
        if (form.checkbox)
        {
            if(!connectionsAtoB.isEmpty()) {
                String message = "A Connection between "+a+" on "+aLine+" and "+b+" on "+bLine+" already exists.";
                List<StationStop> stops = StationStop.find.orderBy("name asc").findList();
                return badRequest(index.render(add.render(stops, failure.render("Connection this way already exists."))));
            }
            //Uni-directional
            Ebean.beginTransaction();
            try {
                StopConnection connection = new StopConnection(stopA, stopB, form.time);
                connection.save();
                Ebean.commitTransaction();
            } finally {
                Ebean.endTransaction();
            }
            return redirect(routes.ConnectionController.list());
        } else {
            if(!connectionsAtoB.isEmpty() && !connectionsBtoA.isEmpty()) {
                String message = "A Connection already exists towards "+b+" "+bLine+" from "
                        +a+" on "+aLine+", or towards "+a+" on "+aLine+" from "+b+" on "+bLine+".";
                List<StationStop> stops = StationStop.find.orderBy("name asc").findList();
                return badRequest(index.render(add.render(stops, failure.render(message))));
            }
            //Two-way connection
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
