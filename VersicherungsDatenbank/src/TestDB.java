import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class TestDB {

	private static int svnr;
	private static String geburtsdatum;
	private static String vorname;
	private static String nachname;
	private static String gruendungsdatum;
	private static String name;
	private static int vid;
	private static int kid;
	private static String gegenstand;
	private static String von;
	private static String bis;

	public static void main(String args[]) throws ClassNotFoundException, SQLException, FileNotFoundException{

		DBManager dbmanager = new DBManager();
		ArrayList<Kondition> konditionen; 
		ArrayList<Kunde> kunden; 
		ArrayList<Versicherung> versicherungen;
		ArrayList<Kunde_hat_Versicherung> kvs;
		Scanner s = new Scanner(System.in);
		String wahl, wh = "";
		ArrayList<String> zeilen;
		String[] substring;
		FileReader fr = new FileReader();

		//dbmanager.insertVersicherung("01-09-2006", "Versicherung_3");
		//dbmanager.insertKunde("01-01-2001", "Max_2", "Mustermann_2");
		//dbmanager.insertKondition(1, "Auto");
		//dbmanager.insertKondition(2, "Traktor");
		//dbmanager.deleteKunde(3);
		//dbmanager.insertVers_hat_Kunde(1, 3, "01-08-2009", "01-01-9999");
		//dbmanager.deleteVers_hat_Kunde(1, 2);
		//dbmanager.selectAllKunde_hat_Versicherung_Join();
		//dbmanager.deleteAll();




		do{

			System.out.println("\n");
			System.out.println("Was möchten Sie tun?");
			System.out.println("=========================");
			System.out.println("IKU...insert Kunde");
			System.out.println("IKO...insert Kondition");
			System.out.println("IV...insert Versicherung");
			System.out.println("IKV...insert Kunde hat Versicherung");
			System.out.println("AKU...Auto insert Kunde -> externe datei in Datenbank schreiben");
			System.out.println("AV...Auto insert Versicherung -> externe datei in Datenbank schreiben");
			System.out.println("AKO...Auto insert Kondition -> externe datei in Datenbank schreiben");
			System.out.println("AKUO...Auto insert Kunde_hat_Versicherung -> externe datei in Datenbank schreiben");
			System.out.println("DKU...delete Kunde");
			System.out.println("DKO...delete Kondition");
			System.out.println("DV...delete Versicherung");
			System.out.println("DKV...delete Kunde hat Versicherung");
			System.out.println("PKU...print all Kunden");
			System.out.println("PKO...print all Kondition");
			System.out.println("PV...print all Versicherungen");
			System.out.println("PKV...print all Kunde hat Versicherung");
			System.out.println("PKVJ...print all Kunde hat Versicherung JOIN");

			wahl = s.nextLine();
			wahl = wahl.toUpperCase();



			switch(wahl){

			case "IKU":

				System.out.println("Bitte geben Sie ihr Geburtsdatum ein:");
				geburtsdatum = s.nextLine();

				System.out.println("Bitte geben Sie ihren Vornamen ein:");
				vorname = s.nextLine();

				System.out.println("Bitte geben Sie ihren Nachnamen ein:");
				nachname = s.nextLine();


				dbmanager.insertKunde(geburtsdatum, vorname, nachname);

				break;


			case "IKO":

				System.out.println("Bitte geben Sie die Versicherungs ID ein:");
				vid = Integer.parseInt(s.nextLine());

				System.out.println("Bitte geben Sie den Gegenstand ein:");
				gegenstand = s.nextLine();

				dbmanager.insertKondition(vid, gegenstand);

				break;


			case "IV":

				System.out.println("Bitte geben Sie das Gründungsdatum ein ein:");
				gruendungsdatum = s.nextLine();

				System.out.println("Bitte geben Sie den Namen der Versicherung ein:");
				name = s.nextLine();

				dbmanager.insertVersicherung(gruendungsdatum, name);

				break;


			case "IKV":

				System.out.println("Bitte geben Sie die Versicherungs ID ein:");
				vid = Integer.parseInt(s.nextLine());

				System.out.println("Bitte geben Sie die SvNr ein");
				svnr = Integer.parseInt(s.nextLine());

				System.out.println("Bitte geben Sie das Anmeldedatum an");
				von = s.nextLine();

				System.out.println("Bitte geben Sie das Abmeldedatum an (01-01-9999 falls noch nicht abgemeldet) ");
				bis = s.nextLine();

				dbmanager.insertVers_hat_Kunde(vid, svnr, von, bis);

				break;


			case "AKU":

				zeilen = fr.readdata("D:/DATEN/Daten Max/HTL/INFI-DBP/kunde.txt");

				for(int i = 0; i<zeilen.size(); i++){

					substring = zeilen.get(i).split(",");

					dbmanager.insertKunde(substring[0], substring[1], substring[2]);

				}


				break;


			case "AV":

				zeilen = fr.readdata("D:/DATEN/Daten Max/HTL/INFI-DBP/versicherung.txt");

				for(int i = 0; i<zeilen.size(); i++){

					substring = zeilen.get(i).split(",");

					dbmanager.insertVersicherung(substring[0], substring[1]);

				}


				break;
				
				
			case "AKO":

				zeilen = fr.readdata("D:/DATEN/Daten Max/HTL/INFI-DBP/kondition.txt");

				for(int i = 0; i<zeilen.size(); i++){

					substring = zeilen.get(i).split(",");

					dbmanager.insertKondition(Integer.parseInt(substring[0]), substring[1]);

				}


				break;
				
				
			case "AKUO":

				zeilen = fr.readdata("D:/DATEN/Daten Max/HTL/INFI-DBP/kunde_hat_Versicherung.txt");

				for(int i = 0; i<zeilen.size(); i++){

					substring = zeilen.get(i).split(",");

					dbmanager.insertVers_hat_Kunde(Integer.parseInt(substring[0]), Integer.parseInt(substring[1]), substring[2], substring[3]);

				}


				break;

			case "DKU":

				System.out.println("Bitte geben Sie die SvNr des zu löschenden Kunden an");
				svnr = Integer.parseInt(s.nextLine());

				dbmanager.deleteKunde(svnr);

				break;


			case "DKO":

				System.out.println("Bitte geben Sie die KID der zu löschenden Kondition an");
				kid = Integer.parseInt(s.nextLine());

				dbmanager.deleteKondition(kid);

				break;


			case "DV":

				System.out.println("Bitte geben Sie die VID der zu löschenden Versicherung an");
				vid = Integer.parseInt(s.nextLine());

				dbmanager.deleteVersicherung(vid);

				break;


			case "DKV":

				System.out.println("Bitte geben Sie die VID an");
				vid = Integer.parseInt(s.nextLine());

				System.out.println("Bitte geben Sie die SvNr an");
				svnr = Integer.parseInt(s.nextLine());

				dbmanager.deleteVers_hat_Kunde(vid, svnr);

				break;

				
			case "PKO":

				konditionen = dbmanager.selectAllKonditionen();

				System.out.println("");

				for(Kondition k : konditionen){

					System.out.println(k.toString());

				}

				System.out.println("");

				break;

				
			case "PKU":

				kunden = dbmanager.selectAllKunden();

				System.out.println("");

				for(Kunde k : kunden){

					System.out.println(k.toString());

				}

				System.out.println("");

				break;

				
			case "PV":

				versicherungen  = dbmanager.selectAllVersicherungen();

				System.out.println("");

				for(Versicherung v : versicherungen){

					System.out.println(v.toString());

				}

				System.out.println("");

				break;


			case "PKV":

				kvs = dbmanager.selectAllKunde_hat_Versicherung();

				System.out.println("");

				for(Kunde_hat_Versicherung kv : kvs){

					System.out.println(kv.toString());

				}

				System.out.println("");

				break;


			case "PKVJ":
				
				dbmanager.selectAllKunde_hat_Versicherung_Join();
				
				break;

			default:

				System.out.println("-----------Falsche Eingabe------------");

				break;

			}

			System.out.println("Weitere Operation durchführen? [J,N]");
			wh = s.nextLine();
			wh = wh.toUpperCase();


		}while(wh.equals("J"));



		//		konditionen = dbmanager.selectAllKonditionen();
		//		kunden = dbmanager.selectAllKunden();
		//		versicherungen = dbmanager.selectAllVersicherungen();
		//

		//
		//		for(Versicherung v : versicherungen){
		//
		//			System.out.println(v.toString());
		//
		//		}
		//
		//		
		//
	}

}
