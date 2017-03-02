import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Month;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {

		DBManager dbManager = new DBManager();
		ArrayList<Verbrauch> verbraeuche;
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		ArrayList<Double> startvalues = new ArrayList<Double>();
		ArrayList<Double> endvalues = new ArrayList<Double>();
		ArrayList<Double> avgvalues = new ArrayList<Double>();
		ArrayList<Double> verbrauchabsolut = new ArrayList<Double>();

		verbraeuche = dbManager.readData("strom_verbrauch" , Calculator.calculateUTST("01 01 2016 00:00:00 UTC"));

		for(int i = 0; i<(verbraeuche.size()-1); i++){
			
			//System.out.println(verbraeuche.get(i).toString() + " -- " + verbraeuche.get(i+1).toString());
			
			parameters.add(Calculator.calculatePitch(verbraeuche.get(i), verbraeuche.get(i+1)));
			
			//System.out.println(parameters.get(i).toString());

		}
		
		for(int i = 0; i<12; i++){

			if((((i%2) == 0) && i<7) || i==7){

				startvalues.add(Calculator.calculateValueAtPosition("01 0" + (i+1) + " 2016 00:00:00 UTC", parameters));	
				endvalues.add(Calculator.calculateValueAtPosition("31 0" + (i+1) + " 2016 23:59:59 UTC", parameters));
				//System.out.println("31");
			}

			else if(((i%2) != 0) && i>7 && i<10){

				startvalues.add(Calculator.calculateValueAtPosition("01 0" + (i+1) + " 2016 00:00:00 UTC", parameters));	
				endvalues.add(Calculator.calculateValueAtPosition("31 0" + (i+1) + " 2016 23:59:59 UTC", parameters));

				//System.out.println("31");
			}

			else if(((i%2) != 0) && i>10){

				startvalues.add(Calculator.calculateValueAtPosition("01 " + (i+1) + " 2016 00:00:00 UTC", parameters));	
				endvalues.add(Calculator.calculateValueAtPosition("31 " + (i+1) + " 2016 23:59:59 UTC", parameters));

				//System.out.println("31");
			}

			else if(i==1){

				startvalues.add(Calculator.calculateValueAtPosition("01 " + (i+1) + " 2016 00:00:00 UTC", parameters));	
				endvalues.add(Calculator.calculateValueAtPosition("28 0" + (i+1) + " 2016 23:59:59 UTC", parameters));
				//System.out.println("28");
			}

			else{

				if(i>9){

					startvalues.add(Calculator.calculateValueAtPosition("01 0" + (i+1) + " 2016 00:00:00 UTC", parameters));	
					endvalues.add(Calculator.calculateValueAtPosition("30 0" + (i+1) + " 2016 23:59:59 UTC", parameters));
					//System.out.println("30");
				}

				else{

					startvalues.add(Calculator.calculateValueAtPosition("01 " + (i+1) + " 2016 00:00:00 UTC", parameters));	
					endvalues.add(Calculator.calculateValueAtPosition("30 " + (i+1) + " 2016 23:59:59 UTC", parameters));
					//System.out.println("30");

				}

			}

		}
		
	/*	System.out.println(Calculator.calculateUTST("01 01 2016 00:00:01 UTC"));
		System.out.println(Calculator.calculateUTST("31 01 2016 23:59:59 UTC"));
		System.out.println(Calculator.calculateUTST("01 02 2016 00:00:01 UTC"));
		System.out.println(Calculator.calculateUTST("28 02 2016 23:59:59 UTC"));
		System.out.println(Calculator.calculateUTST("01 03 2016 00:00:01 UTC"));
		System.out.println(Calculator.calculateUTST("31 03 2016 23:59:59 UTC"));
		
		startvalues.add(Calculator.calculateValueAtPosition("01 01 2016 00:00:01 UTC", parameters));	
		endvalues.add(Calculator.calculateValueAtPosition("01 31 2016 23:59:59 UTC", parameters));
		
		startvalues.add(Calculator.calculateValueAtPosition("03 01 2016 00:00:01 UTC", parameters));	
		endvalues.add(Calculator.calculateValueAtPosition("03 31 2016 23:59:59 UTC", parameters));
		
		startvalues.add(Calculator.calculateValueAtPosition("02 01 2016 00:00:01 UTC", parameters));	
		endvalues.add(Calculator.calculateValueAtPosition("28 31 2016 23:59:59 UTC", parameters));

		*/
//		for(int i = 0; i<startvalues.size(); i++){
//			
//			verbrauchabsolut.add(endvalues.get(i) - startvalues.get(i));
//			avgvalues.add((endvalues.get(i)+startvalues.get(i)) / 2);
//			
//		}
//		
//		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
//		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
//
//		for(int i = 0; i<verbrauchabsolut.size(); i++){
//
//				dataset.addValue(verbrauchabsolut.get(i) , Month.of(i+1) , "");
//
//		}
//
//		for(int i = 0; i<avgvalues.size(); i++){
//
//				dataset2.addValue( avgvalues.get(i) , Month.of(i+1) , "");
//		}
//		
//		
//		JFreeChart barChart = ChartFactory.createBarChart(
//				"absoluter Verbrauch pro Monat", 
//				"Monat", "Verbrauch", 
//				dataset,PlotOrientation.VERTICAL, 
//				true, true, false);
//
//		int width = 640; /* Width of the image */
//		int height = 480; /* Height of the image */ 
//		File BarChart = new File( "BarChart.png" ); 
//		ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
//		
//		JFreeChart barChart2 = ChartFactory.createBarChart(
//				"durchschnittlicher Tagesverbrauch pro Monat", 
//				"Monat", "Verbrauch", 
//				dataset2,PlotOrientation.VERTICAL, 
//				true, true, false);
//
//		File BarChart2 = new File( "BarChart2.png" ); 
//		ChartUtilities.saveChartAsJPEG( BarChart2 , barChart2 , width , height );
//		
	}

}
