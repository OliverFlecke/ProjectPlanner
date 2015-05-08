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
	private List<String> listForPop;
	private JList<String> listToPop;
	private JLabel listHeadderLbl;
	private DefaultListModel<String> listModel;

	public StdListPanel(List<String> listForPop, String listLbl) {
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.listForPop = listForPop;
		
		this.listHeadderLbl = new JLabel(listLbl);
		listHeadderLbl.setFont(new Font("Arial Bold", Font.BOLD, 16));
		
		listModel = new DefaultListModel<String>();
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
		
		
	}
	public StdListPanel(List<String> listForPop, String listLbl, int lblFontSize) {
		
		this(listForPop, listLbl);
		
		listHeadderLbl.setFont(new Font("Arial Bold", Font.BOLD, lblFontSize));
		
	}
	
	public StdListPanel(List<String> listForPop, String listLbl, int lblFontSize, Color color) {
		
		this(listForPop, listLbl, lblFontSize);
		
		listToPop.setBackground(color);
		
	}
	
	public JList<String> getListToPop() {
		return listToPop;
	}

	public void setListForPop(List<String> listForPop) {
		this.listForPop = listForPop;
	}

	
	private DefaultListModel<String> populateList() {
		for (String item: listForPop) {
			listModel.addElement(item);
		}
		return listModel;
		
	}

	public void addTolist(String toAdd) {
		listForPop.add(toAdd);
	}
	
	public DefaultListModel<String> getListModel() {
		return listModel;
	}
	public void updateListModel (List<String> newListToPop) {
		listModel.removeAllElements();
		this.listForPop = newListToPop;
		populateList();
	}
}
