package projectPlanner.users;

/**
 * Admin class to have extra access to the program
 */
public class Admin extends User {

	public Admin() throws Exception {
		super("Admin", "Admin!234", "Admin", "Admin", 1);
	}
	
	/**
	 * @return Will always return true for the admin class
	 */
	@Override
	public boolean isAdmin() {
		return true;
	}
}