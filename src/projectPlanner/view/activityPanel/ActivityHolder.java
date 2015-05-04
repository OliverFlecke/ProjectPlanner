package projectPlanner.view.activityPanel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActivityHolder extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2287647052888581135L;
	//fields
	private JLabel myActivitiesLbl = new JLabel("My Activities");
	private String[] listOfActivties = {"UI PROGRAMMING", "PROCRASTINATING", "DATABASE PROGRAMMING", "MODELLING", "RENDERING", "DESIGN"};
	
	public ActivityHolder() {
		
		//Set font on JLabel
		myActivitiesLbl.setFont(new Font("Arial Bold", Font.BOLD, 20));
		myActivitiesLbl.setAlignmentX(CENTER_ALIGNMENT);
		//Add activities to list
		
		
		//Adding layout and setting up scrollbar
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(myActivitiesLbl);
		addActivities(listOfActivties);
		this.add(Box.createVerticalGlue());

	}


	private void addActivities(String[] listOfActivties2) {
		for (String activity: listOfActivties) {
			Activity newActivity = new Activity(activity);
			newActivity.setMaximumSize(new Dimension(2400, newActivity.height().height));
			this.add(newActivity);		
	}
		
	}

}
