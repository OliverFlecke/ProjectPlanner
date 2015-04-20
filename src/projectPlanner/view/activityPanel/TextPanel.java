package projectPlanner.view.activityPanel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextPanel extends JPanel{
	
	//Initializing the JLabels
	private JLabel leftLbl = new JLabel();
	private JLabel rightLbl = new JLabel();
	
	public TextPanel(String leftText, String rightText) {
		

		setLeftText(leftText);
		setRightText(rightText);
		
		
		//Adding a layout and setting up the panel
		this.setOpaque(false);
		this.setLayout(new FlowLayout());
		this.add(leftLbl);
		this.add(rightLbl);
	}

	public void setLeftText(String newText) {
		this.leftLbl.setText(newText);
	}

	
	public void setRightText(String newText) {
		this.rightLbl.setText(newText);
	}

}
