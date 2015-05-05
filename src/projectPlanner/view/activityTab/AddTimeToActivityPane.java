package projectPlanner.view.activityTab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.Activity;
import projectPlanner.ProjectPlanner;
import projectPlanner.users.User;
import projectPlanner.view.StdListPanel;
import projectPlanner.view.activityPanel.TextPanel;

public class AddTimeToActivityPane extends JPanel {
	
	public JLabel activityTitle;
	public TextPanel hoursSpent;
	public TextPanel projectName;
	public String activityName;
	

	
	public AddTimeToActivityPane(Activity activity) throws Exception{
		activityName = activity.getTitle();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		activityTitle = new JLabel(activityName);
		activityTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		projectName = new TextPanel("Project Name: ", activity.getAttachedProject().getTitle());
		hoursSpent = new TextPanel("Hours Spent:  ", String.valueOf(User.getTimeSpendOnActivity(ProjectPlanner.getCurrentUser(), activity))); 
		
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	
		//Adding elements to the JPanel 
		this.add(activityTitle);
		this.add(projectName);
		this.add(hoursSpent);
		
		
		// centering the elements.
		activityTitle.setAlignmentX(CENTER_ALIGNMENT);
		
	}

}
