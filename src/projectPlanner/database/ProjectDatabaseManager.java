package projectPlanner.database;

import java.sql.*;
import java.util.*;
import java.util.Date;

import projectPlanner.*;
import projectPlanner.users.*;

public class ProjectDatabaseManager extends DatabaseManager implements IProjectDatabaseManager {

	/**
	 * Get the project from the passed result set 
	 * @param resultSet to get the project form 
	 * @return A project with the data
	 * @throws SQLException
	 */
	public static Project getCurrentProject(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("ProjectID");
		String title = resultSet.getString("ProjectTitle");
		double time = resultSet.getFloat("AlottedTime");
		
		// Avoid null pointers
		Calendar startDate = null;
		Calendar endDate = null;
		Date start = resultSet.getDate("StartDate");
		if (start != null) {
			startDate = Calendar.getInstance();
			startDate.setTime(start);
		}
		Date end = resultSet.getDate("EndDate");
		if (end != null) {
			 endDate = Calendar.getInstance();
			 endDate.setTime(end);
		}
		
		User projectLeader = UserDatabaseManager.getUserFromResultSet(resultSet);
		
		return new Project(id, title, time, projectLeader, startDate, endDate);
	}
	
//	/**
//	 * Create a project from the data in the result set, without getting the user
//	 * @param resultSet to get the data from
//	 * @param projectLeaderID the ID of the project leader
//	 * @return The project which is currently in the result set
//	 * @throws SQLException
//	 */
//	public static Project getCurrentProject(ResultSet resultSet, int projectLeaderID) throws SQLException {
//		int id = resultSet.getInt("ProjectID");
//		String title = resultSet.getString("Title");
//		double time = resultSet.getFloat("AlottedTime");
//		
//		Calendar startDate = Calendar.getInstance();
//		Calendar endDate = Calendar.getInstance();
//		startDate.setTime(resultSet.getDate("StartDate"));
//		endDate.setTime(resultSet.getDate("EndDate"));
//		
//		return new Project(id, title, time, projectLeaderID, startDate, endDate);
//	}
	
	@Override
	public Project getProject(int id) throws SQLException {
		String SQL = "SELECT Projects.*, Employees.* FROM Projects " +
				"INNER JOIN Employees " +  
				"ON Projects.ProjectLeader = Employees.EmployeeID " +
				"WHERE Projects.ProjectID = " + id;
		resultSet = executeQuery(SQL);
		
		if (resultSet.next()) 
			return ProjectDatabaseManager.getCurrentProject(resultSet);
		else 
			return null;
	}

	@Override
	public Project getProject(String title) throws SQLException {
		String SQL = "SELECT Projects.*, Employees.* FROM Projects " +
				"INNER JOIN Employees " +  
				"ON Projects.ProjectLeader = Employees.EmployeeID " +
				"WHERE Projects.ProjectTitle = '" + title + "'";
		resultSet = executeQuery(SQL);
		
		if (resultSet.next()) 
			return ProjectDatabaseManager.getCurrentProject(resultSet);
		else 
			return null;
	}

	@Override
	public List<Project> getAllProjects() throws SQLException {
		String SQL = "SELECT * FROM Projects INNER JOIN Employees " + 
				"ON Projects.ProjectLeader = Employees.EmployeeID";
		resultSet = executeQuery(SQL);
		
		List<Project> list = new ArrayList<Project>();
		while (resultSet.next()) {
			list.add(ProjectDatabaseManager.getCurrentProject(resultSet));
		}		
		return list;
	}


