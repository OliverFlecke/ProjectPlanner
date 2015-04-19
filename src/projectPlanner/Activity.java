package projectPlanner;

import java.util.*;

import projectPlanner.database.*;
import projectPlanner.users.*;

/**
 * Defines an activity in a project. 
 */
public class Activity {

	// Data manager for the activity
	private static IActivityDatabaseManager dataManager;
	
	private List<Employee> employeesAttached;		// List of employees attached to the project
	private String title;							// Title of the project
	private int id;									// ID of the project
	private final Project project;					// Project which the activity is linked to
	private double timeAccumulated;					// Time accumulated in this activity

	/**
	 * Create a new activity with a title and a linked projet
	 * @param title of the activity
	 * @param project which this activity is linked to
	 */
	public Activity(String title, Project project) {
		this.title = title;
		this.project = project;
		this.id = Activity.getNewID();
	}
	
	/**
	 * Create an activity with already excisting information
	 * @param id of the activity
	 * @param title
	 * @param project parent of this activity
	 * @param hours spend on the activity
	 */
	public Activity(int id, String title, Project project, int hours) {
		this.title = title;
		this.id = id;
		this.project = project;
		this.timeAccumulated = hours;
	}

	/**
	 * @return A new ID for an activity
	 */
	private static int getNewID() {
		// TODO Get a new ID from the database manager
		return 0;
	}
	
	/**
	 * @return Return the title of the activity
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title to set give to this acticity
	 */
	public void setName(String title) {
		// TODO Update the datamanager entry
		this.title = title;
	}
	
	public double getTimeAccumulated() {
		// TODO Get the data from the database manager
		return timeAccumulated;
	}

	/**
	 * @return The list of employees working on this activity
	 */
	public List<Employee> getEmployeesAttached() {
		// TODO Get users from database
		return employeesAttached;
	}
	
	/**
	 * @param employee to add to this activity
	 */
	public void addEmployee(Employee employee) {
		this.employeesAttached.add(employee);
		// TODO Save user in the database
	}

	/**
	 * @return The id of the activity
	 */
	public int getID() {
		return this.id;
	}	
}
