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
    public String name;
    @ManyToMany(cascade = CascadeType.REMOVE)
    public List<User> members = new ArrayList<User>();
    
    public Course(String name, User owner) {
        this.name = name;
        this.members.add(owner);
    }
    
    public static Model.Finder<Long,Course> find = new Finder(
        Long.class, Course.class
        );
    
    public static List<Course> all() {
        return find.all();
    }
    
    public static Course create(String name, String owner) {
        Course course = new Course(name, User.find.ref(owner));
        course.save();
        course.saveManyToManyAssociations("members");
        return course;
    }
    
    public static void delete(Long id) {
        find.ref(id).delete();
    }
    
    public static List<Course> findInvolving(String user){
        return find.where()
        .eq("members.email", user)
        .findList();
    }
}