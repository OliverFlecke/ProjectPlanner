package projectPlanner.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import projectPlanner.ProjectPlanner;


public class adminLogOnTest {

/** 
 * Tests the scenario when the administrator wants to log in with the a username.
 * <ol>
 *  <li> The administrator logs in with the right username
 *  <li> The project planner responds true to the login request
 *  <li> The administrator is logged in to the project planner
 * </ol>
 */
//	
//	public void adminLoginUserName() {
//
//	ProjectPlanner planner = new ProjectPlanner();
//	
//	// Check first that the administrator is not logged in.
//	
//	assertFalse(planner.adminLoggedIn());
//	
//	// Step 1)
//	
//	boolean login = planner.adminLogin("admin");
//	
//	// Step 2) Check that the method returned true and check that admin is logged in.
//	assertTrue(login);
//	assertTrue(planner.adminLoggedIn());
//}
//
//	/** 
//	 * Tests the scenario when the administrator wants to log in with the a username and a password.
//	 * <ol>
//	 *  <li> The administrator logs in with the right username and right password.
//	 *  <li> The project planner responds true to the login request.
//	 *  <li> The administrator is logged in to the project planner
//	 * </ol>
//	 */
//		
//		public void adminLoginUserNameNPassword() {
//
//		ProjectPlanner planner = new ProjectPlanner();
//		
//		// Check first that the administrator is not logged in.
//		
//		assertFalse(planner.adminLoggedIn());
//		
//		// Step 1)
//		
//		boolean login = planner.adminLogin("admin", "123password");
//		
//		// Step 2) Check that the method returned true and check that admin is logged in.
//		assertTrue(login);
//		assertTrue(planner.adminLoggedIn());
//	}	

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
@Test
public void testLoginFailedRightUsernameNWrongPassword() {

	ProjectPlanner planner = new ProjectPlanner();
	
	// Check first that the administrator is not logged in.

	assertFalse(planner.adminLoggedIn());
	
	// Step 1)
	
	boolean login = planner.adminLogin("admin","wrong password");
	
	// Step 2+3) Check that the method returned false and check that admin is still not logged in.
	assertFalse(login);
	assertFalse(planner.adminLoggedIn());
}
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
