package projectPlanner.users;

/**
 *
 */
public abstract class User {
	private static int numberOfUsers = 0;
	
	// Username and password for the user
	private String username;
	private String password;
	
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
	 * @return the ID of this user
	 */
	public int getID() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}

	/**
	 * @return The static number of users current in the system
	 */
	public static int getNumberOfUsers() {
		return numberOfUsers;
	}
}
