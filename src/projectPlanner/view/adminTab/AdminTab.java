package projectPlanner.view.adminTab;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class AdminTab extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8865050271362348045L;
	//Fields
	private UserCreater userCreater;
	private ProjectCreater projectCreater;
	private JPanel contentPane;
	
	public AdminTab() {
		this.userCreater = new UserCreater();
		this.projectCreater = new ProjectCreater();
		this.contentPane = new JPanel();
		
		userCreater.setMaximumSize(new Dimension(600, 800));
		projectCreater.setMaximumSize(new Dimension(600,800));
		
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(userCreater);
		contentPane.add(projectCreater);
		contentPane.add(Box.createHorizontalGlue());
	
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(contentPane);
		this.add(Box.createVerticalGlue());
		

	}

}
