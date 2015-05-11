package projectPlanner.view.calendarTab;

import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import projectPlanner.Activity;

public class CalendarDay extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7812662961508165890L;
	private Calendar today = Calendar.getInstance();
	private Calendar calendar = Calendar.getInstance();

	//Get todays date
	private int thisDay = today.get(Calendar.DAY_OF_MONTH);
	private int thisMonth  = today.get(Calendar.MONTH);
	private int thisYear  = today.get(Calendar.YEAR);

	//Month to display
	private int month = 4;
	private int year = 2015;


//	private String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	private String[] dayNames = { "Sunday", "Monday", "Tuesday", "Wedensday", "Thursday", "Friday", "Saturday"};
	private int count = 0;
	private int limit = dayNames.length*5;
	private List<Activity> listOfActivities;


	public CalendarDay(List<Activity> listOfActivities) {
		this.setBackground(Color.WHITE);
		this.listOfActivities =listOfActivities;

		//Set a date for next month
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

		Calendar iterator = (Calendar) calendar.clone();
		iterator.add(Calendar.DAY_OF_MONTH,
				-(iterator.get(Calendar.DAY_OF_WEEK) - 1));

		Calendar maximum = (Calendar) calendar.clone();
		maximum.add(Calendar.MONTH, +1);

		for (int i = 0; i <7; i++) {
			JPanel dayPane = new JPanel();
			dayPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dayLbl = new JLabel(dayNames[i]);
			dayPane.add(dayLbl);
			this.add(dayPane);
		}

		while (iterator.getTimeInMillis() < maximum.getTimeInMillis()){
			int lastMonth = iterator.get(Calendar.MONTH);
			int lastYear = iterator.get(Calendar.YEAR);

			JPanel dayPane = new JPanel();
			Border border = BorderFactory.createLineBorder(Color.BLACK);
			Border margin = new EmptyBorder(10,10,10,10);
			dayPane.setBorder(new CompoundBorder(border, margin));
			dayPane.setLayout(new BoxLayout(dayPane,BoxLayout.PAGE_AXIS));

			JLabel dayLbl = new JLabel();

			if ((lastMonth == month) && (lastYear == year)) {
				int lastDay = iterator.get(Calendar.DAY_OF_MONTH);
				dayLbl.setText(Integer.toString(lastDay));
				if ((thisMonth == month) && (thisYear == year) && (thisDay==lastDay)) {
					dayPane.setBackground(new Color(255,220,122));
				} else {
					dayPane.setBackground(Color.WHITE);
				}
			} else {
				dayLbl.setText(" ");
			}
			dayPane.add(dayLbl);
			for(Activity current : this.listOfActivities){
				if(current.getEndDate().get(Calendar.YEAR) == iterator.get(Calendar.YEAR) &&
						current.getEndDate().get(Calendar.DAY_OF_YEAR) == iterator.get(Calendar.DAY_OF_YEAR)){

					JLabel deadline = new JLabel("<html><body>Deadline:<br>" + current.getTitle() + "</body></html>");
					deadline.setForeground(Color.RED);
					dayPane.add(deadline);				
				}
			}
			this.add(dayPane);
			iterator.add(Calendar.DAY_OF_YEAR, +1);
			count ++;
		}

		for (int i = count; i < limit; i++){
			JPanel dayPane = new JPanel();
			dayPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dayLbl = new JLabel(" ");
			dayPane.add(dayLbl);
			this.add(dayPane);
		}	
	}
}
