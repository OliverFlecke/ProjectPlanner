package projectPlanner.database;

import projectPlanner.users.*;

import java.sql.*;
import java.util.List;

import com.microsoft.sqlserver.jdbc.*;


/**
 * @author Oliver Fleckenstein
 *
 */
public class UserDatabaseManager implements IUserDataManager {
	// String to connect to the server
	private static String connectionString = "jdbc:sqlserver://v1bco1pzko.database.windows.net:1433;"
			+ "database=WebApplicationodata_db;"
			+ "user=oliver@v1bco1pzko;"
			+ "password=ZAq1XSw2;";
//			+ "encrypt=true;"
//			+ "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	
	// Fields for the connection, statement and the result. 
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	///////////////////////////////
	// Test the database connection
	///////////////////////////////
	public static void main(String[] args) {
		//new Employee("Ken", "1234", "Kenneth", "Hansen");
		
		//UserDatabaseManager dataManager = new UserDatabaseManager();
		//dataManager.saveEmployee(user, "1234");
		
		User.setDataManager(new UserDatabaseManager());
		User user = User.getUser(4);
		
		System.out.println(user);
	}
	
	/**
	 * Close all the connections to the server, if they are still active 
	 */
	private static void closeConnections() {
		// Try to close all the connection. 
		try {
			if (connection != null) connection.close();
			if (statement != null) statement.close();
			if (resultSet != null) resultSet.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Execute a SQL statement on the database
	 * @param sql statement to execute
	 * @return whenehter the statement succeded
	 */
	private static boolean executeStatement(String sql) {
		boolean state = false;
		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			// Create the connection
			connection = DriverManager.getConnection(connectionString);
			
			// Create the sql statement and execute it
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			System.out.println("SQL statment send");
			state = true;
			
		} catch (Exception ex) {
			System.out.println("Class not found");
			ex.printStackTrace();
		} finally {
			// Try to close all the connection. 
			try {
				if (connection != null) connection.close();
				if (statement != null) statement.close();
				if (resultSet != null) resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return state;
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
	public void saveEmployee(int id, String firstname, String lastname, String username, String password) {
		String sql = "INSERT INTO Employees " + 
				"(ID, Firstname, Lastname, Username, Password) " + 
				"VALUES(" + id + ", '" 
				+ firstname + "', '" 
				+ lastname + "', '" 
				+ username + "', '" 
				+ password + "')";
		
		UserDatabaseManager.executeStatement(sql);
	}
	
	/**
	 * Create a new employee in the database based on a user
	 * @param user to create in the database
	 * @param password to save along with the user
	 */
	@Override
	public void saveEmployee(User user, String password) {
		String SQL = "INSERT INTO Employees " +
				"(ID, Firstname, Lastname, Username, Password) " + 
				"VALUES(" + user.getID() + ", '" 
				+ user.getFirstname() + "', '" 
				+ user.getLastname() + "', '"
				+ user.getUsername() + "', '"
				+ password + "')";
		
		UserDatabaseManager.executeStatement(SQL);
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
			
			String sql = "SELECT COUNT (ID) FROM Employees";
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
		return 0;
	}

	@Override
	/**
	 * @param id to get employee by
	 */
	public User getEmployee(int id) {
		User user = null;
		try {
			// Create connection
			connection = DriverManager.getConnection(connectionString);
			statement = connection.createStatement();
			
			String SQL = "SELECT * FROM Employees WHERE id=" + id + "";
			resultSet = statement.executeQuery(SQL);
			
			
			// Get the first resualt and return it
			if (resultSet.next()) {
				String firstname = resultSet.getString("Firstname");
				String lastname = resultSet.getString("Lastname");
				String username = resultSet.getString("Username");
				String password = resultSet.getString("Password");
				int databaseID = resultSet.getInt("ID");
				
				user = new Employee(username, password, firstname, lastname, databaseID);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeConnections();
		}
		return user;
	}

	@Override
	/**
	 * Get all employees in the database
	 */
	public List<User> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
}