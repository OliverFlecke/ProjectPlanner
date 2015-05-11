package projectPlanner.view.utilities;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CounterPanel extends JPanel{
	
	private JButton lessBtn;
	private JButton moreBtn;
	private JTextField counterFld;
	private double counter;



	public CounterPanel()  {
		
		//initialization
		this.counter = 0.0;
		
		//fld formatting
		counterFld = new JTextField(5);
		counterFld.setEditable(false);
		counterFld.setHorizontalAlignment(JTextField.CENTER);
		
		//btn formatting
		lessBtn = new JButton("<<<");
		lessBtn.setPreferredSize(new Dimension(75,25));
		
		moreBtn = new JButton(">>>");
		moreBtn.setPreferredSize(new Dimension(75,25));
		

		
		//Add listeners to btns
		lessBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (counter >= 0.5){
					counter = counter - 0.5;
				}
				setCounterFld();
				
			}
			
		});
		
		moreBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				counter = counter + 0.5;
				setCounterFld();
				
			}
			
		});
		
		
		//Layout
		this.setLayout(new FlowLayout());
		this.add(lessBtn);
		this.add(counterFld);
		this.add(moreBtn);
		
		
		setCounterFld();
		

		
		
	}
	
	public CounterPanel(double counter) {
		this();
		this.counter = counter;
		setCounterFld();
	}
	
	public double getCount() {
		return Double.parseDouble(counterFld.getText());
	}

	public void setCounterFld() {
		this.counterFld.setText(String.valueOf(counter));
	}

	public void setCounterFld(double value) {
		this.counter = value;
		this.counterFld.setText(String.valueOf(counter));
	}

}
