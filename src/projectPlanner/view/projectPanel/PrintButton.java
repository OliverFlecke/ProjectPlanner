package projectPlanner.view.projectPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PrintButton extends JButton{
	public PrintButton(){
		super("Print selected report as PDF");
		ImageIcon icon = new ImageIcon(getClass().getResource("images/print_pdf.png"));
		this.setIcon(icon);
	}

}
