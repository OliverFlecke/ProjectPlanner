package projectPlanner.view.activityTab;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import projectPlanner.Activity;
import projectPlanner.ProjectPlanner;
import projectPlanner.users.User;
import projectPlanner.view.utilities.ErrorDialog;
import projectPlanner.view.utilities.TabUpdate;



public class ActivityTab extends JPanel implements TabUpdate {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4264482868958176888L;
	
	
	private ActivityListPane leftPane;
	private ActivityModificationPane rightPane;
	private JScrollPane leftSideScrollPane;

	public ActivityTab (List<Activity> listOfActivities) {
	    
	    rightPane = new ActivityModificationPane(listOfActivities);
	    leftPane = new ActivityListPane(listOfActivities, rightPane);
		rightPane.setLayout(new GridLayout(0,7));
	    leftSideScrollPane = new JScrollPane(leftPane);
	    
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSideScrollPane, rightPane);
	    
	    splitPane.setOneTouchExpandable(true);
	    splitPane.setDividerLocation(300);
	    this.setLayout(new GridLayout(1,0));
	    this.add(splitPane);
		
		
	}

	@Override
	public void updateTab() {
		List<Activity> newActivities;
		try {
			newActivities = User.getActivities(ProjectPlanner.getCurrentUser());
			rightPane = new ActivityModificationPane(newActivities);
		    leftPane = new ActivityListPane(newActivities, rightPane);
		} catch (Exception e){
			new ErrorDialog("Acttivities Tab didn't update.");
		}

		
	}

}
