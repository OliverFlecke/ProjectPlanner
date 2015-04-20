package projectPlanner.view.calendarPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarDay extends JPanel {
	
	private Calendar today = Calendar.getInstance();
	private Calendar calendar = Calendar.getInstance();
	
	//Get todays date
	private int thisDay = today.get(Calendar.DAY_OF_MONTH);
	private int thisMonth  = today.get(Calendar.MONTH);
	private int thisYear  = today.get(Calendar.YEAR);
	
	//Month to display
	private int month = 3;
	private int year = 2015;
	
	private String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	private String[] dayNames = { "Sunday", "Monday", "Tuesday", "Wedensday", "Thursday", "Friday", "Saturday"};
	private int count = 0;
	private int limit = dayNames.length*5;

	
	public CalendarDay(){
		this.setBackground(Color.WHITE);
		
		
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
			dayPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
				dayPane.setBackground(Color.WHITE);
			}
			dayPane.add(dayLbl);
			this.add(dayPane);
			iterator.add(Calendar.DAY_OF_YEAR, +1);
			count ++;
		}
		
		for (int i = count; i < limit; i++){
			JPanel dayPane = new JPanel();
			dayPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dayLbl = new JLabel(" ");
			dayPane.setBackground(Color.WHITE);
			dayPane.add(dayLbl);
			this.add(dayPane);
		}
		
		
	}

}
