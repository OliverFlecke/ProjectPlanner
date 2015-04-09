package projectPlanner.users.tests;

import org.junit.*;

import projectPlanner.database.tests.TestDatabaseManager;
import projectPlanner.users.Admin;
import projectPlanner.users.User;
/**
 * 
 */
public class AdminTest {
	Admin admin;
	
	@Before
	public void setup() {
		User.setDataManager(new TestDatabaseManager());
		admin = new Admin();
	}
	
	@Test
	public void CreateAdmin() {
		Assert.assertEquals(User.getNumberOfUsers(), admin.getID());
	}
	
	@Test 
	public void IsAdmin_AlwaysReturnTrue() {
		Assert.assertTrue(admin.isAdmin());
	}
}
