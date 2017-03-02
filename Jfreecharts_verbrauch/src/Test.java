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
		ArrayList<Verbrauch> verbraeucheMon;
		ArrayList<Double> verbraeucheMonth;
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		ArrayList<Double> startvalues = new ArrayList<Double>();
		ArrayList<Double> endvalues = new ArrayList<Double>();
		ArrayList<Double> avgvalues = new ArrayList<Double>();
		ArrayList<Double> verbrauchabsolut = new ArrayList<Double>();
		double avg = 0;

		//		ArrayList<Verbrauch> verbraeucheFeb;
		//		double pitchFeb;
		//		ArrayList<Verbrauch> verbraeucheMar;
		//		double pitchMar;
		//		ArrayList<Verbrauch> verbraeucheApr;
		//		double pitchApr;
		//		ArrayList<Verbrauch> verbraeucheMay;
		//		double pitchMay;
		//		ArrayList<Verbrauch> verbraeucheJun;
		//		double pitchJun;
		//		ArrayList<Verbrauch> verbraeucheJul;
		//		double pitchJul;
		//		ArrayList<Verbrauch> verbraeucheAug;
		//		double pitchAug;
		//		ArrayList<Verbrauch> verbraeucheSep;
		//		double pitchSep;
		//		ArrayList<Verbrauch> verbraeucheOct;
		//		double pitchOct;
		//		ArrayList<Verbrauch> verbraeucheNov;
		//		double pitchNov;
		//		ArrayList<Verbrauch> verbraeucheDec;
		//		double pitchDec;

		verbraeuche = dbManager.readData("wasser_verbrauch");

		for(int i = 1; i<13; i++){

			verbraeucheMon = Calculator.getDaysOfMonth(i, verbraeuche,116); //116 = 2016

			//System.out.println(verbraeucheMon);


			parameters.add(Calculator.calculatePitch(verbraeucheMon));		

			//System.out.println(parameters.get(i-1));

			//Calculator.calculateValueAtPosition(xposition, parameters.get(i-1).getPitch(), parameters.get(i-1).getD());

			//						System.out.println(Calculator.calculateDate(1427068800));
			//						System.out.println(Calculator.calculateUTST("Jun 13 2003 23:11:52 UTC"));

		}



		//		startvalues.add(Calculator.calculateValueAtPosition("01 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("01 31 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("02 01 2016 00:00:01 UTC", parameters.get(1).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("02 28 2016 23:59:59 UTC", parameters.get(1).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("03 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("03 31 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("04 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("04 30 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("05 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("05 31 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("06 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("06 30 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("07 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("07 31 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("08 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("08 31 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("09 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("09 30 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("10 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("10 31 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("11 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("11 30 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));
		//		startvalues.add(Calculator.calculateValueAtPosition("12 01 2016 00:00:01 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));	
		//		endvalues.add(Calculator.calculateValueAtPosition("12 31 2016 23:59:59 UTC", parameters.get(0).getPitch(), parameters.get(0).getD()));

		for(int i = 0; i<12; i++){

			if((((i%2) == 0) && i<7) || i==7){

				startvalues.add(Calculator.calculateValueAtPosition("0" + (i+1) + " 01 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));	
				endvalues.add(Calculator.calculateValueAtPosition("0" + (i+1) + " 31 2016 23:59:59 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));
				//System.out.println("31");
			}

			else if(((i%2) != 0) && i>7 && i<10){

				startvalues.add(Calculator.calculateValueAtPosition("0" + (i+1) + " 01 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));	
				endvalues.add(Calculator.calculateValueAtPosition("0" + (i+1) + " 31 2016 23:59:59 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));

				//System.out.println("31");
			}

			else if(((i%2) != 0) && i>10){

				startvalues.add(Calculator.calculateValueAtPosition("" + (i+1) + " 01 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));	
				endvalues.add(Calculator.calculateValueAtPosition("" + (i+1) + " 31 2016 23:59:59 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));

				//System.out.println("31");
			}

			else if(i==1){

				startvalues.add(Calculator.calculateValueAtPosition("0" + (i+1) + " 01 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));	
				endvalues.add(Calculator.calculateValueAtPosition("0" + (i+1) + " 28 2016 23:59:59 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));
				//System.out.println("28");
			}

			else{

				if(i>9){

					startvalues.add(Calculator.calculateValueAtPosition("0" + (i+1) + " 01 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));	
					endvalues.add(Calculator.calculateValueAtPosition("0" + (i+1) + " 30 2016 23:59:59 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));
					//System.out.println("30");
				}

				else{

					startvalues.add(Calculator.calculateValueAtPosition("" + (i+1) + " 01 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));	
					endvalues.add(Calculator.calculateValueAtPosition("" + (i+1) + " 30 2016 23:59:59 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));
					//System.out.println("30");

				}

			}


		}

		for(int i = 0; i<startvalues.size(); i++){

			if(i<10){
			
			avg = (Calculator.calculateValueAtPosition("0" + (i+1) + " 15 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()))-(Calculator.calculateValueAtPosition("0" + (i+1) + " 14 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));
			
			}
			
			else{
				
				avg = (Calculator.calculateValueAtPosition("" + (i+1) + " 15 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()))-(Calculator.calculateValueAtPosition("" + (i+1) + " 14 2016 00:00:01 UTC", parameters.get(i).getPitch(), parameters.get(i).getD()));

			}
			
			avgvalues.add(avg);
			verbrauchabsolut.add((endvalues.get(i)-startvalues.get(i)));
			//System.out.println(verbrauchabsolut.get(i));
			
		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

		for(int i = 0; i<verbrauchabsolut.size(); i++){

			if(verbrauchabsolut.get(i)!=-0.0){

				dataset.addValue( verbrauchabsolut.get(i) , Month.of(i+1) , "");

			}

		}

		for(int i = 0; i<avgvalues.size(); i++){

			if(avgvalues.get(i)!=-0.0){

				dataset2.addValue( avgvalues.get(i) , Month.of(i+1) , "");

			}

		}
		
		JFreeChart barChart = ChartFactory.createBarChart(
				"absoluter Verbrauch pro Monat", 
				"Monat", "Verbrauch", 
				dataset,PlotOrientation.VERTICAL, 
				true, true, false);

		int width = 640; /* Width of the image */
		int height = 480; /* Height of the image */ 
		File BarChart = new File( "BarChart.png" ); 
		ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
		
		JFreeChart barChart2 = ChartFactory.createBarChart(
				"durchschnittlicher Tagesverbrauch pro Monat", 
				"Monat", "Verbrauch", 
				dataset2,PlotOrientation.VERTICAL, 
				true, true, false);

		File BarChart2 = new File( "BarChart2.png" ); 
		ChartUtilities.saveChartAsJPEG( BarChart2 , barChart2 , width , height );

	}

}
