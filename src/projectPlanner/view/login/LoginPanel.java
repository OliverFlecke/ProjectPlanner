package projectPlanner.view.login;

import java.awt.Cursor;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 2047661947729049615L;
	private Cursor hourglassCursor;
	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;
	private JButton loginBtn;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private ProjectPlanner projectPlanner;
	private LogInDialog logInDialog;
	private Cursor normalCursor;

	public LoginPanel(LogInDialog logInDialog) {
		//add wait cursors
		hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
		normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);

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
				if(verifyLogin()){
					new View(logInDialog);
					logInDialog.setVisible(false);
				}
			}
		});

		//Makes enter button login
		logInDialog.getRootPane().setDefaultButton(loginBtn);

	}

	@SuppressWarnings("deprecation")
	private boolean verifyLogin(){
		try{
			if(isEmpty()){
				logInDialog.getStatusUpdatePnl().updateMessage("Please fill out both fields");
				return false;
			}
			logInDialog.setCursor(hourglassCursor);
			boolean returnValue = projectPlanner.login(usernameTxtField.getText(), passwordTxtField.getText());
			logInDialog.setCursor(normalCursor);
			return returnValue;
		}
		catch(NullPointerException nullEx){
			logInDialog.setCursor(normalCursor);
			logInDialog.getStatusUpdatePnl().updateMessage("Please fill out both fields");
			nullEx.printStackTrace();
			return false;
		}
		catch(UserLoginException userEx) {
			logInDialog.setCursor(normalCursor);
			logInDialog.getStatusUpdatePnl().updateMessage("Wrong username or password");
			return false;
		}
		catch(SQLException sQLEx) {
			logInDialog.setCursor(normalCursor);
			logInDialog.getStatusUpdatePnl().updateMessage("No connection to server");
			return false;
		}
		catch(Exception e) {
			logInDialog.setCursor(normalCursor);
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
