package projectPlanner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import projectPlanner.users.Employee;
import projectPlanner.users.User;

/**
 * Abstract project class containing project elements
 */
public class Project implements Comparable<Project> {

	private Double allottedTime;
	private String title;
	private List<User> projectLeaders;
	private Calendar startDate;
	private Calendar endDate;
	private boolean isActive;
	private static int idCount = 0;
	private int id;
	private List<Activity> activities;

	public Project(String title, Double allottedTime) {
		this.setTitle(title);
		this.setAllottedTime(allottedTime);
		this.projectLeaders = new ArrayList<User>();
		this.activities = new ArrayList<Activity>();
		this.id=idCount++;
	}
	
	public Project(String name, Double allottedTime, User projectLeader){
		this(name, allottedTime);
		projectLeaders.add(projectLeader);
	}
	
	public Project(String name, Double allottedTime, User projectLeader, Calendar startDate){
		this(name, allottedTime, projectLeader);
		this.setStartDate(startDate);
	}
	
	public Project(String name, Double allottedTime, User projectLeader, Calendar startDate, Calendar endDate){
		this(name, allottedTime, projectLeader,startDate);
		this.setEndDate(endDate);
	}
	
	/**
	 * @return The ID of this project
	 */
	public int getID(){
		return this.id;
	}

	/**
	 * @return Whenether this project is active
	 */
	public boolean isActive() {
		return isActive;
	}
	
	public void activateProject(boolean isActive) {
		this.isActive = isActive;
	}
	
	public Double getAllottedTime() {
		return allottedTime;
	}
	
	public void setAllottedTime(Double allottedTime) {
		this.allottedTime = allottedTime;
	}
	
	public Calendar getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
	public Calendar getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addProjectLeader(User admin, Employee employee) throws ActionNotAllowedException{
		projectLeaders.add(employee);			
	}
	
	public void removeProjectLeader(User admin, Employee employee) throws ActionNotAllowedException{
		projectLeaders.remove(employee);	
	}
	
	public void addActivity(Activity activity){
		activities.add(activity);
	}
	
	public void removeActivity(Activity activity){
		activities.remove(activity);
	}
	
	public void printProjectReport(){
		ProjectReport projectReport = new ProjectReport();
		projectReport.print(this);
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
				this.getID()==otherProject.getID() &&
				this.getEndDate().equals(otherProject) &&
				this.getStartDate().equals(otherProject) &&
				this.getAllottedTime().equals(otherProject.getAllottedTime()))
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
	public static Project getProject(int projectID) {
		// TODO Get the project with the project ID from the database
		return null;
	}

}
