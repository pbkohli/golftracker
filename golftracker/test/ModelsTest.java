package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;
import java.util.List;

/**
 * runs before tests
 * sets up a clean in memory database
 * */
public class ModelsTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
        /**
     * Test user creation and retrieval
     * */
    @Test
    public void createAndRetrieveUser() {
        new User ("pbkohli@verizon.net", "Peter", "Pass").save();
        User peter = User.find.where().eq("email", "pbkohli@verizon.net").findUnique();
        assertNotNull(peter);
        assertEquals("Peter", peter.name);
    }
    
    /**
     * Test user authentication
     * */
    @Test
    public void authenticateUser() {
        new User ("pbkohli@verizon.net", "Peter", "Pass").save();
        assertNotNull(User.authenticate("pbkohli@verizon.net", "Pass"));
        assertNull(User.authenticate("pbkohli@verizon.net", "BadPass"));
        assertNull(User.authenticate("pbkohli@gmail.com", "Pass"));
    }
    
    /** 
     * Test course searches
     * */
     @Test
     public void findProjectsInvolving() {
         new User("pbkohli@verizon.net", "Pete", "Pass").save();
         new User("fake@gmail.com", "Name", "password").save();
         
         Course.create("Course 1", "pbkohli@verizon.net");
         Course.create("Course 2", "fake@gmail.com");
         
         List<Course> results = Course.findInvolving("pbkohli@verizon.net");
         assertEquals(1, results.size());
         assertEquals("Course 1", results.get(0).name);
     }
     
     /**
      * Score generation test
      * */
      
     @Test
     public void findScoresFor(){
         User bob = new User("bob@gmail.com", "Bob", "secret");
         bob.save();
         
         Course course = Course.create("Course 1", "bob@gmail.com");
         Score s1 = new Score();
         s1.date = "1/1/2013";
         s1.golfer = bob;
         s1.save();
         
         Score s2 = new Score();
         s2.date = "1/5/2013";
         s2.save();
         
         List<Score> results = Score.findScoreInvolving("bob@gmail.com");
         assertEquals(1, results.size());
         assertEquals("1/1/2013", results.get(0).date);
     }
}