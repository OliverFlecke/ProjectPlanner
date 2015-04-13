package projectPlanner;

import projectPlanner.users.*;

/**
 * Main controller for the project planner program. 
 */
public class ProjectPlanner {

	private User currentUser;
	private boolean isLoggedIn;
	
	public boolean login(String username, String password) throws UserLoginException, Exception {
		currentUser = User.getUser(username);
		if (currentUser == null) {
			throw new UserLoginException("Invalid username or password");
		}
		if(!currentUser.checkPassword(password)){
			throw new UserLoginException("Invalid username or password");
		}
		setIsLoggedIn(true);
		return isLoggedIn; 
	}
	
	public void logout(){
		this.currentUser=null;
		setIsLoggedIn(false);
	}
	public void setIsLoggedIn(boolean setter){
		isLoggedIn=setter;
	}
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
}


