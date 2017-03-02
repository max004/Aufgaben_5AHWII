import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
		    //System.out.println(unixTime );//<- prints 1352504418
		    
		    return unixTime;

	}

	static Parameter calculatePitch(ArrayList<Verbrauch> verbraeuchePerMonth){

		long startx;
		long endx;
		double starty;
		double endy;
		double pitch = 0;
		double d;

		if(verbraeuchePerMonth != null){



			for(int i = 0; i<verbraeuchePerMonth.size()-1; i++){

				for(int j = 1; j<verbraeuchePerMonth.size(); j++){

					if(verbraeuchePerMonth.get(i).getUtst() > verbraeuchePerMonth.get(j).getUtst()){

						Verbrauch v = verbraeuchePerMonth.get(i);
						verbraeuchePerMonth.get(i).isVerbrauch(verbraeuchePerMonth.get(j));
						verbraeuchePerMonth.get(j).isVerbrauch(v);

					}

				}

			}

			startx = verbraeuchePerMonth.get(0).getUtst();
			endx = verbraeuchePerMonth.get(verbraeuchePerMonth.size()-1).getUtst();

			starty = verbraeuchePerMonth.get(0).getVerbrauch();
			endy = verbraeuchePerMonth.get(verbraeuchePerMonth.size()-1).getVerbrauch();

			pitch = (endy - starty)/(endx-startx);
			d = starty - (pitch * startx);

			return new Parameter(pitch,d);

		}

		else return new Parameter(-0.0,-0.0);

	}
	
	static double calculateValueAtPosition(String xposition, double pitch, double d) throws ParseException{
		
		double x = calculateUTST(xposition);
		
		return (pitch * x+ d);
		
	}
	
	



}
