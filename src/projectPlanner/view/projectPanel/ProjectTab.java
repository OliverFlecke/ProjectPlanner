package projectPlanner.view.projectPanel;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import org.apache.pdfbox.exceptions.COSVisitorException;

import projectPlanner.Project;
import projectPlanner.ProjectPlanner;
import projectPlanner.ProjectReport;
import projectPlanner.users.User;
import projectPlanner.view.StdListPanel;
import projectPlanner.view.activityPanel.ActivityPane;
import projectPlanner.view.activityPanel.ActivityHolder;
import projectPlanner.view.adminTab.TextNDate;
import projectPlanner.view.adminTab.TextNField;
import projectPlanner.view.calendarPanel.CalendarDay;
import projectPlanner.view.mainView.View;


public class ProjectTab extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;
	private String path;
	private List<Project> projectsList;
	private JList selectList;
	private User currentUser;

	public ProjectTab () {
		//gets current user
		currentUser = ProjectPlanner.getCurrentUser();
		//get list of projects  associated with current user
		getProjects();

		//Layout for panels
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		//create first panel/column
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//add header in leftmost column
		JLabel projectHeader = new JLabel("Please select the project you want to manage:");
		Font font = projectHeader.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+3);
		projectHeader.setFont(boldFont);
		panel.add(projectHeader);

		//add spacer
		panel.add(Box.createRigidArea(new Dimension(5,5)));

		//add scrollable list of project names, for selection
		selectList = new JList(getProjectNames().toArray());
		selectList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		selectList.setSelectedIndex(0);
		selectList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(selectList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		panel.add(selectList);

		//add another main column
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		//add print button
		PrintButton printButton = new PrintButton();;
		panel1.add(printButton);

		//add activity adding area
		JPanel panel2 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		//add header in leftmost column
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
		
		createActivity.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				Activity addAct = new Activity();
				currentSelectedProject()
			}
		});
		
		

		//adding columns
		this.add(panel);
		this.add(panel1);
		this.add(panel2);
		
		printButtonListener(printButton);

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
					currentSelectedProject().printProjectReport(path);

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

	private Project currentSelectedProject(){
		return projectsList.get(selectList.getSelectedIndex());
	}
}
