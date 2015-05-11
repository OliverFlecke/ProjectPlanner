package projectPlanner.view.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class StdListPanel extends JPanel{
	
	//fields
	private List<String> listForPop;
	private JList<String> listToPop;
	private JLabel listHeadderLbl;
	private DefaultListModel<String> listModel;
	private JPanel container;

	public StdListPanel(List<String> listForPop, String listLbl) {
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.listForPop = listForPop;
		
		
		container = new JPanel();
		this.listHeadderLbl = new JLabel(listLbl);
		listHeadderLbl.setFont(new Font("Arial Bold", Font.BOLD, 16));
		
		listModel = new DefaultListModel<String>();
		listToPop = new JList<String>(populateList());
		listToPop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listToPop.setLayoutOrientation(JList.VERTICAL_WRAP);
		listToPop.setVisibleRowCount(listToPop.getModel().getSize());
		
		
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(listToPop);
		JScrollPane scrollList = new JScrollPane(container);
		scrollList.setBorder(BorderFactory.createEmptyBorder());
		//scrollList.setMinimumSize(new Dimension(100,150));

//		Box listBox = new Box(BoxLayout.Y_AXIS);
//		listBox.add(scrollList);
		listToPop.setFont(new Font("Arial Bold", Font.BOLD, 14));
		listToPop.setBackground(new Color(238,238,238));
		
		
		//Set the JList to center.
		listHeadderLbl.setAlignmentX(CENTER_ALIGNMENT);
		
		
		//Add to the layout
		this.add(listHeadderLbl, BorderLayout.NORTH);
		this.add(scrollList, BorderLayout.CENTER);
		
		
	}
	public StdListPanel(List<String> listForPop, String listLbl, int lblFontSize) {
		
		this(listForPop, listLbl);
		
		listHeadderLbl.setFont(new Font("Arial Bold", Font.BOLD, lblFontSize));
		
	}
	
	public StdListPanel(List<String> listForPop, String listLbl, int lblFontSize, Color color) {
		
		this(listForPop, listLbl, lblFontSize);
		
		listToPop.setBackground(color);
		container.setBackground(color);
	}
	public StdListPanel(List<String> listForPop, String listLbl, int lblFontSize, Color color, boolean editable) {
		
		this(listForPop, listLbl, lblFontSize, color);
		
		setEditable(editable);
		

		
	}

	public void setEditable(boolean value) {
		if (value) {
			listToPop.setEnabled(true);
		} else {
			listToPop.setEnabled(false);
			
		}
	}
	
	
	public JList<String> getListToPop() {
		return listToPop;
	}

	public void setListForPop(List<String> listForPop) {
		this.listForPop = listForPop;
	}

	
	public DefaultListModel<String> populateList() {
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
		resize();
	}
	public void resize() {
		listToPop.setVisibleRowCount(listToPop.getModel().getSize());
	}
}
