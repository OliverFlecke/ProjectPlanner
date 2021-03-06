package projectPlanner.users;

import java.sql.SQLException;
import java.util.*;

import projectPlanner.*;
import projectPlanner.database.*;

/**
 * Abstract user class to define the elements of a user
 */
public abstract class User implements Comparable<User> {
	// Will manage the saving and retriving of user objects 
	protected static IUserDataManager dataManager = new UserDatabaseManager();
	
	// Username and password for the user
	private String username;
	private String password;
	
	// Name and ID of the user
	private int id;
	private String firstname;
	private String lastname;
	
	/**
	 * Create the user with all the relevant data
	 * @param username of the usr
	 * @param password to login to the system
	 * @param id of the user, which should be unic
	 * @param firstname of the user
	 * @param lastname of the user
	 */
	protected User(String username, String password, String firstname, String lastname) throws SQLException {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		
		// Insures the user class has an data manager before trying to get a new user ID
		if (dataManager == null) {
			dataManager = new UserDatabaseManager();
		}
		
		// Save the user by using the dataManager when the user has been created
		dataManager.saveEmployee(this, password);
	}
	
	/**
	 * Create a new user with an already exciting id
	 * @param username of the user
	 * @param password to login to our system
	 * @param firstname of the user
	 * @param lastname of the user
	 * @param id to identify the user
	 */
	protected User(String username, String password, String firstname, String lastname, int id) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
	}
	
	/**
	 * @return get the userdata manager
	 */
	static IUserDataManager getDataManager() {
		return dataManager;
	}
	
	/**
	 * Set the datamanager of all the user objects 
	 * @param newDataManager the new datamanager for all the users
	 */
	public static void setDataManager(IUserDataManager newDataManager) {
		dataManager = newDataManager;
	}
	
	/**
	 * @return The static number of users current in the system
	 * @throws SQLException thrown if the database connection fails
	 */
	public static int getNumberOfUsers() throws SQLException {
		return dataManager.getNumberOfUsers();
	}
	
	/**
	 * @return the ID of this user
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Get the username of this user
	 * @return the username of this user
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @return The firstname of this user
	 */
	public String getFirstname() {
		return this.firstname;
	}
	
	/**
	 * @return the lastname of this user
	 */
	public String getLastname() {
		return this.lastname;
	}
	
	/**
	 * @return Will return true, if the user is an admin
	 */
	public boolean isAdmin() {
		if (this.firstname.equalsIgnoreCase("Admin") &&
				this.lastname.equalsIgnoreCase("Admin")) 
			return true;
		else 
			return false;
	}
	
	/**
	 * Check if the passed password is correct
	 * @param password to check
	 * @return true, if the password is correct
	 */
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	/**
	 * Update the password of this user
	 * @param currentPassword for this user, to insure it is the user, which changes the password
	 * @param newPassword the new password for this user. 
	 * @throws ActionNotAllowedException thrown if the password is wrong
	 * @throws SQLException 
	 */
	public void updatePassword(String currentPassword, String newPassword) throws ActionNotAllowedException, SQLException {
		if (this.checkPassword(currentPassword)) {
			this.password = newPassword;
			dataManager.updateEmployee(this, password);
		} else {
			throw new ActionNotAllowedException("Wrong password", this);
		}
	}
	
	/**
	 * Update the username, and check if the user is allowed
	 * @param newUsername new username for this user
	 * @param password to insure it is the user, that are changing the username
	 * @throws SQLException 
	 */
	public void updateUsername(String newUsername, String password) throws ActionNotAllowedException, SQLException {
		if (this.checkPassword(password)) {
			this.username = newUsername;
			dataManager.updateEmployee(this, password);
		} else {
			throw new ActionNotAllowedException("Wrong password", this);
		}
	}
	
	/**
	 * Update the name of the user
	 * @param newFirstname the new name of the user
	 * @param password to insure the user have rights to change the name
	 * @throws SQLException 
	 */
	public void updateFirstname(String newFirstname, String password) throws ActionNotAllowedException, SQLException {
		if (this.checkPassword(password)) {
			this.firstname = newFirstname;
			dataManager.updateEmployee(this, password);
		} else {
			throw new ActionNotAllowedException("Wrong password", this);
		}
	}
	
	/**
	 * Update the lastname of the user
	 * @param newLastname for the username
	 * @param password to insure the user has access to change the name
	 * @throws ActionNotAllowedException thrown if the password is wrong. 
	 * @throws SQLException 
	 */
	public void updateLastname(String newLastname, String password) throws ActionNotAllowedException, SQLException {
		if (this.checkPassword(password)) {
			this.lastname = newLastname;
			dataManager.updateEmployee(this, password);
		} else {
			throw new ActionNotAllowedException("Wrong password", this);
		}
	}
	
	@Override
	public String toString() {
		return this.firstname + " " + this.lastname + " Username: " + this.username + " Password: " 
				+ this.password + " ID: " + this.id;
	}
	@Override
	public boolean equals(Object other) {
		User otherUser;
		if (other instanceof User)		// Check the passed object
			otherUser = (User) other;
		else 
			return false;
		
		// Statement to compare all fields in the User class
		if (this.getFirstname().equals(otherUser.getFirstname()) &&
				this.getLastname().equals(otherUser.getLastname()) &&
				this.getID() == otherUser.getID() &&
				this.getUsername().equals(otherUser.getUsername()) &&
				otherUser.checkPassword(this.password))
			return true;
		else 
			return false;
	}
	
	@Override 
	public int compareTo(User other) {
		return Integer.compare(this.getID(), other.getID());
	}
	
	/**
	 * Get a user by it's id
	 * @param id to get user by
	 * @return the user found with the relative user id
	 * @throws SQLException 
	 */
	public static User getUser(int id) throws SQLException {
		return dataManager.getEmployee(id);
	}
	
	/**
	 * Get a user by his username
	 * @param username to get user by
	 * @return The user with a matching username 
	 * @throws SQLException 
	 */
	public static User getUser(String username) throws SQLException {
		return dataManager.getEmployee(username);
	}
	
	/**
	 * Get an employee by his firstname and lastname
	 * @param firstname
	 * @param lastname
	 * @return A user with the passed firstname and lastname
	 * @throws SQLException
	 */
	public static User getUser(String firstname, String lastname) throws SQLException {
		return dataManager.getEmployee(firstname, lastname);
	}
	
	/**
	 * Get all the employees in the database
	 * @return All the employees in the database
	 * @throws SQLException
	 */
	public static List<User> getAllUsers() throws SQLException {
		return dataManager.getAllEmployees();
	}

	/**
	 * Get all the related activities for a user
	 * @param user to get the activities from
	 * @return A list of activities this user is working on
	 * @throws SQLException 
	 */
	public static List<Activity> getActivities(User user) throws SQLException {
		return dataManager.getActivities(user);
	}
	
	/**
	 * Get the time the passed user has spend on the passed activity
	 * @param user to check for
	 * @param Activity which the user have spend time on
	 * @return The amount of time the user have spend on the given activity
	 */
	public static double getTimeSpendOnActivity(User user, Activity activity) throws SQLException {
		return dataManager.getTimeSpendOnActivity(user, activity);
	}
	
	/**
	 * Get the time this user has spend on all his related activities
	 * @return The time the user has spend
	 * @throws SQLException
	 */
	public double getTimeSpendOnAllActivities() throws SQLException {
		return dataManager.getTimeSpendOnAllActivities(this);
	}
	
	/**
	 * Set the time a user has spend on an activty
	 * @param time LoggedTIme object to get data from.
	 * @throws SQLException
	 */
	public static void setTimeSpendOnActivity(LoggedTime time) throws SQLException {
		dataManager.logTimeOnActivity(time);
	}
	
	/**
	 * Update a logged time object
	 * @param newTime to update to
	 * @throws SQLException
	 */
	public static void updateLoggedTime(LoggedTime newTime) throws SQLException {
		dataManager.updateLoggedTimeOnActivity(newTime);
	}
	
	/**
	 * @return The time the user has spend on each activity
	 * @throws SQLException
	 */
	public List<LoggedTime> getLoggedTime() throws SQLException {
		return dataManager.getTimeSpendOnEachActivity(this);
	}
}
