package projectPlanner.view.acivitiesPane;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import projectPlanner.view.activitiesPane.Activity;

public class ActivityHolder extends JPanel {
	
	
	public ActivityHolder() {
		
		this.setLayout(new BorderLayout());
		this.add(new Activity("UI PROGRAMMING"));
		this.add(new Activity("PROCRASTINATING"));
	}

}
