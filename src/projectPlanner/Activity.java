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
		this(title, project, null, null);
	}
	
	/**
	 * Create an activity with a start and an enddate 
	 * @param title of the activity 
	 * @param project parent to this activity
	 * @param startDate of the activiyt
	 * @throws SQLException
	 */
	public Activity(String title, Project project, Calendar startDate, Calendar endDate) throws SQLException {
		this(title, project, startDate, endDate, 0);
	}
	
	/**
	 * Create a new activity with all the needed data
	 * @param title of the activity 
	 * @param project linked to the project
	 * @param startDate of the activity
	 * @param endDate of the activity
	 * @param hoursAllotted to the activity
	 * @throws SQLException
	 */
	public Activity(String title, Project project, Calendar startDate, Calendar endDate, double hoursAllotted) throws SQLException {
		createActivity(title, project, startDate, endDate, hoursAllotted);
	}
	
	/**
	 * Create a new activity 
	 */
	private void createActivity(String title, Project project, Calendar startDate, Calendar endDate, double allottedHours) throws SQLException {
		this.title = title;
		this.project = project;
		this.hoursAccumulated = 0;
		this.hoursAllotted = allottedHours;
		this.isActive = true;
		this.startDate = startDate;
		this.endDate = endDate;
		
		if (dataManager == null) 
			dataManager = new ActivityDatabaseManager();
		dataManager.saveActivity(this);
		
		project.addActivity(this);
	}
	
	/**
	 * Create an activity with already excisting information
	 * @param id of the activity
	 * @param title
	 * @param project parent of this activity
	 * @param hours spend on the activity
	 */
	public Activity(int id, String title, Project project, double hours, boolean isActive, 
			Calendar startDate, Calendar endDate, double allottedHours) {
		this.id = id;
		this.title = title;
		this.project = project;
		this.setHoursAccumulated(hours);
		this.hoursAllotted = allottedHours;
		this.isActive = isActive;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Activity(int id, String title, int projectID, double hours, boolean isActive, 
			Calendar startDate, Calendar endDate, double allottedHours) {
		this(id, title, null, hours, isActive, startDate, endDate, allottedHours);
		this.projectID = projectID;
	}
	
	/**
	 * Update the datamanager with the passed object
	 * @param db to be the new datamanager. 
	 */
	static void setDataManager(IActivityDatabaseManager db) {
		dataManager = db;
	}
	
	/**
	 * Update the activity
	 * @throws SQLException
	 */
	public void update(String title, Project project, double hoursAccumulated, double hoursAllotted, 
			boolean isActive, Calendar startDate, Calendar endDate) throws SQLException {
		this.title = title;
		this.project = project;
		this.setHoursAccumulated(hoursAccumulated);
		this.setHoursAllotted(hoursAllotted);
		this.isActive = isActive;
		this.startDate = startDate;
		this.endDate = endDate;
		
		dataManager.updateActivity(this);
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
	 * Set the time accumulated 
	 * @param hours to set the time to
	 */
	public void setHoursAccumulated(double hours) {
		this.hoursAccumulated = hours;
	}
	
	/**
	 * Add thye passed time to the accumulated time
	 * @param hours to add
	 */
	public void addAccumulatedHours(double hours) {
		this.hoursAccumulated += hours;
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
	public void addUser(User user) throws SQLException {
		dataManager.addEmployee(user, this);
	}
	
	/**
	 * Remove an employee from the activity
	 * @param employee to remove
	 * @throws SQLException
	 */
	public void removeEmployee(User user) throws SQLException {
		dataManager.removeEmployee(user, this);
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
		return Integer.compare(this.getID(), other.getID());
	}

	/**
	 * Get all the activities related to the passed employee
	 * @param employee to get the related activites to
	 * @return A list of related activities
	 * @throws SQLException
	 */
	public static List<Activity> getActivities(User employee) throws SQLException {
		return dataManager.getActivitiesByEmployee(employee);
	}

	/**
	 * @param project to get the related activities to
	 * @return A list of related activities
	 */
	public static List<Activity> getActivities(Project project) throws SQLException {
		return dataManager.getActivitiesByProject(project);
	}

	/**
	 * Set the start date
	 * @param startDate
	 * @throws SQLException
	 */
	public void setStartDate(Calendar startDate) throws SQLException {
		this.startDate = startDate;
		dataManager.updateActivity(this);
	}
	
	/**
	 * @return Get the start date
	 */
	public Calendar getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Set the end date of the activity
	 * @param endDate
	 * @throws SQLException
	 */
	public void setEndDate(Calendar endDate) throws SQLException {
		this.endDate = endDate;
		dataManager.updateActivity(this);
	}
	
	/**
	 * @return Get the end date of the activity
	 */
	public Calendar getEndDate() {
		return this.endDate;
	}

	/**
	 * Update the id of the activity
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return the hoursAllotted
	 */
	public double getHoursAllotted() {
		return hoursAllotted;
	}

	/**
	 * @param hoursAllotted the hoursAllotted to set
	 */
	public void setHoursAllotted(double hoursAllotted) {
		this.hoursAllotted = hoursAllotted;
	}	

	
	/**
	 * Get an activity with the given ID
	 * @param ID to get
	 * @return An activity with the matching ID
	 * @throws SQLException
	 */
	public static Activity getActivity(int ID) throws SQLException {
		return dataManager.getActivity(ID);
	}
	
	/**
	 * Delete the passed activity
	 * @param activity to delete
	 * @throws SQLException
	 */
	public static void deleteActicity(Activity activity) throws SQLException {
		dataManager.deleteActivity(activity);
	}
}
