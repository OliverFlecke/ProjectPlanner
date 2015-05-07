package projectPlanner.view.projectTab;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListListener implements ListSelectionListener{

	  @SuppressWarnings("rawtypes")
	  public ListListener() {
		    JList list1 = new JList();
		    list1.addListSelectionListener( this );
		    JList list2 = new JList();
		    list2.addListSelectionListener( this );
	  }
	  
	  public void valueChanged( ListSelectionEvent e ){
		  System.out.println( "Source: " + e.getSource() );
	  }

}
