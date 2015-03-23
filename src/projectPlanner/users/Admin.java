package projectPlanner.users;

/**
 * Admin class to have extra access to the program
 */
public class Admin extends User {

	public Admin() {
		super("Admin", "Admin!234", "Admin");
	}
	
	/**
	 * @return Will always return true for the admin class
	 */
	@Override
	public boolean isAdmin() {
		return true;
	}
}