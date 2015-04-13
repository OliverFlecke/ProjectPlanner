package projectPlanner.database;

import projectPlanner.*;
import projectPlanner.users.*;

import java.sql.*;
import java.util.*;

/**
 * 
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
	public List<User> getUsers() throws SQLException {
		List<User> list = new ArrayList<User>();
		String SQL = "SELECT Activities.*, Employees.*, Projects.* FROM WorksOn " +
						"INNER JOIN Employees ON WorksOn.EmployeeID=Employees.EmployeeID " +
						"INNER JOIN Activities ON WorksOn.ActivityID=Activities.ActivityID " +
						"INNER JOIN Projects ON Activities.ProjectID=Projects.ProjectID " +
						"WHERE WorksOn.EmployeeID=1;";
		resultSet = executeQuery(SQL);
		
		while (resultSet.next()) {
			
		}
		
		
		return list;
	}
}
