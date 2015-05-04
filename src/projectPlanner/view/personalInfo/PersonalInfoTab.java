package projectPlanner.view.personalInfo;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PersonalInfoTab extends JPanel{
	
	
	private String name;
	private ImagePane imagePanel;
	private PersonalInfoPane personalInfo;
	private ImageIcon icon;
	
	
	public PersonalInfoTab () {
		this.name = "Oliver Frankenstein";
		this.personalInfo = new PersonalInfoPane(name);
		
		//personalInfo.setMaximumSize(new Dimension(500, 200));
		
		try {
			icon = new ImageIcon(getClass().getResource("images/genericFace.jpg"));

		} catch (Exception e) {	}
		
		this.imagePanel = new ImagePane(icon, name);
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(imagePanel);
		this.add(Box.createVerticalStrut(5));
		this.add(personalInfo);
		this.add(Box.createVerticalGlue());	
	}
}
