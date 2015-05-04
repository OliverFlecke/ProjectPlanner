package projectPlanner.users;

/**
 * @author Mark Bo Jensen
 *
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import projectPlanner.ProjectPlanner;
import projectPlanner.testCategories.*;
import projectPlanner.users.UserLoginException;

public class AdminLogOnTest {

	/** 
	 * Tests the scenario when the administrator wants to log in with the a username and a password
	 * <ol>
	 *  <li> The administrator logs in with the right username
	 *  <li> The project planner responds true to the login request
	 *  <li> The administrator is logged in to the project planner
	 * </ol>
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws UserLoginException 
	 */
	@Test
	@Category(FastTest.class)
	public void adminLoginUserName() throws UserLoginException, SQLException, Exception {

		ProjectPlanner planner = new ProjectPlanner();
		
		// Check first that the administrator is not logged in.
		
		assertFalse(planner.getIsLoggedIn());
		
		// Step 1)
		boolean login = planner.login("Admin", "Admin");
		
		// Step 2) Check that the method returned true and check that admin is logged in.
		assertTrue(login);
		assertTrue(planner.getIsLoggedIn());
		assertTrue(planner.getCurrentUser().isAdmin());
	}

	/** 
	 * Tests the scenario when the administrator wants to log in with the a username and a password.
	 * <ol>
	 *  <li> The administrator logs in with the right username and wrong password.
	 *  <li> The project planner responds false to the login request.
	 *  <li> The administrator is not logged in to the project planner
	 * </ol>
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws UserLoginException 
	 */
	@Test
	@Category(FastTest.class)
	public void adminLoginUserNameNPassword() throws UserLoginException, SQLException, Exception {

		ProjectPlanner planner = new ProjectPlanner();
		
		// Check first that the administrator is not logged in.
		
		assertFalse(planner.getIsLoggedIn());
		
		// Step 1)
		
		boolean login = planner.login("admin", "admin");
		
		// Step 2) Check that the method returned true and check that admin is logged in.
		assertFalse(login);
		assertFalse(planner.getIsLoggedIn());
		assertFalse(planner.getCurrentUser().isAdmin());
	}	

/** 
 * Tests the scenario when the administrator wants to log in with the wrong username and the wrong password.
 * <ol>
 *  <li> The administrator logs in with the wrong username and wrong password
 *  <li> The project planner responds false to the login request
 *  <li> The administrator is not logged in to the project planner
 * </ol>
 */
//@Test
//public void testLoginFailedWrongUsernameNWrongPassword() {
//
//	ProjectPlanner planner = new ProjectPlanner();
//	
//	// Check first that the administrator is not logged in.
//
//	assertFalse(planner.adminLoggedIn());
//	
//	// Step 1)
//	
//	boolean login = planner.adminLogin("wrong username ","wrong password");
//	
//	// Step 2+3) Check that the method returned false and check that admin is still not logged in.
//	assertFalse(login);
//	assertFalse(planner.adminLoggedIn());
//}
/** 
 * Tests the scenario when the administrator wants to log in with the right username and the wrong password.
 * <ol>
 *  <li> The administrator logs in with the right username and wrong password
 *  <li> The project planner responds false to the login request
 *  <li> The administrator is not logged in to the project planner
 * </ol>
 */
//@Test
//public void testLoginFailedRightUsernameNWrongPassword() {
//
//	ProjectPlanner planner = new ProjectPlanner();
//	
//	// Check first that the administrator is not logged in.
//
//	assertFalse(planner.adminLoggedIn());
//	
//	// Step 1)
//	
//	boolean login = planner.adminLogin("admin","wrong password");
//	
//	// Step 2+3) Check that the method returned false and check that admin is still not logged in.
//	assertFalse(login);
//	assertFalse(planner.adminLoggedIn());
//}
/** 
 * Tests the scenario when the administrator wants to log in with the wrong username and the right password.
 * <ol>
 *  <li> The administrator logs in with the wrong username and right password
 *  <li> The project planner responds false to the login request
 *  <li> The administrator is not logged in to the project planner
 * </ol>
 */
//@Test
//public void testLoginFailedWrongUsernameNRightPassword() {
//
//	ProjectPlanner planner = new ProjectPlanner();
//	
//	// Check first that the administrator is not logged in.
//
//	assertFalse(planner.adminLoggedIn());
//	
//	// Step 1)
//	
//	boolean login = planner.adminLogin("wrong username","123password");
//	
//	// Step 2+3) Check that the method returned false and check that admin is still not logged in.
//	assertFalse(login);
//	assertFalse(planner.adminLoggedIn());
//}
}
