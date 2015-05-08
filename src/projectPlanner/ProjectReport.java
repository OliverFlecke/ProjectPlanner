package projectPlanner;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;

import projectPlanner.view.ErrorDialog;

public class ProjectReport {
	private BufferedImage bIMG;
	private Project project;
	//int that moves spacing down page
	int spacing = 700;
	//different levels of indentation
	int normal = 105;
	int indent = 120;

	public ProjectReport(Project project){
		this.project = project;
	}

	public void print(String path) throws IOException, SQLException {
		createBarChart();

		//formatter for gregorian calender to simple date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMMMM-yyyy");

		// Create a document and add a page
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );

		// Create fonts
		PDFont fontPlain = PDType1Font.HELVETICA;
		PDFont fontBold = PDType1Font.HELVETICA_BOLD;
		PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
		PDFont fontMono = PDType1Font.COURIER;

		// Start a new content stream 
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Name and ID
		contentStream.beginText();
		contentStream.setFont( fontBold, 18 );
		contentStream.moveTextPositionByAmount( normal, spacing );
		contentStream.drawString("Project:  " + project.getTitle() + "    ID:  " + project.getID());
		contentStream.endText();
		spacing -= 20;

		// Active status
		contentStream.beginText();
		contentStream.setFont( fontItalic, 12 );
		contentStream.moveTextPositionByAmount( normal, spacing );
		if(project.isActive()){
			contentStream.drawString("This project is active");
		}else{
			contentStream.drawString("This project is NOT active!");
		}

		contentStream.endText();
		spacing -= 20;

		//project leader
		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Projekt leader: " + project.getProjectLeader().getFirstname() + " " + project.getProjectLeader().getLastname());
		contentStream.endText();
		spacing -= 20;

		//alloted man hours
		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Total alloted man-hours: " + project.getAllottedTime() + " Hours");
		contentStream.endText();
		spacing -= 20;

		//spent man hours
		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Total man-hours spent so far: " + project.getSpentTime() + " Hours");
		contentStream.endText();
		spacing -= 20;

		// start date
		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Start date: " + formatter.format(project.getStartDate().getTime()));
		contentStream.endText();
		spacing -= 20;

		//deadline
		contentStream.beginText();
		contentStream.setFont( fontMono, 9 );
		contentStream.moveTextPositionByAmount( indent, spacing );
		contentStream.drawString("Deadline: " + formatter.format(project.getEndDate().getTime()));
		contentStream.endText();
		spacing -= 30;

		//activities header
		contentStream.beginText();
		contentStream.setFont( fontBold, 18 );
		contentStream.moveTextPositionByAmount( normal, spacing );
		contentStream.drawString("Distribution of man-hours across activities");
		contentStream.endText();
		spacing -= 20;

		//the following loops through activitites creating extra pages as needed
		for(Activity current : project.getActivities()){
			contentStream.beginText();
			contentStream.setFont( fontPlain, 9 );
			contentStream.moveTextPositionByAmount( indent, spacing );
			System.out.println(current.getTitle());
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

		contentStream.close();

		//adding page for image
		PDPage imagePage = new PDPage();
		document.addPage( imagePage );
		contentStream = new PDPageContentStream(document, imagePage);

		//draw image
		PDPixelMap xIMG = new PDPixelMap(document, bIMG);
		contentStream.drawXObject(xIMG, 20, 300, xIMG.getWidth() / 3, xIMG.getHeight() / 3);
		contentStream.close();

		// save and ensure that the document is closed:
		try {
			document.save(path);
		} catch (COSVisitorException e) {
			// Do nothing
		}
		document.close();

		//open the file after saving
		if (Desktop.isDesktopSupported()) {
			try {
				File f = new File(path);
				Desktop.getDesktop().open(f);
			} catch (IOException ex) {
				new ErrorDialog("You don't have a PDF-Reader installed");
			}
		}
	}

	private void createBarChart() throws IOException, SQLException {
		BarChartGenerator bc = new BarChartGenerator(project);
		bIMG = bc.getgraph();
	}
}