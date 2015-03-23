package projectPlanner;

import projectPlanner.users.*;

/**
 * Exception to handel action, which is not allowed by the current employee level
 */
public class ActionNotAllowedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7241905466219231765L;

	// Fields to hold exceptiond data
	private User user;
	private String message;
	
	/**
	 * Create an exception with a message and a user
	 * @param message Error message
	 * @param user affeted by the exception
	 */
	public ActionNotAllowedException(String message, User user) {
		this.message = message;
		this.user = user;
	}
	
	/**
	 * @return Get the relevant user
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * @return The exception message
	 */
	public String getMessage() {
		return this.message;
	}
}
