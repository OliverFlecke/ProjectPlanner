package projectPlanner.view;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

public class DnDTest extends JFrame {
	
	public JList dragToList;
	public JList dragFromList;
	public DefaultListModel<String> employees;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1669099048319235705L;

	public DnDTest() {
		
		
		
		super("DnD Test");

		
		setLayout(new GridLayout(1,0));
		this.getContentPane().setBackground(Color.WHITE);
		
		
		employees = new DefaultListModel<String>();
		employees.addElement("Mark");
		employees.addElement("Sebastian");
		employees.addElement("Oliver");
		
		dragToList = new listOfEmployees(employees);
		dragFromList =  new listOfEmployees(employees);	
		
		
		this.add(dragFromList);
		this.add(dragToList);
		
		this.pack();
		setSize(500,300);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}