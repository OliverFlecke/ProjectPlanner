package projectPlanner;

import java.util.List;

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
	public List<Activity> getCurrentUsersActivities(){
		return User.getActivities(currentUser);

	}
	public List<Activity> getActivitiesByEmployee(Employee employee){
		return Activity.getActivities(employee);
	}
	public List<Activity> getActivitiesByProject(Project project){
		return Activity.getActivities(project);
	}
	public List<Employee> getEmployeesByActivity(Activity activity){
		return Employee.getEmployees(activity);
	}
	public List<Employee> getEmployeesNotInActivity(Activity activity){
		List<Employee> activityEmployees = Employee.getEmployees(activity);
		List<Employee> allEmployees = Employee.getAllEmployees();
		for(Employee current : activityEmployees){
			allEmployees.remove(current);
		}
		return allEmployees;
	}
}

