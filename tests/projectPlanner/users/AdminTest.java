package projectPlanner.users;

import java.sql.SQLException;
import org.junit.*;
import projectPlanner.database.*;

import org.mockito.Mock;
import static org.mockito.Mockito.*;
/**
 * 
 */
public class AdminTest {
	Admin admin;
	
	@Mock
	IUserDataManager dm;
	
	@Before
	public void setup() throws Exception {
		dm = mock(IUserDataManager.class);
		User.setDataManager(dm);
		admin = new Admin();
	}
	
	@Test
	public void CreateAdmin() throws SQLException {
		User admin = new Admin();
		Assert.assertTrue(admin.isAdmin());
	}
	
	@Test 
	public void IsAdmin_AlwaysReturnTrue() {
		Assert.assertTrue(admin.isAdmin());
	}
}
