package projectPlanner.users;

import java.sql.SQLException;
import java.util.Calendar;

import projectPlanner.*;
import projectPlanner.database.IUserDataManager;
import projectPlanner.testCategories.*;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

/**
 * Test for the user class
 */
public class UserEmployeeTest {
	private Employee employee;
	private String password;
	
	@Mock 
	private IUserDataManager db;
	
	@Mock
	private Activity activity;
	
	@Before
	public void setup() throws SQLException {
		password = "Qwer!234";
		
		db = mock(IUserDataManager.class);
		when(db.getNumberOfUsers()).thenReturn(2);
		User.setDataManager(db);
		activity = mock(Activity.class);
		
		employee = new Employee("Ole42", password, "Ole", "Jensen");
	}
	
	@After
	public void tearDown() { }
	
	@Test
	@Category({UserTest.class, FastTest.class})
	public void UsernameIsCorrect() {
		Assert.assertEquals("Ole42", employee.getUsername());
	}
	
	@Test 
	@Category({UserTest.class, FastTest.class})
	public void PasswordIsCorrect() {
		Assert.assertTrue(employee.checkPassword(password));
	}
	
	@Test 
	@Category({UserTest.class, FastTest.class})
	public void PasswordIsWrong() {
		Assert.assertFalse(employee.checkPassword("This password is wrong"));
	}
	
	@Test
	@Category({UserTest.class, FastTest.class})
	public void EmployeeNameIsCorrect() {
		Assert.assertEquals("Ole Jensen", employee.getFirstname() + " " + employee.getLastname());
	}
	
	@Test
	@Category({UserTest.class, FastTest.class})
	public void EmployeeIsNotAdmin() {
		Assert.assertFalse(employee.isAdmin());
	}
	
	@Test 
	@Category({UserTest.class, FastTest.class})
	public void UpdatePassword() throws SQLException {
		String newPassword = "Zxcv!234";
		try {		
			employee.updatePassword(password, newPassword);
		} catch (ActionNotAllowedException ex) {
			Assert.fail("Password was wrong, but should be correct");
			Assert.assertEquals(employee, ex.getUser());
		}
		Assert.assertTrue(employee.checkPassword(newPassword));
	}
	
	@Test
	@Category({UserTest.class, FastTest.class})
	public void UpdatePassword_OldPasswordIsWrong() throws SQLException {
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
	public void ChangeUsername() throws SQLException, ActionNotAllowedException {
		String newUsername = "Ole43";
		employee.updateUsername(newUsername, password);
		Assert.assertEquals(newUsername, employee.getUsername());
	}
	
	@Test 
	@Category({UserTest.class, FastTest.class})
	public void ChangeUsername_PasswordIsWrong() throws SQLException {
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
	public void SetNameOfTheUser() throws SQLException, ActionNotAllowedException {
		String newName = "Ole Hansen";
		employee.updateFirstname(newName, password);

		Assert.assertEquals(newName, employee.getFirstname());
	}
	
	@Test
	@Category({UserTest.class, FastTest.class})
	public void SetNameOfUser_PasswordIsWrong() throws SQLException {
		String newName = "Ole Hansen";
		try {
			employee.updateFirstname(newName, "Wrong password");
			Assert.fail("Exception should have been thrown due to wrong password");
		} catch (ActionNotAllowedException ex) {
			Assert.assertEquals("Wrong password", ex.getMessage());
		}
	}
	
	@Test
	@Category(UserTest.class) 
	public void toStringTest() {
		Assert.assertEquals("Ole Jensen Username: Ole42 Password: Qwer!234 ID: 0", employee.toString());
	}
	
	@Test
	@Category(UserTest.class)
	public void getNumberOfUsers() throws SQLException {
		Assert.assertEquals(2, User.getNumberOfUsers());
	}
	
	@Test
	@Category(UserTest.class)
	public void testGetterMethods() throws SQLException {
		User.getActivities(employee);
		User.getTimeSpendOnActivity(employee, activity);
		employee.getTimeSpendOnAllActivities();
		User.getUser("Oliver", "Fleckenstein");
		User.getAllUsers();
		Employee.getEmployees(activity);
		
		// Get datamanager
		IUserDataManager dataManager = User.getDataManager();
		Assert.assertEquals(db, dataManager);
	}
	
	@Test 
	@Category(UserTest.class) 
	public void logTimeThroughUser() throws SQLException {
		User.setTimeSpendOnActivity(new LoggedTime(1, 1, 10, Calendar.getInstance()));
		User.updateLoggedTime(new LoggedTime(1, 1, 20, Calendar.getInstance()));
	}
	
	@Test 
	@Category(UserTest.class) 
	public void compareUsers() throws SQLException {
		User newUser = new Employee("Username", "Password", "Firstname", "lastname", 2);
		int result = newUser.compareTo(employee);
		Assert.assertEquals(1, result);
	}
}
