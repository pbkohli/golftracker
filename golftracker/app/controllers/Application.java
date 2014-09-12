package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import play.data.Form.*;
import models.*;
import views.html.*;

public class Application extends Controller {
    
    static Form<Course> courseForm = Form.form(Course.class);

    public static Result index() {
        return ok(
            views.html.index.render()
            );
        
    }
    

    public static Result courses() {
        return ok(
            views.html.courses.render()
            );
    }
    
    
    public static Result myrounds() {
        return TODO;
    }
    
    public static Result stats() {
        return TODO;
    }
    
    public static Result login() {
        return ok(
            login.render(Form.form(Login.class))
            );
    }
    
    public static class Login {
        public String email;
        public String password;
    }
    
    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        return ok();
    }
    
}
