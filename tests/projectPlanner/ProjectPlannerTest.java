package projectPlanner;

import java.sql.SQLException;

import org.junit.*;
import org.junit.experimental.categories.Category;

import projectPlanner.*;
import projectPlanner.testCategories.*;
import projectPlanner.users.UserLoginException;
/**
 * @author Oliver Fleckenstein
 *
 */
public class ProjectPlannerTest {

	private ProjectPlanner projectPlanner;
	
	@Before
	public void setup() {
		projectPlanner = new ProjectPlanner();
	}
	
	@After
	public void tearDown() {
		
	}

	@Test
	@Category(FastTest.class)
	public void loginWithUsernameAndPassword_Succes() throws UserLoginException, SQLException, Exception {
		Assert.assertTrue(projectPlanner.login("Oliver", "1234"));
	}
	
	@Test 
	@Category(FastTest.class) 
	public void login_WithWrongPassword_Fail() throws Exception {
		try {
			Assert.assertFalse(projectPlanner.login("Oliver", "WRONG PASSWORD"));
		} catch (UserLoginException ex) {
			Assert.assertEquals("Invalid password. Please try again or contact your admin", ex.getMessage());
		}
	}
	
	@Test
	@Category(FastTest.class) 
	public void login_UsernameNotFound() throws Exception {
		try {
			Assert.assertFalse(projectPlanner.login("Username not found", "Not relevant"));
		} catch (UserLoginException ex) {
			Assert.assertEquals("Username is not registed in the system.", ex.getMessage());
		}
	}
}