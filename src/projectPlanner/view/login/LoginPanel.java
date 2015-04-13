package projectPlanner.view.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

	
	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;
	private JButton loginBtn;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	
	public LoginPanel() {
		
		//Setting up the layout for the panel.
		this.setLayout(new GridBagLayout());
		GridBagConstraints lp = new GridBagConstraints();
		
		lp.fill = (GridBagConstraints.HORIZONTAL);
		
		usernameLbl = new JLabel("Username: ");
		lp.gridx = 0;
		lp.gridy = 0;
		lp.gridwidth = 1;
		this.add(usernameLbl, lp);
		
		usernameTxtField = new JTextField(20);
		lp.gridx = 1;
		lp.gridy = 0;
		lp.gridwidth = 1;
		this.add(usernameTxtField, lp);
		
		passwordLbl = new JLabel("Password: ");
		lp.gridx = 0;
		lp.gridy = 1;
		lp.gridwidth = 1;
		this.add(passwordLbl, lp);
		
		passwordTxtField = new JPasswordField(20);
		lp.gridx = 1;
		lp.gridy = 1;
		lp.gridwidth = 1;
		this.add(passwordTxtField, lp);
		
		loginBtn = new JButton("Log In");
		lp.gridx = 0;
		lp.gridy = 2;
		lp.gridwidth = 2;
		this.add(loginBtn, lp);
	}

}
