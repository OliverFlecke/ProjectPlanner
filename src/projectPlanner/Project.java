package projectPlanner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import org.apache.pdfbox.exceptions.COSVisitorException;

import projectPlanner.database.*;
import projectPlanner.users.*;

/**
 * Project class containing project elements
 */
public class Project implements Comparable<Project> {
	// DataManager
	private static IProjectDatabaseManager dataManager = new ProjectDatabaseManager();
	
	private double allottedTime;			// Time expected to use on the project
	private String title;					// Of the project
	private User projectLeader;				// Reference for the user, whom is leader of this project
	private Calendar startDate;				// Start date of the project
	private Calendar endDate;				// End date of the project
	private boolean isActive;				// Flag to indicate if this project is active or not
	private int id;							// ID of the project
	private List<Activity> activities;		// List of activities attached to this project

	/**
	 * Create a project with all the needed data
	 * @param id of the project
	 * @param title of the project
	 * @param allottedTime to the project
	 * @param projectLeader of the project
	 * @param startDate of the project
	 * @param endDate of the project
	 */
	public Project(int id, String title, double allottedTime, User projectLeader, Calendar startDate, Calendar endDate) {
		this.id = id;
		this.title = title;
		this.allottedTime = allottedTime;
		this.projectLeader = projectLeader;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * Create a project with a title and a allotted time
	 * @param title of the project 
	 * @param allottedTime Time set aside for the project
	 * @throws SQLException
	 */
	public Project(String title, int allottedTime) throws SQLException {
		this.createProject(title, allottedTime);
	}
	
	/**
	 * Create a project with a title, allotted time,s and a project leader. 
	 * @param name of the project
	 * @param allottedTime
	 * @param projectLeader
	 * @throws SQLException
	 */
	public Project(String name, double allottedTime, User projectLeader) throws SQLException {
		this.projectLeader = projectLeader;
		this.createProject(name, allottedTime);
	}
	
	/**
	 * Create a project with everything but an end date
	 * @param name of the project
	 * @param allottedTime to the project
	 * @param projectLeaderID of the project leader of this project
	 * @param startDate
	 * @param endDate
	 * @throws SQLException
	 */
	public Project(String name, double allottedTime, User projectLeader, Calendar startDate) throws SQLException{
		this.projectLeader = projectLeader;
		this.startDate = startDate;
		this.createProject(name, allottedTime);
	}
	
	/**
	 * Create a project with all the needed data
	 * @param name of the project
	 * @param allottedTime to the project
	 * @param projectLeaderID of the project leader of this project
	 * @param startDate
	 * @param endDate
	 * @throws SQLException
	 */
	public Project(String name, double allottedTime, User projectLeader, Calendar startDate, Calendar endDate) throws SQLException{
		this.endDate = endDate;
		this.startDate = startDate;
		this.projectLeader = projectLeader;
		this.createProject(name, allottedTime);
	}

	
	/**
	 * Save the project
	 * @param title of the project
	 * @param allottedTime to the project
	 * @throws SQLException 
	 */
	private void createProject(String title, double allottedTime) throws SQLException {
		this.title = title;
		this.allottedTime = allottedTime;
		this.isActive = true;
		
		if (dataManager == null) 
			dataManager = new ProjectDatabaseManager();
		dataManager.saveProject(this);
	}
	
	static void setDataManager	(IProjectDatabaseManager db) {
		dataManager = db;
	}
	
	/**
	 * @return The ID of this project
	 */
	public int getID(){
		return this.id;
	}

	/**
	 * @return If this project is active or not
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * Set the status of this project to active
	 * @throws SQLException 
	 */
	public void activateProject() throws SQLException {
		this.isActive = true;
		dataManager.updateProject(this);
	}
	
	/**
	 * @return The time allotted to the project
	 */
	public double getAllottedTime() {
		return allottedTime;
	}
	
	/**
	 * Update the allotted time for this project
	 * @param allottedTime The time to set for this project
	 * @throws SQLException 
	 */
	public void setAllottedTime(double allottedTime) throws SQLException {
		this.allottedTime = allottedTime;
		dataManager.updateProject(this);
	}
	
	/**
	 * @return Get the end date of this project
	 */
	public Calendar getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the end date of this project
	 * @param endDate for this project
	 * @throws SQLException 
	 */
	public void setEndDate(Calendar endDate) throws SQLException {
		this.endDate = endDate;
		dataManager.updateProject(this);
	}
	
	/**
	 * Get the start date of this project
	 * @return The start date of this project
	 */
	public Calendar getStartDate() {
		return startDate;
	}
	
	/**
	 * Update the start date for this project
	 * @param startDate
	 * @throws SQLException 
	 */
	public void setStartDate(Calendar startDate) throws SQLException {
		this.startDate = startDate;
		dataManager.updateProject(this);
	}
	
	/**
	 * @return Get the title of this project
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the title of this project
	 * @param title to set for this project
	 * @throws SQLException 
	 */
	public void setTitle(String title) throws SQLException {
		this.title = title;
		dataManager.updateProject(this);
	}
	
	/**
	 * @return The leader of this project
	 * @throws SQLException 
	 */
	public User getProjectLeader() {
		return this.projectLeader;
	}
	
	/**
	 * Set the project leader of this project
	 * @param admin to check that it is the admin that is adding a project leader
	 * @param employee which should be the project leader
	 * @throws ActionNotAllowedException
	 * @throws SQLException 
	 */
	public void setProjectLeader(User admin, Employee employee) throws ActionNotAllowedException, SQLException{
		if (admin.isAdmin()) {
			this.projectLeader = employee;
			dataManager.updateProject(this);
		} else {
			throw new ActionNotAllowedException("You do not have the rights to set the project leader", admin);
		}
	}
	
	/**
	 * Remove the project leader
	 * @param admin This should be an admin account, to insure only admins can add project leaders
	 * @throws ActionNotAllowedException
	 * @throws SQLException 
	 */
	public void removeProjectLeader(User admin) throws ActionNotAllowedException, SQLException{
		if (admin.isAdmin()) {
			this.projectLeader = null;
			dataManager.updateProject(this);
		} else {
			throw new ActionNotAllowedException("You do not have the rights to remove the project leader", admin);
		}
	}
	
	/**
	 * Add an activity to this project
	 * @param activity to add
	 * @throws SQLException  
	 */
	public void addActivity(Activity activity) throws SQLException {
		if (activities != null)
			activities.add(activity);
		else {
			activities = new ArrayList<Activity>();
			activities.add(activity);
		}
		dataManager.addActivityToProject(this, activity);
	}
	
	/**
	 * Remove an activity from this activity
	 * @param activity to remove
	 * @throws SQLException  
	 */
	public void removeActivity(Activity activity) throws SQLException {
		activities.remove(activity);
		dataManager.removeActivityFromProjcet(this, activity);
	}
	
	/**
	 * Create a report of this project
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void printProjectReport(String path) throws IOException, SQLException {
		ProjectReport projectReport = new ProjectReport(this);
		projectReport.print(path);
	}
	
	@Override
	public String toString() {
		return this.title;
	}
	
	@Override
	public boolean equals(Object other) {
		Project otherProject;
		if (other instanceof Project)		// Check the argument is a Project
			otherProject = (Project) other;
		else 
			return false;
		
		// Statement to compare all fields in the User class
		if (this.getTitle().equals(otherProject.getTitle()) &&
				this.getID() == otherProject.getID() &&
//				this.getEndDate().equals(otherProject) &&
//				this.getStartDate().equals(otherProject) &&
				this.getAllottedTime() == otherProject.getAllottedTime() &&
				this.projectLeader.equals(otherProject.getProjectLeader()))
			return true;
		else 
			return false;
	}
	
	@Override 
	public int compareTo(Project other) {
		return Integer.compare(this.getID(), other.getID());
	}
	
	/**
	 * Get a project based on the passed ID 
	 * @param projectID of the project 
	 * @return A project with the passed ID
	 */
	public static Project getProject(int projectID) throws SQLException {
		return dataManager.getProject(projectID);
	}
	
	/**
	 * Get a project by it's title
	 * @param title of the project to get
	 * @return The project with the matching title
	 * @throws SQLException
	 */
	public static Project getProject(String title) throws SQLException {
		return dataManager.getProject(title);
	}
	
	/**
	 * @return Get all the activities in this project
	 * @throws SQLException
	 */
	public List<Activity> getActivities() throws SQLException {
		if (this.activities == null || this.activities.isEmpty())
			this.activities = dataManager.getActivitiesInProject(this);
	
		return this.activities;
	}
	/**
	 * @return Total spent time on project
	 * @throws SQLException
	 */
	public float getSpentTime() throws SQLException{
		float spentTime = 0;
		for(Activity current : getActivities()){
			spentTime += current.getTimeAccumulated();
		}
		return spentTime;
		
	}
	
	/**
	 * Get project by a project leader
	 * @param user to get related project from
	 * @return a list of project
	 * @throws SQLException
	 */
	public static List<Project> getProjectByProjectLeader(User user) throws SQLException {
		return dataManager.getProjectsByProjectLeader(user);
	}
}