package projectPlanner.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import projectPlanner.Activity;
import projectPlanner.users.User;

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
	 * Get a new ID for a user
	 * @return A new ID for a user in the database
	 */
	public int getNewID() throws SQLException;
	
	/**
	 * Get the open activities related to this user
	 * @return All the related activities
	 * @throws SQLException
	 */
	public List<Activity> getActivities(User user) throws SQLException;
}
