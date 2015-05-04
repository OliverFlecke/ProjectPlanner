package projectPlanner.users;

import java.util.*;

import projectPlanner.*;

/**
 * Standard employee account, which will have be able to work and log time
 */
public class Employee extends User {

	@SuppressWarnings("unused")
	private List<Activity> activities;
	
	/**
	 * Default contructor, which takes a username, a password, and a name for the user
	 * @param username of the employee
	 * @param password for the employee to login to the system
	 * @param firstname of the employee
	 * @param lastname of the employee
	 * @throws Exception 
	 */
	public Employee(String username, String password, String firstname, String lastname) throws Exception {
		super(username, password, firstname, lastname);
		this.activities = new ArrayList<Activity>();
	}
	
	
	public Employee(String username, String password, String firstname, String lastname, int id) {
		super(username, password, firstname, lastname, id);
		this.activities = new ArrayList<Activity>();
	}


	public static List<Employee> getEmployees(Activity activity) {
		// TODO Auto-generated method stub
		return null;
	}
}
