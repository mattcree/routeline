package controllers;

import models.Line;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import views.html.index;
import views.html.line.add;
import views.html.line.list;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Cree on 22/04/2017.
 */
@Security.Authenticated(Secured.class)
public class LineController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result list() {
        List<Line> lines = Line.find.all();
        return ok(index.render(list.render(lines)));
    }

    public Result add() {
        return ok(index.render(add.render()));
    }

    public Result doAddLine() {
        Form form = formFactory.form().bindFromRequest();
        String lineName = form.data().get("name").toString();

        Line line = new Line();
        line.name = lineName;

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

}
