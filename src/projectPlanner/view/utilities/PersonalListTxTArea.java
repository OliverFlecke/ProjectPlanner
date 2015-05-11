package projectPlanner.view.utilities;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PersonalListTxTArea extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6915329041265636204L;
	private JLabel title;
	private JTextArea listOfEmployees;
	private String[] listOfEmps;
	
	public PersonalListTxTArea(String[] listOfEmps) {
		this.listOfEmps = listOfEmps;
		title = new JLabel("Employees");
		listOfEmployees = new JTextArea();
		for (String name: this.listOfEmps){
			listOfEmployees.append(name);
		}
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(title);
		this.add(listOfEmployees);
		this.add(Box.createVerticalGlue());
		
	}

}
