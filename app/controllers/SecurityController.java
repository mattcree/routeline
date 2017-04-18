package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Cookie;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.Inject;
import javax.sound.midi.Soundbank;

public class SecurityController extends Controller {

    @Inject
    FormFactory formFactory;

    public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "authToken";


    public static User getUser() {
        System.out.println(Http.Context.current().args.get("user"));
        return (User)Http.Context.current().args.get("user");
    }

    // returns an authToken
    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();
        System.out.println(loginForm);
        if (loginForm.hasErrors()) {
            return badRequest(loginForm.errorsAsJson());
        }

        Login login = loginForm.get();

        User user = User.findByEmailAddressAndPassword(login.emailAddress, login.password);

        if (user == null) {
            return unauthorized();
        }
        else {
            String authToken = user.createToken();
            ObjectNode authTokenJson = Json.newObject();
            authTokenJson.put(AUTH_TOKEN, authToken);
            response().setCookie(Http.Cookie.builder(AUTH_TOKEN, authToken).withSecure(ctx().request().secure()).build());
            return redirect(routes.AdminController.options());
        }
    }

    @Security.Authenticated(Secured.class)
    public Result logout() {
        System.out.println("working");
        response().discardCookie(AUTH_TOKEN);
        getUser().deleteAuthToken();
        return redirect("/");
    }

    public static class Login {

        @Constraints.Required
        @Constraints.Email
        public String emailAddress;

        @Constraints.Required
        public String password;

    }

}

