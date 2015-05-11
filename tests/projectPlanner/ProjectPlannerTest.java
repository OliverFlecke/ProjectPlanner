package projectPlanner;

import java.sql.SQLException;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import projectPlanner.testCategories.*;
import projectPlanner.users.*;

/**
 * @author Oliver Fleckenstein
 *
 */
public class ProjectPlannerTest {

	private ProjectPlanner projectPlanner;
	
	@Mock
	private User user;
	@Mock 
	private Project project;
	@Mock
	private Activity activity;
	
	@Before
	public void setup() {
		projectPlanner = new ProjectPlanner();
		user = mock(User.class);
		project = mock(Project.class);
		activity = mock(Activity.class);
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
		@SuppressWarnings("unused")
		User user = new Employee("Oliver", "1234", "Oliver", "Fleckenstein", 2);
		Project project = null;
		try {
			project = Project.getProject(1);
		} catch (SQLException ex) {
			Assert.fail("Exception should not be thrown here. This is an issue in the project class");
		}
		
<<<<<<< HEAD
		// Create new activity 
		try {
			// The activity will insure it gets added to the passed project
			@SuppressWarnings("unused")
			Activity activity = new Activity("New usercase project", project);		
		} catch (SQLException ex) { 
			Assert.fail("Exception should not be thrown here.");
		}
=======
		// The activity will insure it gets added to the passed project
		Activity activity = new Activity(1, "New usercase project", project, 0, false, null, null, 0);
>>>>>>> origin/master
	}
	
	@Test
	@Category(SlowTest.class)
	public void getCurrentUsersActivities() throws SQLException {
		Assert.assertNotNull(projectPlanner.getCurrentUsersActivities());
	}
	
	@Test
	@Category(FastTest.class)
	public void getEmployees() throws SQLException {
		projectPlanner.getActivitiesByEmployee(user);
		projectPlanner.getActivitiesByProject(project);
		ProjectPlanner.getEmployeesByActivity(activity);
		ProjectPlanner.getEmployeesNotInActivity(activity);
	}
	
	@Test 
	@Category(FastTest.class)
	public void loginAndLogout() throws UserLoginException, Exception {
		Assert.assertTrue(projectPlanner.login("Oliver", "1234"));
		projectPlanner.logout();
	}
	
	@Test 
	@Category(FastTest.class)
	public void createNewUserThroughProjectPlaner() throws Exception {
//		projectPlanner.createNewUser("New username", "Password", "Firstname", "Lastname");
	}
}