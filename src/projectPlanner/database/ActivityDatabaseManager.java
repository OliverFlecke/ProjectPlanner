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
	public static Activity getCurrentActivity(ResultSet result, boolean getProject) throws SQLException {
		String title = result.getString("Title");
		int ID = result.getInt("ActivityID");
		double hours = result.getDouble("AccumulatedHours");
		int projectID = result.getInt("ProjectID");
		boolean status = result.getBoolean("IsActive");
		
		if (getProject) {
			Project project = ProjectDatabaseManager.getCurrentProject(result);
			return new Activity(ID, title, project, hours, status);
		} else 
			return new Activity(ID, title, projectID, hours, status);
	}
	
	@Override
	public void saveActivity(Activity activity) throws SQLException {
		String SQL = "INSERT INTO Activities (Title, AccumulatedHours, ProjectID) " +
				"VALUES('" + activity.getTitle() + "', " +
				activity.getTimeAccumulated() + ", " +
				activity.getProjectID() + ");";
		executeUpdate(SQL);
		
		// Register the link between the project and the activity
		SQL = "INSERT INTO MemberOfProject (ProjectID, ActivityID) "
				+ "VALUES(" + activity.getProjectID() + ", " + activity.getID() + ");";
		executeUpdate(SQL);
	}
	
	@Override
	public Activity getActivity(int ID) throws SQLException {
		String SQL = "SELECT * FROM Activities "
				+ "INNER JOIN Projects ON Projects.ProjectID = Activities.ProjectID "
				+ "INNER JOIN Employees ON Projects.ProjectLeader = Employees.EmployeeID "
				+ "WHERE ActivityID = " + ID;
		resultSet = executeQuery(SQL);
		
		Activity activity = null;
		if (resultSet.next()) 
			activity = getCurrentActivity(resultSet, true);
		
		return activity;
	}
	
	@Override
	public List<Employee> getUsers(Activity activity) throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		String SQL = "SELECT Activities.*, Employees.*, Projects.* FROM WorksOn " +
						"INNER JOIN Employees ON WorksOn.EmployeeID = Employees.EmployeeID " +
						"INNER JOIN Activities ON WorksOn.ActivityID = Activities.ActivityID " +
						"INNER JOIN Projects ON Activities.ProjectID = Projects.ProjectID " +
						"WHERE WorksOn.ActivityID = " + activity.getID() + ";";
		resultSet = executeQuery(SQL);
		
		while (resultSet.next()) {
			list.add(UserDatabaseManager.getUserFromResultSet(resultSet));
		}
		return list;
	}
	
	@Override
	public void addEmployee(User employee, Activity activity) throws SQLException {
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
		executeUpdate(SQL);
	}
	
	@Override
	public List<Activity> getActivitiesByEmployee(Employee employee) throws SQLException {
		List<Activity> activities = new ArrayList<Activity>();
		String SQL = "SELECT * FROM WorksOn "
				+ "INNER JOIN Employees ON Employees.EmployeeID = WorksOn.EmployeeID "
				+ "INNER JOIN Activities ON Activity.ActivityID = WorksOn.ActivityID "
				+ "WHERE WorksOn.EmployeeID = " + employee.getID();
		resultSet = executeQuery(SQL);
		while (resultSet.next()) {
			activities.add(getCurrentActivity(resultSet, false));
		}
		return activities;
	}
	
	@Override
	public List<Activity> getActivitiesByProject(Project project) throws SQLException {
		List<Activity> activities = new ArrayList<Activity>();
		String SQL = "SELECT * FROM Activities "
				+ "INNER JOIN Projects ON Projects.ProjectID = Activities.ActivityID "
				+ "INNER JOIN Employees ON Projects.ProjectLeader = Employees.EmployeeID "
				+ "WHERE Activities.ProjectID = " + project.getID();		
		
		resultSet = executeQuery(SQL);
		while (resultSet.next()) {
			activities.add(ActivityDatabaseManager.getCurrentActivity(resultSet, true));
		}
		return activities;
	}
}
