package projectPlanner.view.calendarPanel;


import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import projectPlanner.view.acivitiesPane.ActivityHolder;
import projectPlanner.view.activitiesPane.Activity;


public class CalendarTab extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;
	
	private JPanel leftPane;
	private JPanel rightPane;
	
	public CalendarTab () {
	    leftPane = new ActivityHolder();
	    rightPane = new CalendarDay();
	    rightPane.setLayout(new GridLayout(0,7));
	    
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
	    splitPane.setOneTouchExpandable(true);
	    splitPane.setDividerLocation(300);
	    this.setLayout(new GridLayout(1,0));
	    this.add(splitPane);
	    
	}

}
