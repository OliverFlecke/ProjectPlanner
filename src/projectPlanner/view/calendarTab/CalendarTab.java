package projectPlanner.view.calendarTab;


import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import projectPlanner.Activity;
import projectPlanner.view.activityPanel.*;

public class CalendarTab extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;
	
	private JPanel leftPane;
	private JPanel rightPane;
	private JScrollPane leftSideScrollPane;
	
	public CalendarTab (List<Activity> listOfActivities) {
	    leftPane = new ActivityHolder(listOfActivities);
	    rightPane = new CalendarDay();
	    rightPane.setLayout(new GridLayout(0,7));
	    leftSideScrollPane = new JScrollPane(leftPane);
	    
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSideScrollPane, rightPane);
	    
	    splitPane.setOneTouchExpandable(true);
	    splitPane.setDividerLocation(400);
	    this.setLayout(new GridLayout(1,0));
	    this.add(splitPane);
	    
	}

}
