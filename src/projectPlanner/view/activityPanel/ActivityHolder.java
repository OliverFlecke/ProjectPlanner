package projectPlanner.view.activityPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.Activity;
import projectPlanner.ProjectPlanner;
import projectPlanner.users.User;

public class ActivityHolder extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2287647052888581135L;
	//fields
	private JLabel myActivitiesLbl = new JLabel("My Activities");
	private List<Activity> listOfActivties;
	private User user;
	
	public ActivityHolder() {
		this.user = ProjectPlanner.getCurrentUser();
		try {
		listOfActivties = User.getActivities(user);
		} catch (Exception e){
			
		}
		//Set font on JLabel
		myActivitiesLbl.setFont(new Font("Arial Bold", Font.BOLD, 20));
		myActivitiesLbl.setAlignmentX(CENTER_ALIGNMENT);
		//Add activities to list
		
		
		//Adding layout and setting up scrollbar
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(myActivitiesLbl);
		addActivities();
		this.add(Box.createVerticalGlue());

	}


	private void addActivities() {
		for (Activity activity: listOfActivties) {
			ActivityPane newActivity = new ActivityPane(activity.getTitle());
			newActivity.setMaximumSize(new Dimension(2400, newActivity.height().height));
			this.add(newActivity);		
	}
		
	}

}
