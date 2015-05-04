package projectPlanner.view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class StdListPanel extends JPanel{
	
	//fields
	public List<String> listForPop;
	public JList<String> listToPop;
	public JLabel listHeadderLbl;

	public StdListPanel(List<String> listForPop, String listLbl){
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.listForPop = listForPop;
		
		this.listHeadderLbl = new JLabel(listLbl);
		
		
		listToPop = new JList<String>(populateList());
		listToPop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listToPop.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listToPop.setVisibleRowCount(6);
		listToPop.setFont(new Font("Arial Bold", Font.BOLD, 14));
		listToPop.setBackground(new Color(238,238,238));
		
		
		//Set the JList to center.
		listHeadderLbl.setAlignmentX(CENTER_ALIGNMENT);
		
		
		//Add to the layout
		this.add(listHeadderLbl);
		this.add(listToPop);
		
		populateList();
		
	}
	
	private DefaultListModel<String> populateList() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (String item: listForPop) {
			listModel.addElement(item);
		}
		return listModel;
		
	}

	public void addTolist(String toAdd) {
		listForPop.add(toAdd);
	}
}
