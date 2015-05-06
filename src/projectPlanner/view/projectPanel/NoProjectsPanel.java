package projectPlanner.view.projectPanel;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoProjectsPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3665486855384817211L;

	public NoProjectsPanel() {
	 this.setLayout(new BorderLayout());
	 JLabel label = new JLabel("You are not the leader of any projects");
	 this.add(label);
	}

}
