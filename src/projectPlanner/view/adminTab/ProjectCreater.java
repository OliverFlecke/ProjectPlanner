package projectPlanner.view.adminTab;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projectPlanner.Project;
import projectPlanner.users.User;
import projectPlanner.view.utilities.ErrorDialog;
import projectPlanner.view.utilities.NameObject;
import projectPlanner.view.utilities.StdListPanel;
import projectPlanner.view.utilities.TextNDate;
import projectPlanner.view.utilities.TextNField;
import projectPlanner.view.utilities.TextNNumber;

public class ProjectCreater extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -890252959284583205L;
	private NameObject title;
	private TextNField projectName;
	private TextNDate deadLineFld;
	private TextNDate startDateFld;
	private StdListPanel listOfEmployees;
	private TextNField projectLeader;
	private JButton createProject;
	private JLabel succesLabel;
	private TextNNumber allottedTime;
	
	public ProjectCreater() {
		title = new NameObject("Create Project");
		projectName = new TextNField("Project Name: ");
		deadLineFld = new TextNDate("EndDate: ");
		startDateFld = new TextNDate("StartDate: ");
		try {
			listOfEmployees = new StdListPanel(getEmployees(), "Available Employees");
		} catch (SQLException e) {
			new ErrorDialog("An server error occurred. We where not able to fetch all the users from the database");
		}
		
		projectLeader = new TextNField("Project Leader");
		createProject = new JButton("Create Project");
		succesLabel = new JLabel("");
		allottedTime = new TextNNumber("Allotted Time: ");
		
		createProject.addActionListener(new CreateNewProjectListener());
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(10,10,10,10));
		
		this.add(title);
		this.add(projectName);
		this.add(allottedTime);
		this.add(startDateFld);
		this.add(deadLineFld);
		this.add(listOfEmployees);
		this.add(projectLeader);
		this.add(createProject);
		this.add(succesLabel);
		this.add(Box.createVerticalGlue());
		
		this.setPreferredSize(new Dimension(300,400));
		
	}

	private List<String> getEmployees() throws SQLException {
		List<User> userList = User.getAllUsers();
		List<String> employees = new ArrayList<String>();
		for (User user : userList) {
			employees.add(user.getUsername());
		}
		return employees;
	}

	private class CreateNewProjectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// Get the double value from the allotted time input
				double time = ((Number)ProjectCreater.this.allottedTime.getValue()).doubleValue();
				User user = User.getUser(ProjectCreater.this.projectLeader.getTxt());
				
				new Project(ProjectCreater.this.projectName.getTxt(), time, user, 
						ProjectCreater.this.startDateFld.getDate(), ProjectCreater.this.deadLineFld.getDate());
				ProjectCreater.this.succesLabel.setText("The project has succesfully been created!");
			} catch (SQLException ex) {
				ProjectCreater.this.succesLabel.setText("");
				new ErrorDialog("An error occurred when trying to connect to the server. Please try agian");
			}
		}
		
	}
}