	@Override
	public void saveProject(Project project) throws SQLException {
		String SQL = "INSERT INTO Projects (ProjectTitle, StartDate, EndDate, IsActive, AlottedTime, ProjectLeader)"
				+ " Values(?, ?, ?, ?, ?, ?)";		
		// Create the connection and prepare the statement
		connection = DriverManager.getConnection(connectionString);
		preStatement = connection.prepareStatement(SQL);
		
		// Insert the different data into the statement
		preStatement.setString(1, project.getTitle());
		if (project.getStartDate() != null) 
			preStatement.setTimestamp(2, new Timestamp(project.getStartDate().getTimeInMillis()));
		else 
			preStatement.setNull(2, 0);
		if (project.getEndDate() != null) 
			preStatement.setTimestamp(3, new Timestamp(project.getEndDate().getTimeInMillis()));
		else 
			preStatement.setNull(3, 0);
		
		// set the rest of the data and execute
		preStatement.setBoolean(4, project.isActive());
		preStatement.setDouble(5, project.getAllottedTime());
		if (project.getProjectLeader() != null)
			preStatement.setInt(6, project.getProjectLeader().getID());
		else 
			preStatement.setNull(6, 0);
		preStatement.executeUpdate(); 	// Execute the statement
	}

	@Override
	public void updateProject(Project project) throws SQLException {
		String SQL = "UPDATE Projects "
				+ "SET ProjectTitle = ?, StartDate = ?, EndDate = ?, IsActive = ?, "
				+ "AlottedTime = ?, ProjectLeader = ? "
				+ "WHERE ProjectID = " + project.getID() + ";";
		
		// Insert the data into the statement and execute it
		connection = DriverManager.getConnection(connectionString);
		preStatement = connection.prepareStatement(SQL);
		preStatement.setString(1, project.getTitle());
		preStatement.setTimestamp(2, new Timestamp(project.getStartDate().getTimeInMillis()));
		preStatement.setTimestamp(3, new Timestamp(project.getEndDate().getTimeInMillis()));
		preStatement.setBoolean(4, project.isActive());
		preStatement.setDouble(5, project.getAllottedTime());
		preStatement.setInt(6, project.getProjectLeader().getID());
		preStatement.executeUpdate();
	}

	@Override
	public List<Project> getActiveProjects() throws SQLException {
		List<Project> projects = new ArrayList<Project>();
		String SQL = "SELECT * FROM Projects "
				+ "INNER JOIN Employees ON Projects.ProjectLeader = Employees.EmployeeID "
				+ "WHERE Projects.IsActive = 1;";
		resultSet = executeQuery(SQL);
		
		// Get all the project from the result
		while (resultSet.next()) {
			projects.add(getCurrentProject(resultSet));
		}
		return projects;
	}

	@Override
	public List<Project> getProjectsByProjectLeader(User user) throws SQLException {
		List<Project> projects = new ArrayList<Project>();
		String SQL = "SELECT * FROM Projects "
				+ "INNER JOIN Employees ON Projects.ProjectLeader = Employees.EmployeeID "
				+ "WHERE Projects.ProjectLeader = " + user.getID();		
		resultSet = executeQuery(SQL);
		
		// Add the results from the respond to a list
		while (resultSet.next()) {
			projects.add(getCurrentProject(resultSet));
		}
		return projects;
	}

	@Override
	public void addActivityToProject(Project project, Activity activity) throws SQLException {
		//String SQL = "INSERT INTO MemberOfProject VALUES(" + project.getID() + ", " + activity.getID() + ")";	
		String SQL = "INSERT INTO MemberOfProject VALUES(" + project.getID() + ", " + activity.getID() + ")";
		executeUpdate(SQL);
	}

	@Override
	public void removeActivityFromProjcet(Project project, Activity activity) throws SQLException {
		String SQL = "DELETE FROM MemberOfProject Where ActivityID = " + activity.getID();
		executeUpdate(SQL);		
	}

	@Override
	public List<Activity> getActivitiesInProject(Project project) throws SQLException {
		List<Activity> activities = new ArrayList<Activity>();
		String SQL = "SELECT * FROM MemberOfProject "
				+ "INNER JOIN Projects ON MemberOfProject.ProjectID = Projects.ProjectID "
				+ "INNER JOIN Activities ON MemberOfProject.ActivityID = Activities.ActivityID "
				+ "INNER JOIN Employees ON Projects.ProjectLeader = Employees.EmployeeID "
				+ "WHERE MemberOfProject.ProjectID = " + project.getID();
		
		// Get the result and get the data out
		resultSet = executeQuery(SQL);
		while (resultSet.next()) {
			activities.add(ActivityDatabaseManager.getCurrentActivity(resultSet, true));
		}
		return activities;
	}
}
