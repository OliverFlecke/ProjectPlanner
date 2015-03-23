package projectPlanner.users.tests;

import projectPlanner.users.*;

import org.junit.*;

/**
 * Test for the user class
 */
public class EmployeeTest {
	private Employee employee;
	
	@Before
	public void setup() {
		employee = new Employee("Ole42", "1234", "Ole Jensen");
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void CreateNewEmployeeIDCheck() {
		Assert.assertEquals(User.getNumberOfUsers(), employee.getID());
	}
	
	@Test
	public void UsernameIsCorrect() {
		Assert.assertEquals("Ole42", employee.getUsername());
	}
}
