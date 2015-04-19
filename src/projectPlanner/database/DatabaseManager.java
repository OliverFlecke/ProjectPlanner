/**
 * 
 */
package projectPlanner.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Abstract 
 * </p>
 * @author Oliver Fleckenstein
 */
public abstract class DatabaseManager {
	// String to connect to the server
	protected static final  String connectionString = "jdbc:sqlserver://v1bco1pzko.database.windows.net:1433;"
			+ "database=WebApplicationodata_db;"
			+ "user=oliver@v1bco1pzko;"
			+ "password=ZAq1XSw2;";
//			+ "encrypt=true;"
//			+ "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	
	// Fields for the connection, statement and the result. 
	protected static Connection connection = null;
	protected static Statement statement = null;
	protected static PreparedStatement preStatement = null;
	protected static ResultSet resultSet = null;
	
	/**
	 * Close all the connections to the server, if they are still active 
	 */
	protected static void closeConnections() {
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
	 * @return If the statement succeed or not
	 */
	protected static boolean executeUpdate(String sql) throws SQLException {	
		// Create the connection
		connection = DriverManager.getConnection(connectionString);
		
		// Create the SQL statement and execute it
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		// Try to close all the connection. 
		closeConnections();
		return true;
	}
	
	/**
	 * Execute a SQL query and returns the result as an result set
	 * @param SQL string to send and get result from. 
	 */
	protected static ResultSet executeQuery(String SQL) throws SQLException {
		// Create a connection and a statement and execute it 
		connection = DriverManager.getConnection(connectionString);
		statement = connection.createStatement();
		return statement.executeQuery(SQL);
	}
}
