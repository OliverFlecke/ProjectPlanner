package projectPlanner.view.activitiesPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Activity extends JPanel {
	
	public JLabel activityTitle;
	private JLabel hoursUsedTitle;
	private JLabel hoursUsedHours;

	
	public Activity(String activityName) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Dimension size = new Dimension(300,200);
		activityTitle = new JLabel(activityName);
		this.add(activityTitle);
		hoursUsedTitle = new JLabel("Hourse used: "); 
		this.add(hoursUsedTitle);
		
		this.setSize(size);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
}
