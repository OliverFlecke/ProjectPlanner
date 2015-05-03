package projectPlanner;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;

import projectPlanner.view.projectPanel.PrintErrorDialog;

public class ProjectReport {
	private Project project;
	//int that moves spacing down page
	int spacing = 700;
	int normal = 110;
	int indent = 120;

	public ProjectReport(Project project){
		this.project = project;
	}

	public void print(String path) throws IOException, COSVisitorException, SQLException{
		//formatter for gregorian calender to simple date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMMMM-yyyy");

		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );

		// Create a new font object selecting one of the PDF base fonts
		PDFont fontPlain = PDType1Font.HELVETICA;
		PDFont fontBold = PDType1Font.HELVETICA_BOLD;
		PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
		PDFont fontMono = PDType1Font.COURIER;
		
		//For some obscure reason we have to create the image before the pdpagecontentstream
		String imgpath = "src/projectPlanner/images/gates.jpg";
		File file = new File(imgpath);
		InputStream in = new FileInputStream(file);
		PDJpeg img = new PDJpeg(document, in);

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Name and ID
		contentStream.beginText();
		contentStream.setFont( fontBold, 18 );
		contentStream.moveTextPositionByAmount( normal, spacing );
		contentStream.drawString("Project:  " + project.getTitle() + "    ID:  " + project.getID());
		contentStream.endText();
		spacing -= 20;
		
		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Projekt leader: " + project.getProjectLeader().getFirstname() + " " + project.getProjectLeader().getLastname());
		contentStream.endText();
		spacing -= 20;

		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Total alloted man-hours: " + project.getAllottedTime() + " Hours");
		contentStream.endText();
		spacing -= 20;

		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Total man-hours spent so far: " + project.getSpentTime() + " Hours");
		contentStream.endText();
		spacing -= 20;

		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Start date: " + formatter.format(project.getStartDate().getTime()));
		contentStream.endText();
		spacing -= 20;

		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Deadline: " + formatter.format(project.getEndDate().getTime()));
		contentStream.endText();
		spacing -= 30;

		contentStream.beginText();
		contentStream.setFont( fontBold, 18 );
		contentStream.moveTextPositionByAmount( normal, spacing );
		contentStream.drawString("Distribution of man-hours across activities");
		contentStream.endText();
		spacing -= 20;
		System.out.println(spacing);
		// TODO remove outer for-loop it is for mock purposes only!
		//the following loops through activitites creating extra pages as needed
		for(int i=0; i<70; i++){
		for(Activity current : project.getActivities()){
			contentStream.beginText();
			contentStream.setFont( fontPlain, 9 );
			contentStream.moveTextPositionByAmount( indent, spacing );
			contentStream.drawString("Activity: " + current.getTitle() + "   Time spent: " + current.getTimeAccumulated() + " Hours" );
			contentStream.endText();
			if(!(spacing>48)){
				contentStream.close();
				PDPage loopPage = new PDPage();
				document.addPage( loopPage );
				contentStream = new PDPageContentStream(document, loopPage);
				spacing = 700;
				contentStream.beginText();
				contentStream.setFont( fontBold, 18 );
				contentStream.moveTextPositionByAmount( normal, spacing );
				contentStream.drawString("Activities continued");
				contentStream.endText();
			}
			spacing-=16;
		}
		System.out.println(spacing);
		}
		contentStream.close();
		
		//adding page for image
		PDPage imagePage = new PDPage();
		document.addPage( imagePage );
		contentStream = new PDPageContentStream(document, imagePage);
		//draw image
		contentStream.drawXObject(img, 115, 400, img.getWidth() / 2, img.getHeight() / 2);

		contentStream.close();

		// Save the results and ensure that the document is properly closed:
		document.save(path);
		document.close();
		
		//open the file after saving
		if (Desktop.isDesktopSupported()) {
		    try {
		        File f = new File(path);
		        Desktop.getDesktop().open(f);
		    } catch (IOException ex) {
		        new PrintErrorDialog("You don't have a PDF-Reader installed");
		    }
		}
	}
}