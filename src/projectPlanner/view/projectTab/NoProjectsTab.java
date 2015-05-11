package projectPlanner.view.projectTab;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

<<<<<<< HEAD
=======
import projectPlanner.view.utilities.TabUpdate;

>>>>>>> origin/master
public class NoProjectsTab extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3665486855384817211L;

	public NoProjectsTab() {
	 this.setLayout(new BorderLayout());
	 JLabel label = new JLabel("You are not the leader of any projects");
	 this.add(label);
	}
}
