package projectPlanner.users;

import projectPlanner.*;

/**
 * Abstract user class to define the elements of a user
 */
public abstract class User {
	// Static variable to hold the count of users, and to create new user IDs
	private static int numberOfUsers = 0;
	
	// Username and password for the user
	private String username;
	private String password;
	
	// Name and ID of the user
	private int id;
	private String name;
	
	/**
	 * Create the user with all the relevant data
	 * @param username of the usr
	 * @param password to login to the system
	 * @param id of the user, which should be unic
	 * @param name of the user
	 */
	protected User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.id = User.getNewUserID();
	}
	
	/**
	 * Get a new user ID
	 * @return a new, unic user ID
	 */
	private static int getNewUserID() {
		return ++numberOfUsers;
	}
	
	/**
	 * @return The static number of users current in the system
	 */
	public static int getNumberOfUsers() {
		return numberOfUsers;
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
	 * @return The name of this user
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return Will return true, if the user is an admin
	 */
	public boolean isAdmin() {
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
	
	public void updatePassword(String currentPassword, String newPassword) throws ActionNotAllowedException {
		if (this.checkPassword(currentPassword)) {
			this.password = newPassword;
		} else {
			throw new ActionNotAllowedException("Wrong password", this);
		}
	}
}
