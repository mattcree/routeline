package controllers;

import models.User;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;
import views.html.signin;

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        //String[] authTokenHeaderValues = ctx.request().headers().get(SecurityController.AUTH_TOKEN_HEADER);
        Http.Cookie authTokenHeaderValues = ctx.request().cookies().get(SecurityController.AUTH_TOKEN);
        if ((authTokenHeaderValues != null)) {
            User user = User.findByAuthToken(authTokenHeaderValues.value());
            System.out.println();
            System.out.println("Here: " + user);
            if (user != null) {
                ctx.args.put("user", user);
                return user.getEmailAddress();
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return unauthorized(index.render(signin.render()));
    }

}