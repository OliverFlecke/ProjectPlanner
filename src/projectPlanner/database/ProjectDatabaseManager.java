package projectPlanner.database;

import java.sql.*;
import java.util.*;

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
		String title = resultSet.getString("Title");
		double time = resultSet.getFloat("AlottedTime");
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.setTime(resultSet.getDate("StartDate"));
		endDate.setTime(resultSet.getDate("EndDate"));
		User projectLeader = UserDatabaseManager.getUserFromResultSet(resultSet);
		
		return new Project(id, title, time, projectLeader, startDate, endDate);
	}
	
	@Override
	public Project getProject(int id) throws SQLException {
		String SQL = "SELECT Project.*, Employees.* FROM Project " +
				"INNER JOIN Employees " +  
				"ON Project.ProjectLeader = Employees.EmployeeID " +
				"WHERE Project.ProjectID = " + id;
		resultSet = executeQuery(SQL);
		
		if (resultSet.next()) 
			return ProjectDatabaseManager.getCurrentProject(resultSet);
		else 
			return null;
	}

	@Override
	public Project getProject(String title) throws SQLException {
		String SQL = "SELECT Project.*, Employees.* FROM Project " +
				"INNER JOIN Employees " +  
				"ON Project.ProjectLeader = Employees.EmployeeID " +
				"WHERE Project.Title = '" + title + "'";
		resultSet = executeQuery(SQL);
		
		if (resultSet.next()) 
			return ProjectDatabaseManager.getCurrentProject(resultSet);
		else 
			return null;
	}

	@Override
	public List<Project> getAllProjects() throws SQLException {
		String SQL = "SELECT * FROM Project INNER JOIN Employees " + 
				"ON Project.ProjectLeader = Employees.EmployeeID";
		resultSet = executeQuery(SQL);
		
		List<Project> list = new ArrayList<Project>();
		while (resultSet.next()) {
			list.add(ProjectDatabaseManager.getCurrentProject(resultSet));
		}		
		return list;
	}


	@Override
	public void saveProject(Project project) throws SQLException {
		String SQL = "INSERT INTO Project (Title, StartDate, EndDate, IsActive, AlottedTime, ProjectLeader)"
				+ " Values(?, ?, ?, ?, ?, ?)";		
		// Create the connection and prepare the statement
		connection = DriverManager.getConnection(connectionString);
		preStatement = connection.prepareStatement(SQL);
		
		// Insert the different data into the statement
		preStatement.setString(1, project.getTitle());
		preStatement.setTimestamp(2, new Timestamp(project.getStartDate().getTimeInMillis()));
		preStatement.setTimestamp(3, new Timestamp(project.getEndDate().getTimeInMillis()));
		preStatement.setBoolean(4, project.isActive());
		preStatement.setDouble(5, project.getAllottedTime());
		preStatement.setInt(6, project.getProjectLeader().getID());
		preStatement.executeUpdate(); 	// Execute the statement
	}

	@Override
	public void updateProject(Project project) throws SQLException {
		String SQL = "UPDATE Project "
				+ "SET Title = ?, StartDate = ?, EndDate = ?, IsActive = ?, "
				+ "AlottedTime = ?, ProjectLeader = ? "
				+ "WHERE ProjectID = " + project.getID() + ";";
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
		String SQL = "SELECT Project.*, Employees.* FROM Project "
				+ "INNER JOIN Employees ON Project.ProjectLeader = Employees.EmployeeID "
				+ "WHERE Project.IsActive = 1;";
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
		String SQL = "SELECT Project.*, Employees.* FROM Project "
				+ "INNER JOIN Employees ON Project.ProjectLeader = Employees.EmployeeID "
				+ "WHERE Project.ProjectLeader = " + user.getID();		
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
	public void removeActivityToProjcet(Project project, Activity activity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Activity> getActivitiesInProject(Project project) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



}
