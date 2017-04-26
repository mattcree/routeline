package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import views.html.index;
import views.html.options;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by Cree on 10/04/2017.
 */

@Security.Authenticated(Secured.class)
public class AdminController extends Controller {

    //Main Admin Menu
    public Result options() {
        return ok(index.render(options.render()));
    }

}
