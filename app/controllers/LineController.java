package controllers;

import controllers.security.Secured;
import models.Line;
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
import views.html.line.add;
import views.html.line.list;

import javax.inject.Inject;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Cree on 22/04/2017.
 */
@Security.Authenticated(Secured.class)
public class LineController extends Controller {

    @Inject
    FormFactory formFactory;

    //Shows list of Lines
    public Result list() {
        List<Line> lines = Line.find.all();
        return ok(index.render(list.render(lines)));
    }

    public Result add() {
        return ok(index.render(add.render(Html.apply(""))));
    }

    public Result doAddLine() {
        Form<LineForm> lineForm = formFactory.form(LineForm.class).bindFromRequest();

        if (lineForm.hasErrors()) {
            return badRequest(lineForm.errorsAsJson());
        }

        LineForm form = lineForm.get();

        String formattedName = WordUtils.capitalize(form.name.toLowerCase().trim());

        if(!Line.find.where().eq("name", formattedName).findList().isEmpty())
            return badRequest(index.render(add.render(failure.render("The Line already exists."))));
        if(!Pattern.matches("[a-zA-Z- ']+",formattedName))
            return badRequest(index.render(add.render(failure.render("Only alphabet characters allowed"))));

        Line line = new Line();
        line.name = formattedName;
        line.save();

        return redirect(routes.LineController.list());
    }

    public Result deleteLine(Long id) {
        Line line = Line.find.byId(id);
        if(line != null) {
            line.delete();
        }
        return redirect(routes.LineController.list());
    }

    public static class LineForm {
        @Constraints.Required
        public String name;
    }

}
