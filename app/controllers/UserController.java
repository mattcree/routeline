package controllers;

import controllers.security.Secured;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.twirl.api.Html;
import views.html.components.failure;
import views.html.index;
import views.html.user.add;
import views.html.user.list;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Cree on 26/04/2017.
 */
@Security.Authenticated(Secured.class)
public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    //Shows list of Users
    public Result list() {
        List<User> user = User.find.all();
        return ok(index.render(list.render(user)));
    }

    public Result add() {
        return ok(index.render(add.render(Html.apply(""))));
    }

    public Result doAddUser() {
        Form form = formFactory.form().bindFromRequest();

        String userName = (String) form.data().get("username");
        String email = (String) form.data().get("email");
        String password = (String) form.data().get("password");
        String passwordMatch = (String) form.data().get("passwordmatch");

        if (User.find.where().eq("email_address",email).findUnique() != null)
            return badRequest(index.render(add.render(failure.render("E-mail already registered."))));
        if (!password.equals(passwordMatch))
            return badRequest(index.render(add.render(failure.render(("Passwords must match.")))));

        User user = new User(email, password, userName);
        user.save();
        return redirect(routes.UserController.list());
    }

}
