package projectPlanner.view.adminTab;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projectPlanner.view.*;
import projectPlanner.users.Employee;
import projectPlanner.view.personalInfo.NameObject;

/**
 * Class to create a user from the UI
 */
public class UserCreater extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2348325294702132017L;
	private NameObject title;
	private TextNField firstname;
	private TextNField lastname;
	private TextNField username;
	private TextNField password;
	private TextNField verifyPassword;
	private JButton createUserBtn;
	private JLabel succesMsg;
	
	
	public UserCreater() {
		title = new NameObject("Create User");
		firstname = new TextNField("First Name: ");
		lastname = new TextNField("Last Name: ");
		username = new TextNField("Username: ");
		password = new TextNField("Password: ");
		verifyPassword = new TextNField("Verify Password: ");
		createUserBtn = new JButton("Create User");
		succesMsg = new JLabel("");
		
		createUserBtn.addActionListener(new createUserListener());
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBorder(new EmptyBorder(10,10,10,10));
		
		this.add(title);
		this.add(firstname);
		this.add(lastname);
		this.add(username);
		this.add(password);
		this.add(verifyPassword);
		this.add(createUserBtn);
		this.add(succesMsg);
		this.add(Box.createVerticalGlue());
		
		this.setPreferredSize(new Dimension(300,400));	
	}

	/**
	 * Listener class for the create user interface
	 */
	public class createUserListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {		
			try {
				String password = UserCreater.this.password.getTxt();
				String passwordVerify = UserCreater.this.verifyPassword.getTxt();
				if (password.equals(passwordVerify)) {
					new Employee(UserCreater.this.username.getTxt(), password, UserCreater.this.firstname.getTxt(), UserCreater.this.lastname.getTxt());
					UserCreater.this.succesMsg.setText("The user was create succesfully");
					UserCreater.this.repaint();
				}
				else { 
					new ErrorDialog("The entered password did not match. Please try again");
				}
			} catch (SQLException e) {
				new ErrorDialog("An error occurred when trying to connect to the server. Please try agian");
			}
		}
	}
}
