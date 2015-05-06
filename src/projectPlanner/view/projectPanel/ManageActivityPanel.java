package projectPlanner.view.projectPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	private TextNField alottedTime;
	private TextNDate startDate;
	private TextNDate endDate;
	private DefaultListModel<String> currentEmployeeListModel;
	private DefaultListModel<String> userListModel;

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
		name = new TextNField("Name");
		name.setTxt(listPanel.getCurrentSelectedActivity().getTitle());
		name.getTxtField().setCaretPosition(0);
		add(name);

		//allotted time field
		alottedTime = new TextNField("Alotted Man-Hours");
		alottedTime.setTxt("Mangler function");
		add(alottedTime);

		//accumulated time
		accumTime = new TextNField("Accumulated Man-Hours");
		accumTime.setTxt(Double.toString(listPanel.getCurrentSelectedActivity().getTimeAccumulated()));
		add(accumTime);

		//start date
		startDate = new TextNDate("Start Date: mangler");
		add(startDate);

		//end date
		endDate = new TextNDate("End Date: mangler");
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

		//submit changes button
		JButton createActivity = new JButton("Submit changes");
		add(createActivity);

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
