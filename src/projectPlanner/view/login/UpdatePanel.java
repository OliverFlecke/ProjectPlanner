package projectPlanner.view.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UpdatePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7009252657272574378L;
	private JLabel statusUpdateLbl;
	private JLabel statusLbl;
	private JLabel marginLbl;
	
	public UpdatePanel() {		
		statusLbl = new JLabel(" ",SwingConstants.CENTER);
		statusLbl.setForeground(Color.red);
		Font font = statusLbl.getFont();
		// same font but bold
		Font boldFont = new Font(font.getFontName(), Font.BOLD, 14);
		statusLbl.setFont(boldFont);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(statusLbl);
		this.add(marginLbl = new JLabel(" "));
	}
	
	public void updateMessage(String string){
		statusLbl.setText(string);
	}

	public JLabel getStatusLbl() {
		return statusLbl;
	}
	public void setStatusColor(Color color){
		statusLbl.setForeground(color);
	}

}
