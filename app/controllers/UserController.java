package controllers;

import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import play.twirl.api.Html;
import views.html.failure;
import views.html.index;
import views.html.user.list;
import views.html.user.add;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Cree on 26/04/2017.
 */
@Security.Authenticated(Secured.class)
public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    //Users
    public Result list() {
        List<User> user = User.find.all();
        return ok(index.render(list.render(user)));
    }

    public Result add() {
        return ok(index.render(add.render(Html.apply(""))));
    }

    public Result doAddUser() {
        Form form = formFactory.form().bindFromRequest();

        String userName = form.data().get("username").toString();
        String email = form.data().get("email").toString();
        String password = form.data().get("password").toString();
        String passwordMatch = form.data().get("passwordmatch").toString();

        if (User.find.where().eq("email_address",email).findUnique() != null)
            return badRequest(index.render(add.render(failure.render("<b>email exists!</b>"))));
        if (User.findByEmailAddressAndPassword(email, password) != null)
            return badRequest(index.render(add.render(failure.render("<b>user already here mate!</b>"))));
        if (!password.equals(passwordMatch))
            return badRequest(index.render(add.render(failure.render(("<b>Error:</b> Passwords must match.")))));

        User user = new User(email, password, userName);

        System.out.println(password);
        System.out.println("get " + user.getPassword());

        user.save();
        return redirect(routes.UserController.list());
    }

}
