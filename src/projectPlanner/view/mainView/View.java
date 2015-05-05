package projectPlanner.view.mainView;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import projectPlanner.users.User;
import projectPlanner.view.adminTab.AdminTab;
import projectPlanner.view.calendarTab.CalendarTab;
import projectPlanner.view.login.LogInDialog;
import projectPlanner.view.personalInfo.PersonalInfoTab;
import projectPlanner.view.projectPanel.ProjectTab;
import projectPlanner.view.activityTab.ActivityTab;
import projectPlanner.Activity;
import projectPlanner.ProjectPlanner;

public class View extends JFrame {
	
	private LogInDialog logInDialog;
	private List<Activity> listOfActivities;

	ImageIcon icon;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1669099048319235705L;

	public View(LogInDialog logInDialog) {
		super("Project Planner");
		
		try {
			listOfActivities = User.getActivities(ProjectPlanner.getCurrentUser());
		} catch (Exception e){
			
		}
		this.logInDialog = logInDialog;
		initMenuBar();
		JTabbedPane tabbedPane = new JTabbedPane();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		try {
			icon = new ImageIcon(getClass().getResource("images/icon.png"));
		} catch (Exception e) {	}
		CalendarTab panel1 = new CalendarTab(listOfActivities);
		tabbedPane.addTab("Calendar", icon, panel1, "this weeks calendar" );

		PersonalInfoTab panel2 = new PersonalInfoTab();
		tabbedPane.addTab("Personal Information", icon, panel2, "Information about you" );

		JComponent panel3 = new ActivityTab(listOfActivities);
		tabbedPane.addTab("Activities", icon, panel3, "Activities you are part of" );
		
		ProjectTab panel4 = new ProjectTab();
		tabbedPane.addTab("Project", icon, panel4, "Project Managers can create new and manage projects" );

		AdminTab panel5 = new AdminTab();
		tabbedPane.addTab("adminTab", icon, panel5, "Super secret tab for admins only" );

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
	
}
