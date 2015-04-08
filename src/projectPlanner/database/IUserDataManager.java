package projectPlanner.database;

import java.util.List;

import projectPlanner.users.User;

/**
 * Interface to get user data from any data source
 */
public interface IUserDataManager {

	/**
	 * Get the number of users in the database
	 * @return the number of users in the database
	 */
	public int getNumberOfUsers();
	
	/**
	 * Save an employee by passing the user object and the password for the user
	 * @param user to be saved 
	 * @param password of the user to be saved
	 */
	public void saveEmployee(User user, String password);
	
	/**
	 * Save the user by passing each relevant data by it self. 
	 * @param id of the user
	 * @param firstname of the user
	 * @param lastname of the user
	 * @param username for the user to login to our system
	 * @param password for the user to login to our system
	 */
	public void saveEmployee(int id, String firstname, String lastname, String username, String password);
	
	/**
	 * Get an employee from the database by his id
	 * @param id of the employee to get
	 */
	public User getEmployee(int id);	
	
	/**
	 * Get all users in the database
	 * @return all the users in the database
	 */
	public List<User> getAllEmployees();
}
