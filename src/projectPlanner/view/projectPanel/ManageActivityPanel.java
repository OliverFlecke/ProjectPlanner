package projectPlanner.view.projectPanel;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projectPlanner.Activity;
import projectPlanner.ProjectPlanner;
import projectPlanner.users.Employee;
import projectPlanner.users.User;
import projectPlanner.view.adminTab.TextNDate;
import projectPlanner.view.adminTab.TextNField;

public class ManageActivityPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 894111141608012138L;
	private JList<String> currentEmployeeList;
	private ListPanel listPanel;
	private JList<String> userList;
	private JLabel activityHeader;
	private TextNField name;
	private TextNField accumTime;
	private TextNField allottedTime;
	private TextNDate startDate;
	private TextNDate endDate;
	private DefaultListModel<String> currentEmployeeListModel;
	private DefaultListModel<String> userListModel;
	private JCheckBox activeCheck;

	public ManageActivityPanel(ListPanel listPanel){
		this.listPanel = listPanel;
		//temp border until visuals are improved
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		//set layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//add header
		activityHeader = new JLabel("Manage the selected activity:" + listPanel.getCurrentSelectedActivity());
		Font font = activityHeader.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+3);
		activityHeader.setFont(boldFont);
		add(activityHeader);

		//add input fields
		//name
		name = new TextNField("Name");
		name.setTxt(listPanel.getCurrentSelectedActivity().getTitle());
		name.getTxtField().setCaretPosition(0);
		add(name);

		//checkbox for active
		activeCheck = new JCheckBox("Checked if activity is of active status");
		activeCheck.setSelected(listPanel.getCurrentSelectedActivity().isActive());
		this.add(activeCheck);

		//allotted time field
		allottedTime = new TextNField("Alotted Man-Hours");
		allottedTime.setTxt(Double.toString(listPanel.getCurrentSelectedActivity().getHoursAllotted()));
		add(allottedTime);

		//accumulated time
		accumTime = new TextNField("Accumulated Man-Hours");
		accumTime.setTxt(Double.toString(listPanel.getCurrentSelectedActivity().getTimeAccumulated()));
		accumTime.setEnabled(false);
		add(accumTime);

		//start date
		startDate = new TextNDate("Start Date:");
		startDate.setDate(listPanel.getCurrentSelectedActivity().getStartDate());
		add(startDate);

		//end date
		endDate = new TextNDate("End Date:");
		endDate.setDate(listPanel.getCurrentSelectedActivity().getStartDate());
		add(endDate);

		//add header remove current employee list
		JLabel employeeListHeader = new JLabel("Select employee for removal from activity");
		employeeListHeader.setFont(boldFont);
		add(employeeListHeader);

		//remove current employee list
		currentEmployeeListModel = new DefaultListModel<String>();
		try {
			for(String current : getCurrentEmployeeNames()){
				currentEmployeeListModel.addElement(current);
			}
		} catch (SQLException e1) {
			new ErrorDialog("There was an error in connecting to the server");
		}
		currentEmployeeList = new JList<String>(currentEmployeeListModel);

		currentEmployeeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		currentEmployeeList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(currentEmployeeList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		add(currentEmployeeList);

		//deselect button
		JButton deselctEmployee = new JButton("Deselect choice");
		this.add(deselctEmployee);

		//listener for deselctbutton
		deselctEmployee.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				currentEmployeeList.clearSelection();
			}
		});

		//add header new employeelist
		JLabel removeEmployeeListHeader = new JLabel("select employee to be added to activity");
		removeEmployeeListHeader.setFont(boldFont);
		add(removeEmployeeListHeader);

		//add new employee list
		userListModel = new DefaultListModel<String>();
		try {
			for(String current : getUserNames()){
				userListModel.addElement(current);
			}
		} catch (SQLException e1) {
			new ErrorDialog("There was an error in connecting to the server");
		}
		userList = new JList<String>(userListModel);

		userList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		userList.setVisibleRowCount(-1);
		JScrollPane listScroller1 = new JScrollPane(userList);
		listScroller1.setPreferredSize(new Dimension(250, 80));
		add(userList);

		//deselect button
		JButton deselctUser = new JButton("Deselect choice");

		this.add(deselctUser);

		//listener for deselctbutton
		deselctUser.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				userList.clearSelection();
			}
		});


		//submit changes button
		JButton submitChanges = new JButton("Submit changes");
		add(submitChanges);
		
		//Actionlistener for button that submits changes
				submitChanges.addActionListener( new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e){
						try {
							listPanel.getCurrentSelectedActivity().update(name.getTxt(), listPanel.getCurrentSelectedProject(),
									Double.parseDouble(accumTime.getTxt()), Double.parseDouble(allottedTime.getTxt()), 
									activeCheck.isSelected(), startDate.getDate(), endDate.getDate());
							refreshActivityManagement();
						} catch (SQLException e1) {
							new ErrorDialog("There was a problem in connecting to the server");
							e1.printStackTrace();
						}

					}
				});

