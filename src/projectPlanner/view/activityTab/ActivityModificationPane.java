package projectPlanner.view.activityTab;

import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import projectPlanner.Activity;
import projectPlanner.view.ErrorDialog;

public class ActivityModificationPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7416316687422673249L;
	private AddEmployeeToProjectPanel addEmpPane;
	private AddTimeToActivityPane timeToActivityPane;
	private List<Activity> listOfActivities;
	private JPanel uiFormatPanel;
	
	public ActivityModificationPane(List<Activity> listOfActivities) {
		this.listOfActivities = listOfActivities;
		
		uiFormatPanel = new JPanel();
		uiFormatPanel.setLayout(new BoxLayout(uiFormatPanel, BoxLayout.LINE_AXIS));	
	}
	
	public void removeAllObjects() {
		this.removeAll();	
	}
	
	public void repaintPanel(int activityIndex) {
		Activity activity = listOfActivities.get(activityIndex);
		removeAllObjects();
		uiFormatPanel.removeAll();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		try {
			timeToActivityPane = new AddTimeToActivityPane(activity);
			addEmpPane = new AddEmployeeToProjectPanel(activity);
		} catch (Exception e) {
			new ErrorDialog("Could not reload panels");
		}
		uiFormatPanel.add(timeToActivityPane);
		uiFormatPanel.add(addEmpPane);
		uiFormatPanel.add(Box.createHorizontalGlue());
		this.add(uiFormatPanel);
		this.add(Box.createVerticalGlue());
		this.revalidate();
		this.repaint();
	}

}
