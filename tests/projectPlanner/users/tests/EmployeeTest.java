package projectPlanner.users.tests;

import projectPlanner.users.*;

import org.junit.*;

/**
 * Test for the user class
 */
public class EmployeeTest {
	private Employee employee;
	private String password;
	
	@Before
	public void setup() {
		employee = new Employee("Ole42", "Qwer!234", "Ole Jensen");
		password = "Qwer!234";
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
	
	@Test 
	public void EmployeePasswordIsCorrect() {
		Assert.assertTrue(employee.checkPassword("Qwer!234"));
	}
	
	@Test
	public void EmployeeNameIsCorrect() {
		Assert.assertEquals("Ole Jensen", employee.getName());
	}
	
	@Test 
	public void UpdatePassword() {
		String newPassword = "Zxcv!234";
		employee.updatePassword(password, newPassword);
		Assert.assertTrue(employee.checkPassword(newPassword));
	}
	
	@Test
	public void ChangeUsername() {
		String newUsername = "Ole43";
		employee.updateUsername(newUsername, password);
		Assert.assertEquals(newUsername, employee.getUsername());
	}
	
	@Test 
	public void SetNameOfTheUser() {
		String newName = "Ole Hansen";
		employee.updateName(newName, password);
		Assert.assertEquals(newName, employee.getName());
	}
}
