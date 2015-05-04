package projectPlanner;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartGenerator{
	JFreeChart barChart;
	
	public BarChartGenerator(Project project) throws SQLException{

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		for(Activity current : project.getActivities()){
			dataset.addValue(current.getTimeAccumulated(), current.getTitle()," ");
		}

		barChart = ChartFactory.createBarChart(
				"DISTRIBUTION OF MAN-HOURS", 
				"Activities", "Man-hours", 
				dataset,PlotOrientation.VERTICAL, 
				true, true, false);
	}
	public BufferedImage getgraph() throws IOException{
		int width = 1700; 
		int height = 960;
        BufferedImage bufferedImage = barChart.createBufferedImage(width, height);
        return bufferedImage;
	}
}