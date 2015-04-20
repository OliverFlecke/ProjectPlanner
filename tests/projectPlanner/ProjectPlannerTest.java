package projectPlanner;

import java.sql.SQLException;

import org.junit.*;
import org.junit.experimental.categories.Category;

import projectPlanner.*;
import projectPlanner.testCategories.*;
import projectPlanner.users.UserLoginException;
/**
 * @author Oliver Fleckenstein
 *
 */
public class ProjectPlannerTest {

	private ProjectPlanner projectPlanner;
	
	@Before
	public void setup() {
		projectPlanner = new ProjectPlanner();
	}
	
	@After
	public void tearDown() {
		
	}

	@Test
	@Category(FastTest.class)
	public void loginWithUsernameAndPassword_Succes() throws UserLoginException, SQLException, Exception {
		boolean state = false;
		state = projectPlanner.login("Oliver", "1234");

		Assert.assertTrue(state);
	}

}
