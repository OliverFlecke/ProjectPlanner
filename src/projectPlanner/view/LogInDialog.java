package projectPlanner.view;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1107795827494603278L;
	
	private JTextField username;
	private JPasswordField password;
	private JLabel statusUpdate;
	private JLabel status;
	private JButton loginBtn;
	private JButton cancelBtn;
	
	public LogInDialog() {
		super("Log In Window");
		
		//Setting up new JPanels
		JPanel loginPanel = new JPanel();
		JPanel statusUpdatePanel = new JPanel();
	}
}
