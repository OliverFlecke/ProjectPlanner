package projectPlanner.database;

import projectPlanner.Activity;
import projectPlanner.users.*;

import java.sql.*;
import java.util.*;

//import com.microsoft.sqlserver.jdbc.*;


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
	public static User getUserFromResultSet(ResultSet resultSet) throws SQLException {
		// Get all the required user data from the table
		String firstname = resultSet.getString("Firstname");
		String lastname = resultSet.getString("Lastname");
		String username = resultSet.getString("Username");
		String password = resultSet.getString("Password");
		int databaseID = resultSet.getInt("EmployeeID");
		return new Employee(username, password, firstname, lastname, databaseID);
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
		// Check that the user is not allready in the database. 
		if (this.getEmployee(user.getID()) != null) 
			return;
		else {
			String SQL = "INSERT INTO Employees " +
					"(EmployeeID, Firstname, Lastname, Username, Password) " + 
					"VALUES(" + user.getID() + ", '" 
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
	
	/**
	 * <p>
	 * This implementation of getNewID will find the highest ID in the employee table, and return an new ID
	 * that is 1 higher than the found ID. If no ID was found, the table must be empty, and we can safely 
	 * assign 1 as the ID to the requsting object. 
	 * </p>
	 * @throws SQLException 
	 */
	@Override
	public int getNewID() throws SQLException {
		// This SQL statment should get the highest employee ID in the table
		String SQL = "SELECT TOP 1 EmployeeID FROM Employees ORDER BY EmployeeID DESC";
		resultSet = executeQuery(SQL);
	
		// If we get a result, get the ID of that employee and return a new, higher ID
		if (resultSet.next()) 
			return resultSet.getInt("EmployeeID") + 1;
		// If we don't get a result, the table will be empty, and we can just use 1 as an id. 
		return 1; 		
	}

	@Override
	public List<Activity> getActivities(User user) throws SQLException {
		// Create the list and the SQL statment
		List<Activity> list = new ArrayList<Activity>();
		String SQL = "SELECT * FROM WorksOn WHERE EmployeeID=" + user.getID();
		
		resultSet = executeQuery(SQL);
		// Iterate though the results and get all the activities
		while (resultSet.next()) {
			list.add(ActivityDatabaseManager.getCurrentActivity(resultSet));
		}	
		return list;
	}
}