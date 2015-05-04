package projectPlanner.database;

import java.sql.*;
import java.util.List;
import java.util.Map;

import projectPlanner.Activity;
import projectPlanner.LoggedTime;
import projectPlanner.users.*;

/**
 * Interface to get user data from any data source
 */
public interface IUserDataManager {

	/**
	 * Get the number of users in the database
	 * @return the number of users in the database
	 */
	public int getNumberOfUsers() throws SQLException;
	
	/**
	 * Update the database with new user data
	 * @param user to update data about
	 * @param password for the user
	 * @throws SQLException
	 */
	public void updateEmployee(User user, String password) throws SQLException;
	
	/**
	 * Save an employee by passing the user object and the password for the user
	 * @param user to be saved 
	 * @param password of the user to be saved
	 */
	public void saveEmployee(User user, String password) throws SQLException;
	
	/**
	 * Save the user by passing each relevant data by it self. 
	 * @param id of the user
	 * @param firstname of the user
	 * @param lastname of the user
	 * @param username for the user to login to our system
	 * @param password for the user to login to our system
	 */
	public void saveEmployee(int id, String firstname, String lastname, String username, String password)
			throws SQLException;
	
	/**
	 * Get an employee from the database by his id
	 * @param id of the employee to get
	 */
	public User getEmployee(int id) throws SQLException;	
	
	/**
	 * Get a user by his username
	 * @param username to get user by
	 * @return A user from the database with the passed username
	 */
	public User getEmployee(String username) throws SQLException;
	
	/**
	 * Get a user by his first or lastname
	 * @param firstname of the user
	 * @param lastname of the user
	 * @return The first found user with the relevant first and last name
	 * @throws SQLException
	 */
	public User getEmployee(String firstname, String lastname) throws SQLException;
	
	/**
	 * Get all users in the database
	 * @return All the users in the database
	 */
	public List<User> getAllEmployees() throws SQLException;
	
	/**
	 * Get the open activities related to this user
	 * @return All the related activities
	 * @throws SQLException
	 */
	public List<Activity> getActivities(User user) throws SQLException;
	
	/**
	 * Get the time the passed user has spend on the passed activity
	 * @param user to check for
	 * @param Activity which the user have spend time on
	 * @return The amount of time the user have spend on the given activity
	 */
	public double getTimeSpendOnActivity(User user, Activity activity) throws SQLException;
	
	/**
	 * Get the time the passed user has spend on the passed activity
	 * @param user to check for
	 * @return The amount of time the user have spend on the given activity
	 */
	public double getTimeSpendOnAllActivities(User user) throws SQLException;
	
	/**
	 * Get a map of each activity the passed user is linked to
	 * @param user to get linked activities to
	 * @return A map of activities and logged time objects 
	 * @throws SQLException
	 */
	public List<LoggedTime> getTimeSpendOnEachActivity(User user) throws SQLException;
}
