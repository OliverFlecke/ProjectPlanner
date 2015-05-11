package projectPlanner.view.utilities;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;

public class TextNField extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3711888023598228029L;
	//Create txtField
	public JTextField txtField = new JTextField(10);
	
	public JTextField getTxtField() {
		return txtField;
	}

	public void setTxtField(JTextField txtField) {
		this.txtField = txtField;
	}

	public JLabel getFieldLbl() {
		return fieldLbl;
	}

	public void setFieldLbl(JLabel fieldLbl) {
		this.fieldLbl = fieldLbl;
	}

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

