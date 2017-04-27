package controllers;

import controllers.security.Secured;
import models.Line;
import models.StationStop;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;
import views.html.stop.add;
import views.html.stop.list;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Cree on 22/04/2017.
 */
@Security.Authenticated(Secured.class)
public class StopController extends Controller {

    @Inject
    FormFactory formFactory;

    //Stops
    public Result list() {
        List<StationStop> stops = StationStop.find.all();
        return ok(index.render(list.render(stops)));
    }

    public Result add() {
        List<Line> lines = Line.find.all();
        return ok(index.render(add.render(lines)));
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
        return redirect(routes.StopController.list());
    }

    public Result deleteStop(Long id) {
        StationStop stop = StationStop.find.byId(id);
        if(stop != null) {
            stop.delete();
        }
        return redirect(routes.StopController.list());
    }

}
