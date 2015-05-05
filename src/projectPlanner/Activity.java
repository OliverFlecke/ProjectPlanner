package projectPlanner;

import java.sql.SQLException;
import java.util.*;

import projectPlanner.database.*;
import projectPlanner.users.*;

/**
 * Defines an activity in a project. 
 */
public class Activity implements Comparable<Activity> {

	// Data manager for the activity
	private static IActivityDatabaseManager dataManager = new ActivityDatabaseManager();
	
	private String title;							// Title of the project
	private int id;									// ID of the activity
	private Project project;						// Project which the activity is linked to
	private int projectID;							// ID of the parrent project 
	private double hoursAccumulated;				// Time accumulated in this activity
	private double hoursAllotted;					// The time allotted to this activity 
	private boolean isActive;						// state whenether the activity is active
	private Calendar startDate;						// Start date of the activity
	private Calendar endDate; 						// End date of the activity
	
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
	
	public Activity(String title, Project project, Calendar startDate) throws SQLException {
		
	}
	
	/**
	 * Create an activity with already excisting information
	 * @param id of the activity
	 * @param title
	 * @param project parent of this activity
	 * @param hours spend on the activity
	 */
	public Activity(int id, String title, Project project, double hours, boolean isActive) {
		this.id = id;
		this.title = title;
		this.project = project;
		this.hoursAccumulated = hours;
		this.isActive = isActive;
	}
	
	public Activity(int id, String title, int projectID, double hours, boolean isActive) {
		this(id, title, null, hours, isActive);
		this.projectID = projectID;
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
	public double getTimeAccumulated() {
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
		if (this.project == null)
			return this.projectID;
		else 
			return this.project.getID();
	}	
	
	/**
	 * Get the project attached to this activity
	 * @return The project which this activity is attached to
	 * @throws SQLException
	 */
	public Project getAttachedProject() throws SQLException {
		if (this.project == null) 
			return Project.getProject(this.projectID);
		else 
			return this.project;
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

	@Override
	public String toString() {
		return this.title;
	}
	
	@Override
	public boolean equals(Object o) {
		Activity other;
		if (o instanceof Activity) 
			other = (Activity) o;
		else 
			return false;
		
		if (this.getID() == other.getID() &&
				this.getTitle().equals(other.getTitle()) &&
				this.isActive() == other.isActive() &&
				this.getTimeAccumulated() == other.getTimeAccumulated())
			return true;
		else 
			return false;
	}
	
	@Override
	public int compareTo(Activity other) {
		return Integer.compare(this.id, other.getID());
	}

	/**
	 * Get all the activities related to the passed employee
	 * @param employee to get the related activites to
	 * @return A list of related activities
	 * @throws SQLException
	 */
	public static List<Activity> getActivities(Employee employee) throws SQLException {
		return dataManager.getActivitiesByEmployee(employee);
	}

	/**
	 * @param project to get the related activities to
	 * @return A list of related activities
	 */
	public static List<Activity> getActivities(Project project) throws SQLException {
		return dataManager.getActivitiesByProject(project);
	}
}
