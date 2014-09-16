package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class Score extends Model {
    @Id
    public Long id;
    public String date;
    @ManyToOne
    public User golfer;
    @ManyToOne
    public Course course;
    
    public static Model.Finder<Long,Score> find = new Model.Finder(Long.class, Score.class);
    
    public static List<Score> findScoreInvolving(String user) {
        return find.where()
        .eq("golfer.email", user)
        .findList();
    }
    
    public static Score create(Score score, Long course, User user) {
        score.course = Course.find.ref(course);
        score.golfer = user;
        score.save();
        return score;
    }
}