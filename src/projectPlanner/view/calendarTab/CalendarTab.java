package projectPlanner.view.calendarTab;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import projectPlanner.Activity;
import projectPlanner.view.activityPanel.*;
import projectPlanner.view.utilities.CounterPanel;
import projectPlanner.view.utilities.MonthCounter;

public class CalendarTab extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;
	
	private JPanel leftPane;
	private JPanel rightPane;
	private JScrollPane leftSideScrollPane;
	private JPanel calendarContainer;
	private MonthCounter monthCounter;
	private List<Activity> listOfActivities;
	
	public CalendarTab (List<Activity> listOfActivities) {
		this.listOfActivities = listOfActivities;
		
		rightPane = new JPanel();
	    leftPane = new ActivityHolder(listOfActivities);
	    calendarContainer = new CalendarDay(listOfActivities, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR));
	    calendarContainer.setLayout(new GridLayout(0,7));
	    leftSideScrollPane = new JScrollPane(leftPane);
	    monthCounter = new MonthCounter(this);
	    monthCounter.setMaximumSize(new Dimension(500,100));
	    
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSideScrollPane, rightPane);
	    
	    
	    rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
	    rightPane.add(monthCounter);
	    rightPane.add(calendarContainer);
	    
	    rightPane.add(Box.createVerticalGlue());
	    
	    splitPane.setOneTouchExpandable(true);
	    splitPane.setDividerLocation(400);
	    this.setLayout(new GridLayout(1,0));
	    this.add(splitPane);
	    
	}
	
	public void repaintCalendar(int month, int year){
		calendarContainer = new CalendarDay(listOfActivities, month, year);
	    rightPane.add(calendarContainer);
	    
	    rightPane.add(Box.createVerticalGlue());
		
		
	}

}
