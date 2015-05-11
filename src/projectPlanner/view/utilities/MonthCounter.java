package projectPlanner.view.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import projectPlanner.view.calendarTab.CalendarTab;

public class MonthCounter extends CounterPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2948590936962344443L;
	private String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	private String monthToDisplay;
	private int monthInt;
	private int yearCounter;
	
	public MonthCounter(CalendarTab calendarTab) {
		monthInt = Calendar.getInstance().get(Calendar.MONTH);
		yearCounter = Calendar.getInstance().get(Calendar.YEAR);
		monthToDisplay = monthNames[monthInt] + "-" + String.valueOf(yearCounter);
		setCounterFld();
		
		//Add listeners to btns
		lessBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (monthInt <= 0){
					monthInt = 11;
					yearCounter--;
				} else {
					monthInt--;
				}
				monthToDisplay = monthNames[monthInt] + "-" + String.valueOf(yearCounter);
				setCounterFld();
				calendarTab.repaintCalendar(monthInt, yearCounter);
				
			}
			
		});
		
		moreBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (monthInt >= 11){
					monthInt = 0;
					yearCounter++;
				} else {
					monthInt++;
				}
				monthToDisplay = monthNames[monthInt] + "-" + String.valueOf(yearCounter);
				setCounterFld();			
				calendarTab.repaintCalendar(monthInt, yearCounter);
			}
			
		});
	}
	
	@Override
	public void setCounterFld() {
		this.counterFld.setText(monthToDisplay);
	}	

}
