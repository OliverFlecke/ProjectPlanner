package projectPlanner;

import projectPlanner.users.*;

/**
 * Main controller for the project planner program. 
 */
public class ProjectPlanner {

	private User currentUser;
	
	public boolean login(String username, String password) throws UserLoginException, Exception {
		currentUser = User.getUserByUsername(username);
		if (currentUser == null) {
			throw new UserLoginException("Username not found");
		}
		
		return false;
	}
}
