package projectPlanner.view.activityPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;




import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.Activity;
import projectPlanner.ProjectPlanner;
import projectPlanner.users.Employee;
import projectPlanner.users.User;
import projectPlanner.view.StdListPanel;

public class ActivityPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5895302587110398862L;
	public JLabel activityTitle;
	public TextPanel hoursSpent;
	public StdListPanel otherEmps;
	public TextPanel projectName;
	public String activityName;
	

	
	public ActivityPane(Activity activity, User user) throws Exception{
		activityName = activity.getTitle();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		activityTitle = new JLabel(activityName);
		activityTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		projectName = new TextPanel("Project Name: ", activity.getAttachedProject().getTitle());
		hoursSpent = new TextPanel("Hours Spent:  ", String.valueOf(User.getTimeSpendOnActivity(user, activity))); 
		
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		this.otherEmps = new StdListPanel(getEmployeesOnActivity(activity), "Colleagues attached to activity:");
		
		
		
		//Adding elements to the JPanel 
		this.add(activityTitle);
		this.add(projectName);
		this.add(hoursSpent);
		this.add(otherEmps);
		
		
		// centering the elements.
		activityTitle.setAlignmentX(CENTER_ALIGNMENT);
		
	}


	public Dimension height() {
		return this.getPreferredSize();
	}
	
	private List<String> getEmployeesOnActivity(Activity activity) throws Exception {
		List<Employee> employees = ProjectPlanner.getEmployeesByActivity(activity);
		List<String> employeeNames = new ArrayList<String>();
		for (Employee employee: employees) {
		}
		return employeeNames;
	}
}
