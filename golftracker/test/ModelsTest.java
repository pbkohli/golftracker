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
}