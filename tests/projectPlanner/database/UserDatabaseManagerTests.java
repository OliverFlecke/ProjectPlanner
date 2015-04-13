package projectPlanner.database;

import java.util.List;

import projectPlanner.database.*;
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
	
	@Before 
	public void setup() {
		dataManager = new UserDatabaseManager();
	}
	
	@Test
	@Category({DatabaseTest.class, SlowTest.class})
	public void getUserByID() {
		// Create a copy of the user in the database
		User user = new Employee("Oliver", "1234", "Oliver", "Fleckenstein", 1);
		
		// Get the actual user from the database
		User userFromDB = dataManager.getEmployee(1);
		
		// Assert the two user objects to check if they are equal. 
		Assert.assertEquals(user, userFromDB);
	}
	
	/**
	 * This test will not check the exact count is right, but will just assume the user table is not empty. 
	 * Do to the fact, that the table will keep growning, we can't compare the number of users to a static number
	 */
	@Test
	@Category({DatabaseTest.class, SlowTest.class})
	public void GetNumberOfUsers_CantCheckExactNumber() {
		int result = dataManager.getNumberOfUsers();
		Assert.assertTrue(result > 0);
	}
	
	/**
	 * As more and more users will keep being added, we can't compare each user to something. 
	 * We will check to see, if we have got the right amount of users back in this test. 
	 */
	@Test
	@Category({DatabaseTest.class, SlowTest.class}) 
	public void getAllUsers_CheckIfRightAmountIsReturned() {
		int numberOfUsers = dataManager.getNumberOfUsers();
		List<User> listOfUsers = dataManager.getAllEmployees();
		
		// Check to see, if we have the right amount of users. 
		Assert.assertEquals(numberOfUsers, listOfUsers.size());
	}
}
