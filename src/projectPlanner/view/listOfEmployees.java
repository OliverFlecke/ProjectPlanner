package projectPlanner.view;


import javax.swing.DefaultListModel;
import javax.swing.JList;

@SuppressWarnings("rawtypes")
public class listOfEmployees extends JList {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8544414592050351078L;

	@SuppressWarnings({ "unchecked" })
	public listOfEmployees(DefaultListModel listModel){
		super(listModel);
		this.setLayoutOrientation(JList.VERTICAL);
		
	}

}
