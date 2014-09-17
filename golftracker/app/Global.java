import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        //Check if db is empty
        if (User.find.findRowCount() == 0) {
                Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("test-data.yml");
                // Insert users first
                Ebean.save(all.get("users"));
                // Insert projects
                Ebean.save(all.get("courses"));
                for(Object project: all.get("courses")) {
                      // Insert the project/user relation
                    Ebean.saveManyToManyAssociations(project, "members");
                }
                // Insert tasks
                Ebean.save(all.get("scores"));
        }
    }
}