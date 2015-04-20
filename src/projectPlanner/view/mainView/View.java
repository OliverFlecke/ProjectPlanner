package projectPlanner.view.mainView;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import projectPlanner.view.listOfEmployees;
import projectPlanner.view.calendarPanel.CalendarTab;

public class View extends JFrame {

	ImageIcon icon;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1669099048319235705L;

	public View() {
		super("Project Planner");
		
		JTabbedPane tabbedPane = new JTabbedPane();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		try {
			icon = new ImageIcon(getClass().getResource("images/icon.png"));
		} catch (Exception e) {	}
		CalendarTab panel1 = new CalendarTab();
		tabbedPane.addTab("Calendar", icon, panel1, "this weeks calendar" );
		
		JComponent panel2 = makeTextPanel("Panel #2");
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
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
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
