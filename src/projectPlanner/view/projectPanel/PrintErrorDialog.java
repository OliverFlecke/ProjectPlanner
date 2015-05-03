package projectPlanner.view.projectPanel;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PrintErrorDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5817236608739810959L;

	public PrintErrorDialog(String s){
		JOptionPane.showMessageDialog(new JFrame(), s, "Error saving project", JOptionPane.ERROR_MESSAGE);
	}

}
