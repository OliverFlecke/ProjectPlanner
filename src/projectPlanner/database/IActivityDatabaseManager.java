package projectPlanner.database;

import java.sql.*;
import java.util.List;

import projectPlanner.*;
import projectPlanner.users.Employee;
import projectPlanner.users.User;

public interface IActivityDatabaseManager {
	
	/**
	 * Get an activity with the passed ID
	 * @param ID of the activity to get
	 * @return The found activity with the passed ID
	 * @throws SQLException
	 */
	public Activity getActivity(int ID) throws SQLException;
	
	/**
	 * Get an activity with the passed title
	 * @param title
	 * @return An activity with the given title
	 * @throws SQLException
	 */
	public Activity getActivity(String title) throws SQLException;

	/**
	 * Get all the users on the relevant 
	 * @return All the users working on this activity
	 * @throws SQLException
	 */
	public List<Employee> getUsers(Activity activity) throws SQLException;

	/**
	 * Add an employee to the activity
	 * @param employee to add to the activity
	 */
	public void addEmployee(User employee, Activity activity) throws SQLException;
	
	/**
	 * Remove an employee from the passed activity
	 * @param employee to remove
	 * @param activity to remove the employee from
	 * @throws SQLException
	 */
	public void removeEmployee(User employee, Activity activity) throws SQLException;

	/**
	 * Update the data about the passed activity
	 * @param activity to update
	 * @throws SQLException
	 */
	public void updateActivity(Activity activity) throws SQLException;
	
	/**
	 * Save the passed activity to the database
	 * @param activity to save
	 * @throws SQLException
	 */
	public void saveActivity(Activity activity) throws SQLException;
	
	/**
	 * Delete the passed activity
	 * @param activity to delete
	 * @throws SQLException
	 */
	public void deleteActivity(Activity activity) throws SQLException;

	/**
	 * Get all the activities related to the passed employee
	 * @param employee to get the related activities from
	 * @return A list of activities related to the employee
	 */
	public List<Activity> getActivitiesByEmployee(Employee employee) throws SQLException;

	/**
	 * Get all the activities related to the passed project
	 * @param project to get the related activities for
	 * @return A list of related acticities
	 */
	public List<Activity> getActivitiesByProject(Project project) throws SQLException;
}
