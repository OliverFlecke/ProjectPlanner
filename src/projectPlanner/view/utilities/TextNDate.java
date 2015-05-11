package projectPlanner.view.utilities;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class TextNDate extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8620755133945416455L;
	//Create DatePicker
	private UtilDateModel dateModel;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	
	//Create Lbl
	private JLabel fieldLbl;
	
	public TextNDate() {
		
		fieldLbl = new JLabel("Date");
		
		dateModel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(dateModel, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		//setDate(Calendar.getInstance());
		
		this.setOpaque(false);
		this.setLayout(new FlowLayout());
		this.add(fieldLbl);
		this.add(datePicker);

		
	}
	
	public TextNDate(String lblName) {
		this();
		setLblName(lblName);
		
	}
	
	public TextNDate(String lblName, Calendar date){
		this(lblName);
		setDate(date);
	}
	
	public void setLblName(String lblName){
		fieldLbl.setText(lblName);
	}
	
	public Calendar getDate(){
		if(dateModel.getValue()==null){
			return null;
		}
		return DateToCalendar(dateModel.getValue());
	}
	
	public void setDate(Calendar date) {
		if(date==null){
			dateModel.setValue(null);
		}
		else{
		dateModel.setValue(date.getTime());
		}
//		Update to convert the calendar object to and date, in order to set the dateModel. 
//		The old way used static members on the calendar object, which would chose the current time, and not the
//		time saved in the date object
//		dateModel.setDate(date.YEAR, date.MONTH, date.DAY_OF_MONTH);
	}
	public  Calendar DateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}
}
