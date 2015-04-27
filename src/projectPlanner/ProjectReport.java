package projectPlanner;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class ProjectReport {
	
	public static void main(String[] args) throws COSVisitorException, IOException{
		System.out.println("Program start");
		ProjectReport projectReport = new ProjectReport();
		System.out.println("null");
		try {
			Project project = Project.getProject(1); 
			System.out.println("got project");
			projectReport.print(project);
		} catch (SQLException e) {
			System.out.println("Can\'t reach server");
		}
	}
	
	public void print(Project project) throws COSVisitorException, IOException {
		// Create a new empty document
		PDDocument document = new PDDocument();
		System.out.println("created document");
		// Create a new blank page and add it to the document
		PDPage blankPage = new PDPage();
		document.addPage( blankPage );
		
		document.save("Projectplanner.pdf");

		document.close();
		System.out.println("created document, bye");
		System.out.println("Done deal");
		System.exit(0);
	}
}