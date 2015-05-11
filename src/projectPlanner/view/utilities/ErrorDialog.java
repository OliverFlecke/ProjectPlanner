package projectPlanner.view.utilities;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5817236608739810959L;

	public ErrorDialog(String s){
		JOptionPane.showMessageDialog(new JFrame(), s, "An error occured", JOptionPane.ERROR_MESSAGE);
	}

}
