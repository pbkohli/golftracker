package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

/**
 * runs before tests
 * sets up a clean in memory database
 * */
public class ModelsTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    @Test
    public void createAndRetrieveUser() {
        new User ("pbkohli@verizon.net", "Peter", "Pass").save();
        User peter = User.find.where().eq("email", "pbkohli@verizon.net").findUnique();
        assertNotNull(peter);
        assertEquals("Peter", peter.name);
    }
}