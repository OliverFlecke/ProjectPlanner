package projectPlanner.database;

import projectPlanner.*;
import projectPlanner.users.*;

import java.sql.*;
import java.util.*;

/**
 * <p>
 * Handles all the communication between the SQL server and the user class. Insures the user data is saved right
 * and can be retrived again. 
 * </p>
 * @author Oliver Fleckenstein
 */
public class UserDatabaseManager extends DatabaseManager implements IUserDataManager {
	
	/**
	 * Get the current user from the resultSet. This should be used when a SQL statement has been made 
	 * on the server, and the resultSet has got a respond including user data
	 * @return The user in the current result set
	 * @throws SQLException is thrown if the coloumns can't be found
	 */
	public static Employee getUserFromResultSet(ResultSet resultSet) throws SQLException {
		// Get all the required user data from the table
		String firstname = resultSet.getString("Firstname");
		String lastname = resultSet.getString("Lastname");
		String username = resultSet.getString("Username");
		String password = resultSet.getString("Password");
		int databaseID = resultSet.getInt("EmployeeID");
		return new Employee(username, password, firstname, lastname, databaseID);
	}
	
	/**
	 * Get the current logged time object from the result set
	 * @param resultSet to the the data from
	 * @return The current logged time object
	 * @throws SQLException
	 */
	public static LoggedTime getCurrentLoggedTime(ResultSet resultSet) throws SQLException {
		int activityID = resultSet.getInt("ActivityID");
		int userID = resultSet.getInt("EmployeeID");
		double time = resultSet.getDouble("TimeSpend");
		Calendar date = Calendar.getInstance();
		date.setTime(resultSet.getDate("Date"));
		
		return new LoggedTime(activityID, userID, time, date);
	}
	
	/**
	 * Create an employee in the database
	 * @param id of the employee
	 * @param firstname of the employee
	 * @param lastname of the employee
	 * @param username for the user to login to the system
	 * @param password for the user to login to the system
	 */
	@Override
	public void saveEmployee(int id, String firstname, String lastname, String username, String password) throws SQLException {
		String sql = "INSERT INTO Employees " + 
				"(EmployeeID, Firstname, Lastname, Username, Password) " + 
				"VALUES(" + id + ", '" 
				+ firstname + "', '" 
				+ lastname + "', '" 
				+ username + "', '" 
				+ password + "')";
		
		UserDatabaseManager.executeUpdate(sql);
	}
	
	/**
	 * Create a new employee in the database based on a user
	 * @param user to create in the database
	 * @param password to save along with the user
	 * @throws SQLException 
	 */
	@Override
	public void saveEmployee(User user, String password) throws SQLException {
		// Check that the user is not already in the database. 
		if (this.getEmployee(user.getID()) != null) 
			return;
		else {
			String SQL = "INSERT INTO Employees " +
					"(Firstname, Lastname, Username, Password) " + 
					"VALUES('"
					+ user.getFirstname() + "', '" 
					+ user.getLastname() + "', '"
					+ user.getUsername() + "', '"
					+ password + "')";
			
			UserDatabaseManager.executeUpdate(SQL);
		}
	}
	
	/**
	 * Get the number of users in the employee table
	 * @return the number of users in the employee table
	 * @throws SQLException 
	 */
	@Override
	public int getNumberOfUsers() throws SQLException {
		String sql = "SELECT COUNT (EmployeeID) FROM Employees";
		resultSet = executeQuery(sql);
		
		// Use of a while loop to insure we have some result. Does not loop through anything
		while (resultSet.next()) {
			return resultSet.getInt(1);
		}
		return -1;		// Returned if nothing was found
	}

	@Override
	/**
	 * Get an employee by his ID from the database
	 * @param id to get employee by
	 */
	public User getEmployee(int id) throws SQLException {
		User user = null;
		String SQL = "SELECT * FROM Employees WHERE EmployeeID=" + id + "";
		
		resultSet = UserDatabaseManager.executeQuery(SQL);	
		
		// Get the first result and return it
		if (resultSet.next()) 
			user = getUserFromResultSet(resultSet);
		return user;
	}

