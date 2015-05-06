package projectPlanner.view.projectPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projectPlanner.Activity;
import projectPlanner.Project;
import projectPlanner.ProjectPlanner;

public class ListPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4745881885279107975L;
	private JList<String> selectProjectList;
	private JLabel actListHeader;
	private DefaultListModel<String> activityListModel;
	private JList<String> selectActivityList;
	private List<Project> projectsList;
	private boolean refreshingActivities;

	public ListPanel(){		
		//temp border until visuals are improved
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//get list of projects  associated with current user
		fetchProjectsList();

		//set layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//add header
		JLabel projectHeader = new JLabel("Please select the project you want to manage:");
		Font font = projectHeader.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+3);
		projectHeader.setFont(boldFont);
		add(projectHeader);

		//add spacer
		add(Box.createRigidArea(new Dimension(5,5)));

		//add scrollable list of project names, for selection
		DefaultListModel<String> projectListModel = new DefaultListModel<String>();
		for(String current : getProjectNames()){
			projectListModel.addElement(current);
		}
		selectProjectList = new JList<String>(projectListModel);
		selectProjectList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		selectProjectList.setSelectedIndex(0);
		selectProjectList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(selectProjectList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		add(selectProjectList);

		//add header in leftmost column for activities belonging to selected project
		actListHeader = new JLabel("Activities for project:" + getCurrentSelectedProject().getTitle());
		actListHeader.setFont(boldFont);
		add(actListHeader);

		//add scrollable list of projects activities
		activityListModel = new DefaultListModel<String>();
		try {
			for(String current : getActivityNames()){
				activityListModel.addElement(current);
			}
		} catch (SQLException e1) {
			new ErrorDialog("There was an error in connecting to the server");
		}
		selectActivityList = new JList<String>(activityListModel);

		selectActivityList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		selectActivityList.setSelectedIndex(0);
		selectActivityList.setVisibleRowCount(-1);
		JScrollPane listScroller2 = new JScrollPane(selectActivityList);
		listScroller2.setPreferredSize(new Dimension(250, 80));
		add(selectActivityList);

		//add printbutton
		PrintButtonPanel printButtonPanel = new PrintButtonPanel(this);
		this.add(printButtonPanel);


		//Listener for changes in project selection for refreshing activities
		selectProjectList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (lse.getValueIsAdjusting()){
					return;
				}
				refreshActivitiesList();
				
			}
		});
	}

	public boolean isRefreshingActivities() {
		return refreshingActivities;
	}

	private void fetchProjectsList() {
		try {
			projectsList = Project.getProjectByProjectLeader(ProjectPlanner.getCurrentUser());
		} catch (SQLException e2) {
			new ErrorDialog("There was an error in connecting to the server");
		}
	}

	private List<Activity> fetchActivitiesList() throws SQLException {
		List<Activity> returnList = new ArrayList<Activity>();
		try {
			returnList = getCurrentSelectedProject().getActivities();			
		} catch (SQLException e2) {
			new ErrorDialog("There was an error in connecting to the server");
		}
		if(returnList.size()==0){
			returnList.add(new Activity("There are no activities", getCurrentSelectedProject()));
		}
		return returnList;
	}

	private List<String> getProjectNames() {
		List<String> projectNames = new ArrayList<String>();
		for(Project current : projectsList){
			projectNames.add(current.getTitle());
		}
		return projectNames;
	}
	public void refreshActivitiesList() {
		refreshingActivities = true;
		//refresh header
		actListHeader.setText("Activities for project:" + getCurrentSelectedProject().getTitle());
		//refresh list
		activityListModel.removeAllElements();
		try {
			refreshActivityNames();
		} catch (SQLException e) {
			new ErrorDialog("There was an error in connecting to the server");
		}
	}

	private void refreshActivityNames() throws SQLException {
		for(String current : getActivityNames()){
			activityListModel.addElement(current);
		}
		SwingUtilities.invokeLater(() -> refreshingActivities = false);
		SwingUtilities.invokeLater(() -> selectActivityList.setSelectedIndex(0));
		
	}


	private List<String> getActivityNames() throws SQLException {
		List<String> activityNames = new ArrayList<String>(); 
		for(Activity current : fetchActivitiesList()){
			activityNames.add(current.getTitle());
		}
		return activityNames;
	}



	public Project getCurrentSelectedProject(){
		return projectsList.get(selectProjectList.getSelectedIndex());
	}

	public Activity getCurrentSelectedActivity(){
		try {
			return fetchActivitiesList().get(selectActivityList.getSelectedIndex());
		} catch (SQLException e) {
			new ErrorDialog("There was a problem in connecting to the server");
			e.printStackTrace();
		}
		return null;
	}

	public JList<String> getSelectActivityList(){
		return selectActivityList;

	}

	public JList<String> getSelectProjectList() {
		return selectProjectList;
	}

	public void setSelectProjectList(JList<String> selectProjectList) {
		this.selectProjectList = selectProjectList;
	}
}
