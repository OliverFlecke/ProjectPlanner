package projectPlanner.view.projectPanel;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class WaitWindow extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1603108265194880949L;
	
	public WaitWindow(){
		super();
		JPanel panel = new JPanel();
		this.add(panel);
		JLabel label = new JLabel("Please wait while the report is generated");
		panel.add(label);
		JProgressBar bar = new JProgressBar();
		panel.add(bar);
		bar.setIndeterminate(true);
		setTitle("Generating report");
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		pack();
		
		
	}
}