	@Override
	public User getEmployee(String username) throws SQLException {
		User user = null;
		String SQL = "SELECT * FROM Employees WHERE Username='" + username + "'";
		
		resultSet = UserDatabaseManager.executeQuery(SQL);
		if (resultSet.next()) 
			user = getUserFromResultSet(resultSet);
		return user;
	}
	
	@Override
	public User getEmployee(String firstname, String lastname) throws SQLException {
		User user = null;
		String SQL = "SELECT * FROM Employees WHERE Firstname='" + firstname + "' AND Lastname='" 
		+ lastname + "'";
		
		resultSet = UserDatabaseManager.executeQuery(SQL);
		if (resultSet.next()) 
			user = getUserFromResultSet(resultSet);
		closeConnections();
		return user;
	}
	
	/**
	 * Get all employees in the database
	 * @throws SQLException 
	 */
	@Override
	public List<User> getAllEmployees() throws SQLException {
		List<User> list = new ArrayList<User>();
		String SQL = "SELECT * FROM Employees";

		resultSet = executeQuery(SQL);
	
		// Iterate over the result set and add each user to the list
		while (resultSet.next()) {
			list.add(getUserFromResultSet(resultSet));
		}	
		return list;
	}

	@Override
	public List<Activity> getActivities(User user) throws SQLException {
		// Create the list and the SQL statment
		List<Activity> list = new ArrayList<Activity>();
		String SQL = "SELECT * FROM WorksOn "
				+ "INNER JOIN Employees ON Employees.EmployeeID = WorksOn.EmployeeID "
				+ "INNER JOIN Activities ON Activities.ActivityID = WorksOn.ActivityID "
				+ "INNER JOIN Projects ON Projects.ProjectID = Activities.ProjectID "
				+ "WHERE Employees.EmployeeID = " + user.getID();
		
		resultSet = executeQuery(SQL);
		// Iterate though the results and get all the activities
		while (resultSet.next()) {
			list.add(ActivityDatabaseManager.getCurrentActivity(resultSet, false));
		}	
		closeConnections();
		return list;
	}

	@Override
	public void updateEmployee(User user, String password) throws SQLException {
		String SQL = "UPDATE Employees " + 
				"SET Firstname='" + user.getFirstname() + 
				"', Lastname='" + user.getLastname() + 
				"', Username='" + user.getUsername() + 
				"', Password='" + password + 
				"' WHERE Employees.EmployeeID=" + user.getID();
		
		UserDatabaseManager.executeUpdate(SQL);
	}
	
	@Override 
	public double getTimeSpendOnActivity(User user, Activity activity) throws SQLException {
		String SQL = "SELECT TimeSpend FROM SpendHoursOn "
				+ "WHERE ActivityID = " + activity.getID() + " AND "
						+ "EmployeeID = " + user.getID();
		resultSet = executeQuery(SQL);
		if (resultSet.next()) 
			return resultSet.getDouble("TimeSpend");
		else 
			return 0;
	}
	
	@Override
	public double getTimeSpendOnAllActivities(User user) throws SQLException {
		double result = 0;
		List<LoggedTime> list = new ArrayList<LoggedTime>();
		
		String SQL = "SELECT * FROM SpendHoursOn "
				+ "WHERE EmployeeID = " + user.getID();
		
		// Execute the query and get the list of time objects
		resultSet = UserDatabaseManager.executeQuery(SQL);
		while (resultSet.next()) {
			list.add(UserDatabaseManager.getCurrentLoggedTime(resultSet));
		}
		
		// Calculate the time
		for (LoggedTime current : list) {
			result += current.getTime();
		}
		
		return result;
	}
	
	@Override
	public List<LoggedTime> getTimeSpendOnEachActivity(User user) throws SQLException {
		List<LoggedTime> list = new ArrayList<LoggedTime>();
		String SQL = "SELECT * FROM SpendHoursOn "
				+ "WHERE EmployeeID = " + user.getID();
		
		resultSet = executeQuery(SQL);
		while (resultSet.next()) {
			list.add(getCurrentLoggedTime(resultSet));
		}
		
		return list;
	}
}