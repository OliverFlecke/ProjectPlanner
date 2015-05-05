package projectPlanner.view.activityTab;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import projectPlanner.Activity;



public class ActivityTab extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4264482868958176888L;
	
	
	private ActivityListPane leftPane;
	private ActivityModificationPane rightPane;
	private JScrollPane leftSideScrollPane;

	public ActivityTab (List<Activity> listOfActivities) {
	    
	    rightPane = new ActivityModificationPane();
	    leftPane = new ActivityListPane(listOfActivities, rightPane);
		rightPane.setLayout(new GridLayout(0,7));
	    leftSideScrollPane = new JScrollPane(leftPane);
	    
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSideScrollPane, rightPane);
	    
	    splitPane.setOneTouchExpandable(true);
	    splitPane.setDividerLocation(300);
	    this.setLayout(new GridLayout(1,0));
	    this.add(splitPane);
		
		
	}

}
