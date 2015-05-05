package projectPlanner.view.projectPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.Activity;
import projectPlanner.view.adminTab.TextNDate;
import projectPlanner.view.adminTab.TextNField;

public class AddActivityPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7357419115600518517L;
	public AddActivityPanel(ListPanel listPanel){
		
		//add activity adding area
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//add header in rightmost column
		JLabel activityHeader = new JLabel("Add a new activity to the selected project");
		Font font = activityHeader.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+3);
		activityHeader.setFont(boldFont);
		add(activityHeader);

		//add input fields
		TextNField name = new TextNField("Name");
		add(name);
		TextNField alottedTime = new TextNField("Alotted Man-Hours");
		add(alottedTime);
		TextNDate startDate = new TextNDate("Start Date");
		add(startDate);
		TextNDate endDate = new TextNDate("End Date");
		add(endDate);
		JButton createActivity = new JButton("Create Activity");
		add(createActivity);

		//Actionlistener for button that submits activity, updates activity list and resets fields
		createActivity.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					@SuppressWarnings("unused")
					Activity addAct = new Activity(name.getTxt(),listPanel.getCurrentSelectedProject());
					name.setTxt("");
					alottedTime.setTxt("");
					startDate.setDate(Calendar.getInstance());
					endDate.setDate(Calendar.getInstance());
					listPanel.refreshActivitiesList();
				} catch (SQLException e1) {
					new ErrorDialog("There was a problem in connecting to the server");
				}

			}
		});
	}

}
