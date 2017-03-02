import java.sql.*;
import java.util.ArrayList;

public class DBManager {

	static Connection c;
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		
//		DBManager dbManager = new DBManager();
//		
//		dbManager.readData("strom_verbrauch");
//		
//	}


	public DBManager() throws ClassNotFoundException, SQLException  {

		try{
			//  mySql
			//			Class.forName("com.mysql.jdbc.Driver");
			//			c = DriverManager.getConnection("jdbc:mysql://localhost/VersicherungsDB", "root", "");

			//sqlite
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:verbrauch.db");

			System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");

		}catch(Exception e){

			e.printStackTrace();
			System.out.println("!!Es kann keine Verbindung zur Datenbank hergestellt werden!!");

		}

	}

	public ArrayList<Verbrauch> readData(String table, long yearUTST) throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Verbrauch> verbraeuche = new ArrayList<Verbrauch>();
		double verbrauch;
		long utst;

		sql = "SELECT * FROM " + table + " WHERE datum >= " + yearUTST + " ORDER BY datum";
		stmt = c.createStatement();
		rs = stmt.executeQuery(sql);

		while(rs.next()){

			utst = rs.getLong("datum");
			verbrauch = rs.getDouble("value");

			verbraeuche.add(new Verbrauch(utst, verbrauch));

		}
		
		System.out.println("-- Auslesen erfolgreich --");

		return verbraeuche;

	}

}
