package projectPlanner.view.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import projectPlanner.ProjectPlanner;
import projectPlanner.users.UserLoginException;
import projectPlanner.view.mainView.View;

public class LoginPanel extends JPanel {


	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;
	private JButton loginBtn;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private ProjectPlanner projectPlanner;
	private LogInDialog logInDialog;

	public LoginPanel(LogInDialog logInDialog) {
		//reference to logInDialog
		this.logInDialog = logInDialog;
		

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

		//Chek login on button click
		loginBtn.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(verifyLogin()){
					View view = new View();
					logInDialog.setVisible(false);

				}
			}
		});

	}

	private boolean verifyLogin(){
		try{
			return projectPlanner.login(usernameTxtField.getText(), passwordTxtField.getPassword().toString());
		}
		catch(NullPointerException nullEx){
			logInDialog.getStatusUpdatePnl().updateMessage("Please fill out all fields");
			return false;
		}
		catch(UserLoginException userEx) {
			logInDialog.getStatusUpdatePnl().updateMessage("Wrong username or password");
			return false;
		}
		catch(SQLException userEx) {
			logInDialog.getStatusUpdatePnl().updateMessage("No connection to server");
			return false;
		}
		catch(Exception userEx) {
			logInDialog.getStatusUpdatePnl().updateMessage("No connection to server");
			return false;

		}
	}

}
