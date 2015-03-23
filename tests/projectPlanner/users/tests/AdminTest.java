package projectPlanner.users.tests;

import org.junit.*;

import projectPlanner.users.Admin;
import projectPlanner.users.User;
/**
 * 
 */
public class AdminTest {
	Admin admin;
	
	@Before
	public void setup() {
		admin = new Admin();
	}
	
	@Test
	public void CreateAdmin() {
		Assert.assertEquals(User.getNumberOfUsers(), admin.getID());
	}
}
