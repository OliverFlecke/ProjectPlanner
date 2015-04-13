package projectPlanner.view.login;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class updatePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7009252657272574378L;
	private JLabel statusUpdateLbl;
	private JLabel statusLbl;
	private JLabel marginLbl;
	
	public updatePanel() {
		
		
		statusUpdateLbl = new JLabel("Status:");
		statusLbl = new JLabel("connected.");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(statusUpdateLbl);
		this.add(statusLbl);
		this.add(marginLbl = new JLabel(" "));
	}

}
