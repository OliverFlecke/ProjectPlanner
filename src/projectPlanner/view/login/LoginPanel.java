package projectPlanner.view.login;

import java.awt.Color;
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
import javax.swing.SwingUtilities;

import projectPlanner.ProjectPlanner;
import projectPlanner.users.UserLoginException;
import projectPlanner.view.mainView.View;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2047661947729049615L;
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
		this.projectPlanner = new ProjectPlanner();


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

		//Check login on button click
		loginBtn.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				logInDialog.getStatusUpdatePnl().setStatusColor(Color.decode("#33CC33"));
				logInDialog.getStatusUpdatePnl().updateMessage("Logging you in. Please wait...");
				SwingUtilities.invokeLater(new Runnable() {
				       public void run() {
				    	   if(verifyLogin()){
								new View(logInDialog);
								System.out.println(1);
								logInDialog.setVisible(false);
								System.out.println(2);
							}
				    	   
				        }
				      });
				
			}
		});

		//Makes enter button login
		logInDialog.getRootPane().setDefaultButton(loginBtn);

	}

	@SuppressWarnings("deprecation")
	private boolean verifyLogin(){
		try{
			if(isEmpty()){
				logInDialog.getStatusUpdatePnl().setStatusColor(Color.RED);
				logInDialog.getStatusUpdatePnl().updateMessage("Please fill out both fields");
				return false;
			}
			boolean returnValue = projectPlanner.login(usernameTxtField.getText(), passwordTxtField.getText());
			return returnValue;
		}
		catch(NullPointerException nullEx){
			logInDialog.getStatusUpdatePnl().setStatusColor(Color.RED);
			logInDialog.getStatusUpdatePnl().updateMessage("Please fill out both fields");
			nullEx.printStackTrace();
			return false;
		}
		catch(UserLoginException userEx) {
			logInDialog.getStatusUpdatePnl().setStatusColor(Color.RED);
			logInDialog.getStatusUpdatePnl().updateMessage("Wrong username or password");
			return false;
		}
		catch(SQLException sQLEx) {
			logInDialog.getStatusUpdatePnl().setStatusColor(Color.RED);
			logInDialog.getStatusUpdatePnl().updateMessage("No connection to server");
			return false;
		}
		catch(Exception e) {
			logInDialog.getStatusUpdatePnl().setStatusColor(Color.RED);
			logInDialog.getStatusUpdatePnl().updateMessage("No connection to server");
			return false;
		}

		
	}
	
	@SuppressWarnings("deprecation")
	private boolean isEmpty(){
		if(usernameTxtField.getText().length()==0 || passwordTxtField.getText().length()==0){
			return true;
		}
		return false;
	}
	public void flush(){
		passwordTxtField.setText("");
		logInDialog.getStatusUpdatePnl().updateMessage("");
	}

}
