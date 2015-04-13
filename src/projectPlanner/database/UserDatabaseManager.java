package projectPlanner.database;

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
	 * Create an employee in the database
	 * @param id of the employee
	 * @param firstname of the employee
	 * @param lastname of the employee
	 * @param username for the user to login to the system
	 * @param password for the user to login to the system
	 */
	@Override
	public void saveEmployee(int id, String firstname, String lastname, String username, String password) {
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
	 */
	@Override
	public void saveEmployee(User user, String password) {
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
	 */
	@Override
	public int getNumberOfUsers() {
		try {
			// Create connection
			connection = DriverManager.getConnection(connectionString);
			statement = connection.createStatement();
			
			String sql = "SELECT COUNT (EmployeeID) FROM Employees";
			resultSet = statement.executeQuery(sql);
			
			// Use of a while loop to insure we have some result. Does not loop through anything
			while (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeConnections();
		}
		return -1;
	}

	@Override
	/**
	 * Get an employee by his ID from the database
	 * @param id to get employee by
	 */
	public User getEmployee(int id) {
		User user = null;
		String SQL = "SELECT * FROM Employees WHERE EmployeeID=" + id + "";
		
		try {
			resultSet = UserDatabaseManager.executeQuery(SQL);	
			
			// Get the first result and return it
			if (resultSet.next()) 
				user = getUserFromResultSet();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	@Override
	public User getEmployeeByUsername(String username) {
		User user = null;
		String SQL = "SELECT * FROM Employees WHERE Username='" + username + "'";
		
		try {
			resultSet = UserDatabaseManager.executeQuery(SQL);
			if (resultSet.next()) 
				user = getUserFromResultSet();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	/**
	 * Get all employees in the database
	 */
	@Override
	public List<User> getAllEmployees() {
		List<User> list = new ArrayList<User>();
		String SQL = "SELECT * FROM Employees";

		resultSet = executeQuery(SQL);
		
		try {
			// Iterate over the result set and add each user to the list
			while (resultSet.next()) {
				list.add(getUserFromResultSet());
			}	
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return list;
	}

	/**
	 * Get the current user from the resultSet. This should be used when a SQL statement has been made 
	 * on the server, and the resultSet has got a respond including user data
	 * @return The user in the current result set
	 * @throws SQLException is thrown if the coloumns can't be found
	 */
	private User getUserFromResultSet() throws SQLException {
		// Get all the required user data from the table
		String firstname = resultSet.getString("Firstname");
		String lastname = resultSet.getString("Lastname");
		String username = resultSet.getString("Username");
		String password = resultSet.getString("Password");
		int databaseID = resultSet.getInt("EmployeeID");
		return new Employee(username, password, firstname, lastname, databaseID);
	}
	
	/**
	 * <p>
	 * This implementation of getNewID will find the highest ID in the employee table, and return an new ID
	 * that is 1 higher than the found ID. If no ID was found, the table must be empty, and we can safely 
	 * assign 1 as the ID to the requsting object. 
	 * </p>
	 */
	@Override
	public int getNewID() {
		// This SQL statment should get the highest employee ID in the table
		String SQL = "SELECT TOP 1 EmployeeID FROM Employees ORDER BY EmployeeID DESC";
		resultSet = executeQuery(SQL);
		
		try {
			// If we get a result, get the ID of that employee and return a new, higher ID
			if (resultSet.next()) 
				return resultSet.getInt("EmployeeID") + 1;
		} catch (SQLException ex) {	}
		// If we don't get a result, the table will be empty, and we can just use 1 as an id. 
		return 1; 		
	}
}