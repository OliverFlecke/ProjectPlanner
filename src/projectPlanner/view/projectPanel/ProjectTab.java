package projectPlanner.view.projectPanel;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;
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

import projectPlanner.Activity;
import projectPlanner.Project;
import projectPlanner.ProjectPlanner;
import projectPlanner.users.User;
import projectPlanner.view.adminTab.TextNDate;
import projectPlanner.view.adminTab.TextNField;


public class ProjectTab extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;
	private String path;
	private List<Project> projectsList;
	private JList<String> selectList;
	private JList<String> selectActivityList;
	private User currentUser;
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private Font boldFont;
	private JLabel actListHeader;
	private DefaultListModel<String> listModel;

	public ProjectTab () {
		//gets current user
		currentUser = ProjectPlanner.getCurrentUser();
		//get list of projects  associated with current user
		getProjects();

		//Layout for panels
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		//create first panel/column
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//add header in leftmost column
		JLabel projectHeader = new JLabel("Please select the project you want to manage:");
		Font font = projectHeader.getFont();
		boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+3);
		projectHeader.setFont(boldFont);
		panel.add(projectHeader);

		//add spacer
		panel.add(Box.createRigidArea(new Dimension(5,5)));

		//add scrollable list of project names, for selection
		DefaultListModel<String> listMdl = new DefaultListModel<String>();
		for(String current : getProjectNames()){
			listMdl.addElement(current);
		}
		selectList = new JList<String>(listMdl);
		selectList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		selectList.setSelectedIndex(0);
		selectList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(selectList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		panel.add(selectList);

		//add another main column
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		//add print button
		PrintButton printButton = new PrintButton();;
		panel1.add(printButton);

		//add activity adding area
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

		//add header in rightmost column
		JLabel activityHeader = new JLabel("Add an activity to the selected project");
		projectHeader.setFont(boldFont);
		panel2.add(activityHeader);

		//add input fields
		TextNField name = new TextNField("Name");
		panel2.add(name);
		TextNField alottedTime = new TextNField("Alotted Man-Hours");
		panel2.add(alottedTime);
		TextNDate startDate = new TextNDate("Start Date");
		panel2.add(startDate);
		TextNDate endDate = new TextNDate("End Date");
		panel2.add(endDate);
		JButton createActivity = new JButton("Create Activity");
		panel2.add(createActivity);

		//actionlistener for button that submits activity and updates activity list
		createActivity.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					Activity addAct = new Activity(name.getTxt(),getCurrentSelectedProject());
					name.setTxt("");
					alottedTime.setTxt("");
					startDate.setDate(Calendar.getInstance());
					endDate.setDate(Calendar.getInstance());
					refreshActivitiesList();
				} catch (SQLException e1) {
					new ErrorDialog("There was a problem in connecting to the server");
				}

			}
		});

		//add header in leftmost column for activities belonging to selected project
		actListHeader = new JLabel("Activities for project:" + getCurrentSelectedProject().getTitle());
		actListHeader.setFont(boldFont);
		panel.add(actListHeader);

		//add scrollable list of projects activities
		listModel = new DefaultListModel<String>();
		try {
			for(String current : getActivityNames()){
				listModel.addElement(current);
			}
		} catch (SQLException e1) {
			new ErrorDialog("There was an error in connecting to the server");
		}
		selectActivityList = new JList<String>(listModel);

		selectActivityList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		selectActivityList.setSelectedIndex(0);
		selectActivityList.setVisibleRowCount(-1);
		JScrollPane listScroller2 = new JScrollPane(selectActivityList);
		listScroller2.setPreferredSize(new Dimension(250, 80));
		panel.add(selectActivityList);


		//Listener for changes in project selection for refreshing activities
		selectList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (lse.getValueIsAdjusting()){
					return;
				}
				refreshActivitiesList();
			}
		});





		//adding columns
		this.add(panel);
		this.add(panel1);
		this.add(panel2);

		printButtonListener(printButton);

	}


	private void refreshActivitiesList() {
		//refresh header
		actListHeader.setText("Activities for project:" + getCurrentSelectedProject().getTitle());
		//refresh list
		listModel.removeAllElements();
		try {
			for(String current : getActivityNames()){
				listModel.addElement(current);
			}
		} catch (SQLException e) {
			new ErrorDialog("there was an error in connecting to the server");
		}
	}


	private List<String> getActivityNames() throws SQLException {
		List<String> activityNames = new ArrayList<String>(); 
		for(Activity current : getCurrentSelectedProject().getActivities()){
			activityNames.add(current.getTitle());
		}
		return activityNames;
	}


	//Listener for printing pdf of currently selected project
	private void printButtonListener(PrintButton printButton) {

		printButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					path = choosePath();
				} catch (UserClosedWindowException e1) {
					return;
				}
				try {
					getCurrentSelectedProject().printProjectReport(path);

				} catch (IOException e1) {
					new ErrorDialog("There was an error in writing the file");
				}

				catch (SQLException e1) {
					new ErrorDialog("There was an error in connecting to the server");
				}		
			}

			public String choosePath() throws UserClosedWindowException{
				PathWindow pathWindow = new PathWindow();
				return pathWindow.getPath();
			}
		});
	}

	private void getProjects() {
		try {
			projectsList = Project.getProjectByProjectLeader(currentUser);
		} catch (SQLException e2) {
			new ErrorDialog("An error occurred in trying to connect to the server");
		}
	}

	private List<String> getProjectNames() {
		List<String> projectNames = new ArrayList<String>();
		for(Project current : projectsList){
			projectNames.add(current.getTitle());
		}
		return projectNames;
	}

	private Project getCurrentSelectedProject(){
		return projectsList.get(selectList.getSelectedIndex());
	}
}
