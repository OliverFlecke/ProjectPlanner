package projectPlanner;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.exceptions.COSVisitorException;

public class ProjectReportDriver {
	public static void main(String[] args){
		System.out.println("Program start");
		try {
			Project mockProject = Project.getProject(1); 
			System.out.println("got project");
			ProjectReport projectReport = new ProjectReport(mockProject);
				projectReport.print(choosePath());
		} catch (SQLException e) {
			System.out.println("Can\'t reach server");
		} catch (IOException e) {
			System.out.println("IO fejl");
		} catch (COSVisitorException e) {
			System.out.println("Skrivning på samme tid");
		} catch (UserClosedWindow e) {
			System.out.println("bruger lukkede vindue");
		}
	}

	public static String choosePath() throws UserClosedWindow{
		JFileChooser chooser = new JFileChooser();
		JFrame jframe = new JFrame();
		int returnVal = chooser.showSaveDialog(jframe);
		
		try{
		String path = chooser.getSelectedFile().getAbsolutePath();
		
		if(!path.endsWith(".pdf") ) {
			path = path + ".pdf";
		}		
		return path;}catch(NullPointerException e){
			throw new UserClosedWindow("The user closed the save window");
		}
	}

}
