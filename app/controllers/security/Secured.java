/*
* Derived from Example/Tutorial Play-Rest-Security
* (https://github.com/jamesward/play-rest-security)
* by James Ward
*/

package controllers.security;

import models.User;

import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

import views.html.index;
import views.html.components.login;

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        Http.Cookie authTokenHeaderValues = ctx.request().cookies().get(SecurityController.AUTH_TOKEN);
        if ((authTokenHeaderValues != null)) {
            //Finds User in DB with authToken same as contained in cookie
            User user = User.findByAuthToken(authTokenHeaderValues.value());

            //If no User exists i.e. authToken is null or no user with same authToken
            //returns null. If User isn't null i.e. authToken matches a Users auth_token in DB,
            //returns the User's email_address.
            if (user != null) {
                ctx.args.put("user", user);
                return user.getEmailAddress();
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return unauthorized(index.render(login.render()));
    }

}