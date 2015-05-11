package projectPlanner.view.projectTab;

import java.io.IOException;
import java.sql.SQLException;

import projectPlanner.Project;

public class PDFTester {
	private Project mockProject;
	public static void main(String[] args){
		@SuppressWarnings("unused")
		PDFTester test = new PDFTester();
	}
	public PDFTester(){
		mockProject = currentProject();
		try {
			mockProject.printProjectReport("C:\\Users\\Sebastian\\Documents\\ole.pdf");
			System.out.println("all done");
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private Project currentProject() {
		try {
			Project mockProject = Project.getProject(1); 
			return mockProject;
		} catch (SQLException e) {
			System.out.println("Can\'t reach server");
		}
		return null;
		
	}

}
