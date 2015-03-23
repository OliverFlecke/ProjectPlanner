package projectPlanner.users;

/**
 * 
 */
public class Admin extends User {

	public Admin() {
		super("Admin", "Admin1234", "Admin");
	}
	
	/**
	 * @return Will always return true for the admin class
	 */
	@Override
	public boolean isAdmin() {
		return true;
	}
}