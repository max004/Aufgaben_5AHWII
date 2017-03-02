import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class Calculator {

	static ArrayList<Verbrauch> getDaysOfMonth(int month, ArrayList<Verbrauch> verbraeuche, int year){

		int[] utst;
		ArrayList<Verbrauch> verbraeucheMonth = new ArrayList<Verbrauch>();

		for(int i=0; i<verbraeuche.size(); i++){

			Date date = calculateDate(verbraeuche.get(i).getUtst());

			if(date.getMonth() == month && date.getYear()==year){

				verbraeucheMonth.add(verbraeuche.get(i));

			}

		}	

		if(verbraeucheMonth.size() == 0){
			return null;
		}

		return verbraeucheMonth;

	}

	static Date calculateDate(long utst){

		Date date = new java.util.Date((long)utst*1000);

		return date;

	}
	
	static long calculateUTST(String dateString) throws ParseException{

		SimpleDateFormat dateFormat = new SimpleDateFormat("mm dd yyyy HH:mm:ss zzz");
		    Date date = dateFormat.parse(dateString);
		    long unixTime = (long) date.getTime()/1000;
		    //System.out.println("--- " + unixTime );//<- prints 1352504418
		    
		    return unixTime;

	}

	static Parameter calculatePitch(Verbrauch start, Verbrauch end){

		long startx;
		long endx;
		double starty;
		double endy;
		double pitch = 0;
		double d;

			startx = start.getUtst();		
			endx = end.getUtst();

			starty = start.getVerbrauch();
			endy = end.getVerbrauch();

			pitch = (endy - starty)/(endx-startx);
			d = starty - (pitch * startx);

			return new Parameter(pitch,d, startx, endx);

	}
	
	static double calculateValueAtPosition(String xposition, ArrayList<Parameter> parameters) throws ParseException{
		
		double x = calculateUTST(xposition);
		
		System.out.println(x);
		
		int index = 0;
		
		for(int i = 0; i<parameters.size(); i++){
			
			if(x > parameters.get(i).getStartx() && x < parameters.get(i).getEndx()){
				
				index = i;
				
				System.out.println(index);
				
				break;
				
			}
			
		}
		
		return (parameters.get(index).getPitch() * x + parameters.get(index).getD());
		
	}
	
}
