package projectPlanner.users;

import java.sql.SQLException;

import org.junit.*;

import projectPlanner.database.TestDatabaseManager;
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
		try {
			admin = new Admin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void CreateAdmin() throws SQLException {
		Assert.assertEquals(User.getNumberOfUsers(), admin.getID());
	}
	
	@Test 
	public void IsAdmin_AlwaysReturnTrue() {
		Assert.assertTrue(admin.isAdmin());
	}
}
