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

import projectPlanner.*;
import projectPlanner.users.User;
import projectPlanner.view.StdListPanel;
import projectPlanner.view.activityPanel.TextPanel;
import projectPlanner.view.adminTab.TextNDate;

public class AddTimeToActivityPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7624424829368549942L;
	private JLabel activityTitle;
	private TextPanel hoursSpent;
	private TextPanel projectName;
	private String activityName;
	private CounterPanel addTimePanel;
	private JButton addTimeBtn;
	private TextNDate dateToLogTimePanel;
	private JPanel addTimeContainerPanel;
	private StdListPanel loggedTimes;
	
	

	
	public AddTimeToActivityPane(Activity activity) throws Exception{
		
		activityName = activity.getTitle();
		
		
		dateToLogTimePanel = new TextNDate();
		dateToLogTimePanel.setDate(Calendar.getInstance());
		
		
		loggedTimes = new StdListPanel(loggedTimeEntries(), "This Months time Logs:");
		
		
		
		
		
		addTimePanel = new CounterPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
				
					try {
						User.setTimeSpendOnActivity(new LoggedTime(activity.getID(), ProjectPlanner.getCurrentUser().getID(), addTimePanel.getCount(), dateToLogTimePanel.getDate()));
						hoursSpent.setRightText(String.valueOf(User.getTimeSpendOnActivity(ProjectPlanner.getCurrentUser(), activity)));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				
			}
			
			
		});
		
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		addTimeContainerPanel = new JPanel();
		addTimeContainerPanel.setLayout(new BoxLayout(addTimeContainerPanel, BoxLayout.PAGE_AXIS));
		addTimeContainerPanel.add(dateToLogTimePanel);
		addTimeContainerPanel.add(addTimePanel);
		addTimeContainerPanel.add(addTimeBtn);
		addTimeContainerPanel.add(Box.createVerticalGlue());
		
		
		//Set size on panels
		activityTitle.setMaximumSize(new Dimension(300, 200));
		hoursSpent.setMaximumSize(new Dimension(300, 200));
		projectName.setMaximumSize(new Dimension(300,200));
		addTimeContainerPanel.setMaximumSize(new Dimension(300, 200));
		
	
		//Adding elements to the JPanel 
		this.add(activityTitle);
		this.add(projectName);
		this.add(hoursSpent);
		this.add(addTimeContainerPanel);
		this.add(loggedTimes);
		this.add(Box.createVerticalGlue());
		
		
		// centering the elements.
		activityTitle.setAlignmentX(CENTER_ALIGNMENT);
		
	}
	
	public List<String> loggedTimeEntries() throws SQLException{
		List<LoggedTime> loggedTimeObjects = ProjectPlanner.getCurrentUser().getLoggedTime();
		ArrayList<String> listOfLoggedTimes = new ArrayList<String>();
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
		
//		for (LoggedTime loggedTimeObject: loggedTimeObjects) {
//			if(loggedTimeObject.getDate().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)){
//				listOfLoggedTimes.add("Date: " + sdfr.format(loggedTimeObject.getDate()) + "Hours logged: " + String.valueOf(loggedTimeObject.getTime()));
//			}
//		}
		
		return listOfLoggedTimes;
		
	}

}
