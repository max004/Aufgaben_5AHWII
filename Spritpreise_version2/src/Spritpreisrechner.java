import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;


public class Spritpreisrechner {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {

		DBManager dbManager = new DBManager();
		ArrayList<Long> days;
		ArrayList<Long> lastDayHours;
		ArrayList<Spritpreis> avgPrices;
		ArrayList<Spritpreis> minPrices;
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries series1;
		final DefaultCategoryDataset datasetBar = new DefaultCategoryDataset( );

		Spritpreisrechner spritpreisrechner = new Spritpreisrechner();

		days = spritpreisrechner.calculateDays(dbManager);
		lastDayHours = spritpreisrechner.calculateOneDay(dbManager);

		avgPrices = dbManager.getAvgSprit(days);
		minPrices = dbManager.getMinSprit(lastDayHours);

		for(Spritpreis a : minPrices){

			System.out.println(a.toString());

		}

		for(int j = 1; j<dbManager.getAnzTankstellen(); j++){

			series1 = new TimeSeries(dbManager.getTankstellenName(j));

			for(int i = 0; i < avgPrices.size(); i++){

				if(avgPrices.get(i).tankstelle == j){
					
					Date date = new Date(avgPrices.get(i).getUtx()*1000);

					series1.add(new Day(date), avgPrices.get(i).getPreis());

				}

			}

			dataset.addSeries(series1);

		}

		for(int i = 0;i<minPrices.size(); i++){

			Time t = new Time(minPrices.get(i).getUtx()*1000);
			datasetBar.addValue(minPrices.get(i).getPreis() , dbManager.getTankstellenName(minPrices.get(i).getTankstelle()) , ""+t);
			
		}

		final JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"Spritpreise im Vergleich",      // chart title
				"Zeit [UTX]",                      // x axis label
				"Preis [�/L]",                      // y axis label
				dataset,                  // data
				true,                     // include legend
				true,                     // tooltips
				false                     // urls
				);

		XYPlot plot = chart.getXYPlot();
		plot.getRangeAxis().setLowerBound(1.155);
		plot.getRangeAxis().setUpperBound(1.255);
		
		StandardXYItemRenderer renderer1 = new StandardXYItemRenderer();
	    renderer1.setSeriesPaint(0, Color.BLUE);
	    renderer1.setSeriesPaint(1, Color.RED);
	    renderer1.setSeriesPaint(2, Color.CYAN);
	    renderer1.setSeriesPaint(3, Color.GREEN);
	    renderer1.setSeriesPaint(1, Color.BLACK);
	    
	    plot.setRenderer(renderer1);

		int width = 1400; /* Width of the image */
		int height = 900; /* Height of the image */ 
		File lineChart = new File( "LineChart.jpeg" ); 
		ChartUtilities.saveChartAsPNG(lineChart ,chart, width ,height);

		JFreeChart barChart = ChartFactory.createBarChart(
				"Guenstigste Spritpreise der letzten 24h", 
				"Stunde", "Spritpreis", 
				datasetBar,PlotOrientation.VERTICAL, 
				true, true, false);
		
		CategoryPlot plotbar = barChart.getCategoryPlot();
		plotbar.getRangeAxis().setLowerBound(1.15);
		plotbar.getRangeAxis().setUpperBound(1.2);
		
		StackedBarRenderer renderer = new StackedBarRenderer(false);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,TextAnchor.TOP_CENTER  ));
		plotbar.setRenderer(renderer);

		File BarChart = new File( "BarChart.jpeg" ); 
		ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );

	}

	public ArrayList<Long> calculateDays(DBManager dbManager) throws SQLException, ParseException{

		long lastDateUTX = dbManager.getLastDate();
		long firstDateUTX = lastDateUTX - (365 * 86400);		
		ArrayList<Long> days = new ArrayList<Long>();

		for(int i = 0; i<365; i++){

			days.add(firstDateUTX + i*86400);

		}

		return days;

	}

	public ArrayList<Long> calculateOneDay(DBManager dbManager) throws SQLException, ParseException{

		long lastDateUTX = dbManager.getLastDate();
		long firstDateUTX = lastDateUTX - 86400;		
		ArrayList<Long> days = new ArrayList<Long>();

		for(int i = 0; i<25; i++){

			days.add(firstDateUTX + i*3600);

		}

		return days;

	}

	public Date getDate(long utx){

		Date date = new Date(utx * 1000);

		return date;

	}

}
