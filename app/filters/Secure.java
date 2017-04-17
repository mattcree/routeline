package filters;

import models.User;
import play.libs.F;
import play.mvc.Action;
import play.mvc.*;
import views.html.index;
import views.html.signin;

/**
 * Created by Cree on 17/04/2017.
 */
public class Secure extends Action.Simple {

    public F.Promise<Result> call(Http.Context ctx) throws Throwable {
        System.out.println(ctx);
        String token = getTokenFromHeader(ctx);
        System.out.println(token);
        if (token != null) {
            User user = User.find.where().eq("auth_token", token).findUnique();
            if (user != null) {
                ctx.request().setUsername(user.username);
                return delegate.call(ctx);
            }
        }

        Result unauthorized = Results.unauthorized(index.render(signin.render()));
        return F.Promise.pure(unauthorized);
    }

    private String getTokenFromHeader(Http.Context ctx) {
        System.out.println(ctx.request().headers());
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        System.out.println(authTokenHeaderValues);
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }

}
