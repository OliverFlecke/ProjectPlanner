package projectPlanner.view.adminTab;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;





import projectPlanner.view.personalInfo.NameObject;

public class UserCreater extends JPanel {
	
	private NameObject title;
	private TextNField firstName;
	private TextNField lastName;
	private TextNField userName;
	private TextNField password;
	private TextNField verifyPassword;
	private JButton createUserBtn;
	
	
	public UserCreater() {
		title = new NameObject("Create User");
		firstName = new TextNField("First Name: ");
		lastName = new TextNField("Last Name: ");
		userName = new TextNField("Username: ");
		password = new TextNField("Password: ");
		verifyPassword = new TextNField("Verify Password: ");
		createUserBtn = new JButton("Create User");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBorder(new EmptyBorder(10,10,10,10));
		
		this.add(title);
		this.add(firstName);
		this.add(lastName);
		this.add(userName);
		this.add(password);
		this.add(verifyPassword);
		this.add(createUserBtn);
		this.add(Box.createVerticalGlue());
		
		this.setPreferredSize(new Dimension(300,400));
		
	}

}
