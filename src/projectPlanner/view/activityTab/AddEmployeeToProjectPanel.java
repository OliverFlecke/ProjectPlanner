package projectPlanner.view.activityTab;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.Activity;
import projectPlanner.users.Employee;
import projectPlanner.users.User;
import projectPlanner.view.StdListPanel;
import projectPlanner.ProjectPlanner;

public class AddEmployeeToProjectPanel extends JPanel{
	
	private JLabel projectName;
	private StdListPanel employeesOnActivity;
	private StdListPanel employeesNotOnActivity;
	private JPanel formattingPanel;
	
	public AddEmployeeToProjectPanel(Activity activity) throws Exception{
		this.projectName = new JLabel(activity.getTitle());
		formattingPanel = new JPanel();
		
		employeesOnActivity = new StdListPanel(getEmployeesOnActivity(activity), "Employees On Activity", 20, Color.WHITE);
		employeesNotOnActivity = new StdListPanel(getEmployeesNotOnActivity(activity), "Employees Not On Activity", 20, Color.WHITE);
		
		
		
		formattingPanel.setLayout(new BoxLayout(formattingPanel, BoxLayout.X_AXIS));
		formattingPanel.add(employeesOnActivity);
		formattingPanel.add(employeesNotOnActivity);
		

		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(projectName);
		this.add(formattingPanel);
		this.add(Box.createVerticalGlue());
		
		
		
	}

	private List<String> getEmployeesNotOnActivity(Activity activity) throws Exception {
		List<User> employees = ProjectPlanner.getEmployeesNotInActivity(activity);
		List<String> employeeNames = new ArrayList<String>();
		for (User employee: employees) {
			if (!employee.isAdmin()) {
				employeeNames.add(employee.getFirstname() + " " + employee.getLastname());
			}
		}
		return employeeNames;
	}

	private List<String> getEmployeesOnActivity(Activity activity) throws Exception {
		List<Employee> employees = ProjectPlanner.getEmployeesByActivity(activity);
		List<String> employeeNames = new ArrayList<String>();
		for (Employee employee: employees) {
			employeeNames.add(employee.getFirstname() + employee.getLastname());
		}
		return employeeNames;
	}

}
