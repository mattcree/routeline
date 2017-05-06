package controllers;

import controllers.security.Secured;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
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
        return ok(index.render(list.render(user, Html.apply(""))));
    }

    public Result add() {
        return ok(index.render(add.render(Html.apply(""))));
    }

    public Result doAddUser() {
        Form<UserForm> userForm = formFactory.form(UserForm.class).bindFromRequest();

        if (userForm.hasErrors()) {
            return badRequest(userForm.errorsAsJson());
        }

        UserForm form = userForm.get();

        if (User.find.where().eq("email_address",form.email).findUnique() != null)
            return badRequest(index.render(add.render(failure.render("E-mail already registered."))));
        if (!form.password.equals(form.passwordmatch))
            return badRequest(index.render(add.render(failure.render(("Passwords must match.")))));

        User user = new User(form.email, form.password, form.username);
        user.save();
        return redirect(routes.UserController.list());
    }

    public Result deleteUser(Long id) {
        if(id == 1)
        {
            List<User> users = User.find.all();
            return badRequest(index.render(list.render(users, failure.render("Can't delete default user"))));
        }
        User user = User.find.byId(id);
        if(user != null) {
            user.delete();
        }
        return redirect(routes.UserController.list());
    }

    public static class UserForm {
        @Constraints.Required
        public String username;
        @Constraints.Required
        @Constraints.Email
        public String email;
        @Constraints.Required
        public String password;
        @Constraints.Required
        public String passwordmatch;
    }

}
