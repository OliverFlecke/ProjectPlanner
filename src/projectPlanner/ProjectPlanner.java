package projectPlanner;

import java.sql.SQLException;
import java.util.List;

import projectPlanner.users.*;

/**
 * Main controller for the project planner program. 
 */
public class ProjectPlanner {

	private User currentUser;				// User logged in to the system
	private boolean isLoggedIn;				// State to indicate if the user is logged in or not

	public boolean login(String username, String password) throws UserLoginException, SQLException, Exception {
		currentUser = User.getUser(username);
		if (currentUser == null) {
			throw new UserLoginException("Username is not registed in the system.");
		} else if (!currentUser.checkPassword(password)){
			throw new UserLoginException("Invalid password. Please try again or contact your admin");
		} 
		boolean loginResult = currentUser.checkPassword(password);
		setIsLoggedIn(loginResult);
		return loginResult; 
	}

	/**
	 * Log out of the system
	 */
	public void logout() {
		this.currentUser = null;
		setIsLoggedIn(false);
	}
	
	/**
	 * Set the login state
	 * @param state to set the login status to
	 */
	private void setIsLoggedIn(boolean state){
		isLoggedIn = state;
	}
	
	/**
	 * @return If the user is logged in or not
	 */
	public boolean getIsLoggedIn() {
		return this.isLoggedIn;
	}
	
	/**
	 * @return The user currently logged in to the system.
	 */
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	/**
	 * @return A list of activity 
	 * @throws SQLException 
	 */
	public List<Activity> getCurrentUsersActivities() throws SQLException {
		return User.getActivities(currentUser);

	}
	
	/**
	 * Get a list of activity by passing an employee
	 * @param employee to get the activities 
	 * @return A list of activities the passed employee is working on
	 */
	public List<Activity> getActivitiesByEmployee(Employee employee) throws SQLException {
		return Activity.getActivities(employee);
	}
	
	/**
	 * Get all the activities in a project
	 * @param project to get the activities from
	 * @return A list of activities related to the project
	 * @throws SQLException 
	 */
	public List<Activity> getActivitiesByProject(Project project) throws SQLException {
		return Activity.getActivities(project); 
	}
	
	/**
	 * 
	 * @param activity
	 * @return
	 */
	public List<Employee> getEmployeesByActivity(Activity activity) {
		return Employee.getEmployees(activity);
	}
	
	/**
	 * Get all employees not working on a given activity
	 * @param activity to check
	 * @return A list of employees not already working on the passed activity
	 * @throws SQLException 
	 */
	public List<User> getEmployeesNotInActivity(Activity activity) throws SQLException {
		List<Employee> activityEmployees = Employee.getEmployees(activity);
		List<User> allEmployees = User.getAllUsers();
		for(Employee current : activityEmployees) {
			allEmployees.remove(current);
		}
		return allEmployees;
	}
	
	/**
	 * Create a new user with the passed username and password
	 * @param username to the new user
	 * @param password to the new password
	 * @param firstname of the user
	 * @param lastname of the user
	 * @throws Exception 
	 */
	public User createNewUser(String username, String password, String firstname, String lastname) throws Exception {
		return new Employee(username, password, firstname, lastname);
	}
}

