/**
 * 
 */
package projectPlanner;

import java.util.Calendar;

/**
 * Represent a time spend a user has spend on a given activity.
 * Only stores the reference for the user and the activity
 * @author Oliver Fleckenstein
 *
 */
public class LoggedTime {
	private int activity;
	private int user; 
	private double time;
	private Calendar date;
	
	/**
	 * Create a new time log object
	 * @param activity 
	 * @param user
	 * @param time
	 * @param date
	 */
	public LoggedTime(int activity, int user, double time, Calendar date) {
		this.activity = activity;
		this.user = user;
		this.time = time;
		this.date = date;
	}
	
	public int getActivityID() {
		return this.activity;
	}
	
	public int getUserID() {
		return this.user;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public Calendar getDate() {
		return this.date;
	}
}
