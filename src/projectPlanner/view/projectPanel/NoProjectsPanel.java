package projectPlanner.view.projectPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoProjectsPanel extends JPanel{
	public NoProjectsPanel() {
	 this.setLayout(new BorderLayout());
	 JLabel label = new JLabel("You are not the leader of any projects");
	 this.add(label);
	}

}
