package projectPlanner.users;

import java.util.*;
import projectPlanner.*;

/**
 * Standard employee account, which will have be able to work and log time
 */
public class Employee extends User {

	private List<Activity> activities;
	
	/**
	 * Default contructor, which takes a username, a password, and a name for the user
	 * @param username of the employee
	 * @param password for the employee to login to the system
	 * @param name of the employee
	 */
	public Employee(String username, String password, String name) {
		super(username, password, name);
		this.activities = new ArrayList<Activity>();
	}
}
