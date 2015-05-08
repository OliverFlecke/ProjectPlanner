/**
 * 
 */
package projectPlanner;

import java.util.Calendar;
 
import projectPlanner.users.*;

/**
 * Represent a time spend a user has spend on a given activity.
 * Only stores the reference for the user and the activity
 * @author Oliver Fleckenstein
 *
 */
public class LoggedTime {
	// Actual reference to the user and activity objects
	private User user;
	private Activity activity;
			
	// Fields to just hold the IDs of the activity and user
	private int activityID;
	private int userID; 
	
	// Hold the date and the time to log
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
		this.activityID = activity;
		this.userID = user;
		this.time = time;
		this.date = date;
	}
	
	/**
	 * Create a new logged time object with a pointer to both the activity and the user
	 * @param activity to log time on
	 * @param user which should log the time
	 * @param time Amount of time to log
	 * @param date to log the time on
	 */
	public LoggedTime(Activity activity, User user, double time, Calendar date) {
		this(activity.getID(), user.getID(), time, date);
		this.activity = activity; 
		this.user = user;
	}
	
	/**
	 * @return The activity ID
	 */
	public int getActivityID() {
		return this.activityID;
	}
	
	/**
	 * @return The ID of the user
	 */
	public int getUserID() {
		return this.userID;
	}
	
	/**
	 * @return The activity linked to this Logged time
	 */
	public Activity getActivity() {
		return this.activity;
	}
	
	/**
	 * @return The user of this logged time object
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * @return The time to log
	 */
	public double getTime() {
		return this.time;
	}
	
	/**
	 * @return The date which the time get logged. 
	 */
	public Calendar getDate() {
		return this.date;
	}
}
