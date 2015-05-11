package projectPlanner;

import static org.mockito.Mockito.mock;

import java.sql.SQLException;
import java.util.List;

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
	public void databaseGetters() throws SQLException {
		Project.getProject(1);
		Project.getProject("Project title");
		Project.getProjectByProjectLeader(employee);
		project.getActivities();
	}
	
	@Test 
	@Category(FastTest.class)
	public void getSpentTime() throws SQLException { 
		Assert.assertTrue(0 <= project.getSpentTime());
	}
	
	@Test
	@Category(FastTest.class)
	public void addActivity_ThenRemove() throws SQLException {
		Activity activity = mock(Activity.class);
		
		List<Activity> before = project.getActivities();
		project.addActivity(activity);
		List<Activity> afterAdd = project.getActivities();
		Assert.assertEquals(before.size(), afterAdd.size());
		
		project.removeActivity(activity);
		List<Activity> afterRemove = project.getActivities();
		Assert.assertEquals(afterAdd.size(), afterRemove.size());
	}
	
	@Test
	@Category(FastTest.class) 
	public void setAllottedTime() throws SQLException {
		project.setAllottedTime(20);
		Assert.assertEquals(20, project.getAllottedTime(), 0);
	}
	
	@Test 
	@Category(FastTest.class)
	public void compareProject() throws SQLException {
		Project newProject = new Project(2, "project_name", 10.0, employee, null, null);
		Assert.assertEquals(-1, project.compareTo(newProject));		
	}
	
	@Test 
	@Category(FastTest.class)
	public void createProject() throws SQLException {
		new Project("Test project", 10);
	}
}
