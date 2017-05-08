package controllers;

import com.avaje.ebean.Ebean;
import controllers.security.Secured;
import models.Line;
import models.Station;
import models.StationStop;
import org.apache.commons.lang3.text.WordUtils;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.twirl.api.Html;
import views.html.components.failure;
import views.html.index;
import views.html.stop.add;
import views.html.stop.list;

import javax.inject.Inject;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Cree on 22/04/2017.
 */
@Security.Authenticated(Secured.class)
public class StopController extends Controller {

    @Inject
    FormFactory formFactory;

    //Shows list of Stops
    public Result list() {
        List<StationStop> stops = StationStop.find.orderBy("name asc").findList();
        return ok(index.render(list.render(stops)));
    }

    public Result add() {
        List<Line> lines = Line.find.orderBy("name asc").findList();
        List<Station> stations = Station.find.orderBy("name asc").findList();
        return ok(index.render(add.render(stations, lines, Html.apply(""))));
    }

    public Result doAddStop() {
        Form<StopForm> stopForm = formFactory.form(StopForm.class).bindFromRequest();

        if (stopForm.hasErrors()) {
            return badRequest(stopForm.errorsAsJson());
        }

        StopForm form = stopForm.get();

        String formattedName = WordUtils.capitalize(form.name.toLowerCase().trim());

        if(!StationStop.find.where().eq("name", formattedName).eq("line", form.line).findList().isEmpty()) {
            List<Line> lines = Line.find.orderBy("name asc").findList();
            List<Station> stations = Station.find.orderBy("name asc").findList();
            return badRequest(index.render(add.render(stations, lines, failure.render("The Stop already exists."))));
        }
        if(!Pattern.matches("[a-zA-Z- ']+",formattedName)){
            List<Line> lines = Line.find.orderBy("name asc").findList();
            List<Station> stations = Station.find.orderBy("name asc").findList();
            return badRequest(index.render(add.render(stations, lines, failure.render("Only alphabet characters allowed"))));
        }

        Ebean.beginTransaction();
        try {
            StationStop stop = new StationStop(formattedName, form.line);
            stop.save();
            Ebean.commitTransaction();
        } finally {
            Ebean.endTransaction();
        }

        return redirect(routes.StopController.list());
    }

    public Result deleteStop(Long id) {
        StationStop stop = StationStop.find.byId(id);
        if(stop != null) {
            stop.delete();
        }
        return redirect(routes.StopController.list());
    }

    public static class StopForm {
        @Constraints.Required
        public String name;
        @Constraints.Required
        public String line;
    }

}
