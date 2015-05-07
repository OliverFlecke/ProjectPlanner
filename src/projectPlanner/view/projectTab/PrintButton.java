package projectPlanner.view.projectTab;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PrintButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3817789821908511761L;

	public PrintButton(){
		super("Print report for selected  project");
		ImageIcon icon = new ImageIcon(getClass().getResource("images/print_pdf.png"));
		this.setIcon(icon);
	}

}
