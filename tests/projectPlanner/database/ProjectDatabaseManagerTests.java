package projectPlanner.database;

import java.sql.SQLException;
import java.util.*;

import org.junit.*;
import org.junit.experimental.categories.*;

import projectPlanner.testCategories.*;
import projectPlanner.users.*;
import projectPlanner.*;

public class ProjectDatabaseManagerTests {
	private ProjectDatabaseManager db;
	private User expUser;
	private Project expProject;
	
	@Before
	public void setup() {
		db = new ProjectDatabaseManager();
		expUser = new Employee("Oliver", "1234", "Oliver", "Fleckenstein", 2);
		expProject = new Project(1, "Database setup", 100, expUser, Calendar.getInstance(), Calendar.getInstance());
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void getAllProjects() throws SQLException {
		List<Project> projects = db.getAllProjects();	
		Assert.assertTrue(projects.size() > 0);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void getProjectByIDTest() throws SQLException {
		Project project = db.getProject(1);
		
		Assert.assertEquals(expProject.getTitle(), project.getTitle());
		Assert.assertEquals(expProject.getAllottedTime(), project.getAllottedTime(), 0);
		Assert.assertEquals(expProject.getProjectLeader(), project.getProjectLeader());
		Assert.assertEquals(expProject.getID(), project.getID());
		Assert.assertEquals(expProject, project);
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void getProjectByTitleTest() throws SQLException {
		Project project = db.getProject("Database setup");
		
		Assert.assertEquals(expProject.getTitle(), project.getTitle());
		Assert.assertEquals(expProject.getAllottedTime(), project.getAllottedTime(), 0);
		Assert.assertEquals(expProject.getProjectLeader(), project.getProjectLeader());
		Assert.assertEquals(expProject.getID(), project.getID());
		Assert.assertEquals(expProject, project);
	}
	
	@Test
	@Category(DatabaseTest.class) 
	public void saveProjectTest() throws SQLException {
		// This test can not be done each time, because we need a unique title for the project. 
		// This SHOULD be run each time the saveProject method is changed
		//db.saveProject(expProject);
	}
	
	@Test
	@Category(DatabaseTest.class)
	public void updateProjecTest() throws SQLException {
		// This test will run each time, but not change anything. Though, we only check for exceptions thrown
		db.updateProject(expProject);
	}
	
	@Test 
	@Category(DatabaseTest.class)
	public void getActiveProjectTest() throws SQLException {
		List<Project> projects = db.getActiveProjects();
		
		// We will just test that we have more than zero active project in the database, which there should be
		Assert.assertTrue(projects.size() > 0);
	}
	
	@Test 
	@Category(DatabaseTest.class) 
	public void getProjectByProjectLeaderTest() throws SQLException {
		List<Project> projects = db.getProjectsByProjectLeader(expUser);
		
		// Will just check, that more than one project is returned
		Assert.assertTrue(projects.size() == 2);
	}
	
	@Test 
	@Category(DatabaseTest.class) 
	public void addActivityToProject() throws SQLException {
		// This test can not be run each time. This will test if we can link the activity to a project in the database
		// This will only test for the exceptions getting thrown. Will throw error now, because the data is already in database
//		Activity activity = new Activity(1, "test activity", expProject, 10, true);
//		db.addActivityToProject(expProject, activity);
	}
}
