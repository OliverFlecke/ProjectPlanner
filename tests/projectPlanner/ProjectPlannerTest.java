package projectPlanner;

import java.sql.SQLException;

import org.junit.*;
import org.junit.experimental.categories.Category;

import projectPlanner.testCategories.*;
import projectPlanner.users.*;
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
	
	@Test
	@Category(UserStoryTest.class)
	public void createActivityAsProjectLeader() throws SQLException {
		// Setup
		User user = new Employee("Oliver", "1234", "Oliver", "Fleckenstein", 2);
		Project project = null;
		try {
			project = Project.getProject(1);
		} catch (SQLException ex) {
			Assert.fail("Exception should not be thrown here. This is an issue in the project class");
		}
		
		// Create new activity 
		try {
			// The activity will insure it gets added to the passed project
			Activity activity = new Activity("New usercase project", project);		
		} catch (SQLException ex) { 
			Assert.fail("Exception should not be thrown here.");
		}
	}
}