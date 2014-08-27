package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Course extends Model {
    
    @Id
    public Long id;
    
    @Required
    public String label;
    
    public static Finder<Long,Course> find = new Finder(
        Long.class, Course.class
        );
    
    public static List<Course> all() {
        return find.all();
    }
    
    public static void create(Course course) {
        course.save();
    }
    
    public static void delete(Long id) {
        find.ref(id).delete();
    }
}