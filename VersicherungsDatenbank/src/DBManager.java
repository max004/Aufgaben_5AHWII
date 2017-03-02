import java.sql.*;
import java.util.ArrayList;

public class DBManager {

	static Connection con;
	static Connection c;


	public DBManager() throws ClassNotFoundException, SQLException  {

		try{
			//  mySql
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");

			//sqlite
			//Class.forName("org.sqlite.JDBC");
			//c = DriverManager.getConnection("jdbc:sqlite:VersicherungsDB.db");

			createDB();

			c = DriverManager.getConnection("jdbc:mysql://localhost/VersicherungsDB", "root", "");

			System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");

		}catch(Exception e){

			e.printStackTrace();
			System.out.println("!!Es kann keine Verbindung zur Datenbank hergestellt werden!!");

		}


		createTables();

	}

	public void createDB() throws SQLException{

		String sql = "CREATE DATABASE IF NOT EXISTS VersicherungsDB";

		Statement stmt = null;

		try {

			stmt = con.createStatement();
			stmt.execute(sql);

			System.out.println("Datenbank erfolgreich erstellt");

		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Erstellen der Datenbank fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}


	public void createTables() throws SQLException{

		String sql_table_vers = "";
		String sql_table_kondition = "";
		String sql_table_kunde = "";
		String sql_table_kunde_hat_vers = "";

		sql_table_vers = "CREATE TABLE IF NOT EXISTS Versicherung"
				+ "( VID INT		NOT NULL,"
				+ " Gruendungsdatum DATE NOT NULL, "
				+ " Name VARCHAR(50) NOT NULL, "
				+ " PRIMARY KEY(VID)"
				+ ") "; 

		sql_table_kondition = "CREATE TABLE IF NOT EXISTS Kondition"
				+ "( KID INT		NOT NULL,"
				+ " Gegenstand VARCHAR(150) NOT NULL, "
				+ " VID INT NOT NULL, "
				+ " FOREIGN KEY(VID) REFERENCES Versicherung(VID), "
				+ " PRIMARY KEY(KID)"
				+ ") ";

		sql_table_kunde = "CREATE TABLE IF NOT EXISTS Kunde"
				+ "( SvNr INT		NOT NULL,"
				+ " Geburtsdatum DATE NOT NULL, "
				+ " Vorname VARCHAR(50) NOT NULL, "
				+ " Nachname VARCHAR(50) NOT NULL, "
				+ " PRIMARY KEY(SvNr)"
				+ ") ";

		sql_table_kunde_hat_vers = "CREATE TABLE IF NOT EXISTS Kunde_hat_Versicherung"
				+ "( VID INT		NOT NULL, "
				+ " SvNr INT		NOT NULL, "
				+ " Von DATETIME NOT NULL, "
				+ " BIS DATETIME NOT NULL, "
				+ " PRIMARY KEY(VID, SvNr)"
				+ ") ";

		Statement stmt = null;

		try {

			stmt = c.createStatement();
			stmt.execute(sql_table_vers);
			stmt.execute(sql_table_kondition);
			stmt.execute(sql_table_kunde);
			stmt.execute(sql_table_kunde_hat_vers);

			System.out.println("Tabellen erfolgreich erstellt");

		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Erstellen der Tabelle fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}



	public void insertVersicherung(String gruendungsdatum, String name) throws SQLException{

		String sql = "INSERT INTO Versicherung VALUES(?,?,?)";
		java.sql.PreparedStatement stmt = null;
		int id = maxIDVers();

		try {

			stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, gruendungsdatum);
			stmt.setString(3, name);
			stmt.executeUpdate();
			stmt.close();

			System.out.println("Einfügen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Einfügen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}

	public void insertKunde(String geburtsdatum, String vorname, String nachname) throws SQLException{

		String sql = "INSERT INTO Kunde VALUES(?,?,?,?)";
		java.sql.PreparedStatement stmt = null;
		int id = maxIDKunde();

		try {

			stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, geburtsdatum);
			stmt.setString(3, vorname);
			stmt.setString(4, nachname);
			stmt.executeUpdate();
			stmt.close();

			System.out.println("Einfügen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Einfügen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}

	public void insertKondition(int vid, String gegenstand) throws SQLException{

		String sql = "INSERT INTO Kondition VALUES(?,?,?)";
		java.sql.PreparedStatement stmt = null;
		int id = maxIDKondi();

		try {

			stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, gegenstand);
			stmt.setInt(3, vid);
			stmt.executeUpdate();
			stmt.close();

			System.out.println("Einfügen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Einfügen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}

	public void insertVers_hat_Kunde(int vid, int svnr, String von, String bis) throws SQLException{

		String sql = "INSERT INTO Kunde_hat_Versicherung VALUES(?,?,?,?)";
		java.sql.PreparedStatement stmt = null;

		try {

			stmt = c.prepareStatement(sql);
			stmt.setInt(1, vid);
			stmt.setInt(2, svnr);
			stmt.setString(3, von);
			stmt.setString(4, bis);
			stmt.executeUpdate();
			stmt.close();

			System.out.println("Einfügen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Einfügen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}

	public int maxIDKunde() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id = 00;

		try {

			sql = "Select max(SvNr) from Kunde";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				id = rs.getInt("max(SvNr)");
			}
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der nächsten ID fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}

		id ++;

		return id;

	}

	public int maxIDVers() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id = 00;

		try {

			sql = "Select max(VID) from Versicherung";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				id = rs.getInt("max(VID)");
			}
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der nächsten ID fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}

		id ++;

		return id;

	}

	public int maxIDKondi() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id = 00;

		try {

			sql = "Select max(KID) from Kondition";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				id = rs.getInt("max(KID)");
			}
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der nächsten ID fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}

		id ++;

		return id;

	}


	public void deleteKunde(int id) throws SQLException{

		String sql = "DELETE FROM Kunde WHERE SvNr == " + id + ";";
		Statement stmt = null;

		try {

			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();

			System.out.println("Löschen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Löschen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}

	public void deleteVersicherung(int id) throws SQLException{

		String sql = "DELETE FROM Versicherung WHERE VID == " + id + ";";
		Statement stmt = null;

		try {

			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();

			System.out.println("Löschen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Löschen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}

	public void deleteKondition(int id) throws SQLException{

		String sql = "DELETE FROM Kondition WHERE KID == " + id + ";";
		Statement stmt = null;

		try {

			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();

			System.out.println("Löschen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Löschen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}
	
	public void deleteAll() throws SQLException{

		String sql = "DELETE FROM Kondition;";
		String sql2 = "DELETE FROM Kunde;";
		String sql3 = "DELETE FROM Versicherung;";
		String sql4 = "DELETE FROM Kunde_hat_Versicherung;";
		Statement stmt = null;

		try {

			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql4);
			stmt.close();

			System.out.println("Löschen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Löschen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}
	

	
	public void deleteVers_hat_Kunde(int vid, int svnr) throws SQLException{

		String sql = "DELETE FROM Kunde_hat_Versicherung WHERE VID == " + vid + " AND SvNr == " + svnr + ";";
		Statement stmt = null;

		try {

			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();

			System.out.println("Löschen erfolgreich");

		}catch(Exception e){

			e.printStackTrace();

			System.out.println("Löschen fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}	

	}



	public ArrayList<Kondition> selectAllKonditionen() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Kondition> konditionen = new ArrayList<Kondition>();
		Kondition kondition;

		int kid = 000;
		int vid = 000;
		String gegenstand = "";


		try {

			sql = "SELECT * FROM Kondition";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				kid = rs.getInt("KID");
				vid = rs.getInt("VID");
				gegenstand = rs.getString("gegenstand");

				kondition = new Kondition(kid, vid, gegenstand);
				konditionen.add(kondition);

			}
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der Einträge fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}

		return konditionen;

	}

	public ArrayList<Kunde> selectAllKunden() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Kunde> kunden = new ArrayList<Kunde>();
		Kunde kunde;;

		int svnr = 000;
		String geburtsdatum = "";
		String vorname = "";
		String nachname = "";


		try {

			sql = "SELECT * FROM Kunde";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				svnr = rs.getInt("SvNr");
				geburtsdatum = rs.getString("Geburtsdatum");
				vorname = rs.getString("Vorname");
				nachname = rs.getString("Nachname");

				kunde = new Kunde(svnr, geburtsdatum, vorname, nachname);
				kunden.add(kunde);

			}
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der Einträge fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}

		return kunden;

	}

	public ArrayList<Versicherung> selectAllVersicherungen() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Versicherung> versicherungen = new ArrayList<Versicherung>();
		Versicherung versicherung;

		String gruendungsdatum;
		String name;
		int vid;


		try {

			sql = "SELECT * FROM Versicherung";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				vid = rs.getInt("VID");
				gruendungsdatum = rs.getString("Gruendungsdatum");
				name = rs.getString("Name");

				versicherung = new Versicherung(gruendungsdatum, name, vid);
				versicherungen.add(versicherung);

			}
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der Einträge fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}

		return versicherungen;

	}
	
	public ArrayList<Kunde_hat_Versicherung> selectAllKunde_hat_Versicherung() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Kunde_hat_Versicherung> kvs = new ArrayList<Kunde_hat_Versicherung>();
		Kunde_hat_Versicherung kv;

		String von;
		String bis;
		int vid;
		int svnr;

		try {

			sql = "SELECT * FROM Kunde_hat_Versicherung";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				vid = rs.getInt("VID");
				von = rs.getString("Von");
				bis = rs.getString("Bis");
				svnr = rs.getInt("SvNr");

				kv = new Kunde_hat_Versicherung(vid, svnr, von, bis);
				kvs.add(kv);

			}
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der Einträge fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}

		return kvs;

	}
	
	public void selectAllKunde_hat_Versicherung_Join() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Kunde_hat_Versicherung> kvs = new ArrayList<Kunde_hat_Versicherung>();
		Kunde_hat_Versicherung kv;

		String von;
		String bis;
		int vid;
		int svnr;
	    String geburtsdatum;
		String vorname;
		String nachname;
		String gruendungsdatum;
		String name;
		
		try {

			sql = "SELECT * FROM Kunde_hat_Versicherung JOIN Kunde USING(SvNr) JOIN Versicherung USING(VID)";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println();
			
			while(rs.next()){

				vid = rs.getInt("VID");
				von = rs.getString("Von");
				bis = rs.getString("Bis");
				svnr = rs.getInt("SvNr");
				geburtsdatum = rs.getString("Geburtsdatum");
				vorname = rs.getString("Vorname");
				nachname = rs.getString("Nachname");
				gruendungsdatum = rs.getString("Gruendungsdatum");
				name  = rs.getString("Name");

				System.out.println("Versicherung: id[" + vid + "] - " + name + " | Kunde: SvNr[" + svnr + "] - " + vorname + " " + nachname);
		
				
			}
			
			System.out.println();
			
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der Einträge fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}


	}

	/*

	public ArrayList<Eintrag> selectEintrag(String suchtext) throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Eintrag> einträge = new ArrayList<Eintrag>();
		Eintrag eintrag;

		int id = 000;
		String komponist = "";
		String stueck_name = "";
		String holz = "";
		String blech = "";
		String schlagzeug = "";
		String streicher = "";
		String duration = "";

		try {

			sql = "Select * from besetzungen where stueck_name like '%" + suchtext + "%' OR komponist like '%" + suchtext + "%'";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				id = rs.getInt("ID");
				komponist = rs.getString("komponist");
				stueck_name = rs.getString("stueck_name");
				holz = rs.getString("holz");
				blech = rs.getString("blech");
				schlagzeug = rs.getString("schlagzeug");
				streicher = rs.getString("streicher");
				duration = rs.getString("duration");

				eintrag = new Eintrag(id, komponist, stueck_name, holz, blech, schlagzeug, streicher, duration);
				einträge.add(eintrag);

			}
		}catch(Exception e){

			e.printStackTrace();
			System.out.println("Ermitteln der Einträge fehlgeschlagen");

		}

		finally {
			if (stmt != null) stmt.close();
		}

		return einträge;

	}


	 */
}
