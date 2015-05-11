package projectPlanner;

import static org.mockito.Mockito.mock;

import java.sql.SQLException;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.Mockito;

import projectPlanner.database.*;
import projectPlanner.testCategories.*;
import projectPlanner.users.*;

public class ProjectTest {
	private Project project;
	private Employee employee;
	@SuppressWarnings("unused")
	private Activity activity;
	
	@Mock 
	private IProjectDatabaseManager dataManager;
	@Mock 
	private Admin admin;
	
	@Before
	public void setup() {
		dataManager = Mockito.mock(IProjectDatabaseManager.class);
		Project.setDataManager(dataManager);
		admin = mock(Admin.class);
		Mockito.when(admin.isAdmin()).thenReturn(true);
		
		activity = new Activity(1, "Database setup", 1, 20.5, true, null, null, 0);
		employee = new Employee("Oliver", "1234", "Oliver", "Fleckenstein", 2);
		project = new Project(1, "project_name", 10.0, employee, null, null);
	}
	
	@Test
	@Category(FastTest.class)
	public void SetNewProjectLeader() throws ActionNotAllowedException, SQLException {
		project.setProjectLeader(admin, employee);
	}
	
	@Test 
	@Category(FastTest.class) 
	public void setNewProjectLeader_NotAdmin_ActionNotAllowed() throws ActionNotAllowedException, SQLException {
		try {
			project.setProjectLeader(employee, employee);
		} catch(ActionNotAllowedException ex) {
			Assert.assertEquals("You do not have the rights to set the project leader", ex.getMessage());
		}
	}

	@Test
	@Category(FastTest.class) 
	public void removeProjectLeader() throws ActionNotAllowedException, SQLException {
		project.removeProjectLeader(admin);
	}
	
	@Test
	@Category(FastTest.class) 
	public void removeProjectLeader_AdminNotPassed() throws SQLException {
		try {
			project.removeProjectLeader(employee);
		} catch (ActionNotAllowedException ex) {
			Assert.assertEquals("You do not have the rights to remove the project leader", ex.getMessage());
		}
	}
	
	@Test 
	@Category(FastTest.class)
	public void testToStringMetode() throws SQLException {
		Assert.assertEquals(project.getTitle(), project.toString());
	}
	
	@Test
	@Category(FastTest.class)
	public void updateTitleOfProject() throws SQLException {
		project.setTitle("New title");
		Assert.assertEquals("New title", project.getTitle());
	}
	
	@Test 
	@Category(FastTest.class)
	public void setProjectAsInactive_ThenAsActive() throws SQLException {
		project.setProjectAsInactive();
		Assert.assertFalse(project.isActive());
		
		project.activateProject();
		Assert.assertTrue(project.isActive());
	}
	
	@Test
	@Category(FastTest.class) 
	public void setAllottedTime() throws SQLException {
		project.setAllottedTime(20);
		Assert.assertEquals(20, project.getAllottedTime(), 0);
	}
}
