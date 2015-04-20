package projectPlanner.view.activityPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;




import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.view.StdListPanel;

public class Activity extends JPanel {
	
	public JLabel activityTitle;
	public TextPanel hoursSpent;
	public StdListPanel otherEmps;

	
	public Activity(String activityName) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		activityTitle = new JLabel(activityName);
		activityTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		hoursSpent = new TextPanel("Hours Spent:  ", "50,5"); 
		
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		this.otherEmps = new StdListPanel(new ArrayList<String>(Arrays.asList("Sebastian", "Oliver", "Mark","Ben", "Peter", "Mathias", "Anders", "Alexander")), "Colleagues attached to activity:");
		
		
		
		//Adding elements to the JPanel 
		this.add(activityTitle);
		this.add(hoursSpent);
		this.add(otherEmps);
		
		
		// centering the elements.
		activityTitle.setAlignmentX(CENTER_ALIGNMENT);
		
	}
}
