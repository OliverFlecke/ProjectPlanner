package projectPlanner.view.activityTab;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.Activity;

public class ActivityModificationPane extends JPanel {
	
	private AddEmployeeToProjectPanel addEmpPane;
	private AddTimeToActivityPane timeToActivityPane;
	private List<Activity> listOfActivities;
	
	public ActivityModificationPane(List<Activity> listOfActivities) {
		this.listOfActivities = listOfActivities;
		
		
		
	}
	
	public void removeAllObjects() {
		
		this.removeAll();
		
	}
	
	public void repaintPanel(int activityIndex) {
		Activity activity = listOfActivities.get(activityIndex);
		removeAllObjects();
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		try {
			timeToActivityPane = new AddTimeToActivityPane(activity);
		} catch (Exception e) {
			
		}
		this.add(timeToActivityPane);
		this.revalidate();
		this.repaint();
	}

}
