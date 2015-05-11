package projectPlanner.view.utilities;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

public class TextNNumber extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -194859536029870814L;

	private JLabel fieldLabel;
	
	// Input box for the allotted time
	private JFormattedTextField allottedTime;
	private NumberFormat allottedFormat;
	
	public TextNNumber() {
		this.fieldLabel = new JLabel("Allotted Time");
		
		this.setOpaque(false);
		this.setLayout(new FlowLayout());
		
		allottedFormat = NumberFormat.getNumberInstance();
		this.allottedTime = new JFormattedTextField(allottedFormat);
		this.allottedTime.setValue(0);
		this.allottedTime.setColumns(10);
		
		this.add(this.fieldLabel);
		this.add(this.allottedTime);
	}
	
	public TextNNumber(String text) {
		this();
		setLabelName(text);
	}
	
	public void setLabelName(String text) {
		this.fieldLabel.setText(text);
	}
	
	/**
	 * @return Get the double value from the input field
	 */
	public double getValue() {
		return ((Number)this.allottedTime.getValue()).doubleValue();
	}
}
