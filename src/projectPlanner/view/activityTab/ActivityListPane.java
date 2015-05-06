package projectPlanner.view.activityTab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projectPlanner.view.StdListPanel;
import projectPlanner.Activity;

public class ActivityListPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4685008498825819041L;
	private StdListPanel activitiesList;
	private List<String> activityNames;
	private JList<String> activitiesJList;
	
	public ActivityListPane (List<Activity> listOfActivities, ActivityModificationPane activityModificationPane) {
		activityNames = getActivityNames(listOfActivities);
		activitiesList = new StdListPanel(activityNames, "List of your Activities", 25);
		activitiesJList = activitiesList.getListToPop();
		
		
		
		//Listener for changes in project selection for refreshing activities
		activitiesJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (lse.getValueIsAdjusting()){
					return;
				}
				activityModificationPane.repaintPanel(activitiesJList.getSelectedIndex());
			}
		});
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(activitiesList);
		this.add(Box.createVerticalGlue());
		
		
		
		
	}
	
	
	public List<String> getActivityNames(List<Activity> listOfActivities) {
		activityNames = new ArrayList<String>();
		for (Activity activity: listOfActivities) {
			activityNames.add(activity.getTitle());
			
		}
		return activityNames;
	}



}
