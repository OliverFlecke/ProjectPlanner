package projectPlanner.view.activityTab;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActivityModificationPane extends JPanel {
	
	public ActivityModificationPane() {
		
		
		
	}
	
	public void removeAllObjects() {
		
	}
	
	public void repaintPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel newLbl = new JLabel("1");
		this.add(newLbl);
		this.revalidate();
		this.repaint();
	}

}
