package projectPlanner;

import java.sql.SQLException;
import java.util.Calendar;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import projectPlanner.database.*;
import projectPlanner.testCategories.*;
/**
 * Test activity methodes
 */
public class ActivityTest {
	@SuppressWarnings("unused")
	private Activity activity;
	
	@Mock 
	private IActivityDatabaseManager db;
	
	@Mock 
	private Project project; 
	
	@Before 
	public void setup() {
		db = mock(ActivityDatabaseManager.class); 
		Activity.setDataManager(db);
		project = mock(Project.class);
	}
	
	@Test 
	@Category(FastTest.class) 
	public void createActivity() throws SQLException {
		Activity activity = new Activity("Test activity", project, Calendar.getInstance(), Calendar.getInstance());
		activity.setName("New test name");
		Assert.assertEquals("New test name", activity.getTitle());
		
		// Add hours
		double before = activity.getTimeAccumulated();
		activity.addAccumulatedHours(2);
		Assert.assertEquals(before + 2, activity.getTimeAccumulated(), 0);
	}
	
}
