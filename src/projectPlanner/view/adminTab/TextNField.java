package projectPlanner.view.adminTab;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;

public class TextNField extends JPanel {
	

	//Create txtField
	public JTextField txtField = new JTextField(10);
	
	//Create Lbl
	public JLabel fieldLbl = new JLabel();
	
	public TextNField() {
		
		this.setOpaque(false);
		this.setLayout(new FlowLayout());
		this.add(fieldLbl);
		this.add(txtField);
		
	}
	
	public TextNField(String lblName) {
		this();
		setLblName(lblName);
		
	}
	
	public TextNField(String lblName, String fieldTxt){
		this(lblName);
		setTxt(fieldTxt);
	}
	
	public void setLblName(String lblName){
		fieldLbl.setText(lblName);
	}
	
	public String getTxt(){
		return txtField.getText();
	}
	
	public void setTxt(String txt){
		txtField.setText(txt);
	}
}

