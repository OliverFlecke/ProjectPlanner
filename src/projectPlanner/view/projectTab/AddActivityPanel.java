package projectPlanner.view.projectTab;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.Activity;
import projectPlanner.view.utilities.ErrorDialog;
import projectPlanner.view.utilities.TextNDate;
import projectPlanner.view.utilities.TextNField;

public class AddActivityPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7357419115600518517L;
	public AddActivityPanel(ListPanel listPanel){
		//temp border until visuals are improved
		this.setBorder(BorderFactory.createLineBorder(Color.black));

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
		TextNField allottedTime = new TextNField("Alotted Man-Hours");
		add(allottedTime);
		TextNDate startDate = new TextNDate("Start Date");
		add(startDate);
		TextNDate endDate = new TextNDate("End Date");
		add(endDate);
		JButton createActivity = new JButton("Create Activity");
		add(createActivity);

		//Jlabel stating succes
		JLabel succesLabel = new JLabel(" ");
		add(succesLabel);


		//Actionlistener for button that submits activity, updates activity list and resets fields
		createActivity.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					@SuppressWarnings("unused")

					Activity addAct = new Activity(name.getTxt(),listPanel.getCurrentSelectedProject(),
							startDate.getDate(),endDate.getDate(),Double.parseDouble(allottedTime.getTxt()));
					succesLabel.setForeground(Color.decode("#33CC33"));
					succesLabel.setText(name.getTxt() + " added successfully");
					name.setTxt("");
					allottedTime.setTxt("");
					startDate.setDate(null);
					endDate.setDate(null);
					listPanel.refreshActivitiesList();
				} catch (SQLException e1) {
					new ErrorDialog("There was a problem in connecting to the server");
					e1.printStackTrace();
				}

			}
		});
	}

}
