package projectPlanner;

import java.sql.SQLException;
import java.util.*;

import projectPlanner.database.*;
import projectPlanner.users.*;

/**
 * Defines an activity in a project. 
 */
public class Activity {

	// Data manager for the activity
	private static IActivityDatabaseManager dataManager;
	
	private String title;							// Title of the project
	private int id;									// ID of the project
	private final Project project;					// Project which the activity is linked to
	private int hoursAccumulated;					// Time accumulated in this activity
	private boolean isActive;

	/**
	 * Create a new activity with a title and a linked project
	 * @param title of the activity
	 * @param project which this activity is linked to
	 * @throws SQLException 
	 */
	public Activity(String title, Project project) throws SQLException {
		this.title = title;
		this.project = project;
		this.hoursAccumulated = 0;
		this.isActive = true;
		
		if (dataManager == null) 
			dataManager = new ActivityDatabaseManager();
		dataManager.saveActivity(this);
	}
	
	/**
	 * Create an activity with already excisting information
	 * @param id of the activity
	 * @param title
	 * @param project parent of this activity
	 * @param hours spend on the activity
	 */
	public Activity(int id, String title, Project project, int hours, boolean isActive) {
		this.id = id;
		this.title = title;
		this.project = project;
		this.hoursAccumulated = hours;
		this.isActive = isActive;
	}
	
	/**
	 * @return Return the title of the activity
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title to set give to this activity
	 */
	public void setName(String title) throws SQLException {
		this.title = title;
		dataManager.updateActivity(this);
	}
	
	/**
	 * @return The time accumulated in this activity
	 */
	public int getTimeAccumulated() {
		return this.hoursAccumulated;
	}

	/**
	 * @return The list of employees working on this activity
	 * @throws SQLException 
	 */
	public List<Employee> getEmployees() throws SQLException {
		return dataManager.getUsers(this);
	}
	
	/**
	 * @param employee to add to this activity
	 * @throws SQLException 
	 */
	public void addEmployee(Employee employee) throws SQLException {
		dataManager.addEmployee(employee, this);
	}

	/**
	 * @return The id of the activity
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * @return The ID of the project, linked to this activity
	 */
	public int getProjectID() {
		return this.project.getID();
	}	
	
	/**
	 * @return If the activity is active or not
	 */
	public boolean isActive() {
		return this.isActive;
	}
	
	/**
	 * Set the activity status of this activity
	 * @param status of the activity
	 * @throws SQLException 
	 */
	public void setActive(boolean status) throws SQLException {
		this.isActive = status;
		dataManager.updateActivity(this);
	}
}
