/*
* Derived from Example/Tutorial Play-Rest-Security
* (https://github.com/jamesward/play-rest-security)
* by James Ward
*/

package controllers.security;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.routes;
import models.User;

import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.libs.Json;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.Inject;

public class SecurityController extends Controller {

    @Inject
    FormFactory formFactory;

    public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "authToken";

    //Returns value of 'user' in header from current Http.Context as a User.
    public static User getUser() {
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
            return unauthorized("HTTP 401: Unauthorized.");
        }
        else {
            //Creates
            String authToken = user.createToken();
            ObjectNode authTokenJson = Json.newObject();
            authTokenJson.put(AUTH_TOKEN, authToken);
            //Sets the cookie on user's machine with their authToken from DB.
            response().setCookie(Http.Cookie.builder(AUTH_TOKEN, authToken).withSecure(ctx().request().secure()).build());
            return redirect(routes.AdminController.options());
        }
    }

    @Security.Authenticated(Secured.class)
    public Result logout() {
        response().discardCookie(AUTH_TOKEN);
        getUser().deleteAuthToken();
        return redirect(routes.AppController.index());
    }

    //Nested static class representing Login form.
    public static class Login {

        @Constraints.Required
        @Constraints.Email
        public String emailAddress;

        @Constraints.Required
        public String password;

    }

}

