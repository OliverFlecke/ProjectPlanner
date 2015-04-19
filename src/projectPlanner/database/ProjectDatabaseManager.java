package projectPlanner.database;

import java.sql.SQLException;
import java.util.List;

import projectPlanner.Project;
import projectPlanner.users.User;

public class ProjectDatabaseManager extends DatabaseManager implements IProjectDatabaseManager {

	@Override
	public Project getProject(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProject(String title) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getAllProjects() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNewID() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveProject(Project project) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProject(Project project) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getActiveProjects() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Project> getProjectsByProjectLeader(User user)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
