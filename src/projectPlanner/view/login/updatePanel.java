package projectPlanner.view.login;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpdatePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7009252657272574378L;
	private JLabel statusUpdateLbl;
	private JLabel statusLbl;
	private JLabel marginLbl;
	
	public UpdatePanel() {		
		statusLbl = new JLabel(" ");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(statusLbl);
		this.add(marginLbl = new JLabel(" "));
	}
	
	public void updateMessage(String string){
		statusLbl.setText(string);
	}

}
