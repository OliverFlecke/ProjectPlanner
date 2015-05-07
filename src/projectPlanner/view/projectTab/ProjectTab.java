package projectPlanner.view.projectTab;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import projectPlanner.view.TabUpdate;

public class ProjectTab extends JPanel implements TabUpdate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;
	private ListPanel listPanel;
	
	public ProjectTab () {		
		//Layout for panels
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		//adding columns
		listPanel = new ListPanel();
		this.add(listPanel);
		ManageActivityPanel manageActivityPanel = new ManageActivityPanel(listPanel);
		this.add(manageActivityPanel);
		AddActivityPanel addActivityPanel = new AddActivityPanel(listPanel);
		this.add(addActivityPanel);

	}

	@Override
	public void updateTab() {
		listPanel.refreshActivitiesList();
		
	}
}
