package projectPlanner.view;


import javax.swing.DefaultListModel;
import javax.swing.JList;

public class listOfEmployees extends JList {
	
	public listOfEmployees(DefaultListModel listModel){
		
		super(listModel);
		this.setLayoutOrientation(JList.VERTICAL);
		
	}

}
