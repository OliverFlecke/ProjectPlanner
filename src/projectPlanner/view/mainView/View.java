package projectPlanner.view.mainView;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import projectPlanner.Project;
import projectPlanner.ProjectPlanner;
import projectPlanner.users.User;
import projectPlanner.view.ErrorDialog;
import projectPlanner.view.adminTab.AdminTab;
import projectPlanner.view.calendarTab.CalendarTab;
import projectPlanner.view.login.LogInDialog;
import projectPlanner.view.personalInfo.PersonalInfoTab;
import projectPlanner.view.projectTab.ProjectTab;
import projectPlanner.view.activityTab.ActivityTab;
import projectPlanner.Activity;

public class View extends JFrame {

	private LogInDialog logInDialog;
	private List<Activity> listOfActivities;

	ImageIcon activity;
	private ImageIcon calendartab;
	private ImageIcon admin;
	private ImageIcon personal;
	private ImageIcon project;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1669099048319235705L;

	public View(LogInDialog logInDialog) {
		super("Project Planner");
		
		
		//set main icon
		try{
		ImageIcon img = new ImageIcon(getClass().getResource("images/calendar.png"));
		setIconImage(img.getImage());
		}catch(Exception e){}

		try {
			listOfActivities = User.getActivities(ProjectPlanner.getCurrentUser());
		} catch (Exception e){
			new ErrorDialog("There was an error in connecting to the server");
		}

		this.logInDialog = logInDialog;
		initMenuBar();
		JTabbedPane tabbedPane = new JTabbedPane();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//get icons for tabs
		try {
			activity = new ImageIcon(getClass().getResource("images/activity.png"));
			calendartab = new ImageIcon(getClass().getResource("images/calendartab.png"));
			admin = new ImageIcon(getClass().getResource("images/admin.png"));
			personal = new ImageIcon(getClass().getResource("images/personal.png"));
			project = new ImageIcon(getClass().getResource("images/project.png"));
		} catch (Exception e) {	}
		
		CalendarTab panel1 = new CalendarTab(listOfActivities);
		tabbedPane.addTab("Calendar", calendartab, panel1, "this weeks calendar" );

		PersonalInfoTab panel2 = new PersonalInfoTab();
		tabbedPane.addTab("Personal Information", personal, panel2, "Information about you" );

		ActivityTab panel3 = new ActivityTab(listOfActivities);
		tabbedPane.addTab("Activities", activity, panel3, "Activities you are part of" );

		if(checkIfProjectsExist()){
			ProjectTab panel4 = new ProjectTab();
			tabbedPane.addTab("Project", project, panel4, "Project Managers can create new projects" );
		}
		
		if(ProjectPlanner.getCurrentUser().isAdmin()){
		AdminTab panel5 = new AdminTab();
		tabbedPane.addTab("adminTab", admin, panel5, "Super secret tab for admins only" );
		}

		this.add(tabbedPane, BorderLayout.CENTER);
		setSize(1200,800);

		//Centers window on screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);



		//Opens confirmation dialog upon exit
		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Are you sure you want exit?", "Exit confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		};
		this.addWindowListener(exitListener);
        // perhaps this functionality is not needed unless you are an admin?? It slows down UI, seeing as everything is run in one thread
		// maybe if you add time to an activity and it's the first one selected
		//@TODO speak with team about it
//		//Listener for updating tabs when accessed, remember to implement TabUpdate interface for tabs
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane.getSelectedComponent() instanceof ProjectTab){
					((ProjectTab) tabbedPane.getSelectedComponent()).updateTab();
				}
				else if(tabbedPane.getSelectedComponent() instanceof ActivityTab){
					((ActivityTab) tabbedPane.getSelectedComponent()).updateTab();
				}
			}
		});


		this.setVisible(true);
	}


	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu logOut = new JMenu("Log Out");
		menuBar.add(logOut);
		this.setJMenuBar(menuBar);

		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 


		logOut.addMenuListener(new MenuListener(){
			@Override
			public void menuCanceled(MenuEvent e) {
				dispose();
				logInDialog.loginPnl().flush();
				logInDialog.setVisible(true);
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				dispose();
				logInDialog.loginPnl().flush();
				logInDialog.setVisible(true);
			}

			@Override
			public void menuSelected(MenuEvent e) {
				dispose();
				logInDialog.loginPnl().flush();
				logInDialog.setVisible(true);

			}
		});

	}

	public boolean checkIfProjectsExist(){
		//check if user is a leader off any projects
		List<Project> projectsList = new ArrayList<Project>();
		try {
			projectsList = Project.getProjectByProjectLeader(ProjectPlanner.getCurrentUser());
		} catch (SQLException e2) {
			new ErrorDialog("There was an error in connecting to the server");
		}
		if(projectsList.size()==0){
			return false;
		}
		return true;
	}

}
