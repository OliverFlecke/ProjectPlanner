package projectPlanner.view.projectPanel;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.pdfbox.exceptions.COSVisitorException;

import projectPlanner.Project;

public class PDFTester {
	private Project mockProject;
	public static void main(String[] args){
		PDFTester test = new PDFTester();
	}
	public PDFTester(){
		mockProject = currentProject();
		try {
			mockProject.printProjectReport("C:\\Users\\Sebastian\\Documents\\ole.pdf");
		} catch (COSVisitorException | IOException | SQLException e) {
			System.out.println("shit");
			e.printStackTrace();
		}
	}
	
	
	
	private Project currentProject() {
		// TODO Get currently user selected project, this is just mock
		try {
			Project mockProject = Project.getProject(1); 
			return mockProject;
		} catch (SQLException e) {
			System.out.println("Can\'t reach server");
		}
		return null;
		
	}

}
