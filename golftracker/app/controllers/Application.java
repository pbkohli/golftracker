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
            views.html.courses.render(Course.all(), courseForm)
            );
    }
    
    public static Result newCourse() {
        Form<Course> filledForm = courseForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                views.html.courses.render(Course.all(), filledForm)
                );
        } else {
            Course.create(filledForm.get());
            return redirect(routes.Application.courses());
        }
    }
    
    public static Result deleteCourse(Long id){
        Course.delete(id);
        return redirect(routes.Application.courses());
    }
    
    public static Result scores() {
        return TODO;
    }
    
    public static Result stats() {
        return TODO;
    }
}
