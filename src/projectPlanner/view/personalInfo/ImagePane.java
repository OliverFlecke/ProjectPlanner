package projectPlanner.view.personalInfo;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePane extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4030752740965199017L;
	private JLabel imgLbl = new JLabel();
	private NameObject name;
	private NameObject position;
	private JPanel namePane = new JPanel();
	private JPanel imgPane = new JPanel();	
	
	public ImagePane(ImageIcon profilPhoto, String name) {
		
		imgLbl.setIcon(profilPhoto);
		imgPane.setLayout(new BoxLayout(imgPane, BoxLayout.Y_AXIS));
		imgPane.add(imgLbl);
		imgPane.add(Box.createVerticalGlue());
		
		namePane.setLayout(new BoxLayout(namePane, BoxLayout.Y_AXIS));
		namePane.add(Box.createVerticalStrut(10));
		namePane.add(new NameObject(name));
		namePane.add(new NameObject("position: CODE-GIMP", 14));
		namePane.add(Box.createVerticalGlue());
		

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createVerticalGlue());
		this.add(namePane);
		this.add(Box.createHorizontalStrut(5));		
		this.add(imgPane);
	}
}
