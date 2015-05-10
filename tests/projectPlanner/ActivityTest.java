package projectPlanner;

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
	private Activity activity;
	
	@Mock 
	private IActivityDatabaseManager db;
	
	@Before 
	public void setup() {
		db = mock(ActivityDatabaseManager.class); 
		Activity.setDataManager(db);
	}
	
	@Test 
	@Category(FastTest.class) 
	public void createActivity() {

	}
}
