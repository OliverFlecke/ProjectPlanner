package projectPlanner.view.activityTab;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.Activity;
import projectPlanner.users.Employee;
import projectPlanner.users.User;
import projectPlanner.view.StdListPanel;
import projectPlanner.ProjectPlanner;

public class AddEmployeeToProjectPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3156631673364831942L;
	private JLabel projectName;
	private StdListPanel employeesOnActivity;
	private StdListPanel employeesNotOnActivity;
	private JPanel formattingPanel;
	private JButton addToActivityBtn;
	private Activity activity;
	List<User> employeesNotOnActivityLst;
	
	public AddEmployeeToProjectPanel(Activity activity) throws Exception{
		this.projectName = new JLabel(activity.getTitle());
		this.activity = activity;
		formattingPanel = new JPanel();
		
		

		
		employeesOnActivity = new StdListPanel(getEmployeesOnActivity(activity), "Employees On Activity", 20, Color.WHITE);
		employeesNotOnActivity = new StdListPanel(getEmployeesNotOnActivity(activity), "Employees Not On Activity", 20, Color.WHITE);
		
		addToActivityBtn = new JButton("Add to Activity");
		addToActivityBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int[] elementsToMove = employeesNotOnActivity.getListToPop().getSelectedIndices();
				
				for (int i=0; i < elementsToMove.length; i++) {
					employeesOnActivity.getListModel().addElement(employeesNotOnActivity.getListModel().getElementAt(elementsToMove[i]));
					employeesNotOnActivity.getListModel().remove(elementsToMove[i]);
					try {
						activity.addUser(employeesNotOnActivityLst.get(elementsToMove[i]));
					} catch (Exception e1) {
						
					}
					
					
				}
				
			}
		});		
		
		formattingPanel.setLayout(new BoxLayout(formattingPanel, BoxLayout.X_AXIS));
		formattingPanel.add(employeesOnActivity);
		formattingPanel.add(employeesNotOnActivity);
		

		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(projectName);
		this.add(formattingPanel);
		this.add(addToActivityBtn);
		this.add(Box.createVerticalGlue());
		
		
		
	}

	private List<String> getEmployeesNotOnActivity(Activity activity) throws Exception {
		employeesNotOnActivityLst = ProjectPlanner.getEmployeesNotInActivity(activity);
		List<String> employees = new ArrayList<String>();
		for (User employee: employeesNotOnActivityLst) {
			if (!employee.isAdmin()) {
				employees.add(employee.getFirstname() + " " + employee.getLastname());
			}
		}
		return employees;
	}

	private List<String> getEmployeesOnActivity(Activity activity) throws Exception {
		List<Employee> employeesOnActivity = ProjectPlanner.getEmployeesByActivity(activity);
		List<String> employees = new ArrayList<String>();
		for (Employee employee: employeesOnActivity) {
			employees.add(employee.getFirstname() + employee.getLastname());
		}
		return employees;
	}

}
