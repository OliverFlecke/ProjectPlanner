package projectPlanner.database;

import java.sql.*;
import java.util.List;

import projectPlanner.*;
import projectPlanner.users.User;

public interface IActivityDatabaseManager {
	
	/**
	 * Get an activity with the passed ID
	 * @param ID of the activity to get
	 * @return The found activity with the passed ID
	 * @throws SQLException
	 */
	public Activity getActivity(int ID) throws SQLException;

	/**
	 * Get all the users on the relevant 
	 * @return All the users working on this activity
	 * @throws SQLException
	 */
	public List<User> getUsers() throws SQLException;
}
