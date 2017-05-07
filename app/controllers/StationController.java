package controllers;


import com.avaje.ebean.Ebean;
import models.Station;
import models.StationStop;
import org.apache.commons.lang3.text.WordUtils;
import play.data.*;
import play.data.validation.Constraints;
import play.mvc.*;
import play.twirl.api.Html;
import views.html.components.failure;
import views.html.index;
import views.html.station.add;
import views.html.station.list;

import javax.inject.Inject;
import java.util.List;
import java.util.regex.Pattern;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * Created by Cree on 07/05/2017.
 */
public class StationController {

    @Inject
    FormFactory formFactory;

    //Shows list of Stops
    public Result list() {
        List<Station> stations = Station.find.all();
        return ok(index.render(list.render(stations)));
    }

    public Result add() {
        return ok(index.render(add.render(Html.apply(""))));
    }

    public Result doAddStation() {
        Form<StationForm> stationForm = formFactory.form(StationForm.class).bindFromRequest();

        if (stationForm.hasErrors()) {
            return badRequest(stationForm.errorsAsJson());
        }

        StationForm form = stationForm.get();

        String formattedName = WordUtils.capitalize(form.name.toLowerCase().trim());

        if(!StationStop.find.where().eq("name", formattedName).findList().isEmpty()) {
            return badRequest(index.render(add.render(failure.render("The Station already exists."))));
        }
        if(!Pattern.matches("[a-zA-Z- ']+",formattedName)){
            return badRequest(index.render(add.render(failure.render("Only alphabet characters allowed"))));
        }

        Ebean.beginTransaction();
        try {
            Station station = new Station(formattedName);
            station.save();
            Ebean.commitTransaction();
        } finally {
            Ebean.endTransaction();
        }

        return redirect(routes.StationController.list());
    }

    public Result deleteStation(Long id) {
        Station station = Station.find.byId(id);
        if(station != null) {
            station.delete();
        }
        return redirect(routes.StationController.list());
    }

    public static class StationForm {
        @Constraints.Required
        public String name;
    }


}
