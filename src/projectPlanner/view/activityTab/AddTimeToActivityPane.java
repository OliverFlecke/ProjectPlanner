package projectPlanner.view.activityTab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projectPlanner.*;
import projectPlanner.users.User;
import projectPlanner.view.ErrorDialog;
import projectPlanner.view.StdListPanel;
import projectPlanner.view.activityPanel.TextPanel;
import projectPlanner.view.adminTab.TextNDate;

public class AddTimeToActivityPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7624424829368549942L;
	private JLabel activityTitle;
	private JLabel editTimeLbl;
	private TextPanel hoursSpent;
	private TextPanel projectName;
	private String activityName;
	private CounterPanel addTimePanel;
	private JButton addTimeBtn;
	private TextNDate dateToLogTimePanel;
	private JPanel addTimeContainerPanel;
	private JPanel updateTimeContainerPanel;
	private StdListPanel loggedTimes;
	private CounterPanel updateTimePanel;
	private JButton updateTimeBtn;
	private List<LoggedTime> visibleLoggedTimes;
	private List<LoggedTime> loggedTimeObjects;
	
	

	
	public AddTimeToActivityPane(Activity activity) throws Exception{
		
		activityName = activity.getTitle();
		
		//init
		visibleLoggedTimes = new ArrayList<LoggedTime>();
		dateToLogTimePanel = new TextNDate();
		dateToLogTimePanel.setDate(Calendar.getInstance());
		loggedTimeObjects = ProjectPlanner.getCurrentUser().getLoggedTime();
		
		
		loggedTimes = new StdListPanel(loggedTimeEntries(activity), "This Months time Logs:");
		
		loggedTimes.getListToPop().addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent lse) {
				if (lse.getValueIsAdjusting()){
					return;
				}
				if (!visibleLoggedTimes.isEmpty()) {
					updateTimePanel.setCounterFld(visibleLoggedTimes.get(loggedTimes.getListToPop().getSelectedIndex()).getTime());
				}
			}
		});
		
		
		
		addTimePanel = new CounterPanel();
		updateTimePanel = new CounterPanel();
		
		
		editTimeLbl = new JLabel("Edit time logged on selected timelog");
		editTimeLbl.setFont(new Font("Arial Black", Font.BOLD, 12));
		activityTitle = new JLabel(activityName);
		activityTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		
		projectName = new TextPanel("Project Name: ", activity.getAttachedProject().getTitle());
		hoursSpent = new TextPanel("Hours Spent:  ", String.valueOf(User.getTimeSpendOnActivity(ProjectPlanner.getCurrentUser(), activity)));
		
		//Setting up add timeBtn
		addTimeBtn = new JButton("Add Time");
		addTimeBtn.setAlignmentX(CENTER_ALIGNMENT);
		addTimeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateTime(activity);
					
				
			}


			
			
		});
		
		
		//Setting up add updateTimeBtn
		updateTimeBtn = new JButton("Update Time");
		updateTimeBtn.setAlignmentX(CENTER_ALIGNMENT);
		updateTimeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				updateTime(activity);
					
				
			}
			
			
		});
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//set up addTimeContainerPanel
		addTimeContainerPanel = new JPanel();
		addTimeContainerPanel.setLayout(new BoxLayout(addTimeContainerPanel, BoxLayout.PAGE_AXIS));
		addTimeContainerPanel.add(dateToLogTimePanel);
		addTimeContainerPanel.add(addTimePanel);
		addTimeContainerPanel.add(addTimeBtn);
		addTimeContainerPanel.add(Box.createVerticalGlue());
		
		//Set up updateTimeContainerPanel
		updateTimeContainerPanel = new JPanel();
		updateTimeContainerPanel.setLayout(new BoxLayout(updateTimeContainerPanel, BoxLayout.PAGE_AXIS));
		updateTimeContainerPanel.add(loggedTimes);
		updateTimeContainerPanel.add(editTimeLbl);
		updateTimeContainerPanel.add(updateTimePanel);
		updateTimeContainerPanel.add(updateTimeBtn);
		updateTimeContainerPanel.add(Box.createVerticalGlue());
		
		
		//Set size on elements
		hoursSpent.setMaximumSize(new Dimension(300, 200));
		projectName.setMaximumSize(new Dimension(300,200));
		addTimeContainerPanel.setMaximumSize(new Dimension(300, 200));
		updateTimeContainerPanel.setMaximumSize(new Dimension(300, 200));
		
	
		//Adding elements to the JPanel 
		this.add(activityTitle);
		this.add(projectName);
		this.add(hoursSpent);
		this.add(addTimeContainerPanel);
		this.add(updateTimeContainerPanel);
		this.add(Box.createVerticalGlue());
		
		
		// centering the elements.
		activityTitle.setAlignmentX(CENTER_ALIGNMENT);
		editTimeLbl.setAlignmentX(CENTER_ALIGNMENT);
		
	}
	
	public List<String> loggedTimeEntries(Activity activity) throws SQLException{
		
		ArrayList<String> listOfLoggedTimes = new ArrayList<String>();
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
		visibleLoggedTimes.clear();
		
		for (LoggedTime loggedTimeObject: loggedTimeObjects) {
			if(loggedTimeObject.getDate().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH) && loggedTimeObject.getActivityID() == activity.getID()){
				visibleLoggedTimes.add(loggedTimeObject);
				listOfLoggedTimes.add("Date: " + sdfr.format(loggedTimeObject.getDate().getTime()) + ", Hours: " + String.valueOf(loggedTimeObject.getTime()));
			}			
		}
		if (listOfLoggedTimes.isEmpty()) {
			listOfLoggedTimes.add("No times logged");		
		}
		
		
		return listOfLoggedTimes;
		
	}
	private void updateTime(Activity activity) {
		
		try {
			for (LoggedTime loggedTimeObject: loggedTimeObjects) {
				if (dateToLogTimePanel.getDate().get(Calendar.MONTH) == loggedTimeObject.getDate().get(Calendar.MONTH) && 
						dateToLogTimePanel.getDate().get(Calendar.YEAR) == loggedTimeObject.getDate().get(Calendar.YEAR) &&
						dateToLogTimePanel.getDate().get(Calendar.DAY_OF_WEEK) == loggedTimeObject.getDate().get(Calendar.DAY_OF_WEEK)){
					hoursSpent.setRightText(String.valueOf(User.getTimeSpendOnActivity(ProjectPlanner.getCurrentUser(), activity)));
					User.updateLoggedTime(new LoggedTime(activity.getID(), ProjectPlanner.getCurrentUser().getID(), addTimePanel.getCount(), dateToLogTimePanel.getDate()));
					loggedTimes.updateListModel(loggedTimeEntries(activity));
					return;
					
					
				}
			}
			User.setTimeSpendOnActivity(new LoggedTime(activity.getID(), ProjectPlanner.getCurrentUser().getID(), addTimePanel.getCount(), dateToLogTimePanel.getDate()));
			hoursSpent.setRightText(String.valueOf(User.getTimeSpendOnActivity(ProjectPlanner.getCurrentUser(), activity)));
			loggedTimes.updateListModel(loggedTimeEntries(activity));
		} catch (SQLException e1) {
			new ErrorDialog("Could not save time worked");
		}
		
	}

}
