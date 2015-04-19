package projectPlanner.database;

import java.sql.SQLException;
import java.util.List;

import projectPlanner.Activity;
import projectPlanner.Project;
import projectPlanner.users.*;

public interface IProjectDatabaseManager {

	/**
	 * Get a project by its ID
	 * @param id of the project 
	 * @return The project with the matching ID
	 * @throws SQLException
	 */
	public Project getProject(int id) throws SQLException;

	/**
	 * Get the project with the passed title
	 * @param title of the project to get
	 * @return The project with the matching title
	 * @throws SQLException
	 */
	public Project getProject(String title) throws SQLException;

	/**
	 * Get all the projects in the database
	 * @return All the projects in the database
	 * @throws SQLException
	 */
	public List<Project> getAllProjects() throws SQLException;
	
	/**
	 * Save the passed project to the database
	 * @param project to save
	 * @throws SQLException
	 */
	public void saveProject(Project project) throws SQLException;
	
	/**
	 * Update the database with new information about the project
	 * @param project to update data about
	 * @throws SQLException
	 */
	public void updateProject(Project project) throws SQLException;
	
	/**
	 * Get all the active projects
	 * @return A list with all the active projects
	 * @throws SQLException
	 */
	public List<Project> getActiveProjects() throws SQLException;
	
	/**
	 * Get all the projects, where the passed user is project leader
	 * @param user to get relevant projects for
	 * @return A list of all the projects, where the passed user is leader
	 * @throws SQLException
	 */
	public List<Project> getProjectsByProjectLeader(User user) throws SQLException;

	/**
	 * Add an activity to this project
	 * @param project to add the activity to
	 * @param activity to add
	 */
	public void addActivityToProject(Project project, Activity activity) throws SQLException;

	/**
	 * Removes an activity from the project
	 * @param project to remove the project from
	 * @param activity to remove
	 */
	public void removeActivityToProjcet(Project project, Activity activity) throws SQLException;

	/**
	 * Get all the activities related to the passed project
	 * @param project to get the related activities form
	 * @return A list of related activities
	 */
	public List<Activity> getActivitiesInProject(Project project) throws SQLException;
}
