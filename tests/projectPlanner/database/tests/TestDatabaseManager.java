package projectPlanner.database.tests;

import java.util.List;

import projectPlanner.database.IUserDataManager;
import projectPlanner.users.User;

public class TestDatabaseManager implements IUserDataManager {

	// Field to assume the number of users. Used only in tests
	private int numberOfUsers = 0;
	
	@Override
	public int getNumberOfUsers() {
		return this.numberOfUsers++;
	}

	@Override
	public void saveEmployee(User user, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveEmployee(int id, String firstname, String lastname,
			String username, String password) {
		// This should not do anything. The class under test should just assume this is done right. 

	}

	@Override
	public User getEmployee(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
