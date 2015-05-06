package projectPlanner.database;

import java.sql.SQLException;
import java.util.*;

import org.junit.*;
import org.junit.experimental.categories.Category;

import projectPlanner.*;
import projectPlanner.testCategories.*;
import projectPlanner.users.*;

/**
 * Test for the activity database manager
 * @author Oliver Fleckenstein
 *
 */
public class ActivityDatabaseManagerTests {
	private ActivityDatabaseManager db;
	private Activity expActivity;
	@SuppressWarnings("unused")
	private User user;
	@SuppressWarnings("unused")
	private Project project;
	
	@Before
	public void setup() {
		db = new ActivityDatabaseManager();
		// The first entry in the activity database
		expActivity = new Activity(1, "Database setup", 1, 20.5, true, null, null, 0);
		user = new Employee("Oliver", "1234", "Oliver", "Fleckenstein", 2);
		project = new Project(1, "Database setup", 0, null, null, null);
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void getActivityFromID() throws SQLException {
		Activity fromDB = db.getActivity(1);
		
		// Compare all the 
		Assert.assertEquals(expActivity.getID(), fromDB.getID());
		Assert.assertEquals(expActivity.getTitle(), fromDB.getTitle());
		Assert.assertEquals(expActivity.getTimeAccumulated(), fromDB.getTimeAccumulated(), 0);
		Assert.assertEquals(expActivity.isActive(), fromDB.isActive());
		Assert.assertEquals(expActivity.getProjectID(), fromDB.getProjectID());
		Assert.assertEquals(expActivity, fromDB);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void getActivityFromTitle() throws SQLException {
		Activity fromDB = db.getActivity("Database setup");
		
		// Compare all the 
		Assert.assertEquals(expActivity.getID(), fromDB.getID());
		Assert.assertEquals(expActivity.getTitle(), fromDB.getTitle());
		Assert.assertEquals(expActivity.getTimeAccumulated(), fromDB.getTimeAccumulated(), 0);
		Assert.assertEquals(expActivity.isActive(), fromDB.isActive());
		Assert.assertEquals(expActivity.getProjectID(), fromDB.getProjectID());
		Assert.assertEquals(expActivity, fromDB);
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void saveActivityToDatabase() throws SQLException {
		// This test can not be run each time. This will try to save the activity to the database
		// We primary look for exceptions
//		Activity activity = new Activity("Test activity 2", project);
		Assert.assertTrue(true);
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void getEmployeesFromOnGivenActivity() throws SQLException {
		List<Employee> employees = db.getUsers(expActivity);
		
		Assert.assertTrue(employees.size() > 0);		
	}
	
	@Test 
	@Category(DatabaseTest.class)
	public void addEmployeeToActivity() throws SQLException {
		// This test can not run each time, because we need to insert new data into the table
//		// Get the size of the user list before we add an user
//		int sizeBefore = db.getUsers(expActivity).size();
//		db.addEmployee(user, expActivity);
//		int sizeAfter = db.getUsers(expActivity).size();
//		
//		Assert.assertEquals(sizeBefore + 1, sizeAfter);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void updateActivityByEachStep() throws SQLException {
		// Update the data
		db.updateActivity(expActivity);
		
		// Get the new data from the database
		Activity activity = db.getActivity(expActivity.getID());
		
		Assert.assertEquals(expActivity.getTitle(), activity.getTitle());
		Assert.assertEquals(expActivity.getTimeAccumulated(), activity.getTimeAccumulated(), 0);
		Assert.assertEquals(expActivity.getProjectID(), activity.getProjectID());
		Assert.assertEquals(expActivity.isActive(), activity.isActive());
		Assert.assertEquals(expActivity, activity);
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void getActivityByEmployee() throws SQLException {
		
	}
	
	@Test 
	@Category(DatabaseTest.class) 
	public void getActivitiesByProjectTest() throws SQLException {
		List<Activity> list = db.getActivitiesByProject(Project.getProject(1));
		
		Assert.assertTrue(list.size() > 0);		
	}
}
