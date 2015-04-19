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
		int hours = result.getInt("AccumulatedHours");
		int projectID = result.getInt("ProjectID");
		boolean status = result.getBoolean("IsActive");
		
		Project project = ProjectDatabaseManager.getCurrentProject(result);
		
		return new Activity(ID, title, project, hours, status);
	}
	
	@Override
	public void saveActivity(Activity activity) throws SQLException {
		String SQL = "INSERT INTO Activity (Title, AccumulatedHours, ProjectID) " +
				"VALUES('" + activity.getTitle() + "', " +
				activity.getTimeAccumulated() + ", " +
				activity.getProjectID() + ";";
		executeQuery(SQL);
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
	public List<Employee> getUsers(Activity activity) throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		String SQL = "SELECT Activities.*, Employees.*, Projects.* FROM WorksOn " +
						"INNER JOIN Employees ON WorksOn.EmployeeID=Employees.EmployeeID " +
						"INNER JOIN Activities ON WorksOn.ActivityID=Activities.ActivityID " +
						"INNER JOIN Projects ON Activities.ProjectID=Projects.ProjectID " +
						"WHERE WorksOn.ActivityID=" + activity.getID() + ";";
		resultSet = executeQuery(SQL);
		
		while (resultSet.next()) {
			list.add(UserDatabaseManager.getUserFromResultSet(resultSet));
		}
		return list;
	}
	
	@Override
	public void addEmployee(Employee employee, Activity activity) throws SQLException {
		String SQL = "INSERT INTO WorksOn Values(" + 
				employee.getID() + ", " + 
				activity.getID() + ");";
		executeUpdate(SQL);
	}
	
	@Override 
	public void updateActivity(Activity activity) throws SQLException {
		String SQL = "UPDATE Activities " +
				"SET Title = '" + 			activity.getTitle() + "'," +
				"AccumulatedHours = " + 	activity.getTimeAccumulated() + ", " +
				"ProjectID = " + 			activity.getProjectID() +
				"WHERE ActivityID = " + 	activity.getID();
		executeQuery(SQL);
	}
}
