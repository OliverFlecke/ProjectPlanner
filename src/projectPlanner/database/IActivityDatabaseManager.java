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
	 * Get all the users on the relevant 
	 * @return All the users working on this activity
	 * @throws SQLException
	 */
	public List<Employee> getUsers(Activity activity) throws SQLException;

	/**
	 * Add an employee to the activity
	 * @param employee to add to the activity
	 */
	public void addEmployee(Employee employee, Activity activity) throws SQLException;

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
}
