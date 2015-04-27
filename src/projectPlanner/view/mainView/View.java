package projectPlanner.view.mainView;


import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;






import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import projectPlanner.view.calendarPanel.CalendarTab;
import projectPlanner.view.login.LogInDialog;
import projectPlanner.view.personalInfo.PersonalInfoTab;

public class View extends JFrame {
	
	private LogInDialog logInDialog;

	ImageIcon icon;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1669099048319235705L;

	public View(LogInDialog logInDialog) {
		super("Project Planner");
		this.logInDialog = logInDialog;
		initMenuBar();
		JTabbedPane tabbedPane = new JTabbedPane();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		try {
			icon = new ImageIcon(getClass().getResource("images/icon.png"));
		} catch (Exception e) {	}
		CalendarTab panel1 = new CalendarTab();
		tabbedPane.addTab("Calendar", icon, panel1, "this weeks calendar" );

		PersonalInfoTab panel2 = new PersonalInfoTab();
		tabbedPane.addTab("Personal Information", icon, panel2, "Information about you" );

		JComponent panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Activities", icon, panel3, "Activities you are part of" );

		JComponent panel4 = makeTextPanel("Panel #4");
		tabbedPane.addTab("Project", icon, panel4, "Project Managers can create new projects" );

		JComponent panel5 = makeTextPanel("Panel #5");
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


	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
	
}
