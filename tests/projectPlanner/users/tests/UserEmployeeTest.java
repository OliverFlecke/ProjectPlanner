package projectPlanner.users.tests;

import projectPlanner.*;
import projectPlanner.users.*;

import org.junit.*;

/**
 * Test for the user class
 */
public class UserEmployeeTest {
	private Employee employee;
	private String password;
	
	@Before
	public void setup() {
		employee = new Employee("Ole42", "Qwer!234", "Ole", "Jensen");
		password = "Qwer!234";
	}
	
	@After
	public void tearDown() {
		employee = null;
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
	public void PasswordIsCorrect() {
		Assert.assertTrue(employee.checkPassword(password));
	}
	
	@Test 
	public void PasswordIsWrong() {
		Assert.assertFalse(employee.checkPassword("This password is wrong"));
	}
	
	@Test
	public void EmployeeNameIsCorrect() {
		Assert.assertEquals("Ole Jensen", employee.getFirstname() + " " + employee.getLastname());
	}
	
	@Test
	public void EmployeeIsNotAdmin() {
		Assert.assertFalse(employee.isAdmin());
	}
	
	@Test 
	public void UpdatePassword() {
		String newPassword = "Zxcv!234";
		try {		
			employee.updatePassword(password, newPassword);
		} catch (ActionNotAllowedException ex) {
			Assert.fail("Password was wrong, but should be correct");
		}
		Assert.assertTrue(employee.checkPassword(newPassword));
	}
	
	@Test
	public void UpdatePassword_OldPasswordIsWrong() {
		String newPassword = "Zxcv!234";
		try {		
			employee.updatePassword("This should be the wrong password", newPassword);
			Assert.fail("Exception should have been thrown. Password was wrong");
		} catch (ActionNotAllowedException ex) {
			Assert.assertEquals("Wrong password", ex.getMessage());
			Assert.assertFalse(employee.checkPassword(newPassword));
		}
	}
	
	@Test
	public void ChangeUsername() {
		String newUsername = "Ole43";
		try {
			employee.updateUsername(newUsername, password);
		} catch (ActionNotAllowedException ex){
			Assert.fail(ex.getMessage());
		}
		Assert.assertEquals(newUsername, employee.getUsername());
	}
	
	@Test 
	public void ChangeUsername_PasswordIsWrong() {
		String newUsername = "Ole43";
		try {
			employee.updateUsername(newUsername, "Old password is wrong");
			Assert.fail("Exception should have been thrown due to wrong password");
		} catch (ActionNotAllowedException ex){
			Assert.assertEquals("Wrong password", ex.getMessage());
			Assert.assertNotEquals(newUsername, employee.getUsername());
			Assert.assertEquals("Ole42", employee.getUsername());
		}
	}
	
	@Test 
	public void SetNameOfTheUser() {
		String newName = "Ole Hansen";
		try {		
			employee.updateFirstname(newName, password);
		} catch (ActionNotAllowedException ex) {
			Assert.fail(ex.getMessage());
		}
		Assert.assertEquals(newName, employee.getFirstname() + " " + employee.getLastname());
	}
	
	@Test
	public void SetNameOfUser_PasswordIsWrong() {
		String newName = "Ole Hansen";
		try {
			employee.updateFirstname(newName, "Wrong password");
			Assert.fail("Exception should have been thrown due to wrong password");
		} catch (ActionNotAllowedException ex) {
			Assert.assertEquals("Wrong password", ex.getMessage());
		}
	}
}
