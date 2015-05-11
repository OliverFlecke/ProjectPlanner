package projectPlanner.view.personalInfo;

import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projectPlanner.ProjectPlanner;
import projectPlanner.users.User;
import projectPlanner.view.utilities.ErrorDialog;

@SuppressWarnings("serial")
public class PersonalInfoTab extends JPanel{


	private String name;
	private ImagePane imagePanel;
	private PersonalInfoPane personalInfo;
	private ImageIcon icon;


	public PersonalInfoTab () {
		this.name = ProjectPlanner.getCurrentUser().getFirstname() + " " + ProjectPlanner.getCurrentUser().getLastname();
		this.personalInfo = new PersonalInfoPane(name);

		//personalInfo.setMaximumSize(new Dimension(500, 200));
		try {	
			File f = new File(getClass().getResource("images/" + ProjectPlanner.getCurrentUser().getID() +".jpg").toURI());
				icon = new ImageIcon(f.getPath());
		} catch (Exception e) {
			try{
			icon = new ImageIcon(getClass().getResource("images/genericFace.jpg"));
			}catch(Exception e1){new ErrorDialog("Could not get profile image");}
		}

		this.imagePanel = new ImagePane(icon, name);
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(imagePanel);
		this.add(Box.createVerticalStrut(5));
		this.add(personalInfo);
		this.add(Box.createVerticalGlue());	
	}
}
