package projectPlanner.database;

import projectPlanner.*;
import projectPlanner.users.*;

import java.sql.*;
import java.util.*;

/**
 * Responsible for the database connection for all activity related storing. 
 */
public class ActivityDatabaseManager extends DatabaseManager implements IActivityDatabaseManager {

	/**
	 * Get an activity from a result set
	 * @param result from the database to get the activity from 
	 * @return The activity from the result set
	 * @throws SQLException if something fails in the extraction from the result set
	 */
	public static Activity getCurrentActivity(ResultSet result) throws SQLException {
		String title = result.getString("Title");
		int ID = result.getInt("ActivityID");
		int hours = result.getInt("AccumilatedHours");
		int projectID = result.getInt("ProjectID");
		
		Project project = Project.getProject(projectID);
		
		return new Activity(ID, title, project, hours);
	}
	
	@Override
	public Activity getActivity(int ID) throws SQLException {
		String SQL = "SELECT * FROM Activity WHERE ActivityID=" + ID;
		resultSet = executeQuery(SQL);
		
		Activity activity = null;
		if (resultSet.next()) 
			activity = getCurrentActivity(resultSet);
		
		return activity;
	}
	
	@Override
	public List<User> getUsers(Activity activity) throws SQLException {
		List<User> list = new ArrayList<User>();
		String SQL = "SELECT Activities.*, Employees.*, Projects.* FROM WorksOn " +
						"INNER JOIN Employees ON WorksOn.EmployeeID=Employees.EmployeeID " +
						"INNER JOIN Activities ON WorksOn.ActivityID=Activities.ActivityID " +
						"INNER JOIN Projects ON Activities.ProjectID=Projects.ProjectID " +
						"WHERE WorksOn.ActivityID=" + activity.getID() + ";";
		resultSet = executeQuery(SQL);
		
		while (resultSet.next()) {
			String firstname = resultSet.getString("Firstname");
			String lastname = resultSet.getString("Lastname");
			String username = resultSet.getString("Username");
			String password = resultSet.getString("Password");
			int ID = resultSet.getInt("EmployeeID");
			list.add(new Employee(username, password, firstname, lastname, ID));
		}
		
		
		return list;
	}
}
