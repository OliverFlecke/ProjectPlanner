package projectPlanner.view.projectPanel;


import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

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
import projectPlanner.view.activityPanel.ActivityPane;
import projectPlanner.view.activityPanel.ActivityHolder;
import projectPlanner.view.calendarPanel.CalendarDay;
import projectPlanner.view.mainView.View;


public class ProjectTab extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791566971957234579L;

	private JButton printButton;
	private JPanel panel;
	private final CountDownLatch pDFDone = new CountDownLatch(1);
	private String path;
	private WaitWindow wait;

	public ProjectTab () {		
		//creates panel
		panel = new JPanel();
		printButton = new PrintButton();
		panel.add(printButton);
		this.setLayout(new GridLayout(1,0));
		this.add(panel);








		//Listener for printing pdf of currently selected project
		printButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					path = choosePath();
				} catch (UserClosedWindowException e1) {
					return;
				}
				//show new thread generating pdf is ongoing
				wait = new WaitWindow();
				wait.setVisible(true);
				try {
					currentProject().printProjectReport(path);

				} catch (IOException e1) {
					pDFDone.countDown();
					new PrintErrorDialog("There was an error in writing the file");
				}

				catch (SQLException e1) {
					pDFDone.countDown();
					new PrintErrorDialog("There was an error in connecting to the server");
				}		

				wait.dispose();
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
					e.printStackTrace();
					System.out.println("Can\'t reach server");
				}
				return null;

			}
		});

	}
}
