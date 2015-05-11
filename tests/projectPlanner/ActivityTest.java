package projectPlanner;

import java.sql.SQLException;
import java.util.Calendar;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import projectPlanner.database.*;
import projectPlanner.testCategories.*;
import projectPlanner.users.*;
/**
 * Test activity methodes
 */
public class ActivityTest {
	private Activity activity;
	
	@Mock 
	private IActivityDatabaseManager db;
	
	@Mock 
	private Project project; 
	
	@Mock 
	private User user;
	
	@Before 
	public void setup() throws SQLException {
		db = mock(ActivityDatabaseManager.class); 
		Activity.setDataManager(db);
		project = mock(Project.class);	
		user = mock(User.class);
		
		activity = new Activity(1, "Test activity", project, 20, true, Calendar.getInstance(), Calendar.getInstance(), 1);
	}
	
	@Test 
	@Category(FastTest.class) 
	public void createActivity() throws SQLException {
		activity.setName("New test name");
		Assert.assertEquals("New test name", activity.getTitle());
		
		// Add hours
		double before = activity.getTimeAccumulated();
		activity.addAccumulatedHours(2);
		Assert.assertEquals(before + 2, activity.getTimeAccumulated(), 0);
		
		// Add allotted hours
		before = activity.getHoursAllotted();
		activity.setHoursAllotted(before + 2);
		Assert.assertEquals(before + 2, activity.getHoursAllotted(), 0);
	}
	
	@Test
	@Category(FastTest.class) 
	public void getterMethodsCheck() throws SQLException {
		// Test that we can call all these metodes without getting exceptions
		Activity.deleteActicity(activity);
		Activity.getActivities(user);
		Activity.getActivities(project);
	}
	
	@Test 
	@Category(FastTest.class) 
	public void updateDates() throws SQLException {
		Calendar date = Calendar.getInstance();
		activity.setEndDate(date);
		Assert.assertEquals(date, activity.getEndDate());
		
		// Set and get the start date
		activity.setStartDate(date);
		Assert.assertEquals(date, activity.getStartDate());
	}
	
	@Test
	@Category(FastTest.class)
	public void compareActivities() throws SQLException {
		Activity other = new Activity(2, "Test activity", project, 20, true, Calendar.getInstance(), Calendar.getInstance(), 1);
		int result = activity.compareTo(other);
		Assert.assertEquals(-1, result);
	}
	
	@Test 
	@Category(FastTest.class)
	public void testToStringOverride() {
		Assert.assertEquals("Test activity", activity.toString());
	}
	
	@Test
	@Category(FastTest.class)
	public void updateActivity() throws SQLException { 
		activity.update("New title", project, 20, 10, true,
				Calendar.getInstance(), Calendar.getInstance());
	}
	
	@Test
	@Category(FastTest.class)
	public void getUsers_AddUsers_RemoveUsers() throws SQLException {
		activity.getEmployees();
		activity.addUser(user);
		activity.removeEmployee(user);
	}
	
	@Test
	@Category(FastTest.class)
	public void getAttachedProject() throws SQLException { 
		Assert.assertEquals(project, activity.getAttachedProject());
	}
	
	@Test
	@Category(FastTest.class) 
	public void activityActiveUpdated() throws SQLException {
		Assert.assertTrue(activity.isActive());
		
		activity.setActive(false);
		Assert.assertFalse(activity.isActive());
	}
	
	@Test
	@Category(FastTest.class)
	public void activityCompareTest() throws SQLException {
		Activity compareActivity = new Activity(1, "Test activity", project, 20, true, Calendar.getInstance(), Calendar.getInstance(), 1);
		Assert.assertEquals(activity, compareActivity); 
	}
}
