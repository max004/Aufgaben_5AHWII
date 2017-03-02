import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileReader {
	
	private ArrayList<String> zeilen = new ArrayList<String>();
	
	public ArrayList<String> readdata(String location) throws FileNotFoundException{
	
	Scanner scanner = new Scanner(new File(location));
	
	while(scanner.hasNextLine()){
		
		zeilen.add(scanner.nextLine()); 
		
	}
	
	return zeilen;

	}
}