//		//delete activity button
		//The delete is time consuming to implement due to sql server issues, perhaps at a later time
//		JButton deleteActivity = new JButton("Delete activity");
//		add(deleteActivity);
//		
//		deleteActivity.addActionListener( new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e){
//				try {
//					Activity.deleteActicity(listPanel.getCurrentSelectedActivity());
//				} catch (SQLException e1) {
//					new ErrorDialog("there was an error in connecting to the server");
//					e1.printStackTrace();
//				}
//				listPanel.refreshActivitiesList();
//			}
//		});

		//Listener for changes in activity selection
		listPanel.getSelectActivityList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (lse.getValueIsAdjusting()){
					return;
				}
				if(!listPanel.isRefreshingActivities()){
					try {
						refreshActivityManagement();
					} catch (SQLException e) {
						new ErrorDialog("There was an issue in connecting to the server");
						e.printStackTrace();
					}

				}
			}
		});

	}

	protected void refreshActivityManagement() throws SQLException {
		activityHeader.setText("Manage the selected activity: " + listPanel.getCurrentSelectedActivity());
		name.setTxt(listPanel.getCurrentSelectedActivity().getTitle());
		name.getTxtField().setCaretPosition(0);
		activeCheck.setSelected(listPanel.getCurrentSelectedActivity().isActive());
		allottedTime.setTxt(Double.toString(listPanel.getCurrentSelectedActivity().getHoursAllotted()));
		accumTime.setTxt(Double.toString(listPanel.getCurrentSelectedActivity().getTimeAccumulated()));
		startDate.setDate(listPanel.getCurrentSelectedActivity().getStartDate());
		endDate.setDate(listPanel.getCurrentSelectedActivity().getStartDate());
		refreshUserNames();
		refreshActivityNames();

	}

	private void refreshActivityNames() throws SQLException {
		currentEmployeeListModel.removeAllElements();
		for(String current : getCurrentEmployeeNames()){
			currentEmployeeListModel.addElement(current);
		}

	}

	private List<String> getUserNames() throws SQLException {
		List<String> returnList = new ArrayList<String>();
		for(User current : getUsers()){
			returnList.add(current.getFirstname() + " " + current.getLastname());
		}
		return returnList;
	}

	private List<User> getUsers() throws SQLException {

		return ProjectPlanner.getEmployeesNotInActivity(listPanel.getCurrentSelectedActivity());
	}

	private List<String> getCurrentEmployeeNames() throws SQLException {
		List<String> returnList = new ArrayList<String>();
		for(User current : getCurrentEmployees()){
			returnList.add(current.getFirstname() + " " + current.getLastname());
		}
		return returnList;

	}

	private List<Employee> getCurrentEmployees() throws SQLException{
		return ProjectPlanner.getEmployeesByActivity(listPanel.getCurrentSelectedActivity());
	}
	private void refreshUserNames() throws SQLException {
		userListModel.removeAllElements();
		for(String current : getUserNames()){
			userListModel.addElement(current);
		}
	}

}
