package projectPlanner.view.personalInfo;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NameObject extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -141314089687535858L;
	private JLabel nameLbl;
	
	
	public NameObject(String txt){
		this(txt, 20);

	}
	
	public NameObject(String txt, int txtSize){
				
		this.nameLbl = new JLabel(txt);
		this.nameLbl.setFont(new Font("Arial Black", Font.BOLD, txtSize));		
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.add(nameLbl);
		this.add(Box.createHorizontalGlue());		
	}

}
