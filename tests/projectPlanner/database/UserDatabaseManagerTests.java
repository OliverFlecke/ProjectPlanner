package projectPlanner.database;

import java.sql.*;
import java.util.Calendar;
import java.util.List;

import projectPlanner.*;
import projectPlanner.users.*;
import projectPlanner.testCategories.*;

import org.junit.*;
import org.junit.experimental.categories.Category;

/**
 * <p>
 * This class will test the UserDatabaseManager class, which is responsible for handling all user data. </br>
 * As much of the UserDatabaseManager is try and catch statments, this code will not be tested. 
 * These catch statments is build to insure the SQL statments is run correctly, and therefore should not throw
 * any exception at runtime. If this should happen anyway, it is not a problem in the program, but do to an
 * error in the database on the server. 
 * </p>
 * @author Oliver Fleckenstein
 */
public class UserDatabaseManagerTests {
	// Field for the database manager to test
	private UserDatabaseManager dataManager;
	private User user;
	private String password;
	private Activity activity;
	
	@Before 
	public void setup() {
		dataManager = new UserDatabaseManager();
		// Create a copy of the user in the database
		user = new Employee("Oliver", "1234", "Oliver", "Fleckenstein", 2);
		password = "1234";
	}
	
	@Test 
	@Category(DatabaseTest.class) 
	public void saveEmployeeByUserObject() throws SQLException {
		dataManager.saveEmployee(user, password);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void saveEmployeeWithAllData() throws SQLException {
		try {
			dataManager.saveEmployee(user.getID(), user.getFirstname(), user.getLastname(), 
				user.getUsername(), password);
		} catch (SQLException ex) {
			Assert.assertEquals("Cannot insert explicit value for identity column in table 'Employees' when IDENTITY_INSERT is set to OFF.", 
					ex.getMessage());
		}
	}
	
	@Test
	@Category({DatabaseTest.class, SlowTest.class})
	public void getUserByID() throws SQLException {
		// Get the actual user from the database
		User userFromDB = dataManager.getEmployee(2);
		
		// Assert the two user objects to check if they are equal. 
		Assert.assertEquals(user, userFromDB);
	}
	
	/**
	 * This test will not check the exact count is right, but will just assume the user table is not empty. 
	 * Do to the fact, that the table will keep growning, we can't compare the number of users to a static number
	 * @throws SQLException 
	 */
	@Test
	@Category(DatabaseTest.class)
	public void GetNumberOfUsers_CantCheckExactNumber() throws SQLException {
		int result = dataManager.getNumberOfUsers();
		Assert.assertTrue(result > 0);
	}
	
	/**
	 * As more and more users will keep being added, we can't compare each user to something. 
	 * We will check to see, if we have got the right amount of users back in this test. 
	 * @throws SQLException 
	 */
	@Test
	@Category(DatabaseTest.class) 
	public void getAllUsers_CheckIfRightAmountIsReturned() throws SQLException {
		int numberOfUsers = dataManager.getNumberOfUsers();
		List<User> listOfUsers = dataManager.getAllEmployees();
		
		// Check to see, if we have the right amount of users. 
		Assert.assertEquals(numberOfUsers, listOfUsers.size());
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void updateUser_UpdatesEachElementInSteps() throws SQLException, ActionNotAllowedException {
		// Get the user with id = 1 from the database
		User user = dataManager.getEmployee(2);
		User.setDataManager(dataManager);
		
		// Updates its firstname to Oliver
		user.updateFirstname("Oliver", "1234");
		Assert.assertEquals("Oliver", user.getFirstname());
		
		// Update the lastname of the user
		user.updateLastname("Fleckenstein", "1234");
		Assert.assertEquals("Fleckenstein", user.getLastname());
	
		// Update the username
		user.updateUsername("Oliver", "1234");
		Assert.assertEquals("Oliver", user.getUsername());
		
		// Updates the password 
		user.updatePassword("1234", "1234");
		Assert.assertTrue(user.checkPassword("1234"));
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void getAllActivitiesFromAUser() throws SQLException {
		List<Activity> activities = dataManager.getActivities(user);
		Assert.assertTrue(activities.size() > 0);
	}
	
	@Test 
	@Category(DatabaseTest.class) 
	public void getTimeUserSpendOnActivities() throws SQLException {
//		User.getTimeSpendOnActivity(user, new Activity(, null, 0, 0, false));
	}
	
	@Test 
	@Category(DatabaseTest.class) 
	public void getLoggedTime() throws SQLException {
		List<LoggedTime> list = user.getLoggedTime();
		Assert.assertTrue(list.size() > 0);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void getUsersByActiviy() throws SQLException {
		Activity activity = new Activity(1, "Database setup", 1, 20.5, true, null, null, 0);
		List<Employee> list = dataManager.getEmployees(activity);
		
		Assert.assertTrue(list.size() >= 1);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void getLoggedTimeObject() throws SQLException {
		List<LoggedTime> list = user.getLoggedTime();
		
		Assert.assertTrue(list.size() > 0);
		LoggedTime first = list.get(0);
		Assert.assertNotNull(first.getActivity());
		Assert.assertNotNull(first.getUser());
	}
	
	@Test 
	@Category(DatabaseTest.class) 
	public void getEmployeeByUsername() throws SQLException {
		User output = dataManager.getEmployee("Oliver");
		Assert.assertEquals(user, output);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void getEmployeeByFullName() throws SQLException {
		User output = dataManager.getEmployee("Oliver", "Fleckenstein");
		Assert.assertEquals(user, output);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void getTimeSpendOnActivity() throws SQLException {
		activity = Activity.getActivity(1);
		double time = dataManager.getTimeSpendOnActivity(user, activity);
		
		Assert.assertEquals(10.5, time, 0);
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void getTimeSpendOnAllActivitiesByUser() throws SQLException {
		double time = dataManager.getTimeSpendOnAllActivities(user);
		
		Assert.assertTrue(time >= 121.75);
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void logTimeOnActivity_PassedLoggedTimeObject() throws SQLException {
		try {
			dataManager.logTimeOnActivity(new LoggedTime(1, 2, 10, Calendar.getInstance()));
		} catch (SQLException ex) {
			Assert.assertEquals("Violation of PRIMARY KEY constraint 'PK__SpendHou__7AD04FF1F1BC864F'. Cannot insert duplicate key in object 'dbo.SpendHoursOn'. The duplicate key value is (2, 1, 2015-05-10).", 
					ex.getMessage());
		}
	}
	
	@Test 
	@Category(DatabaseTest.class)
	public void updateLoggedTimeOnActivity_PassNewLoggedTime() throws SQLException {
		Calendar date = Calendar.getInstance();
		// This sets the exect date
		date.setTimeInMillis(1429740000000L);
//		date.set(2015, 4, 23);
//		System.out.println(date.getTimeInMillis());
		dataManager.updateLoggedTimeOnActivity(new LoggedTime(1, 2, 10.5, date));
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void deleteLoggedTime_CreateAtgainAfterWards() throws SQLException {
		Calendar date = Calendar.getInstance();
		// This sets the exect date
		date.setTimeInMillis(1429740000000L);
		LoggedTime time = new LoggedTime(1, 2, 10.5, date);
		
		double before = dataManager.getTimeSpendOnEachActivity(user).size();
		dataManager.deleteLoggedTime(time);
	
		// Get the number of objects after the deletion
		double afterDelete =  dataManager.getTimeSpendOnEachActivity(user).size();
		Assert.assertTrue(before == afterDelete + 1);
		
		dataManager.logTimeOnActivity(time);
		double afterAdding = dataManager.getTimeSpendOnEachActivity(user).size();
		Assert.assertTrue(afterDelete + 1 == afterAdding);
	}
}
