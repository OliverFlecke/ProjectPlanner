package projectPlanner.view.projectPanel;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projectPlanner.view.adminTab.TextNDate;
import projectPlanner.view.adminTab.TextNField;

public class ManageActivityPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 894111141608012138L;

	public ManageActivityPanel(ListPanel listPanel){
		//set layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//add header
		JLabel projectHeader = new JLabel("Manage the selected activity:" + listPanel.getCurrentSelectedActivity());
		Font font = projectHeader.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+3);
		projectHeader.setFont(boldFont);
		add(projectHeader);

		//add input fields
		TextNField name = new TextNField("Name");
		name.setTxt(listPanel.getCurrentSelectedActivity().getTitle());
		name.getTxtField().setCaretPosition(0);
		add(name);
		
		//allotted time field
		TextNField alottedTime = new TextNField("Alotted Man-Hours");
		alottedTime.setTxt("Mangler function");
		add(alottedTime);
		
		//accumulated time
		TextNField accumTime = new TextNField("Accumulated Man-Hours");
		accumTime.setTxt(Double.toString(listPanel.getCurrentSelectedActivity().getTimeAccumulated()));
		add(accumTime);
		
		//start date
		TextNDate startDate = new TextNDate("Start Date: mangler");
		add(startDate);
		
		//end date
		TextNDate endDate = new TextNDate("End Date: mangler");
		add(endDate);
		
		//remove current employee list
		
		//add new employee list
		
		//submit changes button
		JButton createActivity = new JButton("Submit changes");
		add(createActivity);

		//Listener for changes in project selection for refreshing activities
		listPanel.getSelectActivityList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (lse.getValueIsAdjusting()){
					return;
				}
				projectHeader.setText("Manage the selected activity: " + listPanel.getCurrentSelectedActivity());
				name.setTxt(listPanel.getCurrentSelectedActivity().getTitle());
				name.getTxtField().setCaretPosition(0);
			}
		});

	}

}
