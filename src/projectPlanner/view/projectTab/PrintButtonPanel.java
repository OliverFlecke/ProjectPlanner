package projectPlanner.view.projectTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import projectPlanner.view.utilities.ErrorDialog;

public class PrintButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 675997400136102877L;
	private String path;
	private ListPanel listPanel;

	public PrintButtonPanel(ListPanel listPanel){
		this.listPanel = listPanel;

		//set layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//add print button
		PrintButton printButton = new PrintButton();;
		this.add(printButton);
		printButtonListener(printButton);
	}

	//Listener for printing pdf of currently selected project
	private void printButtonListener(PrintButton printButton) {

		printButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					path = choosePath();
				} catch (UserClosedWindowException e1) {}
				try {
					listPanel.getCurrentSelectedProject().printProjectReport(path);
				} catch (IOException | SQLException e1) {
					new ErrorDialog("There was a problem in generating the report");
				}	
			}

			public String choosePath() throws UserClosedWindowException{
				PathWindow pathWindow = new PathWindow();
				return pathWindow.getPath();
			}
		});
	}



}
