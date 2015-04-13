package projectPlanner;

import org.junit.*;
import org.junit.experimental.categories.Category;

import projectPlanner.*;
import projectPlanner.testCategories.*;
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
	
	@Test
	@Category(FastTest.class)
	public void loginWithUsernameAndPassword_Succes() {
		boolean state = false;
		try {
			state = projectPlanner.login("Oliver", "1234");
		} catch (Exception e) {
			Assert.fail("Should login just fine");
		}
		
		Assert.assertTrue(state);
	}
	
	@After
	public void tearDown() {
		
	}
}
