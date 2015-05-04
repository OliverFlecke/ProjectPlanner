package projectPlanner.view.login;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LogInDialog extends JFrame {

	private JPanel loginPnl;
	private UpdatePanel statusUpdatePnl;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1107795827494603278L;


	public LogInDialog() {
		super("Please Login");

		//Setting up new JPanels
		statusUpdatePnl = new UpdatePanel();
		loginPnl = new LoginPanel(this);
		loginPnl.setSize(500, 100);
		statusUpdatePnl.setSize(500, 100);

		//making a layout and adding panels.
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.add(loginPnl);
		this.add(statusUpdatePnl);

		this.pack();
		this.setSize(400,200);

		//Centers window on screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//close window when exiting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//display frame
		setVisible(true);
		
		setResizable(false);
	}


	public UpdatePanel getStatusUpdatePnl() {
		return statusUpdatePnl;
	}
	public LoginPanel loginPnl(){
		return (LoginPanel) loginPnl;
	}
}
