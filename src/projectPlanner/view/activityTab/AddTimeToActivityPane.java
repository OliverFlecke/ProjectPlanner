package projectPlanner.view.activityTab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectPlanner.*;
import projectPlanner.users.User;
import projectPlanner.view.activityPanel.TextPanel;

public class AddTimeToActivityPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7624424829368549942L;
	private JLabel activityTitle;
	private TextPanel hoursSpent;
	private TextPanel projectName;
	private String activityName;
	private CounterPanel addTimePanel;
	private JButton addTimeBtn;
	private Activity activity;
	
	

	
	public AddTimeToActivityPane(Activity activity) throws Exception{
		
		activityName = activity.getTitle();
		this.activity = activity;
		
		
		addTimePanel = new CounterPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		activityTitle = new JLabel(activityName);
		activityTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		projectName = new TextPanel("Project Name: ", activity.getAttachedProject().getTitle());
		hoursSpent = new TextPanel("Hours Spent:  ", String.valueOf(User.getTimeSpendOnActivity(ProjectPlanner.getCurrentUser(), activity)));
		
		//Setting up add timeBtn
		addTimeBtn = new JButton("Add Time");
		addTimeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				activity.addAccumulatedHours(addTimePanel.getCount());
				
				
			}
			
			
		});
		
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	
		//Adding elements to the JPanel 
		this.add(activityTitle);
		this.add(projectName);
		this.add(hoursSpent);
		this.add(addTimePanel);
		this.add(addTimeBtn);
		
		
		// centering the elements.
		activityTitle.setAlignmentX(CENTER_ALIGNMENT);
		
	}

}
