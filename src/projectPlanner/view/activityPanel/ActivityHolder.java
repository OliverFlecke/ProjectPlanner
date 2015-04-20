package projectPlanner.view.activityPanel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ActivityHolder extends JPanel {
	
	//fields
	private JLabel myActivitiesLbl = new JLabel("My Activities");
	private String[] listOfActivties = {"UI PROGRAMMING", "PROCRASTINATING", "DATABASE PROGRAMMING", "MODELLING", "RENDERING", "DESIGN"};
	
	
	public ActivityHolder() {
		
		//Set font on JLabel
		myActivitiesLbl.setFont(new Font("Arial Bold", Font.BOLD, 20));
		myActivitiesLbl.setAlignmentX(CENTER_ALIGNMENT);
		//Add activities to list
		
		
		//Adding layout and setting up scrollbar
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(myActivitiesLbl);
		addActivities(listOfActivties);

	}


	private void addActivities(String[] listOfActivties2) {
		for (String activity: listOfActivties) {
			this.add(new Activity(activity));		
	}
		
	}

}
