package projectPlanner.view.projectPanel;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.apache.pdfbox.exceptions.COSVisitorException;

import projectPlanner.Project;
import projectPlanner.ProjectReport;
import projectPlanner.view.activityPanel.Activity;
import projectPlanner.view.activityPanel.ActivityHolder;
import projectPlanner.view.calendarPanel.CalendarDay;
import projectPlanner.view.mainView.View;


public class ProjectTab extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;

	JButton printButton;

	private JPanel buttonPanel;

	public ProjectTab () {
		buttonPanel = new JPanel();
		printButton = new PrintButton();
		buttonPanel.add(printButton);
		this.setLayout(new GridLayout(1,0));
		this.add(buttonPanel);


		//Listener for printing pdf of currently selected project
		printButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
					try {
						currentProject().printProjectReport(choosePath());
					} catch (COSVisitorException e1) {
						new PrintErrorDialog("More than one program is trying to access the file");
					} catch (IOException e1) {
						new PrintErrorDialog("There was error in exporting the project");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UserClosedWindowException e1) {
					}

			}

			public String choosePath() throws UserClosedWindowException{
				PathWindow pathWindow = new PathWindow();
				return pathWindow.getPath();
			}

			private Project currentProject() {
				// TODO Get currently user selected project, this is just mock
				try {
					Project mockProject = Project.getProject(1); 
					return mockProject;
				} catch (SQLException e) {
					System.out.println("Can\'t reach server");
				}
				return null;
				
			}
		});

	}
}
