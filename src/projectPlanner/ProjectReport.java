package projectPlanner;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ProjectReport {
	private Project project;

	public ProjectReport(Project project){
		this.project = project;
	}

	public void print(String path) throws IOException, COSVisitorException, SQLException{
		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );

		// Create a new font object selecting one of the PDF base fonts
		PDFont fontPlain = PDType1Font.HELVETICA;
		PDFont fontBold = PDType1Font.HELVETICA_BOLD;
		PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
		PDFont fontMono = PDType1Font.COURIER;

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Define a text content stream using the selected font, moving the cursor and drawing the text
		contentStream.beginText();
		contentStream.setFont( fontBold, 20 );
		contentStream.moveTextPositionByAmount( 100, 700 );
		contentStream.drawString( project.getTitle() );
		contentStream.endText();
		
		for(Activity current : project.getActivities()){
		contentStream.beginText();
		contentStream.setFont( fontPlain, 12 );
		contentStream.moveTextPositionByAmount( 100, 700 );
		contentStream.drawString( project.getTitle() );
		contentStream.endText();
		}
		// Make sure that the content stream is closed:
		contentStream.close();

		// Save the results and ensure that the document is properly closed:
		document.save(path);
		document.close();
	}

}