package projectPlanner.view.login;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInDialog extends JFrame {
	
	private JPanel loginPnl;
	private JPanel statusUpdatePnl;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1107795827494603278L;

	
	public LogInDialog() {
		super("Log In Window");
		
		//Setting up new JPanels
		JPanel loginPnl = new LoginPanel();
		JPanel statusUpdatePnl = new updatePanel();
		loginPnl.setSize(500, 100);
		statusUpdatePnl.setSize(500, 100);
		
		//making a layout and adding panles.
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.add(loginPnl);
		this.add(statusUpdatePnl);
		
		this.pack();
		this.setSize(400,200);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
