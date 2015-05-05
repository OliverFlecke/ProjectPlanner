package projectPlanner.view.projectPanel;


import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import projectPlanner.Project;
import projectPlanner.ProjectPlanner;


public class ProjectTab extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;
	
	public ProjectTab () {		
		//Layout for panels
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		//adding columns
		ListPanel listPanel = new ListPanel();
		this.add(listPanel);
		ManageActivityPanel manageActivityPanel = new ManageActivityPanel(listPanel);
		this.add(manageActivityPanel);
		AddActivityPanel addActivityPanel = new AddActivityPanel(listPanel);
		this.add(addActivityPanel);

	}


	
}
